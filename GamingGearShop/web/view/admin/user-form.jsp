<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle"
       value="${empty EDIT_USER ? 'Thêm Người Dùng' : 'Sửa Người Dùng'}"
       scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/AdminController?action=user_list"
       class="btn-admin-secondary">
        <i class="bi bi-arrow-left"></i> Quay lại
    </a>
</div>

<c:if test="${not empty ERROR}">
    <div class="alert-error">
        <i class="bi bi-exclamation-triangle-fill"></i> ${ERROR}
    </div>
</c:if>

<div class="form-card">
    <form action="${pageContext.request.contextPath}/AdminController" method="POST">
        <input type="hidden" name="action"
               value="${empty EDIT_USER ? 'user_insert' : 'user_update'}">

        <c:if test="${not empty EDIT_USER}">
            <input type="hidden" name="userID" value="${EDIT_USER.userID}">
        </c:if>

        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Tài khoản (ID)</label>
                <c:choose>
                    <c:when test="${empty EDIT_USER}">
                        <input type="text" name="userID"
                               class="form-control-admin"
                               placeholder="VD: user01" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control-admin"
                               value="${EDIT_USER.userID}" disabled>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Họ và tên</label>
                <input type="text" name="fullName"
                       class="form-control-admin"
                       value="${EDIT_USER.fullName}" required
                       placeholder="Nhập họ tên...">
            </div>
        </div>

        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Email</label>
                <input type="email" name="email"
                       class="form-control-admin"
                       value="${EDIT_USER.email}"
                       placeholder="Nhập email...">
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Số điện thoại</label>
                <input type="text" name="phone"
                       class="form-control-admin"
                       value="${EDIT_USER.phone}"
                       placeholder="Nhập số điện thoại...">
            </div>
        </div>

        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Địa chỉ</label>
                <input type="text" name="address"
                       class="form-control-admin"
                       value="${EDIT_USER.address}"
                       placeholder="Nhập địa chỉ...">
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Vai trò</label>
                <select name="roleID" class="form-control-admin">
                    <option value="2" ${EDIT_USER.roleID == 2 ? 'selected' : ''}>Khách hàng</option>
                    <option value="1" ${EDIT_USER.roleID == 1 ? 'selected' : ''}>Admin</option>
                </select>
            </div>
        </div>

        <c:if test="${empty EDIT_USER}">
            <div class="form-group-admin">
                <label class="form-label-admin">Mật khẩu</label>
                <input type="password" name="password"
                       class="form-control-admin"
                       placeholder="Nhập mật khẩu..." required>
            </div>
        </c:if>

        <div class="form-actions">
            <button type="submit" class="btn-admin-primary">
                <i class="bi bi-save"></i>
                ${empty EDIT_USER ? 'Lưu' : 'Cập Nhật'}
            </button>
            <a href="${pageContext.request.contextPath}/AdminController?action=user_list"
               class="btn-admin-secondary">
                <i class="bi bi-x"></i> Hủy
            </a>
        </div>
    </form>
</div>

<%@ include file="_layout_end.jsp" %>