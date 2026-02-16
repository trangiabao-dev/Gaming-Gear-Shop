<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gaming Gear Shop</title>
        <link href="https://fonts.googleapis.com/css2?family=Chakra+Petch:wght@400;700&display=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <div class="banner">
            <h1>Gaming Gear Shop</h1>
            <p>Chuyên chuột, bàn phím, tai nghe chính hãng</p>

            <div class="header-action-group">
                <%-- Nút Giỏ Hàng Mới Thêm --%>
                <a href="MainController?action=viewCart" class="btn-header-custom" style="position: relative;">
                    🛒 Giỏ hàng
                    <c:if test="${not empty sessionScope.CART}">
                        <span style="background: yellow; color: red; border-radius: 50%; padding: 2px 6px; font-size: 10px; position: absolute; top: -5px; right: -5px;">
                            ${sessionScope.CART.cart.size()}
                        </span>
                    </c:if>
                </a>

                <c:if test="${empty sessionScope.LOGIN_USER}"> 
                    <div class="auth-buttons">
                        <a href="MainController?action=login" class="btn-header"> 
                            Đăng nhập 
                        </a> 

                        <a href="MainController?action=register" class="btn-header btn-header-register">
                            Đăng ký 
                        </a>
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.LOGIN_USER}">
                    <div class="user-info-custom"> 
                        <span class="user-name-custom">
                            Xin chào, <b>${sessionScope.LOGIN_USER.fullName}</b>
                        </span>
                        <a href="MainController?action=logout" class="btn-logout-custom">
                            Đăng xuất
                        </a>
                    </div>
                </c:if>

                <form action="MainController" method="GET" class="search-form-custom">
                    <input type="hidden" name="action" value="search" />

                    <input type="text" 
                           name="keyword" 
                           class="search-input-custom" 
                           value="${requestScope.searchKeyword}" 
                           placeholder="Tìm sản phẩm..."/>

                    <button type="submit" class="search-btn-custom">
                        Tìm kiếm
                    </button>
                </form>

            </div>
                           
            <c:if test="${not empty requestScope.message}">
                <div class="search-error-msg">
                    ${requestScope.message}
                    <a href="MainController?action=search&keyword=" class="search-error-link">Quay lại</a>
                </div>
            </c:if>
        </div>

        <div class="product-list">

            <div class="sidebar">
                <h3 class="sidebar-title">Danh mục sản phẩm</h3>

                <ul class="category-list"> 
                    <li>
                        <form action="MainController" method="GET">
                            <input type="hidden" name="action" value="home"/>
                            <button type="submit" class="menu-item">
                                Tất cả sản phẩm
                            </button>
                        </form>
                    </li>

                    <c:forEach items="${listCategory}" var="category">
                        <li>
                            <form action="MainController" method="GET">
                                <input type="hidden" name="action" value="home"/>
                                <input type="hidden" name="catID" value="${category.catID}" />
                                <button type="submit" class="menu-item">
                                    ${category.catName}
                                </button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <c:forEach items="${listProduct}" var="product">
                <div class="product">
                    <a href="MainController?action=detail&productID=${product.productID}">
                        <img src="${product.imageURL}" alt="${product.productName}" width="100%"/>
                    </a>

                    <a href="MainController?action=detail&productID=${product.productID}" style="text-decoration: none; color: black;">
                        <h3>${product.productName}</h3>
                    </a>
                    <p>Giá: ${product.priceFormat} VNĐ</p>

                    <%-- Nút Mua hàng gửi dữ liệu về AddToCartController --%>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="productID" value="${product.productID}">
                        <input type="hidden" name="productName" value="${product.productName}">
                        <input type="hidden" name="price" value="${product.price}">
                        <input type="hidden" name="action" value="addToCart">
                        <button type="submit" class="search-btn-custom" style="width: 100%; border-radius: 5px; margin-top: 10px;">
                            THÊM VÀO GIỎ
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>

        <c:if test="${endPage > 1}">
            <div class="pagination">
                <c:forEach begin="1" end="${endPage}" var="i">

                    <c:url var="pageUrl" value="MainController">
                        <c:param name="action" value="home"/>
                        <c:param name="indexPage" value="${i}"/>

                        <c:if test="${not empty catID}">
                            <c:param name="catID" value="${catID}"/>
                        </c:if>

                        <c:if test="${not empty brandID}">
                            <c:param name="brandID" value="${brandID}"/>
                        </c:if>

                        <c:if test="${not empty keyword}">
                            <c:param name="keyword" value="${keyword}"/>
                        </c:if>
                    </c:url>

                    <a href="${pageUrl}" class="${tag == i ? 'active' : ''}">
                        ${i}
                    </a>
                </c:forEach>
            </div>
        </c:if>
    </body>
</html>
