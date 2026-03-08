/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ProductDAO;
import Model.FeedbackDTO;
import Model.ProductDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.URL;

/**
 *
 * @author ACER
 */
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        if(action == null || action.trim().isEmpty()){
            action = "home";
        }
        
        try{
            switch(action) {
                case "detail":
                    showDetail(request, response);
                    break;
                case "home":
                case "search":
                case "filterPrice":  
                default:
                    showHome(request, response);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("ERROR_MSG", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher(URL.PAGE_ERROR).forward(request, response);
        }
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Tham số JSP gửi lên
        String catID = request.getParameter("catID");
        String brandID = request.getParameter("brandID");
        String keyword = request.getParameter("keyword");
        String minPrice = request.getParameter("min");
        String maxPrice = request.getParameter("max");
        String indexPageString = request.getParameter("indexPage");

        ProductDAO pDAO = new ProductDAO();
        CategoryDAO cDAO = new CategoryDAO();
        BrandDAO bDAO = new BrandDAO();

        // Đưa Menu hiện lên Web
        request.setAttribute("listCategory", cDAO.getAllCategories());
        request.setAttribute("listBrand", bDAO.getAllBrands());
        
        // Xử lý phân trang
        int indexPage = 1;
        if(indexPageString != null && !indexPageString.trim().isEmpty()){
            indexPage = Integer.parseInt(indexPageString);
        }
        int numberProductPage = 8;
        
        // Lọc xem khách hàng muốn xem gì
        List<ProductDTO> listProduct = new ArrayList<>();
        int totalProducts = 0;
        
        if(keyword != null && !keyword.trim().isEmpty()){
            List<ProductDTO> searchList = pDAO.searchByName(keyword.trim());
            if(searchList != null){
                totalProducts = searchList.size();
                listProduct = cutPage(searchList, indexPage, numberProductPage);
            }
            request.setAttribute("keyword", keyword); // giữ lại keyword trên thanh tìm kiếm
            
        }else if(catID != null && !catID.trim().isEmpty()){
            List<ProductDTO> catList = pDAO.getByCategory(catID);
            if(catList != null){
                totalProducts = catList.size();
                listProduct = cutPage(catList, indexPage, numberProductPage);
            }
            request.setAttribute("catID", catID);
            
        }else if(brandID != null && !brandID.trim().isEmpty()){
            List<ProductDTO> brandList = pDAO.getByBrand(brandID);
            if(brandList != null){
                totalProducts = brandList.size();
                listProduct = cutPage(brandList, indexPage, numberProductPage);
            }
            request.setAttribute("brandID", brandID);
            
        }else if(minPrice != null && maxPrice != null){
            try{
                double min = Double.parseDouble(minPrice);
                double max = Double.parseDouble(maxPrice);
                List<ProductDTO> priceList = pDAO.getProductsByPriceRange(min, max);
                if(priceList != null){
                    totalProducts = priceList.size();
                    listProduct = cutPage(priceList, indexPage, numberProductPage);
                }
                request.setAttribute("minPrice", min);
                request.setAttribute("maxPrice", max);
            }catch(NumberFormatException e){
                request.setAttribute("message", "Mức giá không hợp lệ! Vui lòng sử dụng các nút lọc có sẵn.");
                listProduct = new ArrayList<>();
            }
            
        }else{
            totalProducts = pDAO.getCountProduct();
            listProduct = pDAO.numberProductOnPage(indexPage);
        }
        
        // Tính số trang
        int endPage = totalProducts / numberProductPage;
        if(totalProducts % numberProductPage != 0){
            endPage++;
        }
        
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", indexPage);
        
        if(listProduct == null || listProduct.isEmpty()){
            if(request.getAttribute("message")  == null){
                request.setAttribute("message", "Không tìm thấy sản phẩm nào phù hợp!"); // Làm cho trường hợp khách sửa URL
            }
        }
        
        request.getRequestDispatcher(URL.PAGE_HOME).forward(request, response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("productID");
        ProductDAO pDAO = new ProductDAO();
        ProductDTO pDTO = pDAO.getProductByID(id);

        if (pDTO == null) {
            request.setAttribute("message", "Sản phẩm không tồn tại!");
            request.getRequestDispatcher(URL.PAGE_HOME).forward(request, response); // Forward thẳng ra JSP
            return;
        }

        FeedbackDAO fDAO = new FeedbackDAO();
        List<FeedbackDTO> feedbackList = fDAO.getFeedbackByProductID(id);

        request.setAttribute("productFeedbacks", feedbackList);
        request.setAttribute("detail", pDTO);

        request.getRequestDispatcher(URL.PAGE_DETAIL).forward(request, response);
    }
    
    private List<ProductDTO> cutPage(List<ProductDTO> list, int indexPage, int numberProductPage){
        if(list == null || list.isEmpty()){
            return new ArrayList<>();
        }
        int start = (indexPage - 1) * numberProductPage;
        int end = Math.min(start + numberProductPage, list.size()); // Math.min(): So sánh 2 số, chọn số nhỏ hơn
        
        if(start >= list.size()){
            return new ArrayList<>();
        }
        return list.subList(start, end);
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
