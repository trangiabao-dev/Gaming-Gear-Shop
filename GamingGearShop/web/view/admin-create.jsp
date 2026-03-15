<%-- 
    Document   : admin-create.jsp
    Created on : Mar 11, 2026, 9:27:52 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sản Phẩm Mới</title>
    </head>
    <body>
        <h2>Thêm Sản Phẩm Mới</h2>
        <p><a href="AdminController?action=list">🔙 Quay lại danh sách</a></p>
        <hr>

        <p style="color: red; font-weight: bold;">${requestScope.ERROR}</p>

        <form action="AdminController" method="POST">

            <input type="hidden" name="action" value="insert">

            <table cellpadding="5">
                <tr>
                    <td>Mã Sản Phẩm (ID):</td>
                    <td><input type="text" name="productID" required placeholder="VD: P010"></td>
                </tr>
                <tr>
                    <td>Tên Sản Phẩm:</td>
                    <td><input type="text" name="productName" required></td>
                </tr>
                <tr>
                    <td>Giá Bán (VNĐ):</td>
                    <td><input type="number" name="price" required min="0"></td>
                </tr>
                <tr>
                    <td>Số Lượng Tồn Kho:</td>
                    <td><input type="number" name="quantity" required min="1"></td>
                </tr>
                <tr>
                    <td>Mã Danh Mục:</td>
                    <td><input type="text" name="catID" value="CAT01" required></td>
                </tr>
                <tr>
                    <td>Mã Thương Hiệu:</td>
                    <td><input type="text" name="brandID" value="BR01" required></td>
                </tr>
                <tr>
                    <td>Link Hình Ảnh:</td>
                    <td><input type="text" name="imageURL" placeholder="http://..."></td>
                </tr>
                <tr>
                    <td valign="top">Mô Tả Sản Phẩm:</td>
                    <td><textarea name="description" rows="4" cols="30"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit">💾 Lưu Sản Phẩm</button>
                        <button type="reset">Xóa làm lại</button>
                    </td>
                </tr>
            </table>
        </form>

    </body>
</html>