/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.UserDAO;
import Model.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static utils.URL.PAGE_LOGIN;

/**
 *
 * @author thinh
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    // Mặc định url là trang login để hứng lỗi
    String url = PAGE_LOGIN;

    try {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        // 1. Kiểm tra validation
        if (userID == null || userID.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("ERROR", "Vui lòng nhập đầy đủ thông tin!");
        } else {
            // 2. Gọi DAO kiểm tra
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLogin(userID, password);

            if (user != null) {
                // === TRƯỜNG HỢP ĐĂNG NHẬP THÀNH CÔNG ===
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", user);
                
                // QUAN TRỌNG: Dùng sendRedirect để gọi MainController
                // MainController sẽ lo việc lấy sản phẩm và hiện trang home
                response.sendRedirect("MainController"); 
                return; // Dừng code ở đây ngay lập tức, không chạy xuống finally nữa
                
            } else {
                request.setAttribute("ERROR", "Sai tên đăng nhập hoặc mật khẩu!");
            }
        }
    } catch (Exception e) {
        log("Error at LoginController: " + e.toString());
    } 
    
    // === TRƯỜNG HỢP THẤT BẠI ===
    // Chỉ chạy xuống đây nếu đăng nhập SAI
    request.getRequestDispatcher(url).forward(request, response);
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
