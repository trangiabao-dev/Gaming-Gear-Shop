<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán - Gaming Gear Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/payment.css" rel="stylesheet">
</head>
<body>

    <%-- NAVBAR --%>
    <nav class="navbar navbar-expand-lg fixed-top floating-navbar">
        <div class="container-fluid px-4 px-lg-5">
            <a class="navbar-brand brand-boutique" href="${pageContext.request.contextPath}/MainController?action=home">
                Gaming Gear Shop<span></span>
            </a>
        </div>
    </nav>

    <div class="payment-wrapper">
        <div class="payment-card">

            <%-- HEADER --%>
            <div class="payment-header">
                <div class="payment-icon">
                    <i class="bi bi-qr-code-scan"></i>
                </div>
                <h2 class="payment-title">Thanh Toán Đơn Hàng</h2>
                <p class="payment-subtitle">Quét mã QR bằng ứng dụng ngân hàng để thanh toán</p>
            </div>

            <%-- TỔNG TIỀN --%>
            <div class="payment-amount">
                <span class="amount-label">Số tiền cần thanh toán</span>
                <span class="amount-value">${PAYMENT_TOTAL} ₫</span>
            </div>

            <%-- QR CODE --%>
            <div class="qr-wrapper">
                <div class="qr-box">
                    <img src="https://api.qrserver.com/v1/create-qr-code/?size=220x220&data=GAMING+GEAR+SHOP+THANH+TOAN+${PAYMENT_TOTAL}+VND"
                         alt="QR Code" class="qr-img"/>
                </div>
                <p class="qr-note">
                    <i class="bi bi-info-circle me-1"></i>
                    Mở app ngân hàng → Chọn QR Pay → Quét mã
                </p>
            </div>

            <%-- THÔNG TIN CHUYỂN KHOẢN --%>
            <div class="bank-info-box">
                <h4 class="bank-info-title">
                    <i class="bi bi-bank me-2"></i>Thông tin chuyển khoản
                </h4>
                <div class="bank-row">
                    <span class="bank-label">Ngân hàng</span>
                    <span class="bank-value">Vietcombank</span>
                </div>
                <div class="bank-row">
                    <span class="bank-label">Số tài khoản</span>
                    <span class="bank-value copy-text">1234 5678 9012</span>
                </div>
                <div class="bank-row">
                    <span class="bank-label">Chủ tài khoản</span>
                    <span class="bank-value">GAMING GEAR SHOP</span>
                </div>
                <div class="bank-row">
                    <span class="bank-label">Số tiền</span>
                    <span class="bank-value highlight">${PAYMENT_TOTAL} ₫</span>
                </div>
                <div class="bank-row">
                    <span class="bank-label">Nội dung CK</span>
                    <span class="bank-value highlight">THANHTOAN ${PAYMENT_TOTAL}</span>
                </div>
            </div>

            <%-- ĐẾM NGƯỢC --%>
            <div class="countdown-box">
                <i class="bi bi-clock me-2"></i>
                Thời gian thanh toán còn lại:
                <span id="timer" class="countdown-timer">05:00</span>
            </div>

            <%-- NÚT XÁC NHẬN --%>
            <a href="${pageContext.request.contextPath}/MainController?action=confirmPayment"
               class="btn-confirm">
                <i class="bi bi-check-circle-fill me-2"></i>
                TÔI ĐÃ THANH TOÁN XONG
            </a>

            <a href="${pageContext.request.contextPath}/MainController?action=viewCart"
               class="btn-back">
                <i class="bi bi-arrow-left me-1"></i> Quay lại giỏ hàng
            </a>

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        var time = 300;
        var interval = setInterval(function () {
            time--;
            var m = Math.floor(time / 60);
            var s = time % 60;
            document.getElementById('timer').textContent =
                (m < 10 ? '0' : '') + m + ':' + (s < 10 ? '0' : '') + s;
            if (time <= 0) {
                clearInterval(interval);
                alert('Hết thời gian thanh toán! Vui lòng đặt lại đơn hàng.');
                window.location.href = '${pageContext.request.contextPath}/MainController?action=viewCart';
            }
        }, 1000);
    </script>
</body>
</html>