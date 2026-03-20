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
import utils.PasswordUtil;
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
                case "forgotpassword":        // THÊM VÀO
                    url = showForgotPassword(request);
                    break;
                case "sendotp":               // THÊM VÀO
                    url = sendOTP(request);
                    break;
                case "resetpassword":         // THÊM VÀO
                    url = resetPassword(request);
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
        String email = request.getParameter("email");
        UserDAO dao = new UserDAO();

        if (!password.equals(confirm)) {
            request.setAttribute("ERROR", "Xác nhận mật khẩu không khớp!");
            return URL.PAGE_REGISTER;
        }
        if (dao.checkDuplicate(userID)) {
            request.setAttribute("ERROR", "ID '" + userID + "' đã tồn tại!");
            return URL.PAGE_REGISTER;
        }

        // Mới — mã hóa password trước khi lưu
        String hashedPassword = PasswordUtil.hashPassword(password);
       UserDTO newUser = new UserDTO(userID, fullName, hashedPassword, 2, address, phone, true, email, null);                                                                                   
        boolean checkInsert = dao.insert(newUser);

        if (checkInsert) {
            request.setAttribute("SUCCESS", "Đăng ký thành công! Vui lòng đăng nhập.");
            return URL.PAGE_LOGIN;
        } else {
            request.setAttribute("ERROR", "Lỗi hệ thống! Không thể tạo tài khoản.");
            return URL.PAGE_REGISTER;
        }

    }

    private String showForgotPassword(HttpServletRequest request) {
        return URL.PAGE_FORGOT_PASSWORD;
    }

    private String sendOTP(HttpServletRequest request) {
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("ERROR", "Vui lòng nhập email!");
            return URL.PAGE_FORGOT_PASSWORD;
        }

        UserDAO dao = new UserDAO();
        UserDTO user = dao.findByEmail(email.trim());

        if (user == null) {
            request.setAttribute("ERROR", "Email không tồn tại trong hệ thống!");
            return URL.PAGE_FORGOT_PASSWORD;
        }

        String otp = utils.OTPUtil.generateOTP();
        boolean sent = utils.OTPUtil.sendOTP(email, otp);

        if (sent) {
            dao.saveOTP(email, otp);
            request.getSession().setAttribute("RESET_EMAIL", email);
            request.setAttribute("SUCCESS", "Mã OTP đã được gửi về email của bạn!");
            return URL.PAGE_VERIFY_OTP;
        } else {
            request.setAttribute("ERROR", "Gửi email thất bại! Vui lòng thử lại.");
            return URL.PAGE_FORGOT_PASSWORD;
        }
    }

    private String resetPassword(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("RESET_EMAIL");
        String inputOTP = request.getParameter("otp");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (!newPass.equals(confirmPass)) {
            request.setAttribute("ERROR", "Mật khẩu xác nhận không khớp!");
            return URL.PAGE_VERIFY_OTP;
        }

        UserDAO dao = new UserDAO();
        UserDTO user = dao.findByEmail(email);

        if (user == null || !inputOTP.equals(user.getOtp())) {
            request.setAttribute("ERROR", "Mã OTP không đúng!");
            return URL.PAGE_VERIFY_OTP;
        }

        // Mã hóa password mới trước khi lưu
        String hashedPassword = PasswordUtil.hashPassword(newPass);
        boolean updated = dao.resetPassword(email, hashedPassword);

        if (updated) {
            session.removeAttribute("RESET_EMAIL");
            request.setAttribute("SUCCESS", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");
            return URL.PAGE_LOGIN;
        } else {
            request.setAttribute("ERROR", "Lỗi hệ thống! Vui lòng thử lại.");
            return URL.PAGE_VERIFY_OTP;
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
