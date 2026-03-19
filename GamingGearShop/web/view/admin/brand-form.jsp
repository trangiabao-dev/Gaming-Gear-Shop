<%-- 
    Document   : brand-form
    Created on : Mar 20, 2026, 12:49:07 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle"
       value="${empty EDIT_BRAND ? 'Thêm Thương Hiệu' : 'Sửa Thương Hiệu'}"
       scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/AdminController?action=brand_list"
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
               value="${empty EDIT_BRAND ? 'brand_insert' : 'brand_update'}">

        <div class="form-group-admin">
            <label class="form-label-admin">Mã Thương Hiệu</label>
            <c:choose>
                <c:when test="${empty EDIT_BRAND}">
                    <input type="text" name="brandID"
                           class="form-control-admin"
                           placeholder="VD: BR05" required>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control-admin"
                           value="${EDIT_BRAND.brandID}" disabled>
                    <input type="hidden" name="brandID" value="${EDIT_BRAND.brandID}">
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group-admin">
            <label class="form-label-admin">Tên Thương Hiệu</label>
            <input type="text" name="brandName"
                   class="form-control-admin"
                   value="${EDIT_BRAND.brandName}" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-admin-primary">
                <i class="bi bi-save"></i>
                ${empty EDIT_BRAND ? 'Lưu' : 'Cập Nhật'}
            </button>
            <a href="${pageContext.request.contextPath}/AdminController?action=brand_list"
               class="btn-admin-secondary">
                <i class="bi bi-x"></i> Hủy
            </a>
        </div>

    </form>
</div>

<%@ include file="_layout_end.jsp" %>