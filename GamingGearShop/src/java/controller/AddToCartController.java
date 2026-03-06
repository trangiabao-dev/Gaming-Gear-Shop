/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ProductDAO;
import Model.Cart;
import Model.ProductDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.URL;

/**
 *
 * @author thinh
 */
public class AddToCartController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String url = URL.PROCESS_HOME;
        HttpSession session = request.getSession();

        try {
            String productID = request.getParameter("productID");

            if (productID == null || productID.trim().isEmpty() || productID.length() > 10) {
                session.setAttribute("message", "Product ID không hợp lệ!");
                response.sendRedirect(url);
                return;
            }

            // 2. Lấy quantity an toàn (Check logic số lượng)
            int quantity = 1;
            String quantityRaw = request.getParameter("quantity");

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

            // 3. Lấy sản phẩm từ DB
            ProductDAO dao = new ProductDAO();
            ProductDTO product = dao.getProductByID(productID);

            if(product == null){
                session.setAttribute("message", "Sản phẩm không tồn tại!");
            }else{
                // 4. Set số lượng muốn mua
                product.setQuantity(quantity);

                // 5. Lấy cart từ session (Sử dụng tên "cart" chữ thường như bạn đã sửa)
                Cart cart = (Cart) session.getAttribute("cart");

                if (cart == null) {
                    cart = new Cart();
                }

                // 6. Add vào cart
                cart.add(product);

                // 7. Lưu lại session
                session.setAttribute("cart", cart);

                // 8. Flash message (Giữ nguyên văn của bạn)
                session.setAttribute("message", "Đã thêm thành công: " + product.getProductName());
            }
        }catch(Exception e) {
            log("Error at AddToCartController: " + e.toString());
            session.setAttribute("message", "Có lỗi xảy ra!");
        }
        response.sendRedirect(url);
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
