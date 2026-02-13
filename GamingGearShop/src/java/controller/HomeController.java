/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.CategoryDTO;
import Model.ProductDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.URL;

/**
 *
 * @author ACER
 */
public class HomeController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String url = URL.PAGE_HOME;
        
        try{
            String catID = request.getParameter("catID");   // Nếu có thì nhận
            
            // Cho vừa vào Web là TRANG 1
            String indexPage = request.getParameter("index");
            if(indexPage == null){
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            // ----------------------
            
            ProductDAO pDAO = new ProductDAO();
            CategoryDAO cDAO = new CategoryDAO();
            
            // Hiện Menu
            List<CategoryDTO> listCategory = cDAO.getAllCategories();
            request.setAttribute("listCategory", listCategory);
            
            // Lọc Theo Menu và Phân trang
            List<ProductDTO> listProduct = new ArrayList<>();
            if(catID == null || catID.trim().isEmpty()){
                
                // Tính số trang cần dùng
                int totalProducts = pDAO.getTotalProduct();
                int numberProductPage = 8;
                int endPage = totalProducts / numberProductPage;
                
                if(totalProducts % numberProductPage != 0){ // Nếu sản phẩm chẵn không +, lẽ sẽ ++
                    endPage++;
                }
                listProduct = pDAO.numberProductOnPage(index);
                request.setAttribute("endPage", endPage);
                request.setAttribute("tag", index);
            }else{
                listProduct = pDAO.getByCategory(catID);
            }
            request.setAttribute("listProduct", listProduct);
                
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
