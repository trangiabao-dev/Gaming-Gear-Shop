<%-- 
    Document   : admin-product
    Created on : Mar 8, 2026, 2:03:54 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản Lý Sản Phẩm - Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <style>
            .img-preview { width: 50px; height: 50px; object-fit: cover; border-radius: 5px; }
            .status-active { color: #198754; font-weight: bold; }
            .status-hidden { color: #dc3545; font-weight: bold; }
        </style>
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="bi bi-gear-fill"></i> Quản Lý Kho Hàng</h2>
                <div>
                    <a href="MainController?action=home" class="btn btn-outline-secondary me-2">Về Trang Chủ</a>
                    <a href="AdminProductController?action=create_page" class="btn btn-primary">
                        <i class="bi bi-plus-circle"></i> Thêm Sản Phẩm Mới
                    </a>
                </div>
            </div>

            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>Ảnh</th>
                                <th>ID</th>
                                <th>Tên Sản Phẩm</th>
                                <th>Giá Tiền</th>
                                <th>Trạng Thái</th>
                                <th class="text-center">Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ADMIN_PRODUCT_LIST}" var="p">
                                <tr>
                                    <td><img src="${p.imageURL}" class="img-preview" alt="product"></td>
                                    <td>${p.productID}</td>
                                    <td><strong>${p.productName}</strong></td>
                                    <td>${p.priceFormat} ₫</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.status}">
                                                <span class="status-active"><i class="bi bi-check-circle"></i> Đang bán</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status-hidden"><i class="bi bi-eye-slash"></i> Đã ẩn</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <a href="AdminProductController?action=edit_page&id=${p.productID}" class="btn btn-sm btn-outline-warning me-1">
                                            <i class="bi bi-pencil-square"></i> Sửa
                                        </a>
                                        <c:if test="${p.status}">
                                            <a href="AdminProductController?action=delete&id=${p.productID}" 
                                               class="btn btn-sm btn-outline-danger"
                                               onclick="return confirm('Bạn có chắc muốn ẩn sản phẩm này không?')">
                                                <i class="bi bi-trash"></i> Xóa
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
