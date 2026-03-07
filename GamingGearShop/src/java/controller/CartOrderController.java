package controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.OrderDTO;
import Model.ProductDTO;
import Model.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.URL;

public class CartOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = URL.PAGE_HOME;

        try {
            if (action == null) {
                action = "viewCart";
            }
            switch (action) {
                case "addToCart":
                    url = addToCart(request);
                    break;
                case "viewCart":
                    url = viewCart(request);
                    break;
                case "Remove":
                    url = removeFromCart(request);
                    break;
                case "CheckOut":
                    url = checkout(request);
                    break;
                default:
                    url = URL.PROCESS_HOME;
                    break;
            }
        } catch (IllegalArgumentException e) {
            log("Dữ liệu đầu vào không hợp lệ: " + e.getMessage());
            url = URL.PAGE_ERROR;
        } finally {
            if (url.endsWith(".jsp")) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
        }
    }

    // 1. Thêm vào giỏ hàng
    private String addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String productID = request.getParameter("productID");
        String quantityRaw = request.getParameter("quantity");
        int quantity = 1;

        if (quantityRaw != null) {
            try {
                quantity = Integer.parseInt(quantityRaw);
                if (quantity <= 0) quantity = 1;
            } catch (NumberFormatException e) {
                quantity = 1; // Bắt lỗi parse số rõ ràng
            }
        }

        ProductDAO dao = new ProductDAO();
        ProductDTO product = dao.getProductByID(productID);

        if (product != null) {
            product.setQuantity(quantity);
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            cart.add(product);
            session.setAttribute("CART", cart);
            session.setAttribute("message", "Đã thêm thành công: " + product.getProductName());
        }
        return URL.PROCESS_HOME;
    }

    // 2. Xem giỏ hàng
    private String viewCart(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                request.setAttribute("CART_ITEMS", cart.getCart().values());
            }
        }
        String msg = request.getParameter("msg");
        if ("Success".equals(msg)) {
            request.setAttribute("PAYMENT_STATUS", "SUCCESS");
        } else if ("Error".equals(msg)) {
            request.setAttribute("PAYMENT_STATUS", "ERROR");
        }
        return URL.PAGE_CART;
    }

    // 3. Xóa khỏi giỏ hàng
    private String removeFromCart(HttpServletRequest request) {
        String productID = request.getParameter("productID");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart != null && productID != null) {
            cart.delete(productID);
            session.setAttribute("CART", cart);
        }
        return "CartOrderController?action=viewCart";
    }

    // 4. Xử lý thanh toán
    private String checkout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return URL.PAGE_LOGIN;

        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        Cart cart = (Cart) session.getAttribute("CART");

        if (user == null) return URL.PAGE_LOGIN;
        
        if (cart != null && !cart.getCart().isEmpty()) {
            double total = 0;
            for (ProductDTO item : cart.getCart().values()) {
                total += item.getPrice() * item.getQuantity();
            }

            OrderDAO dao = new OrderDAO();
            // Lệnh tạo Order mới với status = 1
            OrderDTO order = new OrderDTO(0, new java.sql.Date(System.currentTimeMillis()), total, user.getUserID(), 1);

            if (dao.checkOut(order, cart)) {
                session.removeAttribute("CART"); // Xóa giỏ hàng khi thành công
                return "CartOrderController?action=viewCart&msg=Success";
            } else {
                return "CartOrderController?action=viewCart&msg=Error";
            }
        }
        return "CartOrderController?action=viewCart&msg=Empty";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}