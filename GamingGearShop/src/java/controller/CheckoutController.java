/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import Model.Cart;
import Model.OrderDTO;
import Model.ProductDTO;
import Model.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class CheckoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "error.jsp";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                Cart cart = (Cart) session.getAttribute("CART");

                // 1. Kiểm tra: Chưa đăng nhập thì về Login
                if (user == null) {
                    url = "login.jsp"; 
                } 
                // 2. Kiểm tra: Giỏ hàng phải tồn tại VÀ KHÔNG RỖNG (Quan trọng!)
                else if (cart != null && !cart.getCart().isEmpty()) {
                    
                    // Tính tổng tiền
                    double total = 0;
                    for (ProductDTO item : cart.getCart().values()) {
                        total += item.getPrice() * item.getQuantity();
                    }

                    // Gọi DAO lưu vào SQL
                    OrderDAO dao = new OrderDAO();
                    // OrderID để 0 vì trong SQL tự tăng (Identity)
                    OrderDTO order = new OrderDTO(0, null, total, user.getUserID(), 1); 
                    
                    boolean check = dao.checkOut(order, cart);

                    if (check) {
                        session.removeAttribute("CART"); // Xóa giỏ hàng sau khi mua xong
                        url = "MainController?action=home&msg=Success";
                    } else {
                        // Nếu lỗi DAO (ví dụ hết hàng)
                        request.setAttribute("ERROR_MSG", "Thanh toán thất bại! Có thể do lỗi hệ thống.");
                        url = "MainController?action=viewCart"; 
                    }
                } else {
                    // Nếu giỏ hàng rỗng
                    url = "MainController?action=viewCart&msg=Empty";
                }
            } else {
                 url = "login.jsp";
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            // Dùng Redirect để tránh F5 bị mua lại lần nữa (Double Submit)
            response.sendRedirect(url);
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
