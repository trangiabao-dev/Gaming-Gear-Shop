<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng - Gaming Gear Shop</title>
        <%-- Link font và css chung của Thịnh --%>
        <link href="https://fonts.googleapis.com/css2?family=Chakra+Petch:wght@400;700&display=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <%-- Header Banner dùng lại class chung --%>
        <div class="banner"> 
            <h1>Gaming Gear Shop</h1>
            <p>Giỏ hàng của bạn</p>
            
            <div class="header-action-group">
                <a href="MainController?action=home" class="btn-header-custom"> 
                    Tiếp tục mua sắm
                </a>
            </div>
        </div>

        <div class="cart-container">
            <%-- Trường hợp giỏ hàng trống --%>
            <c:if test="${empty requestScope.CART_ITEMS}">
                <div style="text-align: center; padding: 50px;">
                    <h2 style="color: #555;">Giỏ hàng của bạn đang trống!</h2>
                    <br>
                    <a href="MainController?action=home" class="btn-header">MUA SẮM NGAY</a>
                </div>
            </c:if>

            <%-- Trường hợp có hàng --%>
            <c:if test="${not empty requestScope.CART_ITEMS}">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="0" />
                        <c:forEach var="item" items="${requestScope.CART_ITEMS}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td style="text-align: left; font-weight: bold;">${item.productName}</td>
                                <td>${item.price} VNĐ</td>
                                <td>${item.quantity}</td>
                                <td style="color: #d0011b; font-weight: bold;">
                                    ${item.price * item.quantity} VNĐ
                                </td>
                                <td>
                                    <a href="MainController?action=Remove&productID=${item.productID}" 
                                       class="btn-remove"
                                       onclick="return confirm('Bạn muốn bỏ sản phẩm này?')">
                                        Xóa
                                    </a>
                                </td>
                            </tr>
                            <%-- Tính tổng tiền --%>
                            <c:set var="total" value="${total + (item.price * item.quantity)}" />
                        </c:forEach>
                    </tbody>
                </table>

                <div class="total-section">
                    <h2>Tổng tiền: <span style="color: #d0011b;">${total} VNĐ</span></h2>
                    
                    <%-- Nút đặt hàng --%>
                    <a href="MainController?action=CheckOut" class="btn-header" 
                       style="background-image: linear-gradient(rgb(228, 84, 100) 0%, rgb(215, 0, 24) 100%); color: white;">
                        XÁC NHẬN ĐẶT HÀNG
                    </a>
                </div>
            </c:if>
        </div>
    </body>
</html>