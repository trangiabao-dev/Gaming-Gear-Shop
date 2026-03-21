/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.FeedbackDAO;
import Model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class FeedbackController extends HttpServlet {

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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "addFeedback":
                addFeedback(request, response);
                break;
            case "deleteFeedback":
                deleteFeedback(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/MainController?action=home");
        }
    }

    private void addFeedback(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        UserDTO user = (session != null) ? (UserDTO) session.getAttribute("LOGIN_USER") : null;

        String productID = request.getParameter("productID");
        String content = request.getParameter("content");
        String ratingStr = request.getParameter("rating");
        String redirectURL = request.getContextPath()
                + "/MainController?action=detail&productID=" + productID;

        // Chưa đăng nhập
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/MainController?action=login");
            return;
        }

        // Validate
        if (content == null || content.trim().isEmpty() || ratingStr == null) {
            response.sendRedirect(redirectURL + "&feedMsg=empty");
            return;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                rating = 5;
            }
        } catch (NumberFormatException e) {
            rating = 5;
        }

        FeedbackDAO dao = new FeedbackDAO();

        // Kiểm tra đã mua chưa
        if (!dao.hasPurchased(user.getUserID(), productID)) {
            response.sendRedirect(redirectURL + "&feedMsg=notbought");
            return;
        }

        boolean ok = dao.addFeedback(user.getUserID(), productID, content.trim(), rating);
        response.sendRedirect(redirectURL + (ok ? "&feedMsg=ok" : "&feedMsg=error"));
    }

    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // dành cho admin
        String feedIDStr = request.getParameter("feedID");
        try {
            int feedID = Integer.parseInt(feedIDStr);
            new FeedbackDAO().deleteFeedback(feedID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/AdminController?action=feedback_list&msg=delete_ok");
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
