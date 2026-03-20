<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xác Nhận OTP - Gaming Gear Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
</head>
<body class="auth-page">
    <div class="auth-wrapper">
        <div class="auth-container">
            <div class="auth-left">
                <h1 class="brand-glow">Gaming Gear Shop</h1>
                <p class="brand-desc">
                    ✉️ Kiểm tra email của bạn.<br><br>
                    Nhập mã OTP 6 số đã được gửi về email và đặt mật khẩu mới.
                </p>
            </div>
            <div class="auth-right">
                <h2>Xác Nhận OTP</h2>

                <c:if test="${not empty ERROR}">
                    <div class="alert alert-danger mt-2">
                        <i class="bi bi-exclamation-circle me-2"></i>${ERROR}
                    </div>
                </c:if>
                <c:if test="${not empty SUCCESS}">
                    <div class="alert alert-success mt-2">
                        <i class="bi bi-check-circle me-2"></i>${SUCCESS}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/MainController" method="POST">
                    <input type="hidden" name="action" value="resetPassword"/>

                    <div class="form-group">
                        <label>Mã OTP</label>
                        <input type="text" name="otp" required
                               placeholder="Nhập mã 6 số..." 
                               maxlength="6"
                               oninvalid="this.setCustomValidity('Vui lòng nhập mã OTP!')"
                               oninput="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu mới</label>
                        <input type="password" name="newPassword" required
                               placeholder="Nhập mật khẩu mới..."
                               oninvalid="this.setCustomValidity('Vui lòng nhập mật khẩu mới!')"
                               oninput="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <label>Xác nhận mật khẩu</label>
                        <input type="password" name="confirmPassword" required
                               placeholder="Nhập lại mật khẩu..."
                               oninvalid="this.setCustomValidity('Vui lòng xác nhận mật khẩu!')"
                               oninput="this.setCustomValidity('')"/>
                    </div>

                    <button type="submit" class="btn-primary">
                        <i class="bi bi-shield-lock-fill me-2"></i>ĐẶT LẠI MẬT KHẨU
                    </button>
                </form>

                <div class="auth-extra mt-3">
                    <a href="${pageContext.request.contextPath}/MainController?action=forgotPassword">
                        ← Gửi lại mã OTP
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>