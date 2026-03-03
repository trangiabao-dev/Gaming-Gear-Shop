<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${detail.productName} - Gaming Gear Shop</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/detail.css" rel="stylesheet">
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
                    <form action="${pageContext.request.contextPath}/MainController" method="GET" class="d-flex mx-auto my-3 my-lg-0" style="max-width: 500px; width: 100%;">
                        <input type="hidden" name="action" value="search" />
                        <div class="d-flex w-100">
                            <input type="text" name="keyword" class="form-control search-pill" value="${requestScope.searchKeyword}" placeholder="Tìm sản phẩm của bạn...">
                            <button type="submit" class="btn btn-search-pill text-white"><i class="bi bi-search"></i></button>
                        </div>
                    </form>

                    <div class="d-flex align-items-center gap-2 justify-content-center mt-2 mt-lg-0">
                        <a href="${pageContext.request.contextPath}/MainController?action=viewCart" class="action-btn text-decoration-none">
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

        <div class="back-container"> 
            <a href="${pageContext.request.contextPath}/MainController?action=home" class="back-link">
                <i class="bi bi-arrow-left"></i> Quay lại cửa hàng
            </a>
        </div>

        <div class="product-box"> 
            <div class="left-side"> 
                <div class="image-container">
                    <img src="${detail.imageURL}" alt="${detail.productName}" class="big-image"/>
                </div>
            </div>

            <div class="right-side">
                <h1 class="header-name">${detail.productName}</h1>

                <p class="product-meta">
                    Mã SP: <b>${detail.productID}</b> &nbsp;|&nbsp; 
                    Tình trạng: 
                    <span class="stock-status ${detail.quantity > 0 ? 'in-stock' : 'out-stock'}">
                        ${detail.quantity > 0 ? 'Còn hàng' : 'Hết hàng'} (${detail.quantity})
                    </span> 
                </p>

                <div class="money"> 
                    ${detail.priceFormat} ₫
                </div>

                <form action="${pageContext.request.contextPath}/MainController" method="POST" class="buying-form">
                    <input type="hidden" name="action" value="addToCart" />
                    <input type="hidden" name="productID" value="${detail.productID}" />
                    <input type="hidden" name="productName" value="${detail.productName}" />
                    <input type="hidden" name="price" value="${detail.price}" />

                    <div class="quantity-section"> 
                        <label for="qty">Số lượng:</label>
                        <input type="number" name="quantity" id="qty" value="1" 
                               min="1" max="${detail.quantity}" class="input-number" required/>
                    </div>

                    <button type="submit" class="button_cart" ${detail.quantity <= 0 ? 'disabled' : ''}>
                        <i class="bi bi-cart-plus me-2"></i> THÊM VÀO GIỎ HÀNG NGAY
                    </button>
                </form>

                <div class="description">
                    <span class="description-title">Đặc điểm nổi bật:</span>
                    <div class="description-content">
                        ${detail.description}
                    </div>
                </div>
            </div> 
        </div>

        <div class="product-box feedback-section">
            <h3 class="description-title mb-4">Đánh giá từ người dùng</h3>

            <div class="feedback-list">
                <c:forEach items="${productFeedbacks}" var="fb">
                    <div class="feedback-item">
                        <div class="feedback-user-col">
                            <div class="user-avatar">
                                ${fb.userID.substring(0, 1).toUpperCase()}
                            </div>
                            <span class="user-display-name">${fb.userID}</span>
                        </div>

                        <div class="feedback-content-col">
                            <div class="rating-stars">
                                <c:forEach begin="1" end="${fb.rating}"><span class="star-gold">★</span></c:forEach>
                                <c:forEach begin="1" end="${5 - fb.rating}"><span class="star-gray">★</span></c:forEach>
                            </div>
                            <div class="feedback-text-content">
                                ${fb.content}
                            </div>
                            <div class="posted-date">
                                <i class="bi bi-clock"></i> Đã đăng vào: ${fb.date}
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty productFeedbacks}">
                    <div class="no-feedback-status">
                        <div class="no-feedback-icon"><i class="bi bi-chat-square-text"></i></div>
                        <p class="no-feedback-text">Sản phẩm này hiện chưa có đánh giá nào từ khách hàng.</p>
                    </div>
                </c:if>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>