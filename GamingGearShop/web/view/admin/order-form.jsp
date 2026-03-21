<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Chi Tiết Đơn Hàng" scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/AdminController?action=order_list"
       class="btn-admin-secondary">
        <i class="bi bi-arrow-left"></i> Quay lại
    </a>
</div>

<div class="form-card">
    <h3 style="margin-bottom: 16px;">Đơn hàng #${ORDER_DETAIL.orderID}</h3>

    <div class="form-row-2col">
        <div>
            <p><b>Khách hàng:</b> ${ORDER_DETAIL.userID}</p>
            <p><b>Ngày đặt:</b> ${ORDER_DETAIL.orderDate}</p>
            <p><b>Tổng tiền:</b> ${ORDER_DETAIL.total} ₫</p>
        </div>
        <div>
            <p><b>Trạng thái:</b>
                <c:choose>
                    <c:when test="${ORDER_DETAIL.status == 0}">Đã hủy</c:when>
                    <c:when test="${ORDER_DETAIL.status == 1}">Đang xử lý</c:when>
                    <c:when test="${ORDER_DETAIL.status == 2}">Đang giao</c:when>
                    <c:when test="${ORDER_DETAIL.status == 3}">Hoàn thành</c:when>
                </c:choose>
            </p>
        </div>
    </div>

    <hr>
    <h4>Danh sách sản phẩm</h4>
    <table class="admin-table">
        <thead>
            <tr>
                <th>Mã SP</th>
                <th>Đơn giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="detail" items="${ORDER_DETAILS}">
                <tr>
                    <td>${detail.productID}</td>
                    <td>${detail.price} ₫</td>
                    <td>${detail.quantity}</td>
                    <td>${detail.price * detail.quantity} ₫</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>