<%-- 
    Document   : admin-navbar
    Created on : Mar 20, 2026, 12:51:02 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 shadow-sm">
    <div class="container-fluid px-4">
        
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/AdminController?action=product_list">
            <i class="bi bi-shield-lock-fill text-warning me-2"></i>ADMIN PANEL
        </a>
        
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="adminNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 gap-1 ms-lg-4">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AdminController?action=product_list">
                        <i class="bi bi-box-seam me-1"></i> Sản Phẩm
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AdminController?action=category_list">
                        <i class="bi bi-tags me-1"></i> Danh Mục
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AdminController?action=brand_list">
                        <i class="bi bi-award me-1"></i> Thương Hiệu
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-muted" href="#">
                        <i class="bi bi-receipt me-1"></i> Đơn Hàng
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-muted" href="#">
                        <i class="bi bi-people me-1"></i> Người Dùng
                    </a>
                </li>
            </ul>
            
            <div class="d-flex align-items-center gap-3">
                <span class="text-light">Chào sếp, <b>${sessionScope.LOGIN_USER.fullName}</b></span>
                <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn btn-outline-light btn-sm fw-bold">
                    <i class="bi bi-shop me-1"></i> Về Cửa Hàng
                </a>
            </div>
        </div>
        
    </div>
</nav>
