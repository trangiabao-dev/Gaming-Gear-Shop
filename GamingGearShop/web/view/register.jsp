<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Ký - Gaming Gear Shop</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body> 
        <div class="banner">
            <h1>Gaming Gear Shop</h1>
            <p>Chuyên chuột, bàn phím, tai nghe chính hãng</p>
        </div>

        <div class="login-box" style="margin-top: 20px;"> <h2>Đăng Ký Tài Khoản</h2>



            <form action="${pageContext.request.contextPath}/MainController" method="POST">

                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="text" name="userID" class="form-control" 
                           placeholder="Nhập ID muốn tạo..." 
                           value="${param.userID}" 
                           required>
                </div>
                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" name="password" class="form-control" 
                           placeholder="Nhập mật khẩu..." required>
                </div>

                <div class="form-group">
                    <label>Xác nhận mật khẩu</label>
                    <input type="password" name="confirm" class="form-control" 
                           placeholder="Nhập lại mật khẩu khớp ở trên..." required>
                </div>

                <div class="form-group">
                    <label>Họ và tên</label>
                    <input type="text" name="fullName" class="form-control" 
                           placeholder="Nhập họ tên đầy đủ..." 
                           value="${param.fullName}" 
                           required>
                </div>

                <div class="form-group">
                    <label>Số điện thoại</label>
                    <input type="tel" name="phone" class="form-control" 
                           placeholder="Nhập số điện thoại (10 số)..." 
                           value="${param.phone}" 
                           pattern="[0-9]{10}" 
                           title="Số điện thoại phải gồm 10 chữ số"
                           required>
                </div>

                <div class="form-group">
                    <label>Địa chỉ nhận hàng</label>
                    <input type="text" name="address" class="form-control" 
                           placeholder="Nhập địa chỉ chi tiết..." 
                           value="${param.address}" 
                           required>
                </div>

                <input type="hidden" name="action" value="register"/>

                <button type="submit" class="btn-submit">ĐĂNG KÝ NGAY</button>

            </form>

            <div style="margin-top: 20px; text-align: center;">
                <span style="color: #666;">Đã có tài khoản? </span>
                <a href="${pageContext.request.contextPath}/MainController?action=login" 
                   style="color: #d0011b; text-decoration: none; font-weight: bold;">
                    Đăng nhập tại đây
                </a>
            </div>

        </div>

    </body>
</html>