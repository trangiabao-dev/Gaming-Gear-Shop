package utils;

public class URL {

    // TRANG GIAO DIỆN (JSP) ====
    public static final String PAGE_HOME = "view/home.jsp";
    public static final String PAGE_LOGIN = "view/login.jsp";
    public static final String PAGE_ERROR = "view/error.jsp";
    public static final String PAGE_REGISTER = "view/register.jsp";
    public static final String PAGE_CART = "view/cart.jsp";
    public static final String PAGE_DETAIL = "view/detail.jsp";
    
    // ==== CÁC CONTROLLER BẢO ====
    public static final String MAIN_CONTROLLER = "MainController";
    public static final String PRODUCT_CONTROLLER = "ProductController"; // Thay thế cho Search, Detail, Filter
    
    // ==== CÁC CONTROLLER CỦA THỊNH ====
    public static final String LOGIN_CONTROLLER = "LoginController";
    public static final String LOGOUT_CONTROLLER = "LogoutController";
    public static final String REGISTER_CONTROLLER = "RegisterController";
    public static final String ADDTOCART_CONTROLLER = "AddToCartController";
    public static final String CHECKOUT_CONTROLLER = "CheckoutController";
    public static final String VIEWCART_CONTROLLER = "ViewCartController";
    public static final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartController";
    
    // ==== TƯƠNG TÁC KHÁC ====
    public static final String ADD_FEED_BACK_CONTROLLER = "AddFeedbackController";
            
    // ==== ĐƯỜNG DẪN ĐIỀU HƯỚNG NHANH ====
    public static final String PROCESS_HOME = "MainController?action=home";
}