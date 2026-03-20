<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên Mật Khẩu - Gaming Gear Shop</title>
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
                    🔑 Quên mật khẩu?<br><br>
                    Nhập email đã đăng ký, chúng tôi sẽ gửi mã OTP để đặt lại mật khẩu cho bạn.
                </p>
            </div>
            <div class="auth-right">
                <h2>Quên Mật Khẩu</h2>

                <c:if test="${not empty ERROR}">
                    <div class="alert alert-danger mt-2">
                        <i class="bi bi-exclamation-circle me-2"></i>${ERROR}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/MainController" method="POST">
                    <input type="hidden" name="action" value="sendOTP"/>
                    <div class="form-group">
                        <label>Email đã đăng ký</label>
                        <input type="email" name="email" required 
                               placeholder="Nhập email của bạn..."
                               oninvalid="this.setCustomValidity('Vui lòng nhập email!')"
                               oninput="this.setCustomValidity('')"/>
                    </div>
                    <button type="submit" class="btn-primary">
                        <i class="bi bi-send-fill me-2"></i>GỬI MÃ OTP
                    </button>
                </form>

                <div class="auth-extra mt-3">
                    <a href="${pageContext.request.contextPath}/MainController?action=login">
                        ← Quay lại đăng nhập
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>