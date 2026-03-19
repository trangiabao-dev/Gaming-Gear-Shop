<%-- 
    Document   : category-form
    Created on : Mar 20, 2026, 12:48:37 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle"
       value="${empty EDIT_CATEGORY ? 'Thêm Danh Mục' : 'Sửa Danh Mục'}"
       scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/AdminController?action=category_list"
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
               value="${empty EDIT_CATEGORY ? 'category_insert' : 'category_update'}">

        <div class="form-group-admin">
            <label class="form-label-admin">Mã Danh Mục</label>
            <c:choose>
                <c:when test="${empty EDIT_CATEGORY}">
                    <input type="text" name="catID"
                           class="form-control-admin"
                           placeholder="VD: CAT05" required>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control-admin"
                           value="${EDIT_CATEGORY.catID}" disabled>
                    <input type="hidden" name="catID" value="${EDIT_CATEGORY.catID}">
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group-admin">
            <label class="form-label-admin">Tên Danh Mục</label>
            <input type="text" name="catName"
                   class="form-control-admin"
                   value="${EDIT_CATEGORY.catName}" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-admin-primary">
                <i class="bi bi-save"></i>
                ${empty EDIT_CATEGORY ? 'Lưu' : 'Cập Nhật'}
            </button>
            <a href="${pageContext.request.contextPath}/AdminController?action=category_list"
               class="btn-admin-secondary">
                <i class="bi bi-x"></i> Hủy
            </a>
        </div>

    </form>
</div>

<%@ include file="_layout_end.jsp" %>