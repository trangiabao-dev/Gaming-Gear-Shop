/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import Model.UserDTO;
import java.io.IOException;
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
       String url = URL.PAGE_REGISTER; // Mặc định là quay lại trang đăng ký nếu lỗi
        
        try {
            // 1. Lấy dữ liệu từ form register.jsp
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm"); // Ô nhập lại mật khẩu
            
            // Biến kiểm tra lỗi
            String errorMsg = "";
            boolean foundError = false;
            
            UserDAO dao = new UserDAO();

            // 2. Validation (Kiểm tra dữ liệu)
            // Check 1: Mật khẩu xác nhận có khớp không?
            if (!password.equals(confirm)) {
                foundError = true;
                errorMsg = "Xác nhận mật khẩu không khớp!";
            } 
            // Check 2: UserID đã tồn tại chưa? (Chỉ check nếu mật khẩu đã OK)
            else if (dao.checkDuplicate(userID)) {
                foundError = true;
                errorMsg = "Tên đăng nhập (ID) '" + userID + "' đã tồn tại!";
            }

            // 3. Xử lý logic
            if (foundError) {
                // === CÓ LỖI ===
                request.setAttribute(ERROR, errorMsg);
                // Giữ lại thông tin user đã nhập để họ đỡ phải gõ lại (trừ password)
                // (Bạn nhớ thêm value="${param.userID}" vào input bên jsp nhé)
                
            } else {
                // === KHÔNG CÓ LỖI (HỢP LỆ) ===
                
                // Tạo UserDTO mới với RoleID là số 2 (User thường)
                // Constructor này phải khớp với bên UserDTO.java mà bạn đã sửa
                UserDTO newUser = new UserDTO(userID, fullName, DEFAULT_ROLE, password);
                
                // Gọi hàm insert bên DAO
                boolean checkInsert = dao.insert(newUser); 

                if (checkInsert) {
                    // Thành công -> Chuyển sang trang Login
                    url = URL.PAGE_LOGIN;
                    request.setAttribute(SUCCESS, "Đăng ký thành công! Vui lòng đăng nhập.");
                } else {
                    // Thất bại (Lỗi SQL hoặc mạng)
                    url = URL.PAGE_ERROR;
                    request.setAttribute(ERROR, "Lỗi hệ thống! Không thể tạo tài khoản.");
                }
            }

        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
            e.printStackTrace();
        } finally {
            // Chuyển hướng trang web
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
