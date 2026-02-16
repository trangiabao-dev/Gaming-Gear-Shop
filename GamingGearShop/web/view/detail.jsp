<%-- 
    Document   : detail
    Created on : Feb 15, 2026, 8:44:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="back-container"> 
            <a href="MainController?action=home" class="back-link">
                <- Quay lại trang chủ
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
                    Mã SP: <b>${detail.productID}</b> | 
                    Tình trạng: <span class="stock-status">${detail.quantity > 0 ? 'Còn hàng' : 'Hết hàng'} (${detail.quantity})</span> 
                </p>

                <div class="money"> 
                    ${detail.priceFormat}₫
                </div>

                <form action="MainController" method="POST" class="buying-form">
                    <input type="hidden" name="action" value="addToCart" />
                    <input type="hidden" name="productID" value="${detail.productID}" />

                    <div class="quantity-section"> 
                        <label for="qty">Số lượng: </label>
                        <input type="number" name="quantity" id="qty" value="1" 
                               min="1" max="${detail.quantity}" class="input-number" required/>
                    </div>

                    <div class="buttons"> 
                        <button type="submit" class="button_cart">
                            THÊM VÀO GIỎ HÀNG NGAY
                        </button>
                    </div>
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
            <h3 class="description-title">Đánh giá từ người dùng</h3>

            <div class="feedback-section-container">
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
                                <div class="rating-header">
                                    <div class="rating-stars">
                                        <c:forEach begin="1" end="${fb.rating}">★</c:forEach>
                                        <c:forEach begin="1" end="${5 - fb.rating}"><span class="star-gray">★</span></c:forEach>
                                    </div>
                                </div>

                                <div class="feedback-text-content">
                                    ${fb.content}
                                </div>

                                <div class="feedback-meta">
                                    <span class="posted-date">🕒 Đã đăng vào: ${fb.date}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty productFeedbacks}">
                        <div class="no-feedback-status">
                            <div class="no-feedback-icon">💬</div>
                            <p class="no-feedback-text">Sản phẩm này hiện chưa có đánh giá nào từ khách hàng.</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
