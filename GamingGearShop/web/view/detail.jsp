<%-- 
    Document   : detail
    Created on : Feb 15, 2026, 8:44:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="back-container"> 
            <a href="MainController?action=home" class="back-link">
                Quay lại trang chủ
            </a>
        </div>

        <div class="product-box"> 
            <div class="left-side"> 
                <img src="${detail.imageURL}" alt="${detail.productName}" class="big-image"/>
            </div>

            <div class="right-side">
                <h1 class="header-name">${detail.productName}</h1>

                <p class="product-meta">
                    Mã Sản Phẩm: <b>${detail.productID}</b>
                    Kho: <span class="stock-status">${detail.quantity}</span> 
                </p>
            </div>

            <div class="money"> 
                ${detail.priceFormat} VNĐ
            </div>

            <div class="description">
                <span class="description-title">Mô tả sản phầm:</span><br/>
                ${detail.description}
            </div>

            <form action="MainController" method="POST" class="buying-form">
                <input type="hidden" name="action" value="addToCart" />
                <input type="hidden" name="productID" value="${detail.productID}" />

                <div class="quantity-section"> 
                    <label for="qty">Số lượng: </label>
                    <input type="number" name="quantity" id="qty" value="1" 
                           min="1" max="${detail.quantity}" class="input-number" required="required"
                           oninvalid="this.setCustomValidity('Số lượng không được để trống!')" 
                           oninput="this.setCustomValidity('')"/>
                </div>

                <div class="buttons"> 
                    <button type="submit" class="button_cart">
                        THÊM VÀO GIỎ
                    </button>
                </div>
        </div>
    </form>
</body>
</html>
