package utils;

public class URL {

    // ===== TRANG KHÁCH HÀNG =====
    public static final String PAGE_HOME = "view/home.jsp";
    public static final String PAGE_LOGIN = "view/login.jsp";
    public static final String PAGE_REGISTER = "view/register.jsp";
    public static final String PAGE_CART = "view/cart.jsp";
    public static final String PAGE_DETAIL = "view/detail.jsp";
    public static final String PAGE_ERROR = "view/error.jsp";
    public static final String PAGE_ORDER_HISTORY = "view/order-history.jsp";
    public static final String PAGE_FORGOT_PASSWORD = "view/forgot-password.jsp";
    public static final String PAGE_VERIFY_OTP = "view/verify-otp.jsp";
    public static final String PAGE_PAYMENT = "view/payment.jsp";

    // ===== TRANG ADMIN =====
    public static final String PAGE_ADMIN_PRODUCT = "view/admin/product-list.jsp";
    public static final String PAGE_ADMIN_PRODUCT_FORM = "view/admin/product-form.jsp";
    public static final String PAGE_ADMIN_USER = "view/admin/user-list.jsp";
    public static final String PAGE_ADMIN_CATEGORY = "view/admin/category-list.jsp";
    public static final String PAGE_ADMIN_CATEGORY_FORM = "view/admin/category-form.jsp";
    public static final String PAGE_ADMIN_BRAND = "view/admin/brand-list.jsp";
    public static final String PAGE_ADMIN_BRAND_FORM = "view/admin/brand-form.jsp";
    public static final String PAGE_ADMIN_ORDER = "view/admin/order-list.jsp";
    public static final String PAGE_ADMIN_USER_FORM = "view/admin/user-form.jsp";
    public static final String PAGE_ADMIN_ORDER_FORM = "view/admin/order-form.jsp";

    // ===== CÁC CONTROLLER =====
    public static final String PRODUCT_CONTROLLER = "ProductController";
    public static final String USER_CONTROLLER = "UserController";
    public static final String CART_ORDER_CONTROLLER = "CartOrderController";

    // ===== ĐIỀU HƯỚNG NHANH =====
    public static final String PROCESS_HOME = "MainController?action=home";
    public static final String PROCESS_ADMIN_PRODUCT = "AdminController?action=product_list";
    public static final String PROCESS_ADMIN_USER = "AdminController?action=user_list";
    public static final String PROCESS_ADMIN_CATEGORY = "AdminController?action=category_list";
    public static final String PROCESS_ADMIN_BRAND = "AdminController?action=brand_list";
}
