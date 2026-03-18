package controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.OrderDTO;
import Model.ProductDTO;
import Model.UserDTO;
import java.io.IOException;
import java.util.List;
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

        String action = request.getParameter("action");
        String url = URL.PAGE_HOME;

        try {
            if (action == null) {
                action = "viewCart";
            }
            // Cũng ép chữ thường để đồng bộ với MainController
            switch (action.toLowerCase()) {
                case "addtocart":
                    url = addToCart(request);
                    break;
                case "viewcart":
                    url = viewCart(request);
                    break;
                case "remove":
                    url = removeFromCart(request);
                    break;
                case "checkout":
                    url = checkout(request);
                    break;
                case "orderhistory":          
                    url = viewOrderHistory(request);
                    break;
                case "cancelorder":          
                    url = cancelOrder(request);
                    break;
                default:
                    url = URL.PROCESS_HOME;
                    break;
            }
        } catch (Exception e) {
            log("Lỗi tại Giỏ hàng: " + e.getMessage());
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

    private String addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String productID = request.getParameter("productID");
        String quantityRaw = request.getParameter("quantity");
        int quantity = 1;

        if (quantityRaw != null) {
            try {
                quantity = Integer.parseInt(quantityRaw);
                if (quantity <= 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException e) {
                quantity = 1;
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

    private String removeFromCart(HttpServletRequest request) {
        String productID = request.getParameter("productID");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart != null && productID != null) {
            cart.delete(productID);
            session.setAttribute("CART", cart);
        }
        // Gọi thẳng qua MainController để giao diện không bị lệch
        return "MainController?action=viewCart";
    }

    private String checkout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return URL.PAGE_LOGIN;
        }

        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        Cart cart = (Cart) session.getAttribute("CART");

        if (user == null) {
            return URL.PAGE_LOGIN;
        }

        if (cart != null && !cart.getCart().isEmpty()) {
            double total = 0;
            for (ProductDTO item : cart.getCart().values()) {
                total += item.getPrice() * item.getQuantity();
            }

            OrderDAO dao = new OrderDAO();
            OrderDTO order = new OrderDTO(0, new java.sql.Date(System.currentTimeMillis()), total, user.getUserID(), 1);

            if (dao.checkOut(order, cart)) {
                session.removeAttribute("CART");
                return "MainController?action=viewCart&msg=Success";
            } else {
                return "MainController?action=viewCart&msg=Error";
            }
        }
        return "MainController?action=viewCart&msg=Empty";
    }

    private String viewOrderHistory(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return URL.PAGE_LOGIN;
        }

        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if (user == null) {
            return URL.PAGE_LOGIN;
        }

        OrderDAO dao = new OrderDAO();
        List<OrderDTO> orders = dao.getOrdersByUser(user.getUserID());
        request.setAttribute("ORDER_LIST", orders);
        return URL.PAGE_ORDER_HISTORY; // Tạo constant này ở bước sau
    }

    private String cancelOrder(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return URL.PAGE_LOGIN;
        }

        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if (user == null) {
            return URL.PAGE_LOGIN;
        }

        String orderIDStr = request.getParameter("orderID");
        if (orderIDStr == null) {
            return "MainController?action=orderHistory";
        }

        try {
            int orderID = Integer.parseInt(orderIDStr);
            OrderDAO dao = new OrderDAO();
            dao.cancelOrder(orderID, user.getUserID());
        } catch (NumberFormatException e) {
            /* bỏ qua */ }

        return "MainController?action=orderHistory";
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
