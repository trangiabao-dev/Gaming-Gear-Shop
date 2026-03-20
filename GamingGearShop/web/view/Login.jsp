<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng Nhập - Gaming Gear Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css">
        <link href="css/auth.css" rel="stylesheet">
    </head>
    <body>

        <div class="auth-wrapper">
            <div class="auth-container">

                <div class="auth-left">
                    <h1 class="brand-glow">Gaming Gear Shop</h1>
                    <p class="brand-desc">
                        🚀 Bứt phá hiệu năng. Làm chủ mọi trận đấu.<br><br>
                        Trang bị thiết bị gaming cao cấp dành cho game thủ thực thụ.
                    </p>
                </div>

                <div class="auth-right">

                    <h2>Đăng Nhập</h2>

                    <form action="${pageContext.request.contextPath}/MainController" method="post">
                        <input type="hidden" name="action" value="login" />

                        <div class="form-group">
                            <label>Tài khoản</label>
                            <input type="text" name="userID" required placeholder="Nhập tên đăng nhập..." required
                                   oninvalid="this.setCustomValidity('Vui lòng nhập Tên Đăng Nhập!')" 
                                   oninput="this.setCustomValidity('')"/><br/>
                        </div>

                        <div class="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" name="password" required placeholder="Nhập mật khẩu..." 
                                   oninvalid="this.setCustomValidity('Vui lòng nhập mật khẩu!')" 
                                   oninput="this.setCustomValidity('')"/><br/>
                        </div>

                        <button type="submit" class="btn-primary">
                            ĐĂNG NHẬP
                        </button>
                    </form>

                    <div class="auth-extra">
                        Chưa có tài khoản?
                        <a href="${pageContext.request.contextPath}/MainController?action=register">
                            Đăng ký
                        </a>
                    </div>

                    <div class="auth-home">
                        <a href="${pageContext.request.contextPath}/MainController?action=home" class="home-link">
                            ← Quay về Trang chủ
                        </a>
                    </div>

                    <div class="auth-extra">
                        <a href="${pageContext.request.contextPath}/MainController?action=forgotPassword">
                            <i class="bi bi-key-fill me-1"></i>Quên mật khẩu?
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>