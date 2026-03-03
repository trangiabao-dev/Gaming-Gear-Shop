/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import Model.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.URL;

/**
 *
 * @author thinh
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // QUAN TRỌNG: quy định khách hàng roleID là 2
    private static final int DEFAULT_ROLE = 2;

    private static final String ERROR = "ERROR";
    private static final String SUCCESS = "SUCCESS";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = URL.PAGE_REGISTER;

        try {
            // 1. Lấy dữ liệu từ form
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            String errorMsg = "";
            boolean foundError = false;
            UserDAO dao = new UserDAO();

            // 2. TẬP TRUNG TẤT CẢ VALIDATION TẠI ĐÂY (Check theo DB NOT NULL)
            if (userID == null || userID.trim().isEmpty() || userID.length() > 50) {
                foundError = true;
                errorMsg = "ID không được để trống và tối đa 50 ký tự!";

            } else if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 50) {
                foundError = true;
                errorMsg = "Họ tên không được để trống và tối đa 50 ký tự!";

            } else if (password == null || password.trim().isEmpty() || password.length() > 50) {
                foundError = true;
                errorMsg = "Mật khẩu không được để trống và tối đa 50 ký tự!";

            } else if (!password.equals(confirm)) {    // Kiểm tra khớp mật khẩu
                foundError = true;
                errorMsg = "Xác nhận mật khẩu không khớp!";

            } else if (phone == null || phone.trim().isEmpty()) { // Kiểm tra SĐT
                foundError = true;
                errorMsg = "Số điện thoại không được để trống!";
            } else if (address == null || address.trim().isEmpty()) { // Kiểm tra Địa chỉ
                foundError = true;
                errorMsg = "Địa chỉ không được để trống!";
            } else if (dao.checkDuplicate(userID)) {   // Kiểm tra trùng ID
                foundError = true;
                errorMsg = "ID '" + userID + "' đã tồn tại!";
            }

            // 3. XỬ LÝ LOGIC DỰA TRÊN KẾT QUẢ KIỂM TRA
            if (foundError) {
                request.setAttribute(ERROR, errorMsg);
            } else {
                // Dữ liệu hợp lệ -> Tiến hành lưu vào Database
               UserDTO newUser = new UserDTO(userID, fullName, password, 2, address, phone, true);
                boolean checkInsert = dao.insert(newUser);

                if (checkInsert) {
                    url = URL.PAGE_LOGIN;
                    request.setAttribute(SUCCESS, "Đăng ký thành công!");
                } else {
                    url = URL.PAGE_ERROR;
                    request.setAttribute(ERROR, "Lỗi hệ thống! Không thể tạo tài khoản.");
                }
            }
        } catch (SQLException e) {
            log("Error at RegisterController: " + e.toString());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
