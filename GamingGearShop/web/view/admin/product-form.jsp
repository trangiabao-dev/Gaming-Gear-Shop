<%-- 
    Document   : product-form
    Created on : Mar 20, 2026, 12:32:50 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle"
       value="${empty EDIT_PRODUCT ? 'Thêm Sản Phẩm Mới' : 'Sửa Sản Phẩm'}"
       scope="request"/>
<%@ include file="_layout.jsp" %>

<%-- Nút quay lại — không cần page-header vì topbar đã có tiêu đề --%>
<div style="margin-bottom: 20px;">
    <a href="${pageContext.request.contextPath}/AdminController?action=product_list"
       class="btn-admin-secondary">
        <i class="bi bi-arrow-left"></i> Quay lại danh sách
    </a>
</div>

<c:if test="${not empty ERROR}">
    <div class="alert-error">
        <i class="bi bi-exclamation-triangle-fill"></i> ${ERROR}
    </div>
</c:if>

<div class="form-card">
    <form action="${pageContext.request.contextPath}/AdminController"
          method="POST"
          enctype="multipart/form-data">

        <input type="hidden" name="action"
               value="${empty EDIT_PRODUCT ? 'product_insert' : 'product_update'}">

        <c:if test="${not empty EDIT_PRODUCT}">
            <input type="hidden" name="productID" value="${EDIT_PRODUCT.productID}">
        </c:if>

        <%-- Hàng 1: Mã SP + Tên SP --%>
        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Mã Sản Phẩm</label>
                <c:choose>
                    <c:when test="${empty EDIT_PRODUCT}">
                        <input type="text" name="productID"
                               class="form-control-admin"
                               placeholder="VD: P010" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control-admin"
                               value="${EDIT_PRODUCT.productID}" disabled>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Tên Sản Phẩm</label>
                <input type="text" name="productName"
                       class="form-control-admin"
                       value="${EDIT_PRODUCT.productName}" required
                       placeholder="Nhập tên sản phẩm...">
            </div>
        </div>

        <%-- Hàng 2: Giá + Số lượng --%>
        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Giá Bán (VNĐ)</label>
                <input type="number" name="price"
                       class="form-control-admin"
                       value="${EDIT_PRODUCT.price}" min="0" required>
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Số Lượng Tồn Kho</label>
                <input type="number" name="quantity"
                       class="form-control-admin"
                       value="${EDIT_PRODUCT.quantity}" min="0" required>
            </div>
        </div>

        <%-- Hàng 3: Danh mục + Thương hiệu --%>
        <div class="form-row-2col">
            <div class="form-group-admin">
                <label class="form-label-admin">Danh Mục</label>
                <select name="catID" class="form-control-admin" required>
                    <option value="">-- Chọn danh mục --</option>
                    <c:forEach var="cat" items="${CATEGORY_LIST}">
                        <option value="${cat.catID}"
                                ${EDIT_PRODUCT.catID == cat.catID ? 'selected' : ''}>
                            ${cat.catName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group-admin">
                <label class="form-label-admin">Thương Hiệu</label>
                <select name="brandID" class="form-control-admin" required>
                    <option value="">-- Chọn thương hiệu --</option>
                    <c:forEach var="brand" items="${BRAND_LIST}">
                        <option value="${brand.brandID}"
                                ${EDIT_PRODUCT.brandID == brand.brandID ? 'selected' : ''}>
                            ${brand.brandName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <%-- Upload ảnh --%>
        <div class="form-group-admin">
            <label class="form-label-admin">
                Hình Ảnh
                <c:if test="${not empty EDIT_PRODUCT}">
                    <span class="form-note">(để trống nếu không đổi ảnh)</span>
                </c:if>
            </label>

            <c:if test="${not empty EDIT_PRODUCT.imageURL}">
                <img src="${pageContext.request.contextPath}/${EDIT_PRODUCT.imageURL}"
                     class="preview-img" alt="Ảnh hiện tại">
            </c:if>

            <div class="file-input-wrapper">
                <div class="file-input-btn">
                    <i class="bi bi-cloud-upload"></i>
                    <span id="fileNameText">Chọn ảnh sản phẩm...</span>
                </div>
                <input type="file" name="imageFile"
                       accept=".jpg,.jpeg,.png,.gif,.webp"
                       ${empty EDIT_PRODUCT ? 'required' : ''}
                       onchange="updateFileName(this)">
            </div>

            <div class="file-name-display" id="fileNameDisplay"></div>
        </div>

        <%-- Mô tả --%>
        <div class="form-group-admin">
            <label class="form-label-admin">Mô Tả Chi Tiết</label>
            <textarea name="description" class="form-control-admin"
                      rows="4"
                      placeholder="Đặc điểm nổi bật...">${EDIT_PRODUCT.description}</textarea>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-admin-primary">
                <i class="bi bi-save"></i>
                ${empty EDIT_PRODUCT ? 'Lưu Sản Phẩm' : 'Cập Nhật'}
            </button>
            <a href="${pageContext.request.contextPath}/AdminController?action=product_list"
               class="btn-admin-secondary">
                <i class="bi bi-x"></i> Hủy
            </a>
        </div>

    </form>
</div>

<%@ include file="_layout_end.jsp" %>