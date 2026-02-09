<%-- 
    Document   : Login
    Created on : Feb 10, 2026, 12:26:19 AM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Nhập - Gaming Gear Shop</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="header">
            <div class="container">
                <h1>Gaming <span>Gear</span> Shop</h1>
            </div>
        </div>

        <div class="login-box">
            <h2>Đăng Nhập</h2>
            
            <p style="color: red; text-align: center; font-weight: bold;">${requestScope.ERROR}</p>

            <form action="LoginController" method="POST">
                
                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="text" name="userID" class="form-control"required>
                </div>

                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <button type="submit" class="btn-login">ĐĂNG NHẬP NGAY</button>

                <div style="text-align: center; margin-top: 20px;">
                    <a href="welcome.jsp" style="text-decoration: none; color: #666; font-size: 14px;">
                        ← Quay lại trang chủ
                    </a>
                </div>

            </form>
        </div>

    </body>
</html>
