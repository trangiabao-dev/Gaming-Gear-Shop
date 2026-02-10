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

        <style>
            /* 1. Xóa bỏ mọi định dạng mặc định của button */
            .menu-item {
                background: none;
                border: none;
                padding: 10px 15px;
                margin: 5px 0;
                width: 100%;
                text-align: left;
                font-size: 16px;
                color: #333;
                cursor: pointer;
                transition: all 0.3s ease; /* Hiệu ứng mượt mà khi rê chuột */
                border-radius: 5px;
            }

            /* 2. Hiệu ứng khi di chuột vào (Hover) */
            .menu-item:hover {
                background-color: #f0f0f0; /* Đổi nền xám nhạt */
                color: #ff4757;           /* Đổi chữ sang màu đỏ thương hiệu */
                padding-left: 25px;       /* Đẩy chữ sang phải một chút tạo cảm giác động */
            }
        </style>

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
