<%-- 
    Document   : admin
    Created on : Mar 8, 2026, 10:04:48 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Sản Phẩm - Bản Thô</title>
    </head>
    <body>

        <h2>Khu Vực Quản Trị Hệ Thống</h2>
        <p>Xin chào Admin: <b>${sessionScope.LOGIN_USER.fullName}</b></p>

        <p><a href="MainController?action=home">🔙 Về Trang Cửa Hàng</a></p>

        <hr>

        <h3>Danh Sách Sản Phẩm</h3>
        <p><a href="AdminController?action=create_page">[+] Thêm Sản Phẩm Mới</a></p>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>Mã SP</th>
                    <th>Hình ảnh</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Giá Bán</th>
                    <th>Tồn Kho</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope.ADMIN_PRODUCT_LIST}">
                    <tr>
                        <td>${item.productID}</td>

                        <td><img src="${item.imageURL}" alt="Ảnh SP" width="60" height="60"></td>

                        <td>${item.productName}</td>
                        <td>${item.priceFormat} VNĐ</td>
                        <td>${item.quantity}</td>

                        <td>
                            <a href="#">Sửa</a> | 
                            <a href="#">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty requestScope.ADMIN_PRODUCT_LIST}">
            <p style="color: red;">Hiện tại không có sản phẩm nào trong kho!</p>
        </c:if>
            
    </body>
</html>
