<%-- 
    Document   : brand-list
    Created on : Mar 20, 2026, 12:48:52 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Danh Sách Thương Hiệu" scope="request"/>
<%@ include file="_layout.jsp" %>

<div style="margin-bottom: 20px; text-align: right;">
    <a href="${pageContext.request.contextPath}/AdminController?action=brand_create_page"
       class="btn-admin-primary">
        <i class="bi bi-plus-lg"></i> Thêm Thương Hiệu
    </a>
</div>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>Mã Thương Hiệu</th>
                <th>Tên Thương Hiệu</th>
                <th>Trạng Thái</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${BRAND_LIST}">
                <tr>
                    <td>${b.brandID}</td>
                    <td>${b.brandName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${b.status}">
                                <span class="badge-active">Đang dùng</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge-inactive">Đã ẩn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/AdminController?action=brand_edit_page&brandID=${b.brandID}"
                           class="btn-admin-edit">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <c:choose>
                            <c:when test="${b.status}">
                                <a href="${pageContext.request.contextPath}/AdminController?action=brand_delete&brandID=${b.brandID}"
                                   class="btn-admin-danger"
                                   onclick="return confirm('Ẩn thương hiệu này?')">
                                    <i class="bi bi-eye-slash"></i> Ẩn
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/AdminController?action=brand_restore&brandID=${b.brandID}"
                                   class="btn-admin-success">
                                    <i class="bi bi-arrow-counterclockwise"></i> Khôi phục
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty BRAND_LIST}">
                <tr>
                    <td colspan="4" class="empty-row">
                        <i class="bi bi-inbox"></i> Chưa có thương hiệu nào
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>