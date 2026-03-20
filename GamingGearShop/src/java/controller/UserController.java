package controller;

import DAO.UserDAO;
import Model.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.URL;

public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = URL.PAGE_HOME;

        try {
            if (action == null) {
                action = "loginPage";
            }

            switch (action.toLowerCase()) {
                case "login":
                    url = loginUser(request, response);
                    break;
                case "logout":
                    url = logoutUser(request);
                    break;
                case "register":
                    if (request.getMethod().equalsIgnoreCase("GET")) {
                        url = URL.PAGE_REGISTER;
                    } else {
                        url = registerUser(request);
                    }
                    break;
                default:
                    url = URL.PAGE_LOGIN;
                    break;
            }
        } catch (Exception e) {
            log("Lỗi tại UserController: " + e.getMessage());
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

    private String loginUser(HttpServletRequest request, HttpServletResponse response) {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        if (userID == null || userID.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("ERROR", "Vui lòng nhập đầy đủ thông tin!");
            return URL.PAGE_LOGIN;
        }

        UserDAO dao = new UserDAO();
        UserDTO user = dao.checkLogin(userID, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_USER", user);
            
            int cartCount = new DAO.CartDAO().countCartItems(userID);
            session.setAttribute("CART_COUNT", cartCount);
            
            // Sửa thành PROCESS_HOME để về thẳng trang chủ hiện sản phẩm
            return URL.PROCESS_HOME;
        } else {
            request.setAttribute("ERROR", "Sai tên đăng nhập hoặc mật khẩu!");
            return URL.PAGE_LOGIN;
        }
    }

    private String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return URL.PROCESS_HOME;
    }

    private String registerUser(HttpServletRequest request) {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        UserDAO dao = new UserDAO();

        if (!password.equals(confirm)) {
            request.setAttribute("ERROR", "Xác nhận mật khẩu không khớp!");
            return URL.PAGE_REGISTER;
        }
        if (dao.checkDuplicate(userID)) {
            request.setAttribute("ERROR", "ID '" + userID + "' đã tồn tại!");
            return URL.PAGE_REGISTER;
        }

        UserDTO newUser = new UserDTO(userID, fullName, password, 2, address, phone, true);
        boolean checkInsert = dao.insert(newUser);

        if (checkInsert) {
            request.setAttribute("SUCCESS", "Đăng ký thành công! Vui lòng đăng nhập.");
            return URL.PAGE_LOGIN;
        } else {
            request.setAttribute("ERROR", "Lỗi hệ thống! Không thể tạo tài khoản.");
            return URL.PAGE_REGISTER;
        }
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
