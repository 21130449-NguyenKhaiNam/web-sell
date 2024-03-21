<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href=<c:url value="/assets/css/productBuying.css"/>>
    <title>Gian hàng</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main class="main">
    <div class="p-5">
        <div class="p-5 search">
            <form class="form-inline my-2 my-lg-0 d-flex">
                <input class="search__inp form-control mr-sm-2 p-3 me-2" type="search" placeholder="Search"
                       aria-label="Search">
                <button class="search__btn btn btn-outline-success my-2 my-sm-0 ps-4 pe-4 hvr-rectangle-out"
                        type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </div>
    </div>

    <section class="products">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <form action="/filterProductBuying" class="form__filter" id="form__filter">
                        <div class="filter__group"><span class="filter__title">Phân loại sản phẩm</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('categoryList')}"
                                           var="category">
                                    <label class="filter__radio-item">
                                        <input name="categoryId" type="checkbox" class="filter__input filter__radio"
                                               hidden="hidden" value="${category.id}">
                                        <span class="filter-radio__icon-wrapper ">
                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>
                                        <span class="hvr-skew-forward">
                                                ${category.nameType}
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group"><span class="filter__title">Mức giá</span>
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
                                        <span class="filter-radio__icon-wrapper"><i
                                                class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>
                                        <span class="hvr-skew-forward">
                                                ${moneyFrom} - ${moneyTo}
                                        </span>
                                    </label> </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group"><span class="filter__title">Kích cỡ</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${requestScope.sizeList}" var="item">
                                    <label class="filter__radio-item">
                                        <input name="size" value="${item.nameSize}" type="checkbox"
                                               class="filter__input filter__radio" hidden="hidden">
                                        <span class="filter-radio__icon-wrapper">
                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span>
                                        <span class="hvr-skew-forward">
                                                ${item.nameSize}
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <span class="filter__separate"></span>
                        <div class="filter__group"><span class="filter__title">Màu sắc</span>
                            <div class="filter__color-list">
                                <c:forEach items="${requestScope.colorList}" var="item">
                                    <label class="filter__color-item">
                                        <input name="color" type="checkbox" value="${item.codeColor}"
                                               class="filter__input filter__color" hidden="hidden">
                                        <span class="filter__color-show shadow rounded hvr-grow"
                                              style="background-color: ${item.codeColor}"></span>
                                    </label> </c:forEach>
                            </div>
                        </div>
                        <button class="filter__submit button--hover button" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9">
                    <c:set var="list" value="${requestScope.productCardList}"/>
                    <c:if test="${not empty list}">
                        <div class="product__list">
                            <c:forEach var="item" items="${list}">
                                <div class="product__item hvr-grow-shadow">
                                    <c:set var="image" value="undifined"/>
                                    <c:if test="${!productFactory.getListImagesByProductId(item.id).isEmpty()}">
                                        <c:set var="image"
                                               value="${productFactory.getListImagesByProductId(item.id).get(0).nameImage}"/>
                                    </c:if>
                                    <c:url var="linkProductDetail" value="/showProductDetail">
                                        <c:param name="id" value="${item.id}"/>
                                        <c:param name="ten-san-pham" value="${item.name}"/>
                                    </c:url>
                                    <a href="${linkProductDetail}">
                                        <img src="${pageContext.servletContext.contextPath}/assets/img/product_img/${image}"
                                             class="product__img" alt="" loading="lazy"/>
                                    </a>
                                    <div class="product__info">
                                        <a class="product__name" target="_blank"
                                           href="${linkProductDetail}">${item.name}</a>
                                        <div class="product__review">
                                            <div class="product__review-stars">
                                                <c:forEach var="starA" begin="1" step="1"
                                                           end="${productFactory.calculateStar(item.id)}">
                                                    <i class="fa-solid fa-star"></i> </c:forEach>
                                                <c:forEach var="starB" begin="1" step="1"
                                                           end="${5 - productFactory.calculateStar(item.id)}">
                                                    <i class="fa-regular fa-star"></i> </c:forEach>
                                            </div>
                                            <a class="product__review-num" target="_blank"
                                               href="${linkProductDetail}">${productFactory.getReviewCount(item.id)}
                                                nhận xét
                                            </a>
                                        </div>
                                        <span class="product__price">
                                            <fmt:formatNumber value="${item.originalPrice}" type="currency"
                                                              currencyCode="VND" var="originalPrice"/>
                                            <c:choose>
                                                <c:when test="${item.salePrice != null}">
                                                        <strong class="product__price--sale">
                                                            <fmt:formatNumber value="${item.salePrice}"
                                                                              type="currency"
                                                                              currencyCode="VND"
                                                                              var="salePrice"/>${salePrice}
                                                        </strong>
                                                    <strong class="product__price--original"> ${originalPrice} </strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <strong class="product__price--sale"> ${originalPrice} </strong>
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                </div>
                            </c:forEach></div>
                    </c:if> <c:if test="${empty list}"><p class="product__list--empty">Không có sản phẩm nào ứng
                    với bộ lọc </p></c:if>
                </div>
            </div>
            <ul class="paging"><c:if test="${requestScope.quantityPage != 0}">
                <c:forEach var="pageNumber" begin="1" end="${requestScope.quantityPage}">
                    <c:url var="linkPaing" value="${requestScope.requestURL}">
                        <c:param name="page" value="${pageNumber}"/> </c:url> <c:choose>
                    <c:when test="${pageNumber == requestScope.currentPage}">
                        <a class="page page--current" href="${linkPaing}">${pageNumber}</a>
                    </c:when> <c:otherwise>
                    <a class="page" href="${linkPaing}">${pageNumber}</a>
                </c:otherwise> </c:choose> </c:forEach> </c:if></ul>
        </div>
    </section>
</main>
<c:import url="/public/footer.jsp"/>
<% List<String> inputChecked = (List<String>) request.getAttribute("listInputChecked");%>
<script>
    function checkedInputTag(name) {
        let inputElements = document.querySelectorAll("input");
        inputElements.forEach(function (element) {
            if (element.value === name) element.checked = true;
        })
    }

    <% if (inputChecked != null && !inputChecked.isEmpty()) { for (String input : inputChecked) {       %>
    checkedInputTag("<%=input%>");
    <%}}%>
    $(document).ready(function () {
        // Bắt sự kiện gửi đi của form lọc
        $('#form__filter').submit(function (event) {
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
        })

        function updateProducts(response) {
            window.history.pushState('string', '', response.url);
            let container = $('.product__list')[0]
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
                    let linkProductDetail = '/showProductDetail?id=' + contentProduct.id + '&ten-san-pham=' + contentProduct.name
                    let stars = ''
                    let noStars = ''
                    for(let star = 0; star < contentProduct.stars; ++star) {
                        stars += '<i class="fa-solid fa-star"></i>'
                    }
                    for(let star = 5 - contentProduct.stars; star >= 0; --star) {
                        noStars += '<i class="fa-regular fa-star"></i>'
                    }
                    return ` <div class = "product__item" >
                    <a class="product__name" target="_blank" href="` + linkProductDetail + `">
                        <img src = "${pageContext.servletContext.contextPath}/assets/img/product_img/` + product.images[0].nameImage + `" class="product__img" >
                    </a>
                    <div class = "product__info" >
                        <a class="product__name" target="_blank" href="` + linkProductDetail + `">` + contentProduct.name + `</a>
                        <div class="product__review">
                            <div class="product__review-stars">` + stars + noStars + `</div>
                            <a class="product__review-num" target="_blank" href="` + linkProductDetail + `">` +  contentProduct.reviewCounts + ` nhận xét</a>
                        </div>
                        <span class="product__price"><strong class="product__price--sale">` + contentProduct.salePrice + ` </strong>
                        <strong class="product__price--original">` + contentProduct.originalPrice + `</strong></span>
                    </div>
                </div>`;
                })
            }
            container.innerHTML = content.join("")
        }
    })
</script>
</body>
</html>