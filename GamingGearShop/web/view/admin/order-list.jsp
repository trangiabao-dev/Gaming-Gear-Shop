<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Danh Sách Đơn Hàng" scope="request"/>
<%@ include file="_layout.jsp" %>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>Mã đơn</th>
                <th>Khách hàng</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${ADMIN_ORDER_LIST}">
                <tr>
                    <td>#${order.orderID}</td>
                    <td>${order.userID}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.total} ₫</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status == 0}">
                                <span class="badge-inactive">Đã hủy</span>
                            </c:when>
                            <c:when test="${order.status == 1}">
                                <span class="badge-active" style="background:#f59e0b">Đang xử lý</span>
                            </c:when>
                            <c:when test="${order.status == 2}">
                                <span class="badge-active" style="background:#3b82f6">Đang giao</span>
                            </c:when>
                            <c:when test="${order.status == 3}">
                                <span class="badge-active">Hoàn thành</span>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/AdminController?action=order_detail&orderID=${order.orderID}"
                           class="btn-admin-edit">
                            <i class="bi bi-eye"></i> Xem
                        </a>
                        <c:if test="${order.status == 1}">
                            <a href="${pageContext.request.contextPath}/AdminController?action=update_order_status&orderID=${order.orderID}&status=2"
                               class="btn-admin-secondary">
                                <i class="bi bi-truck"></i> Đang giao
                            </a>
                        </c:if>
                        <c:if test="${order.status == 2}">
                            <a href="${pageContext.request.contextPath}/AdminController?action=update_order_status&orderID=${order.orderID}&status=3"
                               class="btn-admin-secondary">
                                <i class="bi bi-check-circle"></i> Hoàn thành
                            </a>
                        </c:if>
                        <c:if test="${order.status != 0}">
                            <a href="${pageContext.request.contextPath}/AdminController?action=update_order_status&orderID=${order.orderID}&status=0"
                               class="btn-admin-danger"
                               onclick="return confirm('Hủy đơn hàng này?')">
                                <i class="bi bi-x-circle"></i> Hủy
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty ADMIN_ORDER_LIST}">
                <tr>
                    <td colspan="6" class="empty-row">
                        <i class="bi bi-inbox"></i> Chưa có đơn hàng nào
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>