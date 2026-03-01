<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ hàng - Gaming Gear Shop</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet">
    </head>
    
    <body>
        <nav class="navbar navbar-expand-lg fixed-top floating-navbar">
            <div class="container-fluid px-4 px-lg-5">
                <a class="navbar-brand brand-boutique" href="${pageContext.request.contextPath}/MainController?action=home">
                    Gaming Gear Shop<span></span>
                </a>
                
                <button class="navbar-toggler border-0 shadow-none text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
                    <i class="bi bi-list fs-2"></i>
                </button>

                <div class="collapse navbar-collapse" id="navMenu">
                    <form action="${pageContext.request.contextPath}/MainController" method="GET" class="d-flex mx-auto my-3 my-lg-0 w-100 max-w-500">
                        <input type="hidden" name="action" value="search" />
                        <div class="d-flex w-100">
                            <input type="text" name="keyword" class="form-control search-pill" value="${requestScope.searchKeyword}" placeholder="Tìm sản phẩm của bạn...">
                            <button type="submit" class="btn btn-search-pill text-white"><i class="bi bi-search"></i></button>
                        </div>
                    </form>

                    <div class="d-flex align-items-center gap-2 justify-content-center mt-2 mt-lg-0">
                        <a href="${pageContext.request.contextPath}/MainController?action=viewCart" class="action-btn active-nav text-decoration-none">
                            <i class="bi bi-bag-heart fs-5"></i> 
                            <span class="d-none d-xl-inline">Giỏ hàng</span>
                            <c:if test="${not empty sessionScope.CART}">
                                <span class="cart-bubble">${sessionScope.CART.cart.size()}</span>
                            </c:if>
                        </a>

                        <div class="dropdown">
                            <button class="action-btn dropdown-toggle border-0 bg-transparent" type="button" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle fs-5"></i>
                                <span class="d-none d-xl-inline ms-1">
                                    ${not empty sessionScope.LOGIN_USER ? sessionScope.LOGIN_USER.fullName : 'Tài khoản'}
                                </span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end soft-dropdown">
                                <c:choose>
                                    <c:when test="${empty sessionScope.LOGIN_USER}">
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MainController?action=login">Đăng nhập</a></li>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MainController?action=register">Đăng ký mới</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/MainController?action=logout">Đăng xuất</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="cart-wrapper">
            
            <div class="mb-4"> 
                <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn-continue">
                    <i class="bi bi-arrow-left"></i> Tiếp tục mua sắm
                </a>
            </div>

            <div class="cart-box">
                <c:choose>
                    <%-- 1. TRƯỜNG HỢP: THANH TOÁN THÀNH CÔNG --%>
                    <c:when test="${requestScope.PAYMENT_STATUS == 'SUCCESS'}">
                        <div class="status-box">
                            <div class="status-icon success"><i class="bi bi-check-circle-fill"></i></div>
                            <h2 class="status-title text-success-custom">THANH TOÁN THÀNH CÔNG!</h2>
                            <p class="status-desc">Cảm ơn bạn đã mua sắm tại Gaming Gear Shop. Đơn hàng của bạn đang được xử lý và sẽ giao sớm nhất.</p>
                            <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn-checkout">MUA SẮM TIẾP</a>
                        </div>
                    </c:when>

                    <%-- 2. TRƯỜNG HỢP: GIỎ HÀNG TRỐNG --%>
                    <c:when test="${empty requestScope.CART_ITEMS && requestScope.PAYMENT_STATUS != 'SUCCESS'}">
                        <div class="status-box">
                            <div class="status-icon empty"><i class="bi bi-cart-x"></i></div>
                            <h2 class="status-title">Giỏ hàng trống</h2>
                            <p class="status-desc">Chưa có sản phẩm nào trong giỏ hàng. Hãy lấp đầy nó bằng các thiết bị Gaming xịn xò nhé!</p>
                            <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn-checkout">
                                <i class="bi bi-cart-plus me-2"></i> MUA SẮM NGAY
                            </a>
                        </div>
                    </c:when>

                    <%-- 3. TRƯỜNG HỢP: CÓ SẢN PHẨM TRONG GIỎ HÀNG --%>
                    <c:otherwise>
                        <h2 class="mb-4 cart-header-title">Giỏ Hàng Của Bạn</h2>
                        
                        <div class="cart-table-wrapper">
                            <table class="cart-table">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Đơn giá</th>
                                        <th>Số lượng</th>
                                        <th>Thành tiền</th>
                                        <th class="text-center">Xóa</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0" />
                                    <c:forEach var="item" items="${requestScope.CART_ITEMS}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td class="product-name-col">${item.productName}</td>
                                            <td class="price-col">${item.price} ₫</td>
                                            <td>
                                                <span class="quantity-badge">${item.quantity}</span>
                                            </td>
                                            <td class="total-col">${item.price * item.quantity} ₫</td>
                                            <td class="action-col">
                                                <a href="${pageContext.request.contextPath}/MainController?action=Remove&productID=${item.productID}" 
                                                   class="btn-remove" title="Bỏ khỏi giỏ"
                                                   onclick="return confirm('Bạn có chắc muốn bỏ sản phẩm này khỏi giỏ hàng?')">
                                                    <i class="bi bi-trash3-fill"></i>
                                                </a>
                                            </td>
                                        </tr>
                                        <c:set var="total" value="${total + (item.price * item.quantity)}" />
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="cart-summary">
                            <div class="summary-text">
                                <h3>Tổng thanh toán:</h3>
                                <div class="final-price">${total} ₫</div>
                            </div>
                            
                            <div class="text-end">
                                <a href="${pageContext.request.contextPath}/MainController?action=CheckOut" class="btn-checkout">
                                    <i class="bi bi-credit-card-2-front-fill me-2"></i> XÁC NHẬN ĐẶT HÀNG
                                </a>
                                
                                <%-- Gọi class cart-error-msg thay vì dùng style --%>
                                <c:if test="${requestScope.PAYMENT_STATUS == 'ERROR'}">
                                    <div class="cart-error-msg">
                                        <i class="bi bi-exclamation-octagon-fill me-1"></i> Thanh toán thất bại! Vui lòng kiểm tra lại.
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>