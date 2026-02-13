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
                    <a href="MainController?action=home" class="search-error-link">Quay lại</a>
                </div>
            </c:if>
        </div>

        <div class="product-list">

            <div class="sidebar">
                <h3 class="sidebar-title">Danh mục sản phẩm</h3>

                <ul class="category-list"> 

                    <c:forEach items="${listCategory}" var="category">
                        <li>
                            <form action="MainController" method="POST">
                                <input type="hidden" name="action" value="home"/>
                                <input type="hidden" name="catID" value="${category.catID}" />

                                <button type="submit" class="menu-item">
                                    <i class="fa fa-chevron-right"></i> 
                                    ${category.catName}
                                </button>
                            </form>
                        </li>
                    </c:forEach>

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
                    <img src="${product.imageURL}" 
                         alt="${product.productName}" 
                         width="100%"/>

                    <h3>${product.productName}</h3>
                    <h3>${product.productName}</h3>

                    <p>Giá: ${product.priceFormat} VNĐ</p>
                </div>
            </c:forEach>

        </div>
<<<<<<< HEAD
                  
        <c:if test="${endPage != null}">
            <div class="pagination">
                <c:forEach begin="1" end="${endPage}" var="i">
                    <a href="MainController?action=home&index=${i}" class="${tag == i ? 'active' : ''}">
                        ${i}
                    </a>
                </c:forEach>
            </div>
        </c:if>
=======

>>>>>>> 4cf4c1093a9cff30bc658de933bb18b32ad3160a
    </body>
</html>
