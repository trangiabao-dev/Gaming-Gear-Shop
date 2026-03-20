<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gaming Gear Shop</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>

        <%-- ===== NAVBAR ===== --%>
        <nav class="navbar navbar-expand-lg fixed-top floating-navbar">
            <div class="container-fluid px-4 px-lg-5">
                <a class="navbar-brand brand-boutique" href="MainController?action=home">
                    Gaming Gear Shop<span></span>
                </a>
                <button class="navbar-toggler border-0 shadow-none text-white"
                        type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
                    <i class="bi bi-list fs-2"></i>
                </button>
                <div class="collapse navbar-collapse" id="navMenu">
                    <form action="MainController" method="GET"
                          class="d-flex mx-auto my-3 my-lg-0" style="max-width:500px;width:100%">
                        <input type="hidden" name="action" value="search"/>
                        <div class="d-flex w-100">
                            <input type="text" name="keyword" class="form-control search-pill"
                                   value="${requestScope.searchKeyword}"
                                   placeholder="Tìm sản phẩm của bạn...">
                            <button type="submit" class="btn btn-search-pill text-white">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </form>
                    <div class="d-flex align-items-center gap-2 justify-content-center mt-2 mt-lg-0">
                        <a href="MainController?action=viewCart" class="action-btn text-decoration-none">
                            <i class="bi bi-bag-heart fs-5"></i>
                            <span class="d-none d-xl-inline">Giỏ hàng</span>
                            <c:if test="${sessionScope.CART_COUNT > 0}">
                                <span class="cart-bubble">${sessionScope.CART_COUNT}</span>
                            </c:if>
                        </a>
                        <div class="dropdown">
                            <button class="action-btn dropdown-toggle border-0 bg-transparent"
                                    type="button" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle fs-5"></i>
                                <span class="d-none d-xl-inline">
                                    ${not empty sessionScope.LOGIN_USER ? sessionScope.LOGIN_USER.fullName : 'Tài khoản'}
                                </span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end soft-dropdown">
                                <c:choose>
                                    <c:when test="${empty sessionScope.LOGIN_USER}">
                                        <li><a class="dropdown-item" href="MainController?action=login">Đăng nhập</a></li>
                                        <li><a class="dropdown-item" href="MainController?action=register">Đăng ký mới</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${sessionScope.LOGIN_USER.roleID == 1}">
                                            <li>
                                                <a class="dropdown-item fw-bold text-primary"
                                                   href="${pageContext.request.contextPath}/AdminController?action=product_list">
                                                    <i class="bi bi-shield-lock-fill me-2"></i>Khu Vực Quản Trị
                                                </a>
                                            </li>
                                            <li><hr class="dropdown-divider"></li>
                                            </c:if>
                                        <li>
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/MainController?action=orderhistory">
                                                <i class="bi bi-clock-history me-2"></i>Lịch sử đơn hàng
                                            </a>
                                        </li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li>
                                            <a class="dropdown-item text-danger" href="MainController?action=logout">
                                                Đăng xuất
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <%-- ===== BANNER ===== --%>
        <div class="banner-section">
            <button class="banner-arrow left" onclick="changeSlide(-1)">
                <i class="bi bi-chevron-left"></i>
            </button>

            <div class="banner-slide active"
                 style="background:linear-gradient(135deg,#1a0005 0%,#d70018 55%,#ff6535 100%)">
                <div class="banner-content">
                    <div class="banner-tag">🎮 Gaming Gear Shop</div>
                    <h2 class="banner-title">Thiết Bị Gaming<br>Cao Cấp Chính Hãng</h2>
                    <p class="banner-sub">Nâng tầm trải nghiệm chơi game với thiết bị đỉnh cao</p>
                    <a href="MainController?action=home" class="banner-cta">
                        Khám phá ngay <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
                <div class="banner-deco"><i class="bi bi-controller"></i></div>
            </div>

            <div class="banner-slide"
                 style="background:linear-gradient(135deg,#0f172a 0%,#1d4ed8 55%,#38bdf8 100%)">
                <div class="banner-content">
                    <div class="banner-tag">⚡ Flash Sale</div>
                    <h2 class="banner-title">Giảm Đến 30%<br>Số Lượng Có Hạn</h2>
                    <p class="banner-sub">Nhanh tay săn deal — Ưu đãi chỉ trong hôm nay</p>
                    <a href="MainController?action=filterPrice&min=0&max=500000" class="banner-cta">
                        Xem ngay <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
                <div class="banner-deco"><i class="bi bi-lightning-fill"></i></div>
            </div>

            <button class="banner-arrow right" onclick="changeSlide(1)">
                <i class="bi bi-chevron-right"></i>
            </button>
            <div class="banner-dots">
                <button class="banner-dot active" onclick="goToSlide(0)"></button>
                <button class="banner-dot" onclick="goToSlide(1)"></button>
            </div>
        </div>

        <%-- ===== NỘI DUNG CHÍNH ===== --%>
        <div class="container-fluid px-4 px-lg-5 mb-5 pb-5" style="padding-top:24px;">
            <div class="row g-4">

                <%-- SIDEBAR --%>
                <div class="col-lg-3">
                    <div class="sidebar-sticky">

                        <div class="filter-box">
                            <h3 class="filter-title">Danh Mục</h3>
                            <form action="MainController" method="GET" class="m-0">
                                <input type="hidden" name="action" value="home"/>
                                <button type="submit"
                                        class="cat-item ${empty param.catID ? 'active' : ''}">
                                    Tất cả sản phẩm
                                </button>
                            </form>
                            <c:forEach items="${listCategory}" var="category">
                                <form action="MainController" method="GET" class="m-0">
                                    <input type="hidden" name="action" value="home"/>
                                    <input type="hidden" name="catID" value="${category.catID}"/>
                                    <button type="submit"
                                            class="cat-item ${param.catID == category.catID ? 'active' : ''}">
                                        ${category.catName}
                                    </button>
                                </form>
                            </c:forEach>
                        </div>

                        <div class="filter-box">
                            <h3 class="filter-title">Mức Giá</h3>
                            <div class="price-grid">
                                <a href="MainController?action=filterPrice&min=0&max=200000"
                                   class="price-btn text-decoration-none">Dưới 200k</a>
                                <a href="MainController?action=filterPrice&min=200000&max=500000"
                                   class="price-btn text-decoration-none">200k - 500k</a>
                                <a href="MainController?action=filterPrice&min=500000&max=1000000"
                                   class="price-btn text-decoration-none">500k - 1 Triệu</a>
                                <a href="MainController?action=filterPrice&min=1000000&max=50000000"
                                   class="price-btn text-decoration-none">Trên 1 Triệu</a>
                            </div>
                        </div>

                        <div class="filter-box">
                            <h3 class="filter-title">Thương Hiệu</h3>
                            <div class="btn-group dropend w-100">
                                <button type="button" class="brand-trigger dropdown-toggle"
                                        data-bs-toggle="dropdown">
                                    <span>Chọn thương hiệu</span>
                                </button>
                                <ul class="dropdown-menu brand-popup ms-3">
                                    <li>
                                        <form action="MainController" method="GET" class="m-0">
                                            <input type="hidden" name="action" value="home"/>
                                            <button type="submit"
                                                    class="dropdown-item ${empty param.brandID ? 'fw-bold text-danger' : ''}">
                                                Tất cả
                                            </button>
                                        </form>
                                    </li>
                                    <li><hr class="dropdown-divider"></li>
                                        <c:forEach items="${listBrand}" var="brand">
                                        <li>
                                            <form action="MainController" method="GET" class="m-0">
                                                <input type="hidden" name="action" value="home"/>
                                                <input type="hidden" name="brandID" value="${brand.brandID}"/>
                                                <button type="submit"
                                                        class="dropdown-item ${param.brandID == brand.brandID ? 'fw-bold text-danger' : ''}">
                                                    ${brand.brandName}
                                                </button>
                                            </form>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>

                <%-- SẢN PHẨM --%>
                <div class="col-lg-9">
                    <div class="d-flex justify-content-between align-items-center mb-3 ps-2">
                        <h2 class="m-0 section-title">Sản Phẩm Nổi Bật</h2>
                        <span class="text-muted fw-bold">${listProduct.size()} kết quả</span>
                    </div>

                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 g-3">
                        <c:forEach items="${listProduct}" var="product" varStatus="st">
                            <div class="col">
                                <div class="boutique-card">

                                    <%-- Badge --%>
                                    <c:if test="${st.index < 2}">
                                        <div class="card-badge-wrap">
                                            <span class="badge-hot">HOT</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${product.quantity > 0 && product.quantity < 10}">
                                        <div class="card-badge-wrap"
                                             style="top:${st.index < 2 ? '32px' : '10px'}">
                                            <span class="badge-low">Sắp hết</span>
                                        </div>
                                    </c:if>

                                    <%-- SẢN PHẨM VẪN CLICK ĐƯỢC NHƯ BÌNH THƯỜNG --%>
                                    <a href="MainController?action=detail&productID=${product.productID}" class="img-wrap">
                                        <img src="${product.imageURL}" alt="${product.productName}">
                                    </a>

                                    <a href="MainController?action=detail&productID=${product.productID}" class="item-name text-decoration-none" title="${product.productName}">
                                        ${product.productName}
                                    </a>

                                    <div class="item-price">${product.priceFormat} ₫</div>

                                    <form action="MainController" method="POST" class="mt-auto">
                                        <input type="hidden" name="productID" value="${product.productID}">
                                        <input type="hidden" name="productName" value="${product.productName}">
                                        <input type="hidden" name="price" value="${product.price}">
                                        <input type="hidden" name="action" value="addToCart">
                                        <button type="submit" class="btn-buy"
                                                ${product.quantity <= 0 ? 'disabled' : ''}>
                                            <c:choose>
                                                <c:when test="${product.quantity <= 0}">
                                                    <i class="bi bi-x-circle"></i> Hết hàng
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="bi bi-cart-plus"></i> Thêm Giỏ Hàng
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div> <%-- KẾT THÚC CỘT SẢN PHẨM (col-lg-9) --%>

            </div> <%-- KẾT THÚC ROW CHIA CỘT (row g-4) --%>

            <%-- ĐÂY RỒI: ĐƯA HẲN PHÂN TRANG RA NGOÀI CÙNG ĐỂ NÓ NẰM GIỮA TOÀN BỘ MÀN HÌNH --%>
            <c:if test="${endPage > 1}">
                <div class="w-100 d-flex justify-content-center" style="margin-top: 40px;">
                    <nav>
                        <ul class="pagination soft-pagination m-0">
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <c:url var="pageUrl" value="MainController">
                                    <c:param name="action" value="home"/>
                                    <c:param name="indexPage" value="${i}"/>
                                    <c:if test="${not empty catID}">
                                        <c:param name="catID" value="${catID}"/>
                                    </c:if>
                                    <c:if test="${not empty brandID}">
                                        <c:param name="brandID" value="${brandID}"/>
                                    </c:if>
                                    <c:if test="${not empty keyword}">
                                        <c:param name="keyword" value="${keyword}"/>
                                    </c:if>
                                    <c:if test="${not empty minPrice}">
                                        <c:param name="min" value="${minPrice}"/>
                                    </c:if>
                                    <c:if test="${not empty maxPrice}">
                                        <c:param name="max" value="${maxPrice}"/>
                                    </c:if>
                                </c:url>
                                <li class="page-item ${tag == i ? 'active' : ''}">
                                    <a class="page-link text-decoration-none" href="${pageUrl}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </c:if>

        </div> <%-- KẾT THÚC CONTAINER CHÍNH --%>

        <%-- ===== FOOTER (CÁC LINK KHÔNG CLICK ĐƯỢC NỮA) ===== --%>
        <footer class="footer">
            <div class="footer-inner">
                <div class="footer-grid">
                    <div>
                        <div class="footer-brand-name">
                            <i class="bi bi-controller me-2"></i>Gaming Gear Shop
                        </div>
                        <p class="footer-desc">
                            Chuyên cung cấp thiết bị gaming chính hãng, chất lượng cao
                            với giá tốt nhất thị trường. Cam kết bảo hành uy tín.
                        </p>
                        <div class="footer-social">
                            <span class="social-btn"><i class="bi bi-facebook"></i></span>
                            <span class="social-btn"><i class="bi bi-youtube"></i></span>
                            <span class="social-btn"><i class="bi bi-tiktok"></i></span>
                            <span class="social-btn"><i class="bi bi-instagram"></i></span>
                        </div>
                    </div>

                    <%-- Cột "Về Chúng Tôi" đã được gỡ bỏ hoàn toàn --%>

                    <div>
                        <div class="footer-col-title">Hỗ Trợ</div>
                        <span class="footer-link">Chính sách đổi trả</span>
                        <span class="footer-link">Bảo hành sản phẩm</span>
                        <span class="footer-link">Hướng dẫn mua hàng</span>
                        <span class="footer-link">Câu hỏi thường gặp</span>
                    </div>
                    <div>
                        <div class="footer-col-title">Liên Hệ</div>
                        <span class="footer-link">
                            <i class="bi bi-telephone-fill"></i> 1800 6789
                        </span>
                        <span class="footer-link">
                            <i class="bi bi-envelope-fill"></i> support@gaminggear.vn
                        </span>
                        <span class="footer-link">
                            <i class="bi bi-geo-alt-fill"></i> TP. Hồ Chí Minh
                        </span>
                        <span class="footer-link">
                            <i class="bi bi-clock-fill"></i> 8:00 - 22:00 mỗi ngày
                        </span>
                    </div>
                </div>
                <div class="footer-bottom">
                    <span>© 2026 Gaming Gear Shop. All rights reserved.</span>
                    <span>Thiết kế bởi nhóm GamingGear</span>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    var currentSlide = 0;
                    var slides = document.querySelectorAll('.banner-slide');
                    var dots = document.querySelectorAll('.banner-dot');
                    var timer = null;

                    function goToSlide(n) {
                        slides[currentSlide].classList.remove('active');
                        dots[currentSlide].classList.remove('active');
                        currentSlide = (n + slides.length) % slides.length;
                        slides[currentSlide].classList.add('active');
                        dots[currentSlide].classList.add('active');
                    }

                    function changeSlide(dir) {
                        goToSlide(currentSlide + dir);
                        resetTimer();
                    }

                    function resetTimer() {
                        clearInterval(timer);
                        timer = setInterval(function () {
                            goToSlide(currentSlide + 1);
                        }, 4500);
                    }

                    timer = setInterval(function () {
                        goToSlide(currentSlide + 1);
                    }, 4500);
        </script>

    </body>
</html>