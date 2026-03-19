<%-- 
    Document   : category-list
    Created on : Mar 20, 2026, 12:48:19 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Danh Sách Danh Mục" scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px; text-align: right;">
    <a href="${pageContext.request.contextPath}/AdminController?action=category_create_page"
       class="btn-admin-primary">
        <i class="bi bi-plus-lg"></i> Thêm Danh Mục
    </a>
</div>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>Mã Danh Mục</th>
                <th>Tên Danh Mục</th>
                <th>Trạng Thái</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cat" items="${CATEGORY_LIST}">
                <tr>
                    <td>${cat.catID}</td>
                    <td>${cat.catName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${cat.status}">
                                <span class="badge-active">Đang dùng</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge-inactive">Đã ẩn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/AdminController?action=category_edit_page&catID=${cat.catID}"
                           class="btn-admin-edit">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <c:choose>
                            <c:when test="${cat.status}">
                                <a href="${pageContext.request.contextPath}/AdminController?action=category_delete&catID=${cat.catID}"
                                   class="btn-admin-danger"
                                   onclick="return confirm('Ẩn danh mục này?')">
                                    <i class="bi bi-eye-slash"></i> Ẩn
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/AdminController?action=category_restore&catID=${cat.catID}"
                                   class="btn-admin-success">
                                    <i class="bi bi-arrow-counterclockwise"></i> Khôi phục
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty CATEGORY_LIST}">
                <tr>
                    <td colspan="4" class="empty-row">
                        <i class="bi bi-inbox"></i> Chưa có danh mục nào
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>