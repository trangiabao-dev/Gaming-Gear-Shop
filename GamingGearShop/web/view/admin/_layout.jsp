<%-- 
    Document   : layout
    Created on : Mar 20, 2026, 1:05:42 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${pageTitle} - Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
    </head>
    <body>

        <%-- TOAST THÔNG BÁO --%>
        <c:if test="${param.msg == 'insert_ok'}">
            <div class="toast-msg toast-success">
                <i class="bi bi-check-circle-fill"></i> Thêm mới thành công!
            </div>
        </c:if>
        <c:if test="${param.msg == 'update_ok'}">
            <div class="toast-msg toast-success">
                <i class="bi bi-check-circle-fill"></i> Cập nhật thành công!
            </div>
        </c:if>
        <c:if test="${param.msg == 'delete_ok'}">
            <div class="toast-msg toast-warning">
                <i class="bi bi-eye-slash-fill"></i> Đã ẩn thành công!
            </div>
        </c:if>
        <c:if test="${param.msg == 'restore_ok'}">
            <div class="toast-msg toast-success">
                <i class="bi bi-arrow-counterclockwise"></i> Đã khôi phục thành công!
            </div>
        </c:if>

        <%-- SIDEBAR --%>
        <div class="sidebar">

            <div class="sidebar-brand">
                <a href="${pageContext.request.contextPath}/MainController?action=home"
                   style="text-decoration: none;">
                    <h1><i class="bi bi-controller"></i> Gaming Gear</h1>
                    <p>Khu vực quản trị</p>
                </a>
            </div>

            <div class="sidebar-menu">

                <div class="menu-label">Quản lý sản phẩm</div>

                <a href="${pageContext.request.contextPath}/AdminController?action=product_list"
                   class="menu-item ${param.action == 'product_list' || param.action == 'product_create_page' || param.action == 'product_edit_page' ? 'active' : ''}">
                    <i class="bi bi-box-seam"></i> Sản Phẩm
                </a>

                <a href="${pageContext.request.contextPath}/AdminController?action=category_list"
                   class="menu-item ${param.action == 'category_list' || param.action == 'category_create_page' || param.action == 'category_edit_page' ? 'active' : ''}">
                    <i class="bi bi-tags"></i> Danh Mục
                </a>

                <a href="${pageContext.request.contextPath}/AdminController?action=brand_list"
                   class="menu-item ${param.action == 'brand_list' || param.action == 'brand_create_page' || param.action == 'brand_edit_page' ? 'active' : ''}">
                    <i class="bi bi-shield-check"></i> Thương Hiệu
                </a>

                <div class="menu-label">Quản lý khác</div>

                <a href="${pageContext.request.contextPath}/AdminController?action=user_list"
                   class="menu-item ${param.action == 'user_list' ? 'active' : ''}">
                    <i class="bi bi-people"></i> Người Dùng
                </a>

                <a href="${pageContext.request.contextPath}/AdminController?action=order_list"
                   class="menu-item ${param.action == 'order_list' ? 'active' : ''}">
                    <i class="bi bi-receipt"></i> Đơn Hàng
                </a>

                <a href="${pageContext.request.contextPath}/AdminController?action=feedback_list"
                   class="menu-item ${param.action == 'feedback_list' ? 'active' : ''}">
                    <i class="bi bi-chat-square-text"></i> Đánh Giá
                </a>

            </div>

            <div class="sidebar-footer">
                <a href="${pageContext.request.contextPath}/MainController?action=home">
                    <i class="bi bi-arrow-left-circle"></i> Về Cửa Hàng
                </a>
                <a href="${pageContext.request.contextPath}/MainController?action=logout">
                    <i class="bi bi-box-arrow-right"></i> Đăng Xuất
                </a>
            </div>

        </div>

        <%-- MAIN --%>
        <div class="main-wrapper">

            <div class="topbar">
                <div class="topbar-title">${pageTitle}</div>
                <div class="topbar-user">
                    <div class="topbar-avatar">
                        ${sessionScope.LOGIN_USER.fullName.substring(0,1).toUpperCase()}
                    </div>
                    <span class="topbar-name">${sessionScope.LOGIN_USER.fullName}</span>
                </div>
            </div>

            <div class="page-content">
