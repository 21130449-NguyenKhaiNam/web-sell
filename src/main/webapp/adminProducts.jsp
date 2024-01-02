<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/admin/admin.css">
    <link rel="stylesheet" href="assets/css/productBuying.css">
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <title>Quản lý sản phẩm</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="adminProducts.jsp"
                                                class="navbar__link button button button--hover navbar__link--clicked">Sản
                                                                                                                       phẩm</a>
                </li>
                <li class="navbar__item"><a href="adminOrders.jsp"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="adminUsers.jsp"
                                            class="navbar__link button button button--hover ">Khách
                                                                                              hàng</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <form action="filterProductAdmin" class="form__filter">
                        <div class="filter__group">
                            <span class="filter__title">Tên sản phẩm</span>
                            <label class="filter__text-block">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input class="filter__input filter__text" type="text" name="keyword">
                            </label>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group">
                            <span class="filter__title">Thời gian cập nhập</span>
                            <div class="filter__date-block">

                                <label class="filter__date">
                                    <span>Từ:</span>

                                    <input type="date" name="date"
                                           id="date-start" placeholder="dd-mm-yyyy" ">

                                </label>
                                <label class="filter__date">
                                    <span>Đến:</span>

                                    <input type="date" name="date"
                                           id="date-end">

                                </label>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group">
                            <span class="filter__title">Phân loại sản phẩm</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('categoryList')}"
                                           var="category">
                                    <label class="filter__radio-item">
                                        <input name="categoryId" type="checkbox" class="filter__input filter__radio"
                                               hidden="hidden" value="${category.id}">
                                        <span class="filter-radio__icon-wrapper">
                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>
                                            ${category.nameType}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group">
                            <span class="filter__title">Mức giá</span>

                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('moneyRangeList')}"
                                           var="moneyRange">
                                    <fmt:formatNumber value="${moneyRange.from}" type="currency" currencyCode="VND"
                                                      var="moneyFrom"/>
                                    <fmt:formatNumber value="${moneyRange.to}" type="currency" currencyCode="VND"
                                                      var="moneyTo"/>
                                    <label class="filter__radio-item">
                                        <input name="moneyRange" type="checkbox" class="filter__input filter__radio"
                                               hidden="hidden" value="${moneyRange.getFrom()}-${moneyRange.getTo()}">
                                        <span class="filter-radio__icon-wrapper">
                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>${moneyFrom} - ${moneyTo}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group">
                            <span class="filter__title">Kích cỡ</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${requestScope.sizeList}" var="item">
                                    <label class="filter__radio-item">
                                        <input name="size" value="${item.nameSize}" type="checkbox"
                                               class="filter__input filter__radio"
                                               hidden="hidden">
                                        <span class="filter-radio__icon-wrapper">
                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>
                                            ${item.nameSize}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group">
                            <span class="filter__title">Màu sắc</span>
                            <div class="filter__color-list">
                                <c:forEach items="${requestScope.colorList}" var="item">
                                    <label class="filter__color-item">
                                        <input name="color" type="checkbox" value="${item.codeColor}"
                                               class="filter__input filter__color"
                                               hidden="hidden">
                                        <span class="filter__color-show" style="background-color: ${item.codeColor}">
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <button class="filter__reset button--hover button" type="reset">Chọn lại</button>
                        <button class="filter__submit button--hover button" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9">
                    <div>
                        <h1>Danh sách sản phẩm</h1>
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>
                        <span id="button-create-product" class="button button__add">
                            <i class="fa-solid fa-plus"></i>
                            Thêm sản phẩm
                        </span>
                        <div id="button-remove-product" class="button button__delete">
                            <i class="fa-solid fa-minus"></i>
                            Xóa sản phẩm
                        </div>
                    </div>
                    <div class="table__wrapper">
                        <table class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Xem</th>
                                <th class="table__head">Chỉnh sửa</th>
                                <th class="table__head">Mã sản phẩm</th>
                                <th class="table__head">Tên sản phẩm</th>
                                <th class="table__head">
                                    Phân loại sản phẩm
                                </th>
                                <th class="table__head">Giá gốc</th>
                                <th class="table__head">Giá giảm</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="list" value="${requestScope.productCardList}"/>
                            <c:forEach var="item" items="${list}">
                                <tr class="table__row">
                                    <td class="table__data-view">
                                        <label>
                                            <i class="fa-solid fa-eye"></i>
                                        </label>
                                    </td>
                                    <td class="table__data-edit">
                                        <label>
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </label>
                                    </td>
                                    <td class="table__data table__data-id">
                                        <p class="table__cell">${item.id}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${item.name}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${productFactory.getNameCategoryById(item.id)}</p>
                                    </td>
                                    <fmt:formatNumber value="${item.originalPrice}" type="currency" currencyCode="VND"
                                                      var="originalPrice"/>
                                    <fmt:formatNumber value="${item.salePrice}" type="currency" currencyCode="VND"
                                                      var="salePrice"/>
                                    <td class="table__data">
                                        <p class="table__cell">${salePrice}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${originalPrice}</p>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--Paging-->
                    <%System.out.println(request.getAttribute("quantityPage"));%>
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
        <iframe class="modal__product-iframe" src="adminProductForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-product-create" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Thêm sản phẩm</h1>
            <i class="modal__product-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminProductForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>

<script src="js/admin/adminProducts.js"></script>
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


</script>
</body>
</html>
