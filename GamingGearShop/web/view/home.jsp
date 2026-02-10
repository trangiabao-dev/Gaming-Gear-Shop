<%-- 
    Document   : index
    Created on : Feb 8, 2026, 4:25:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/css2?family=Chakra+Petch:wght@400;700&display=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <%-- LOGIN --%> 
        <c:if test="${empty sessionScope.LOGIN_USER}"> 
            <%-- Gọi qua Controller thay vì gọi trực tiếp file .jsp --%>
            <a href="MainController?action=login" class="btn-header btn-header-login"> 
                <i class="fa-solid fa-user"></i>
                Đăng nhập 
            </a> 
        </c:if>

        <%-- LOGOUT --%>
        <c:if test="${not empty sessionScope.LOGIN_USER}">
            <div class="btn-header btn-header-logout">
                <span class="user-name">
                    Xin chào, ${sessionScope.LOGIN_USER.fullName}
                </span>
            </div>

            <%-- Điều hướng về MainController để xử lý xóa Session --%>
            <a href="MainController?action=logout" class="btn-header btn-header-login">
                <i class="fa-solid fa-right-from-bracket"></i>
                Thoát
            </a>
        </c:if>

        <div class="banner"> 
            <h1>Gaming Gear Shop</h1>
            <p>Chuyên chuột, bàn phím, tai nghe chính hãng</p>
        </div>

        <div class="product-list"> 
            <c:forEach items="${listProduct}" var="product" end="3">
                <div class="product">
                    <img src="${product.imageURL}" alt="${product.productName}" width="100%"/>

                    <h3>${product.productName}</h3>

                    <p>Giá: ${product.priceFormat} VNĐ</p>
                </div>
            </c:forEach>
        </div>

        <div class="sidebar">
            <h3>Danh mục sản phẩm</h3>
            <c:forEach items="${listCategory}" var="category">
                <li>
                    <form action="MainController" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="home"/>
                        <input type="hidden" name="catID" value="${category.catID}" />
                        <button type="submit" class="menu-item">
                            <i class="fa fa-chevron-right"></i> ${category.catName}
                        </button>
                    </form>
                </li>
            </c:forEach>
        </div>
    </body>
</html>
