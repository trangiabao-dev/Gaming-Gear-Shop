package controller;

import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.CartDTO;
import Model.OrderDTO;
import Model.OrderDetailDTO;
import Model.ProductDTO;
import Model.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
                case "confirmpayment":
                    url = confirmPayment(request);
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
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

        // Chưa đăng nhập thì về trang login
        if (user == null) {
            return URL.PAGE_LOGIN;
        }

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
        // Lưu xuống DB
        new CartDAO().addToCart(user.getUserID(), productID, quantity);

        // Cập nhật lại số lượng trong session để navbar hiển thị đúng
        updateCartCountSession(session, user.getUserID());

        return URL.PROCESS_HOME;
    }

    private String viewCart(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserDTO user = (session != null) ? (UserDTO) session.getAttribute("LOGIN_USER") : null;

        if (user != null) {
            // Lấy giỏ hàng từ DB
            List<CartDTO> cartItems = new CartDAO().getCartByUser(user.getUserID());
            request.setAttribute("CART_ITEMS", cartItems);
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
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

        if (user != null && productID != null) {
            new CartDAO().removeFromCart(user.getUserID(), productID);
            updateCartCountSession(session, user.getUserID());
        }
        return "MainController?action=viewCart";
    }

    private String checkout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return URL.PAGE_LOGIN;
        }

        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if (user == null) {
            return URL.PAGE_LOGIN;
        }

        CartDAO cartDAO = new CartDAO();
        List<CartDTO> cartItems = cartDAO.getCartByUser(user.getUserID());

        if (cartItems == null || cartItems.isEmpty()) {
            return "MainController?action=viewCart&msg=Empty";
        }

        // Tính tổng tiền
        double total = 0;
        for (CartDTO item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        // Lưu tổng tiền vào session để hiện trên trang payment
        // CHƯA lưu DB — chờ user bấm xác nhận
        session.setAttribute("PENDING_TOTAL", total);

        // Set attribute để hiển thị trên payment.jsp
        request.setAttribute("PAYMENT_TOTAL", (long) total);
        request.setAttribute("PAYMENT_ORDER_ID", System.currentTimeMillis());

        return URL.PAGE_PAYMENT;
    }

   private String confirmPayment(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) return URL.PAGE_LOGIN;

    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    if (user == null) return URL.PAGE_LOGIN;

    CartDAO cartDAO = new CartDAO();
    List<CartDTO> cartItems = cartDAO.getCartByUser(user.getUserID());

    if (cartItems == null || cartItems.isEmpty()) {
        return "MainController?action=viewCart&msg=Empty";
    }

    Double total = (Double) session.getAttribute("PENDING_TOTAL");
    if (total == null) return "MainController?action=viewCart";

    // Lưu xuống DB
    OrderDAO orderDAO = new OrderDAO();
    OrderDTO order = new OrderDTO(0,
            new java.sql.Date(System.currentTimeMillis()),
            total, user.getUserID(), 1);

    if (orderDAO.checkOutFromDB(order, cartItems)) {
        // Xóa giỏ hàng
        cartDAO.clearCart(user.getUserID());
        session.removeAttribute("CART_COUNT");
        session.removeAttribute("PENDING_TOTAL");

        // Gửi email xác nhận nếu user có email
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            utils.OTPUtil.sendOrderConfirmation(
                user.getEmail(),
                user.getFullName(),
                order.getOrderID(),
                total
            );
        }

        return "MainController?action=viewCart&msg=Success";
    } else {
        return "MainController?action=viewCart&msg=Error";
    }
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

        List<OrderDTO> orders = new OrderDAO().getOrdersByUser(user.getUserID());
        request.setAttribute("ORDER_LIST", orders);
        return URL.PAGE_ORDER_HISTORY;
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
        if (orderIDStr != null) {
            try {
                int orderID = Integer.parseInt(orderIDStr);
                new OrderDAO().cancelOrder(orderID, user.getUserID());
            } catch (NumberFormatException e) {
            }
        }
        return "MainController?action=orderHistory";
    }

    // Cập nhật số lượng sản phẩm trong giỏ vào session để navbar hiển thị
    private void updateCartCountSession(HttpSession session, String userID) {
        int count = new CartDAO().countCartItems(userID);
        session.setAttribute("CART_COUNT", count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
