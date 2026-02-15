/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
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

        try {
            String catID = request.getParameter("catID");
            String brandID = request.getParameter("brandID");
            String keyword = request.getParameter("keyword");
            String indexPageString = request.getParameter("indexPage");

            ProductDAO pDAO = new ProductDAO();
            CategoryDAO cDAO = new CategoryDAO();
            BrandDAO bDAO = new BrandDAO();

            // Đưa Menu hiện lên (2 dòng request)
            request.setAttribute("listCategory", cDAO.getAllCategories());
            request.setAttribute("listBrand", bDAO.getAllBrands());

            if (indexPageString == null) {
                indexPageString = "1";
            }
            int indexPage = Integer.parseInt(indexPageString);

            List<ProductDTO> listTamThoi = new ArrayList<>();
            boolean isFiltering = false;
            if (catID != null && !catID.trim().isEmpty()) {
                listTamThoi = pDAO.getByCategory(catID);
                isFiltering = true;
            } else if (brandID != null && !brandID.trim().isEmpty()) {
                listTamThoi = pDAO.getByBrand(brandID);
                isFiltering = true;
            } else if (keyword != null && !keyword.trim().isEmpty()) {
                listTamThoi = pDAO.searchByName(keyword);
                isFiltering = true;
            }

            List<ProductDTO> listProduct = new ArrayList();
            int numberProductPage = 8;
            int totalProducts = 0;

            if (isFiltering) {    // Nếu Lọc và Search
                totalProducts = listTamThoi.size();

                int start = (indexPage - 1) * numberProductPage; // CT PHÂN TRANG
                int end = Math.min(start + numberProductPage, totalProducts);

                if (start < totalProducts) {
                    listProduct = listTamThoi.subList(start, end);
                }
            } else {              // TRANG CHỦ
                totalProducts = pDAO.getTotalProduct();
                listProduct = pDAO.numberProductOnPage(indexPage);
            }

            // Tính số trang cuối
            int endPage = totalProducts / numberProductPage;
            if (totalProducts % numberProductPage != 0) {
                endPage++;
            }

            request.setAttribute("listProduct", listProduct);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", indexPage);

            // Gửi dữ liệu mỗi lần chuyển trang lại (không trả sẽ mất)
            request.setAttribute("catID", catID);
            request.setAttribute("brandID", brandID);
            request.setAttribute("keyword", keyword);
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
