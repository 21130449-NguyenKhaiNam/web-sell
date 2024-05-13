<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css" />">
    <title>Quản lý sản phẩm</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminProducts.jsp" />"
                       class="navbar__link button button button--hover navbar__link--clicked hvr-grow-shadow">Sản
                        phẩm</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Đơn hàng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Người dùng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Nhận xét</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Phân loại</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/dashboard.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Thống kê</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/log.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Thay đổi</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <ul class="navbar__list d-flex flex-column">
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminProducts.jsp" />"
                               class="navbar__link button button button--hover navbar__link--clicked hvr-grow-shadow">Người
                                dùng</a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                               class="navbar__link button button button--hover hvr-grow-shadow">Đơn hàng</a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                               class="navbar__link button button button--hover hvr-grow-shadow">Sản phẩm</a>
                        </li>
                        <li class="navbar__item">
                            <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                               class="navbar__link button button button--hover hvr-grow-shadow">...</a>
                        </li>
                    </ul>
                </div>
                <div class="col-9">
                    <div>
                        <h1>Thông tin</h1>
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>

                    </div>
                    <div class="table__wrapper">
                        <table class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã số</th>
                                <c:if test="${sessionScope.auth.role == '2'}">
                                    <th class="table__head">IP</th>
                                </c:if>
                                <th class="table__head">Mức độ</th>
                                <th class="table__head">Thuộc tính</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Ngày thay đổi</th>
                                <th class="table__head">
                                    <table>
                                        <th>
                                            Giá trị trước
                                        </th>
                                        <th>
                                            Giá trị sau
                                        </th>
                                    </table>
                                </th>
                            </tr>
                            </thead>
                            <tbody class="product__list-admin">
                            <tr class="table__row">
                                <c:if test="${sessionScope.auth.role == '2'}">
                                    <td class="table__data-edit">
                                        <label>
                                            <p>1</p>
                                        </label>
                                    </td>
                                </c:if>

                                <td class="table__data table__data-id">
                                    <p class="table__cell">192.168.1.1</p>
                                </td>
                                <style>
                                    .table__cell[data-info="info"] {
                                        background-color: yellow;
                                    }
                                    .table__cell[data-info="dangerous"] {
                                        background-color: red;
                                    }
                                </style>
                                <td class="table__data table__data-name">
                                    <p class="table__cell line-clamp line-1" data-info="info">Thông tin</p>
                                </td>
                                <td class="table__data">
                                    <p class="table__cell">Áo thể thao Unisex</p>
                                </td>
                                <td class="table__data">
                                    <p class="table__cell">12/12/2022</p>
                                </td>
                                <td class="table__data">
                                    <p class="table__cell">28/03/2024</p>
                                </td>
                                <td class="table__data">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td>
                                                Giá: 700 <br>
                                                Tên: Đồ cũ <br>
                                                Loại: Đồ giả
                                            </td>
                                            <td>
                                                Giá: 701 <br>
                                                Tên: Đồ cũ 90% <br>
                                                Loại: Đồ giả real 1-1
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--Paging-->
                    <ul class="paging">
                        <c:if test="${requestScope.quantityPage != 0}">
                            <c:forEach var="pageNumber" begin="1" end="${requestScope.quantityPage}">
                                <c:url var="linkPaing" value="${requestScope.requestURL}">
                                    <c:param name="page" value="${pageNumber}"/>
                                </c:url>
                                <c:choose>
                                    <c:when test="${pageNumber == requestScope.currentPage}">
                                        <a class="page page--current" href="${linkPaing}">${pageNumber}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page" href="${linkPaing}">${pageNumber}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</main>
<div id="dialog-product-read" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Xem sản phẩm</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="<c:url value="/public/admin/adminProductForm.jsp"/>"
                frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-product-create" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Thêm sản phẩm</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="<c:url value="/public/admin/adminProductForm.jsp"/>"
                frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-product-update" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Cập nhập sản phẩm</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="<c:url value="/public/admin/adminProductUpdateForm.jsp"/>"
                frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<script>
    const role = "<%=((User)session.getAttribute("auth")).getRole()%>";
</script>
<script src="<c:url value="/js/admin/adminProducts.js"/>"></script>
<%
    List<String> inputChecked = (List<String>) request.getAttribute("listInputChecked");
    Object keyword = request.getAttribute("keyword");
    Object dateStart = request.getAttribute("sqlDateStart");
    Object dateEnd = request.getAttribute("sqlDateEnd");
%>
<script>
    function checkDate(inputDate, dateString) {
        inputDate.value = dateString;
    }

    function checkNameInput(keyword) {
        let inputElements = document.querySelector(`input[type = "text"]`);
        inputElements.value = keyword;
    }

    function checkedInputTag(name) {
        let inputElements = document.querySelectorAll("input");
        inputElements.forEach(function (element) {
            if (element.value === name)
                element.checked = true;
        })
    }

    <%
     if (inputChecked!=null && !inputChecked.isEmpty()){
         for (String input : inputChecked) {
    %>
    checkedInputTag("<%=input%>");
    <%}
     }%>

    <% if (keyword != null){%>
    checkNameInput("<%=keyword%>");
    <%}%>

    <% if (dateStart != null){%>
    checkDate(document.querySelector("#date-start"), "<%=dateStart%>");
    <%}%>

    <% if (dateEnd != null){%>
    checkDate(document.querySelector("#date-end"), "<%=dateEnd%>");
    <%}%>

    $(document).ready(function () {
        $('#form__filter').submit(
            function (event) {
                // Ngăn chặn hành vi mặc định của form (chẳng hạn chuyển hướng trang)
                event.preventDefault();

                var formData = $(this).serialize();

                $.ajax({
                    type: 'GET',
                    url: $(this).attr('action'),
                    data: formData,
                    success: function (response) {
                        updateProducts(response)
                    },
                    error: function (err) {
                        console.log(err)
                    }
                });

                function updateProducts(response) {
                    window.history.pushState('string', '', response.url);
                    let container = $('.product__list-admin')[0]
                    let products = response.products
                    let content = ''
                    if (products.length <= 0) {
                        content = '<p class="product__list--empty">Không có sản phẩm nào ứng với bộ lọc </p>'
                    } else {
                        const vndFormat = Intl.NumberFormat("vi-VI", {
                            style: "currency",
                            currency: "VND",
                        });
                        content = products.map(function (product) {
                            const contentProduct = product.product
                            return `
                                <tr class="table__row">
                                    <td class="table__data-view">
                                        <label>
                                            <i class="fa-solid fa-eye"></i>
                                        </label>
                                    </td>
                                    <c:if test="${sessionScope.auth.role == '2'}">
                                        <td class="table__data-edit">
                                            <label>
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </label>
                                        </td>
                                    </c:if>

                                    <td class="table__data table__data-id">
                                        <p class="table__cell">` + contentProduct.id + `</p>
                                    </td>
                                    <td class="table__data table__data-name">
                                        <p class="table__cell line-clamp line-1">` + contentProduct.name + `</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">Bo sung loai san pham</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">` + ${vndFormat.format(contentProduct.salePrice)} +`</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">` + ${vndFormat.format(contentProduct.originalPrice)} +`</p>
                                    </td>` +
                                (contentProduct.visibility ? `
                                    <td class="table__data table__data-visibility table__data-hide">
                                                <div class="button button--hover button__hide">Ẩn</div>
                                            </td>
                                ` : `
                                    <td class="table__data table__data-visibility table__data-un-hide">
                                                <div class="button button--hover button__un-hide">Bỏ ẩn</div>
                                            </td>
                                `) + `</tr>`
                        })
                    }
                    container.innerHTML = content.join("")
                }
            })
    })
    let ulCom = $('.search__box')[0]

    function handelSearch() {
        let debounceTimer;
        $('.filter__input').keydown(function () {

            var formData = $(this).serialize();

            clearTimeout(debounceTimer);

            debounceTimer = setTimeout(() => {
                $.ajax({
                    url: '/searchProduct',
                    method: 'GET',
                    data: formData,
                    success: function (response) {
                        ulCom.innerHTML = ""
                        for (let i = 0; i < response.length; ++i) {
                            const li = document.createElement("li")
                            li.setAttribute("class", "mb-1")
                            const a = document.createElement("a")
                            a.setAttribute("class", "text-dark mb-2 search__box-item")
                            a.setAttribute("href", "/")
                            a.innerText = response[i]
                            li.appendChild(a)
                            ulCom.appendChild(li)
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                })
            }, 800);
        })
    }

    handelSearch()

    $('.filter__input').on('focus', function () {
        $('.search__box').addClass('focused');
    });

    $('.filter__input').on('blur', function () {
        $('.search__box').removeClass('focused');
    });
</script>
</body>
</html>
