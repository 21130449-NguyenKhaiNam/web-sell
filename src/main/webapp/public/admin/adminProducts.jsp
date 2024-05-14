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
<main id="main">
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <div class="mb-2 form__filter" id="form__search">
                        <div class="filter__group">
                            <span class="filter__title">Tên sản phẩm</span>
                            <label class="filter__text-block">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input class="filter__input filter__text" type="text" name="keyword">
                            </label>
                        </div>
                        <ul class="search__box shadow"></ul>
                    </div>

                    <form action="<c:url value="/filterProductAdmin"/>" class="form__filter" id="form__filter">
                        <div class="filter__group">
                            <span class="filter__title">Thời gian cập nhập</span>
                            <div class="filter__date-block">
                                <label class="filter__date">
                                    <span>Từ:</span>
                                    <input type="date" name="date"
                                           id="date-start" placeholder="dd-mm-yyyy">
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
                            <div class="filter__radio-grid">
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
                                        <span class="filter__color-show shadow rounded"
                                              style="background-color: ${item.codeColor}">
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <button class="filter__submit button--hover button p-2" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9">
                    <div>
                        <h1>Danh sách sản phẩm</h1>
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>
                         <span id="button-import-product" class="button button__import">
                             <form action="/admin-import-product" method="POST" enctype="multipart/form-data" style="display: flex;justify-content: center;align-items: center">
                                 <input type="file" name="file">
                                 <span class="btn_uploadImg">
                                     <i class="fa-solid fa-upload"></i>
                                     <input type="submit" value="Upload" style="background-color: #4BAC4D; color: #fff"/>
                                 </span>
                             </form>
                        </span>
                        <span id="button-create-product" class="button button__add">
                            <i class="fa-solid fa-plus"></i>
                            Thêm sản phẩm
                        </span>
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
                                <th>Hiển thị</th>
                            </tr>
                            </thead>
                            <tbody class="product__list-admin">

                            </tbody>
                        </table>
                    </div>
                    <!--Paging-->
                    <ul class="paging"></ul>
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
                            let originPrice = contentProduct.salePrice
                            let salePrice = contentProduct.originalPrice
                            return `
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
                                        <p class="table__cell">` + contentProduct.id + `</p>
                                    </td>
                                    <td class="table__data table__data-name">
                                        <p class="table__cell line-clamp line-1">` + contentProduct.name + `</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">Bo sung loai san pham</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">` + originPrice +`đ</p>
                                    </td>
                                    <td class="table__data">
                                            <p class="table__cell">` + salePrice +`đ</p>
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
