<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Danh Sách Người Dùng" scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px; text-align: right;">
    <a href="${pageContext.request.contextPath}/AdminController?action=user_create_page"
       class="btn-admin-primary">
        <i class="bi bi-plus-lg"></i> Thêm Người Dùng
    </a>
</div>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>Tài khoản</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${USER_LIST}">
                <tr>
                    <td>${u.userID}</td>
                    <td>${u.fullName}</td>
                    <td>${u.email}</td>
                    <td>${u.phone}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.roleID == 1}">
                                <span class="badge-active">Admin</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: var(--bs-blue);">Khách hàng</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${u.status}">
                                <span class="badge-active">Hoạt động</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge-inactive">Đã khóa</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/AdminController?action=user_edit_page&userID=${u.userID}"
                           class="btn-admin-edit">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <a href="${pageContext.request.contextPath}/AdminController?action=user_toggle&userID=${u.userID}"
                           class="btn-admin-danger"
                           onclick="return confirm('${u.status ? 'Khóa' : 'Mở khóa'} tài khoản này?')">
                            <c:choose>
                                <c:when test="${u.status}">
                                    <i class="bi bi-lock"></i> Khóa
                                </c:when>
                                <c:otherwise>
                                    <i class="bi bi-unlock"></i> Mở khóa
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty USER_LIST}">
                <tr>
                    <td colspan="7" class="empty-row">
                        <i class="bi bi-inbox"></i> Chưa có người dùng nào
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>