<%-- 
    Document   : index
    Created on : Feb 8, 2026, 4:25:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/css2?family=Chakra+Petch:wght@400;700&displSay=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="auth-section">
            <%-- LOGIN --%>
            <c:if test="${sessionScope.LOGIN_USER == null}">
                <a href="view/login.jsp" class="btn-header btn-header-login">
                    <i class="fa-solid fa-user"></i> 
                    Đăng nhập
                </a>
            </c:if>
            <%-- LOGOUT --%>
            <c:if test="${sessionScope.LOGIN_USER != null}">

                <!-- KHỐI XIN CHÀO (NỀN ĐEN – CHỮ VÀNG) -->
                <div class="btn-header btn-header-logout">
                    <span class="user-name">
                        Xin chào, ${sessionScope.LOGIN_USER.fullName}
                    </span>
                </div>

                <!-- NÚT THOÁT (GIỐNG NÚT ĐĂNG NHẬP) -->
                <a href="LogoutController" class="btn-header btn-header-login">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    Thoát
                </a>

            </c:if>
        </div>

        <div class="banner"> 
            <h1>Gaming Gear Shop</h1>
            <p>Chuyên chuột, bàn phím, tai nghe chính hãng</p>
        </div>

        <div class="product-list"> 

            <div class="product">
                <img src="images/Mouse-Wireless-X7.jpg" alt="Chuột Không Dây Máy Tính Bluetooth X7 Wireless" width="200"/>
                <h3>Chuột Không Dây Máy Tính Bluetooth X7 Wireless Pin Sạc Kết Nối 2 Chế Độ Cho Mọi Thiết Bị Máy Tính, Laptop</h3>
                <p>Giá: 180.000 VNĐ</p>
            </div>

            <div class="product"> 
                <img src="images/Mouse-Free-Wolf-X8.jpg"
                     alt="Chuột Không Dây Free Wolf X8"
                     width="200"/>

                <h3>Chuột Không Dây Gaming Wolf X8 LED 7 Màu RGB, Pin Có Thể Sạc Dùng Cực Lâu, Chuột Chơi Game Không Dây</h3>
                <p>Giá: 149.000 VNĐ</p>
            </div>

            <div class="product"> 
                <img src="images/Mouse-Forwarder-Wireless.jpg" alt="Chuột chơi game Forwarder Wireless" width="200"/>
                <h3>Chuột chơi game Forwarder Wireless Tắt tiếng Máy tính cơ bên ngoài USB Văn phòng dạ quang có dây</h3>
                <p>Giá: 225.000 VNĐ</p>
            </div>

            <div class="product"> 
                <img src="images/Mouse-Free-Wolf-X8.jpg" alt="Chuột Không Dây Free Wolf X8"/ width="200"/>
                <h3>Chuột Không Dây Free Wolf X8 - Thiết Kế Gaming Pin Sạc, Led RGB 7 Màu, Kết Nối Không Dây Usb 2.4G</h3>
                <p>Giá: 189.000 VNĐ</p>
            </div>

            <c:forEach items="${listProduct}" var="product" end="3">
                <div class="product">
                    <img src="${product.imageURL}" alt="${product.productName}" width="100%"/>

                    <h3>${product.productName}</h3>

                    <p>Giá: ${product.priceFormat} VNĐ</p>
                </div>
            </c:forEach>

        </div>

        <ul>
            <c:forEach items="${listCategory}" var="category">
                <li>
                    <form action="MainController" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="home"/>
                        <input type="hidden" name="catID" value="${category.catID}" />
                        <button type="submit" style="background:none; border:none; color:blue; cursor:pointer; text-decoration:underline;">
                            ${category.catName}
                        </button>
                    </form>
                </li>
            </c:forEach>
        </ul>

    </body>
</html>
