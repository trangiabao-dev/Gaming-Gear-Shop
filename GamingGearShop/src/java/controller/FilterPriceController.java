///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import DAO.ProductDAO;
//import Model.ProductDTO;
//import java.io.IOException;
//import java.util.List;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import utils.URL;
//
///**
// *
// * @author ACER
// */
//public class FilterPriceController extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        
//        String url = URL.PAGE_HOME;
//        
//        try{
//            String minRaw = request.getParameter("min");
//            String maxRaw = request.getParameter("max");
//            
//            double min, max;
//            
//            if(minRaw == null){
//                min = 0;
//            }else{
//                min = Double.parseDouble(minRaw);
//            }
//            
//            if(maxRaw == null){
//                max = Double.MAX_VALUE;
//            }else{
//                max = Double.parseDouble(maxRaw);
//            }
//            
//            ProductDAO pDAO = new ProductDAO();
//            List<ProductDTO> listProduct = pDAO.getProductsByPriceRange(min, max);
//            
//            request.setAttribute("listProduct", listProduct);
//            request.setAttribute("minPrice", min); 
//            request.setAttribute("maxPrice", max);
//            
//        }catch(NumberFormatException e){
//            e.printStackTrace();
//        }finally{
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
