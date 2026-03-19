package controller;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.UserDAO;
import Model.BrandDTO;
import Model.CategoryDTO;
import Model.UserDTO;
import java.io.File;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.OrderDTO;
import Model.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.URL;

/**
 *
 * @author ACER
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class AdminController extends HttpServlet {

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

        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "product_list";
        }

        try {
            switch (action) {
                // ===== SẢN PHẨM =====
                case "product_list":
                    showProductList(request, response);
                    break;
                case "product_create_page":
                    showProductForm(request, response, null);
                    break;
                case "product_insert":
                    insertProduct(request, response);
                    break;
                case "product_edit_page":
                    showProductEditPage(request, response);
                    break;
                case "product_update":
                    updateProduct(request, response);
                    break;
                case "product_delete":
                    productStatus(request, response, false);
                    break;
                case "product_restore":
                    productStatus(request, response, true);
                    break;

                // ===== NGƯỜI DÙNG =====
                case "user_list":
                    showUserList(request, response);
                    break;
                case "user_toggle":
                    userStatus(request, response);
                    break;

                // ===== DANH MỤC =====
                case "category_list":
                    showCategoryList(request, response);
                    break;
                case "category_create_page":
                    showCategoryForm(request, response, null);
                    break;
                case "category_insert":
                    insertCategory(request, response);
                    break;
                case "category_edit_page":
                    showCategoryEditPage(request, response);
                    break;
                case "category_update":
                    updateCategory(request, response);
                    break;
                case "category_delete":
                    categoryStatus(request, response, false);
                    break;
                case "category_restore":
                    categoryStatus(request, response, true);
                    break;

                // ===== THƯƠNG HIỆU =====
                case "brand_list":
                    showBrandList(request, response);
                    break;
                case "brand_create_page":
                    showBrandForm(request, response, null);
                    break;
                case "brand_insert":
                    insertBrand(request, response);
                    break;
                case "brand_edit_page":
                    showBrandEditPage(request, response);
                    break;
                case "brand_update":
                    updateBrand(request, response);
                    break;
                case "brand_delete":
                    brandStatus(request, response, false);
                    break;
                case "brand_restore":
                    brandStatus(request, response, true);
                    break;

                // ===== ORDER =====
                case "order_list":
                    showOrderList(request, response);
                    break;
                case "update_order_status":
                    updateOrderStatus(request, response);
                    break;
                case "":

                    break;

                default:
                    showProductList(request, response);
                    break;
            }
        } catch (Exception e) {
            log("Lỗi AdminController ở " + action + ": " + e.getMessage());
            request.setAttribute("ERROR_MSG", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher(URL.PAGE_ERROR).forward(request, response);
        }
    }

    // PHẦN PRODUCT
    private void showProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductDTO> list = new ProductDAO().getAllProductsForAdmin();
        request.setAttribute("PRODUCT_LIST", list);
        request.getRequestDispatcher(URL.PAGE_ADMIN_PRODUCT).forward(request, response);
    }

    private void showProductForm(HttpServletRequest request, HttpServletResponse response,
            ProductDTO product) throws ServletException, IOException {
        request.setAttribute("CATEGORY_LIST", new CategoryDAO().getAllCategories());
        request.setAttribute("BRAND_LIST", new BrandDAO().getAllBrands());
        request.setAttribute("EDIT_PRODUCT", product);
        request.getRequestDispatcher(URL.PAGE_ADMIN_PRODUCT_FORM).forward(request, response);
    }

    private void showProductEditPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productID");
        ProductDTO pDTO = new ProductDAO().findById(id);

        if (pDTO == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_PRODUCT);
            return;
        }
        showProductForm(request, response, pDTO);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String catID = request.getParameter("catID");
        String brandID = request.getParameter("brandID");
        String description = request.getParameter("description");

        if (productID == null || productID.trim().isEmpty()
                || productName == null || productName.trim().isEmpty()) {
            request.setAttribute("ERROR", "Mã SP và Tên SP không được bỏ trống!");
            showProductForm(request, response, null);
            return;
        }

        ProductDAO pDAO = new ProductDAO();
        if (pDAO.findById(productID.trim()) != null) {
            request.setAttribute("ERROR", "Mã sản phẩm '" + productID + "' đã tồn tại!");
            showProductForm(request, response, null);
            return;
        }

        double price = 0;
        int quantity = 0;
        try {
            price = Double.parseDouble(priceStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            request.setAttribute("ERROR", "Giá và Số lượng phải là số hợp lệ!");
            showProductForm(request, response, null);
            return;
        }

        String imageURL = imageProcessing(request, productID.trim());
        if (imageURL == null) {
            request.setAttribute("ERROR", "Vui lòng chọn ảnh sản phẩm!");
            showProductForm(request, response, null);
            return;
        }

        ProductDTO newProduct = new ProductDTO(productID.trim(), productName.trim(),
                price, quantity, description, imageURL, true, catID, brandID);

        boolean ok = pDAO.insert(newProduct);
        if (ok) {
            response.sendRedirect(
                    request.getContextPath() + "/" + URL.PROCESS_ADMIN_PRODUCT + "&msg=insert_ok"
            );
        } else {
            request.setAttribute("ERROR", "Lỗi khi lưu sản phẩm!");
            showProductForm(request, response, null);
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String catID = request.getParameter("catID");
        String brandID = request.getParameter("brandID");
        String description = request.getParameter("description");

        ProductDAO pDAO = new ProductDAO();
        ProductDTO pDTO = pDAO.findById(productID);

        if (pDTO == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_PRODUCT);
            return;
        }

        try {
            pDTO.setProductName(productName.trim());
            pDTO.setPrice(Double.parseDouble(priceStr));
            pDTO.setQuantity(Integer.parseInt(quantityStr));
            pDTO.setCatID(catID);
            pDTO.setBrandID(brandID);
            pDTO.setDescription(description);
        } catch (NumberFormatException e) {
            request.setAttribute("ERROR", "Giá và Số lượng phải là số hợp lệ!");
            showProductForm(request, response, pDTO);
            return;
        }

        String newImageURL = imageProcessing(request, productID);
        if (newImageURL != null) {
            pDTO.setImageURL(newImageURL);
        }

        boolean ok = pDAO.update(pDTO);
        if (ok) {
            response.sendRedirect(
                    request.getContextPath() + "/" + URL.PROCESS_ADMIN_PRODUCT + "&msg=update_ok"
            );
        } else {
            request.setAttribute("ERROR", "Lỗi khi cập nhật sản phẩm!");
            showProductForm(request, response, pDTO);
        }
    }

    private void productStatus(HttpServletRequest request, HttpServletResponse response,
            boolean status) throws ServletException, IOException {
        String id = request.getParameter("productID");

        ProductDAO pDAO = new ProductDAO();
        ProductDTO pDTO = pDAO.findById(id);

        if (pDTO != null) {
            pDTO.setStatus(status);
            pDAO.update(pDTO);
        }

        String msg = status ? "restore_ok" : "delete_ok";
        response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_PRODUCT + "&msg=" + msg);
    }

    // PHẦN USER
    private void showUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDTO> list = new UserDAO().getAllUsers();
        request.setAttribute("USER_LIST", list);
        request.getRequestDispatcher(URL.PAGE_ADMIN_USER).forward(request, response);
    }

    private void userStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        UserDAO uDAO = new UserDAO();
        UserDTO uDTO = uDAO.findById(userID);

        if (uDTO != null) {
            uDTO.setStatus(!uDTO.isStatus());
            uDAO.update(uDTO);
        }

        response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_USER);
    }

    // PHẦN DANH MỤC
    private void showCategoryList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryDTO> list = new CategoryDAO().getAllCategoriesForAdmin();
        request.setAttribute("CATEGORY_LIST", list);
        request.getRequestDispatcher(URL.PAGE_ADMIN_CATEGORY).forward(request, response);
    }

    private void showCategoryForm(HttpServletRequest request, HttpServletResponse response,
            CategoryDTO category) throws ServletException, IOException {
        request.setAttribute("EDIT_CATEGORY", category);
        request.getRequestDispatcher(URL.PAGE_ADMIN_CATEGORY_FORM).forward(request, response);
    }

    private void showCategoryEditPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("catID");
        CategoryDTO cDTO = new CategoryDAO().findById(id);
        if (cDTO == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_CATEGORY);
            return;
        }
        showCategoryForm(request, response, cDTO);
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catID = request.getParameter("catID");
        String catName = request.getParameter("catName");

        if (catID == null || catID.trim().isEmpty()) {
            request.setAttribute("ERROR", "Mã danh mục không được bỏ trống!");
            showCategoryForm(request, response, null);
            return;
        }

        CategoryDAO cDAO = new CategoryDAO();
        if (cDAO.findById(catID.trim()) != null) {
            request.setAttribute("ERROR", "Mã danh mục đã tồn tại!");
            showCategoryForm(request, response, null);
            return;
        }

        boolean ok = cDAO.insert(new CategoryDTO(catID.trim(), catName.trim(), true));
        if (ok) {
            response.sendRedirect(
                    request.getContextPath() + "/" + URL.PROCESS_ADMIN_CATEGORY + "&msg=insert_ok"
            );
        } else {
            request.setAttribute("ERROR", "Lỗi khi lưu danh mục!");
            showCategoryForm(request, response, null);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catID = request.getParameter("catID");
        String catName = request.getParameter("catName");

        CategoryDAO cDAO = new CategoryDAO();
        CategoryDTO cDTO = cDAO.findById(catID);
        if (cDTO == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_CATEGORY);
            return;
        }

        cDTO.setCatName(catName.trim());
        boolean ok = cDAO.update(cDTO);
        if (ok) {
            response.sendRedirect(
                    request.getContextPath() + "/" + URL.PROCESS_ADMIN_CATEGORY + "&msg=update_ok"
            );
        } else {
            request.setAttribute("ERROR", "Lỗi khi cập nhật danh mục!");
            showCategoryForm(request, response, cDTO);
        }
    }

    private void categoryStatus(HttpServletRequest request, HttpServletResponse response,
            boolean status) throws ServletException, IOException {
        String catID = request.getParameter("catID");

        CategoryDAO cDAO = new CategoryDAO();
        CategoryDTO cDTO = cDAO.findById(catID);

        if (cDTO != null) {
            cDTO.setStatus(status);
            cDAO.update(cDTO);
        }

        String msg = status ? "restore_ok" : "delete_ok";
        response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_CATEGORY + "&msg=" + msg);
    }

    // PHẦN BRAND
    private void showBrandList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<BrandDTO> list = new BrandDAO().getAllBrandsForAdmin();
        request.setAttribute("BRAND_LIST", list);
        request.getRequestDispatcher(URL.PAGE_ADMIN_BRAND).forward(request, response);
    }

    private void showBrandForm(HttpServletRequest request, HttpServletResponse response,
            BrandDTO brand) throws ServletException, IOException {
        request.setAttribute("EDIT_BRAND", brand);
        request.getRequestDispatcher(URL.PAGE_ADMIN_BRAND_FORM).forward(request, response);
    }

    private void showBrandEditPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("brandID");
        BrandDTO brand = new BrandDAO().findById(id);
        if (brand == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_BRAND);
            return;
        }
        showBrandForm(request, response, brand);
    }

    private void insertBrand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String brandID = request.getParameter("brandID");
        String brandName = request.getParameter("brandName");

        if (brandID == null || brandID.trim().isEmpty()) {
            request.setAttribute("ERROR", "Mã thương hiệu không được bỏ trống!");
            showBrandForm(request, response, null);
            return;
        }

        BrandDAO bDAO = new BrandDAO();
        if (bDAO.findById(brandID.trim()) != null) {
            request.setAttribute("ERROR", "Mã thương hiệu đã tồn tại!");
            showBrandForm(request, response, null);
            return;
        }

        boolean ok = bDAO.insert(new BrandDTO(brandID.trim(), brandName.trim()));
        if (ok) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_BRAND + "&msg=insert_ok");
        } else {
            request.setAttribute("ERROR", "Lỗi khi lưu thương hiệu!");
            showBrandForm(request, response, null);
        }
    }

    private void updateBrand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String brandID = request.getParameter("brandID");
        String brandName = request.getParameter("brandName");

        BrandDAO bDAO = new BrandDAO();
        BrandDTO brand = bDAO.findById(brandID);
        if (brand == null) {
            response.sendRedirect(request.getContextPath() + "/" + URL.PROCESS_ADMIN_BRAND);
            return;
        }

        brand.setBrandName(brandName.trim());
        boolean ok = bDAO.update(brand);
        if (ok) {
            response.sendRedirect(
                    request.getContextPath() + "/" + URL.PROCESS_ADMIN_BRAND + "&msg=update_ok"
            );
        } else {
            request.setAttribute("ERROR", "Lỗi khi cập nhật thương hiệu!");
            showBrandForm(request, response, brand);
        }
    }

    private void brandStatus(HttpServletRequest request, HttpServletResponse response,
            boolean status) throws ServletException, IOException {
        String brandID = request.getParameter("brandID");

        BrandDAO bDAO = new BrandDAO();
        BrandDTO brand = bDAO.findById(brandID);

        if (brand != null) {
            brand.setStatus(status);
            bDAO.update(brand);
        }

        String msg = status ? "restore_ok" : "delete_ok";
        response.sendRedirect(
                request.getContextPath() + "/" + URL.PROCESS_ADMIN_BRAND + "&msg=" + msg
        );
    }

    private String imageProcessing(HttpServletRequest request, String productID)
            throws IOException, ServletException {
        Part filePart = request.getPart("imageFile");

        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        String fileName = filePart.getSubmittedFileName();

        String fileNameLower = fileName.toLowerCase();
        if (!fileNameLower.endsWith(".jpg")
                && !fileNameLower.endsWith(".jpeg")
                && !fileNameLower.endsWith(".png")
                && !fileNameLower.endsWith(".gif")
                && !fileNameLower.endsWith(".webp")) {
            return null;
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String savedFileName = productID + fileExtension;

        String uploadDir = getServletContext().getRealPath("/uploads/products");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        filePart.write(uploadDir + File.separator + savedFileName);
        return "uploads/products/" + savedFileName;
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OrderDTO> orders = new OrderDAO().getAllOrders();
        request.setAttribute("ADMIN_ORDER_LIST", orders);
        request.getRequestDispatcher(URL.PAGE_ADMIN_ORDER).forward(request, response);
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String orderIDStr = request.getParameter("orderID");
        String statusStr = request.getParameter("status");
        try {
            int orderID = Integer.parseInt(orderIDStr);
            int status = Integer.parseInt(statusStr);
            new OrderDAO().updateOrderStatus(orderID, status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("AdminController?action=order_list");
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
