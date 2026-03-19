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

        String url = URL.PAGE_ERROR;

        try {
            String action = request.getParameter("action");
            if (action == null || action.trim().isEmpty()) {
                action = "home";
            }

            // Kỹ thuật Ép chữ thường: Để tránh lỗi "Remove" và "CheckOut" từ JSP
            String actionLower = action.toLowerCase();

            switch (actionLower) {
                case "home":
                case "detail":
                case "search":
                case "filterprice":
                    url = URL.PRODUCT_CONTROLLER;
                    break;
                case "login":
                case "logout":
                case "register":
                    url = URL.USER_CONTROLLER;
                    break;
                case "addtocart":
                case "viewcart":
                case "remove":     // Khớp với mọi kiểu gõ từ JSP
                case "checkout":   // Khớp với mọi kiểu gõ từ JSP
                case "orderhistory":
                case "cancelorder":
                    url = URL.CART_ORDER_CONTROLLER;
                    break;
                case "addfeedback":
//                    url = URL.ADD_FEED_BACK_CONTROLLER;
                    break;
                default:
                    url = URL.PRODUCT_CONTROLLER;
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
