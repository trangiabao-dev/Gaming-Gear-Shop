<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng Nhập - Gaming Gear Shop</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body> 
        <div style="text-align: center; margin-top: 40px;">
            <a href="${pageContext.request.contextPath}/MainController" style="text-decoration: none;">
                <h1 style="color: #d0011b; margin: 0; font-family: sans-serif;">
                    Gaming <span>Gear</span> Shop
                </h1>
            </a>
        </div>

        <div class="login-box">

            <h2>Đăng Nhập</h2>

            <c:if test="${not empty requestScope.ERROR}">
                <div style="color: red; text-align: center; font-weight: bold; margin-bottom: 15px;">
                    ${requestScope.ERROR}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/LoginController" method="POST">
                
                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="text" name="userID" class="form-control" 
                           placeholder="Nhập ID..." 
                           value="${param.userID}" 
                           required>
                </div>

                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" name="password" class="form-control" 
                           placeholder="Nhập mật khẩu..." required>
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