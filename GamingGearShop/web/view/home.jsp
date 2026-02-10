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
        <link href="https://fonts.googleapis.com/css2?family=Chakra+Petch:wght@400;700&display=swap" rel="stylesheet"><!-- comment -->
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

                    <p>Giá: ${product.price} VNĐ</p>
                </div>
            </c:forEach>
        </div>

    </body>
</html>
