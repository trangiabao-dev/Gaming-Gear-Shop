<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Quản Lý Đánh Giá" scope="request"/>
<%@ include file="_layout.jsp" %>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Người dùng</th>
                <th>Sản phẩm</th>
                <th>Sao</th>
                <th>Nội dung</th>
                <th>Ngày</th>
                <th>Xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="fb" items="${FEEDBACK_LIST}">
                <tr>
                    <td>${fb.feedID}</td>
                    <td>${fb.userID}</td>
                    <td>${fb.productID}</td>
                    <td><c:forEach begin="1" end="${fb.rating}">⭐</c:forEach></td>
                    <td>${fb.content}</td>
                    <td>${fb.date}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/FeedbackController?action=deleteFeedback&feedID=${fb.feedID}"
                           class="btn-admin-danger"
                           onclick="return confirm('Xóa đánh giá này?')">
                            <i class="bi bi-trash3"></i> Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty FEEDBACK_LIST}">
                <tr><td colspan="7" class="empty-row"><i class="bi bi-inbox"></i> Chưa có đánh giá nào</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>