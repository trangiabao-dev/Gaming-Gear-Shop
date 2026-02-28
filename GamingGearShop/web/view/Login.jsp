<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Nhập - Gaming Gear Shop</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body> 
        <div class="banner">
            <h1>Gaming Gear Shop</h1>
            <p>Chuyên chuột, bàn phím, tai nghe chính hãng</p>
        </div>

        <div class="login-box">

            <h2>Đăng Nhập</h2>

            <form action="${pageContext.request.contextPath}/LoginController" method="POST">

                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="text" name="userID" class="form-control" 
                           placeholder="Nhập ID..." 
                           value="${param.userID}" 
                           required
                           oninvalid="this.setCustomValidity('Mật khẩu không được để trống!')" 
                           oninput="this.setCustomValidity('')">
                </div>

                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" name="password" class="form-control" 
                           placeholder="Nhập mật khẩu..." required>
                    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
                </div>

                <button type="submit" class="btn-submit">ĐĂNG NHẬP NGAY</button>

            </form>
            <div style="margin-top: 20px; text-align: center;">
                <a href="${pageContext.request.contextPath}/MainController" class="back-link">
                    ← Quay lại trang chủ
                </a>
            </div>
        </div>
    </body>
</html>