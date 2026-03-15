package utils;

public class URL {

    // ===== TRANG KHÁCH HÀNG =====
    public static final String PAGE_HOME     = "view/home.jsp";
    public static final String PAGE_LOGIN    = "view/login.jsp";
    public static final String PAGE_REGISTER = "view/register.jsp";
    public static final String PAGE_CART     = "view/cart.jsp";
    public static final String PAGE_DETAIL   = "view/detail.jsp";
    public static final String PAGE_ERROR    = "view/error.jsp";

    // ===== TRANG ADMIN =====
    public static final String PAGE_ADMIN_PRODUCT         = "view/admin/product-list.jsp";
    public static final String PAGE_ADMIN_PRODUCT_FORM    = "view/admin/product-form.jsp";
    public static final String PAGE_ADMIN_USER            = "view/admin/user-list.jsp";
    public static final String PAGE_ADMIN_CATEGORY        = "view/admin/category-list.jsp";
    public static final String PAGE_ADMIN_CATEGORY_FORM   = "view/admin/category-form.jsp";
    public static final String PAGE_ADMIN_BRAND           = "view/admin/brand-list.jsp";
    public static final String PAGE_ADMIN_BRAND_FORM      = "view/admin/brand-form.jsp";

    // ===== CÁC CONTROLLER =====
    public static final String PRODUCT_CONTROLLER    = "ProductController";
    public static final String USER_CONTROLLER       = "UserController";
    public static final String CART_ORDER_CONTROLLER = "CartOrderController";

    // ===== ĐIỀU HƯỚNG NHANH =====
    public static final String PROCESS_HOME          = "MainController?action=home";
    public static final String PROCESS_ADMIN_PRODUCT = "AdminController?action=product_list";
    public static final String PROCESS_ADMIN_USER    = "AdminController?action=user_list";
    public static final String PROCESS_ADMIN_CATEGORY = "AdminController?action=category_list";
    public static final String PROCESS_ADMIN_BRAND   = "AdminController?action=brand_list";
}