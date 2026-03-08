package utils;

public class URL {

    // TRANG GIAO DIỆN (JSP) ====
    public static final String PAGE_HOME = "view/home.jsp";
    public static final String PAGE_LOGIN = "view/login.jsp";
    public static final String PAGE_ERROR = "view/error.jsp";
    public static final String PAGE_REGISTER = "view/register.jsp";
    public static final String PAGE_CART = "view/cart.jsp";
    public static final String PAGE_DETAIL = "view/detail.jsp";
    public static final String PAGE_ADMIN = "view/admin.jsp";
    
    // ==== CÁC CONTROLLER BẢO ====
    public static final String MAIN_CONTROLLER = "MainController";
    public static final String PRODUCT_CONTROLLER = "ProductController"; // Thay thế cho Search, Detail, Filter

    // ==== CÁC CONTROLLER CỦA THỊNH ====
    public static final String USER_CONTROLLER = "UserController";
    public static final String CART_ORDER_CONTROLLER = "CartOrderController";
    
    // ==== TƯƠNG TÁC KHÁC ====
    public static final String ADD_FEED_BACK_CONTROLLER = "AddFeedbackController";

    // ==== ĐƯỜNG DẪN ĐIỀU HƯỚNG NHANH ====
    public static final String PROCESS_HOME = "MainController?action=home";
}
