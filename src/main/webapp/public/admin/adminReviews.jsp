<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" />
<jsp:useBean id="userFactory" class="utils.UserFactory" />
<!doctype html>
<html lang="en">
    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminReviews.css" />">
        <title>Quản lý nhận xét</title>
    </head>
    <body>
        <c:import url="/public/header.jsp" />
        <main id="main">
            <nav class="navbar">
                <div class="container-xl">
                    <ul class="navbar__list">
                        <li
                                class="navbar__item">
                            <a href="<c:url value="/public/admin/adminProducts.jsp" />"
                               class="navbar__link button button button--hover">Sản phẩm
                            </a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                               class="navbar__link button button button--hover ">Đơn hàng
                            </a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                               class="navbar__link button button button--hover ">Người dùng
                            </a>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                               class="navbar__link button button button--hover  navbar__link--clicked">Nhận xét
                            </a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                               class="navbar__link button button button--hover ">Phân loại
                            </a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/dashboard.jsp" />"
                               class="navbar__link button button button--hover ">Thống kê
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
            <section class="content">
                <div class="container-xl">
                    <div class="row">
                        <div class="col-12">
                            <div>
                                <h1>Danh sách nhận xét</h1>
                            </div>
                            <div class="table__wrapper">
                                <table class="table">
                                    <thead>
                                        <tr class="table__row">
                                            <th class="table__head">Xem</th>
                                            <th class="table__head table__head-id">Id</th>
                                            <th class="table__head">Mã khách hàng</th>
                                            <th class="table__head">Tên sản phẩm
                                            </th>
                                            <th class="table__head">Mã đơn hàng</th>
                                            <th class="table__head">Số sao</th>
                                            <th class="table__head">Hiển thị</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="list" value="${requestScope.listReview}" />
                                        <c:forEach var="item" items="${list}">
                                            <c:set var="user" value="${userFactory.getUserByIdProductDetail(item.orderDetailId)}" />
                                            <tr class="table__row">
                                                <td class="table__data-view">
                                                    <label>
                                                        <div class="icon-link icon-link-hover review__detail" data-id-review="${item.id}" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                            <i class="fa-solid fa-eye"></i>
                                                        </div>
                                                    </label>
                                                </td>
                                                <td class="table__data">
                                                    <p class="table__cell">${user.id}</p>
                                                </td>
                                                <td class="table__data">
                                                    <p class="table__cell ">${productFactory.getNameProductByIdOrderDetail(item.orderDetailId)}</p>
                                                </td>
                                                <td class="table__data">
                                                    <p class="table__cell">${item.orderDetailId}</p>
                                                </td>
                                                <td class="table__data">
                                                    <p class="table__cell">${item.ratingStar}</p>
                                                </td>
                                                <c:choose> <c:when test="${item.visibility==true}">
                                                    <td class="table__data table__data-visibility table__data-hide">
                                                        <div data-id-review="${item.id}" class="button button--hover button__hide ">Ẩn</div>
                                                    </td>
                                                </c:when> <c:otherwise>
                                                    <td class="table__data table__data-visibility table__data-un-hide">
                                                        <div data-id-review="${item.id}" class="button button--hover button__un-hide ">Bỏ ẩn</div>
                                                    </td>
                                                </c:otherwise> </c:choose>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <ul class="paging">
                                <c:if test="${requestScope.quantityPage != 0}">
                                    <c:forEach var="pageNumber" begin="1" end="${requestScope.quantityPage}">
                                        <c:url var="linkPaing" value="${requestScope.requestURL}">
                                            <c:param name="page" value="${pageNumber}" /> </c:url> <c:choose>
                                        <c:when test="${pageNumber == requestScope.currentPage}">
                                            <a class="page page--current" href="${linkPaing}">${pageNumber}</a>
                                        </c:when> <c:otherwise>
                                        <a class="page" href="${linkPaing}">${pageNumber}</a>
                                    </c:otherwise> </c:choose> </c:forEach> </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <%--<div id="dialog-review-read" class="modal">--%> <%--    <article class="modal__content modal__product">--%> <%--        <div>--%> <%--            <h1>Nhận xét chi tiết</h1>--%> <%--            <i class="modal__product-close  modal__review-close fa-solid fa-xmark"></i>--%> <%--        </div>--%> <%--        <iframe class="modal__product-iframe" src="adminReviewForm.jsp" frameborder="0"></iframe>--%> <%--    </article>--%> <%--    <div class="modal__blur"></div>--%> <%--</div>--%>
        <div class="modal fade" id="staticBackdrop" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div id="model" class="modal-dialog modal-dialog-scrollable" style="max-width: 80%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Thông tin đánh giá</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-4">
                                <img id="staticImageProduct" src="" class="img-thumbnail rounded" alt="...">
                            </div>
                            <div class="col-8">
                                <div class="row">
                                    <div class="col-12 mt-2">
                                        <h3 class="form-label">Tên sản phẩm</h3>
                                        <div class="form-control" id="staticNameProduct"></div>
                                    </div>

                                    <div class="col-6 mt-2">
                                        <h3 class="form-label">Thể loại</h3>
                                        <div class="form-control" id="staticCategory"></div>
                                    </div>
                                    <div class="col-6 mt-2">
                                        <h3 class="form-label text-nowrap">Ngày tạo</h3>
                                        <div class="form-control" id="staticDate"></div>
                                    </div>
                                    <div class="col-4 mt-2">
                                        <h3 class="form-label text-nowrap">Màu sắc</h3>
                                        <div class="form-control" disabled id="staticColor"></div>
                                    </div>
                                    <div class="col-2 mt-2">
                                        <h3 class="form-label text-nowrap">Số lượng</h3>
                                        <div class="form-control" id="staticQuantity"></div>
                                    </div>
                                    <div class="col-4 mt-2">
                                        <h3 class="form-label text-nowrap">Số sao</h3>
                                        <div class="form-control" id="staticStars"></div>
                                    </div>
                                </div>
                                <div class="col-6 mt-2">
                                    <h3 class="form-label text-wrap">Kích cỡ</h3>
                                    <div class="form-control" id="staticSize"></div>
                                </div>
                            </div>
                            <div class="col-12 mt-2">
                                <label class="form-label">Nhận xét</label>
                                <div id="staticReview" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/js/admin/adminReviews.js" />"></script>
    </body>
</html>