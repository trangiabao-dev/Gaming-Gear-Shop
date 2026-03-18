<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"/>
</c:if>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lịch sử đơn hàng - Gaming Gear Shop</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet">
    </head>

    <body>
        <%-- NAVBAR --%>
        <nav class="navbar navbar-expand-lg fixed-top floating-navbar">
            <div class="container-fluid px-4 px-lg-5">
                <a class="navbar-brand brand-boutique" href="${pageContext.request.contextPath}/MainController?action=home">
                    Gaming Gear Shop<span></span>
                </a>
                <button class="navbar-toggler border-0 shadow-none text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
                    <i class="bi bi-list fs-2"></i>
                </button>
                <div class="collapse navbar-collapse" id="navMenu">
                    <form action="${pageContext.request.contextPath}/MainController" method="GET" class="d-flex mx-auto my-3 my-lg-0 w-100 max-w-500">
                        <input type="hidden" name="action" value="search" />
                        <div class="d-flex w-100">
                            <input type="text" name="keyword" class="form-control search-pill" placeholder="Tìm sản phẩm của bạn...">
                            <button type="submit" class="btn btn-search-pill text-white"><i class="bi bi-search"></i></button>
                        </div>
                    </form>
                    <div class="d-flex align-items-center gap-2 justify-content-center mt-2 mt-lg-0">
                        <a href="${pageContext.request.contextPath}/MainController?action=viewCart" class="action-btn text-decoration-none">
                            <i class="bi bi-bag-heart fs-5"></i>
                            <span class="d-none d-xl-inline">Giỏ hàng</span>
                            <c:if test="${not empty sessionScope.CART}">
                                <span class="cart-bubble">${sessionScope.CART.cart.size()}</span>
                            </c:if>
                        </a>
                        <div class="dropdown">
                            <button class="action-btn dropdown-toggle border-0 bg-transparent" type="button" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle fs-5"></i>
                                <span class="d-none d-xl-inline ms-1">
                                    ${not empty sessionScope.LOGIN_USER ? sessionScope.LOGIN_USER.fullName : 'Tài khoản'}
                                </span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end soft-dropdown">
                                <c:choose>
                                    <c:when test="${empty sessionScope.LOGIN_USER}">
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MainController?action=login">Đăng nhập</a></li>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MainController?action=register">Đăng ký mới</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MainController?action=orderHistory">
                                                <i class="bi bi-clock-history me-2"></i>Lịch sử đơn hàng</a>
                                        </li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/MainController?action=logout">Đăng xuất</a></li>
                                        </c:otherwise>
                                    </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <%-- NỘI DUNG CHÍNH --%>
        <div class="cart-wrapper">
            <div class="mb-4">
                <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn-continue">
                    <i class="bi bi-arrow-left"></i> Tiếp tục mua sắm
                </a>
            </div>

            <div class="cart-box">
                <h2 class="cart-header-title mb-4">Lịch Sử Đơn Hàng</h2>

                <c:choose>
                    <c:when test="${empty ORDER_LIST}">
                        <div class="status-box">
                            <div class="status-icon empty">
                                <i class="bi bi-bag-x"></i>
                            </div>
                            <h2 class="status-title">Chưa có đơn hàng nào</h2>
                            <p class="status-desc">Bạn chưa đặt đơn hàng nào. Hãy mua sắm ngay!</p>
                            <a href="${pageContext.request.contextPath}/MainController?action=home" class="btn-checkout">
                                MUA SẮM NGAY
                            </a>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="cart-table-wrapper">
                            <table class="cart-table">
                                <thead>
                                    <tr>
                                        <th>Mã đơn</th>
                                        <th>Ngày đặt</th>
                                        <th>Tổng tiền</th>
                                        <th>Trạng thái</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${ORDER_LIST}">
                                        <tr>
                                            <td>#${order.orderID}</td>
                                            <td>${order.orderDate}</td>
                                            <td class="total-col">${order.total} ₫</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status == 0}">
                                                        <span class="badge bg-danger">Đã hủy</span>
                                                    </c:when>
                                                    <c:when test="${order.status == 1}">
                                                        <span class="badge bg-warning text-dark">Đang xử lý</span>
                                                    </c:when>
                                                    <c:when test="${order.status == 2}">
                                                        <span class="badge bg-info text-dark">Đang giao</span>
                                                    </c:when>
                                                    <c:when test="${order.status == 3}">
                                                        <span class="badge bg-success">Hoàn thành</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:if test="${order.status == 1}">
                                                    <a href="${pageContext.request.contextPath}/MainController?action=cancelOrder&orderID=${order.orderID}"
                                                       class="btn-remove"
                                                       onclick="return confirm('Bạn có chắc muốn HỦY đơn hàng #${order.orderID}?')">
                                                        <i class="bi bi-x-circle-fill"></i> Hủy đơn
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>