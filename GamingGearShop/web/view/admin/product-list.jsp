<%-- 
    Document   : product-list
    Created on : Mar 18, 2026, 11:46:18 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Danh Sách Sản Phẩm" scope="request"/>

<%@ include file="_layout.jsp" %>

<%-- NỘI DUNG --%>
<div class="page-header">
    <h2 class="page-heading">Danh Sách Sản Phẩm</h2>
    <a href="AdminController?action=product_create_page" class="btn-admin-primary">
        <i class="bi bi-plus-lg"></i> Thêm Sản Phẩm
    </a>
</div>

<%-- THÔNG BÁO LỖI NẾU CÓ --%>
<c:if test="${not empty ERROR_MSG}">
    <div class="error-box">
        <i class="bi bi-exclamation-triangle-fill"></i> ${ERROR_MSG}
    </div>
</c:if>

<div class="admin-card">
    <table class="admin-table">
        <thead>
            <tr>
                <th>Hình ảnh</th>
                <th>Mã SP</th>
                <th>Tên Sản Phẩm</th>
                <th>Giá Bán</th>
                <th>Tồn Kho</th>
                <th>Trạng Thái</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${PRODUCT_LIST}">
                <tr>
                    <td>
                        <img src="${pageContext.request.contextPath}/${p.imageURL}"
                             alt="${p.productName}"
                             class="product-thumb">
                    </td>
                    <td>${p.productID}</td>
                    <td class="product-name">${p.productName}</td>
                    <td class="product-price">${p.priceFormat} ₫</td>
                    <td>${p.quantity}</td>
                    <td>
                        <c:choose>
                            <c:when test="${p.status}">
                                <span class="badge-active">Đang bán</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge-inactive">Đã ẩn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <%-- Nút Sửa --%>
                        <a href="AdminController?action=product_edit_page&productID=${p.productID}"
                           class="btn-admin-edit">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>

                        <%-- Nút Ẩn hoặc Khôi phục tùy trạng thái --%>
                        <c:choose>
                            <c:when test="${p.status}">
                                <a href="AdminController?action=product_delete&productID=${p.productID}"
                                   class="btn-admin-danger"
                                   onclick="return confirm('Bạn có chắc muốn ẩn sản phẩm này?')">
                                    <i class="bi bi-eye-slash"></i> Ẩn
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="AdminController?action=product_restore&productID=${p.productID}"
                                   class="btn-admin-success">
                                    <i class="bi bi-arrow-counterclockwise"></i> Khôi phục
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

            <%-- Trường hợp không có sản phẩm nào --%>
            <c:if test="${empty PRODUCT_LIST}">
                <tr>
                    <td colspan="7" class="empty-row">
                        <i class="bi bi-inbox"></i> Chưa có sản phẩm nào
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="_layout_end.jsp" %>
