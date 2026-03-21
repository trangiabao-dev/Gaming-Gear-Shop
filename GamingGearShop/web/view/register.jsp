<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng Ký - Gaming Gear Shop</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
    </head>
    <body class="auth-page">

        <div class="auth-wrapper">
            <div class="auth-container wide-container">

                <%-- BÊN TRÁI --%>
                <div class="auth-left">
                    <h1 class="brand-glow">Gaming Gear Shop</h1>
                    <p class="brand-desc">
                        🚀 Gia nhập cộng đồng Game thủ đỉnh cao.<br><br>
                        Tạo tài khoản ngay hôm nay để nhận các ưu đãi độc quyền
                        và theo dõi đơn hàng dễ dàng.
                    </p>
                </div>

                <%-- BÊN PHẢI --%>
                <div class="auth-right">
                    <h2>Đăng Ký</h2>

                    <%-- Hiển thị lỗi nếu có --%>
                    <c:if test="${not empty ERROR}">
                        <div class="auth-error">
                            <i class="bi bi-exclamation-triangle-fill"></i> ${ERROR}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/MainController"
                          method="post">
                        <input type="hidden" name="action" value="register"/>

                        <%-- Hàng 1: Tên đăng nhập + Họ tên --%>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Tên đăng nhập</label>
                                <input type="text" name="userID" required
                                       placeholder="Nhập ID..."
                                       value="${param.userID}"
                                       oninvalid="this.setCustomValidity('Vui lòng nhập ID!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                            <div class="form-group">
                                <label>Họ và tên</label>
                                <input type="text" name="fullName" required
                                       placeholder="Họ tên đầy đủ..."
                                       value="${param.fullName}"
                                       oninvalid="this.setCustomValidity('Vui lòng nhập Họ và tên!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                        </div>

                        <%-- Hàng 2: Mật khẩu + Xác nhận --%>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" name="password" required
                                       placeholder="Nhập mật khẩu..."
                                       oninvalid="this.setCustomValidity('Vui lòng nhập mật khẩu!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                            <div class="form-group">
                                <label>Xác nhận mật khẩu</label>
                                <input type="password" name="confirm" required
                                       placeholder="Nhập lại mật khẩu..."
                                       oninvalid="this.setCustomValidity('Vui lòng xác nhận mật khẩu!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                        </div>

                        <%-- Hàng 3: SĐT + Địa chỉ --%>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <input type="tel" name="phone" required
                                       placeholder="Nhập số điện thoại (10 số)..."
                                       value="${param.phone}"
                                       pattern="[0-9]{10}"
                                       title="Số điện thoại phải gồm đúng 10 chữ số"
                                       oninvalid="this.setCustomValidity('Vui lòng nhập đúng 10 số!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ nhận hàng</label>
                                <input type="text" name="address" required
                                       placeholder="Nhập địa chỉ chi tiết..."
                                       value="${param.address}"
                                       oninvalid="this.setCustomValidity('Vui lòng nhập địa chỉ!')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                        </div>

                        <%-- Hàng 4: Email (1 cột full width) --%>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" required
                                   placeholder="Nhập email của bạn..."
                                   value="${param.email}"
                                   oninvalid="this.setCustomValidity('Vui lòng nhập email!')"
                                   oninput="this.setCustomValidity('')">
                        </div>

                        <button type="submit" class="btn-primary">
                            TẠO TÀI KHOẢN
                        </button>

                    </form>

                    <div class="auth-extra">
                        Đã có tài khoản?
                        <a href="${pageContext.request.contextPath}/MainController?action=login">
                            Đăng nhập tại đây
                        </a>
                    </div>

                    <div class="auth-home">
                        <a href="${pageContext.request.contextPath}/MainController?action=home"
                           class="home-link">
                            ← Quay về Trang chủ
                        </a>
                    </div>

                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>