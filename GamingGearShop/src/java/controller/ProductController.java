///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import DAO.BrandDAO;
//import DAO.CategoryDAO;
//import DAO.FeedbackDAO;
//import DAO.ProductDAO;
//import Model.FeedbackDTO;
//import Model.ProductDTO;
//import java.io.IOException;
//import java.util.ArrayList;
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
//public class ProductController extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        
//        String action = request.getParameter("action");
//        if(action == null || action.trim().isEmpty()){
//            action = "home";
//        }
//        
//        try{
//            switch(action) {
//                case "home":
//                    showHome(request, response);
//                    break;
//                case "detail":
//                    showDetail(request, response);
//                    break;
//                case "search":
//                    searchProduct(request, response);
//                    break;
//                case "filterPrice":
//                    filterByPrice(request, response);
//                    break;
//                default:
//                    showHome(request, response);
//                    break;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            request.setAttribute("ERROR_MSG", "Lỗi hệ thống: " + e.getMessage());
//            RequestDispatcher rd = request.getRequestDispatcher(URL.PAGE_ERROR);
//            rd.forward(request, response);
//        }
//    }
//
//    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String catID = request.getParameter("catID");
//        String brandID = request.getParameter("brandID");
//        String keyword = request.getParameter("keyword");
//        String indexPageString = request.getParameter("indexPage");
//
//        ProductDAO pDAO = new ProductDAO();
//        CategoryDAO cDAO = new CategoryDAO();
//        BrandDAO bDAO = new BrandDAO();
//
//        // Đưa Menu hiện lên
//        request.setAttribute("listCategory", cDAO.getAllCategories());
//        request.setAttribute("listBrand", bDAO.getAllBrands());
//
//        if (indexPageString == null) {
//            indexPageString = "1";
//        }
//        int indexPage = Integer.parseInt(indexPageString);
//
//        List<ProductDTO> listTamThoi = new ArrayList<>();
//        boolean isFiltering = false;
//
//        if (catID != null && !catID.trim().isEmpty()) {
//            listTamThoi = pDAO.getByCategory(catID);
//            isFiltering = true;
//        } else if (brandID != null && !brandID.trim().isEmpty()) {
//            listTamThoi = pDAO.getByBrand(brandID);
//            isFiltering = true;
//        } else if (keyword != null && !keyword.trim().isEmpty()) {
//            listTamThoi = pDAO.searchByName(keyword);
//            isFiltering = true;
//        }
//
//        List<ProductDTO> listProduct = new ArrayList<>();
//        int numberProductPage = 8;
//        int totalProducts = 0;
//
//        if (isFiltering) {
//            totalProducts = listTamThoi.size();
//            int start = (indexPage - 1) * numberProductPage;
//            int end = Math.min(start + numberProductPage, totalProducts);
//            if (start < totalProducts) {
//                listProduct = listTamThoi.subList(start, end);
//            }
//        } else {
//            totalProducts = pDAO.getTotalProduct();
//            listProduct = pDAO.numberProductOnPage(indexPage);
//        }
//
//        int endPage = totalProducts / numberProductPage;
//        if (totalProducts % numberProductPage != 0) {
//            endPage++;
//        }
//
//        request.setAttribute("listProduct", listProduct);
//        request.setAttribute("endPage", endPage);
//        request.setAttribute("tag", indexPage);
//        request.setAttribute("catID", catID);
//        request.setAttribute("brandID", brandID);
//        request.setAttribute("keyword", keyword);
//
//        RequestDispatcher rd = request.getRequestDispatcher(URL.PAGE_HOME);
//        rd.forward(request, response);
//    }
//
//    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("productID");
//        ProductDAO pDAO = new ProductDAO();
//        ProductDTO pDTO = pDAO.getProductByID(id);
//
//        if (pDTO == null) {
//            request.setAttribute("message", "Sản phẩm không tồn tại hoặc đã bị xóa!");
//            request.getRequestDispatcher(URL.PROCESS_HOME).forward(request, response);
//            return;
//        }
//
//        FeedbackDAO fDAO = new FeedbackDAO();
//        List<FeedbackDTO> feedbackList = fDAO.getFeedbackByProductID(id);
//
//        request.setAttribute("productFeedbacks", feedbackList);
//        request.setAttribute("detail", pDTO);
//
//        request.getRequestDispatcher(URL.PAGE_DETAIL).forward(request, response);
//    }
//
//    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String keyword = request.getParameter("keyword");
//        CategoryDAO cDAO = new CategoryDAO();
//        request.setAttribute("listCategory", cDAO.getAllCategories());
//
//        ProductDAO pDAO = new ProductDAO();
//        List<ProductDTO> listProduct = new ArrayList<>();
//
//        if (keyword != null && !keyword.trim().isEmpty()) {
//            keyword = keyword.trim();
//            if (keyword.length() > 500) {
//                keyword = keyword.substring(0, 500);
//            }
//            listProduct = pDAO.searchByName(keyword);
//            if (listProduct == null || listProduct.isEmpty()) {
//                request.setAttribute("message", "Không tìm thấy sản phẩm nào với từ khóa: " + keyword);
//            }
//        } else {
//            listProduct = pDAO.getAllProducts();
//            keyword = "";
//        }
//
//        request.setAttribute("listProduct", listProduct);
//        request.setAttribute("searchKeyword", keyword);
//        request.getRequestDispatcher(URL.PAGE_HOME).forward(request, response);
//    }
//
//    private void filterByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String minRaw = request.getParameter("min");
//        String maxRaw = request.getParameter("max");
//
//        double min = (minRaw == null) ? 0 : Double.parseDouble(minRaw);
//        double max = (maxRaw == null) ? Double.MAX_VALUE : Double.parseDouble(maxRaw);
//
//        ProductDAO pDAO = new ProductDAO();
//        List<ProductDTO> listProduct = pDAO.getProductsByPriceRange(min, max);
//
//        request.setAttribute("listProduct", listProduct);
//        request.setAttribute("minPrice", min);
//        request.setAttribute("maxPrice", max);
//
//        request.getRequestDispatcher(URL.PAGE_HOME).forward(request, response);
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
