/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.URL;

/**
 *
 * @author thinh
 */
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = URL.PAGE_ERROR;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "home";
            } else if (action.trim().isEmpty()) {
                action = "home";
            }
            switch (action) {
                case "home":
                case "detail":
                case "search":
                case "filterPrice":
                    url = "ProductController";
                    break;
                case "addToCart":
                    url = URL.ADDTOCART_CONTROLLER;
                    break;
                case "Remove":
                    url = URL.REMOVE_FROM_CART_CONTROLLER;
                    break;
                case "CheckOut":
                    url = URL.CHECKOUT_CONTROLLER;
                    break;
                case "viewCart":
                    url = URL.VIEWCART_CONTROLLER;
                    break;
                case "login":
                    url = URL.LOGIN_CONTROLLER;
                    break;
                case "logout":
                    url = URL.LOGOUT_CONTROLLER;
                    break;
                //Case XỬ LÝ ĐĂNG KÝ(Khi bấm nút "Submit" trong form
                case "register":
                    // "Create" là value của input hidden trong register.jsp
                    url = URL.REGISTER_CONTROLLER; // Chuyển sang Servlet xử lý logic
                    break;
                case "addFeedback":
                    url = URL.ADD_FEED_BACK_CONTROLLER;
                    break;
                default:
                    url = "ProductController";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
