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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="../../assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="../../assets/bootstrap/bootstrap-grid.min.css">
    <!--JQuery-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="../../assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../../assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="../../assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="../../assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="../../assets/css/reset.css">
    <link rel="stylesheet" href="../../assets/css/base.css">
    <link rel="stylesheet" href="../../assets/css/productBuying.css">
    <title>Gian hàng</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<main class="main">
    <section class="products">
        <div class="container-xl">
            <div class="row ">
                <div class="col-3">
                    <form action="filterProductBuying" class="form__filter">
                        <div class="filter__group">
                            <span class="filter__title">Phân loại sản phẩm</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('categoryList')}" var="category">
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
                        <button class="filter__submit button--hover button" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9">
                    <c:set var="list" value="${requestScope.productCardList}"/>
                    <c:if test="${not empty list}">
                        <div class="product__list">
                            <c:forEach var="item" items="${list}">
                                <div class="product__item">
                                    <c:set var="image" value="undifined"/>
                                    <c:if test="${!productFactory.getListImagesByProductId(item.id).isEmpty()}">
                                        <c:set var="image"
                                               value="${productFactory.getListImagesByProductId(item.id).get(0).nameImage}"/>
                                    </c:if>
                                    <img src="${pageContext.servletContext.contextPath}/assets/img/product_img/${image}"
                                         class="product__img" alt="" loading="lazy"/>
                                    <div class="product__info">
                                        <c:url var="linkProductDetail" value="/showProductDetail">
                                            <c:param name="id" value="${item.id}"/>
                                            <c:param name="ten-sapn-pham" value="${item.name}"/>
                                        </c:url>
                                        <a class="product__name" target="_blank"
                                           href="${linkProductDetail}">${item.name}</a>
                                        <div class="product__review">
                                            <div class="product__review-stars">
                                                <c:forEach var="starA" begin="1" step="1"
                                                           end="${productFactory.calculateStar(item.id)}">
                                                    <i class="fa-solid fa-star"></i>
                                                </c:forEach>
                                                <c:forEach var="starB" begin="1" step="1"
                                                           end="${5 - productFactory.calculateStar(item.id)}">
                                                    <i class="fa-regular fa-star"></i>
                                                </c:forEach>
                                            </div>
                                            <a class="product__review-num" target="_blank"
                                               href="${linkProductDetail}">${productFactory.getReviewCount(item.id)}
                                                nhận
                                                xét</a>
                                        </div>
                                        <span class="product__price">
                                            <fmt:formatNumber value="${item.originalPrice}" type="currency"
                                                              currencyCode="VND"
                                                              var="originalPrice"/>
                                            <c:choose>
                                                <c:when test="${item.salePrice != null}">
                                                    <strong class="product__price--sale">
                                                        <fmt:formatNumber value="${item.salePrice}" type="currency"
                                                                          currencyCode="VND"
                                                                          var="salePrice"/>
                                                            ${salePrice}
                                                    </strong>
                                                    <strong class="product__price--original">
                                                            ${originalPrice}
                                                    </strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <strong class="product__price--sale">
                                                            ${originalPrice}
                                                    </strong>
                                                </c:otherwise>
                                            </c:choose>

                                        </span>
                                    </div>
                                </div>
                            </c:forEach>
                    </div>
                    </c:if>
                    <c:if test="${empty list}">
                        <p class="product__list--empty">Không có sản phẩm nào ứng với bộ lọc</p>
                    </c:if>

                </div>
            </div>
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
    </section>
</main>
<%@include file="../footer.jsp" %>
<%
    List<String> inputChecked = (List<String>) request.getAttribute("listInputChecked");
    System.out.println("inputChecked (UI):" + inputChecked);
%>
<script>
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
</script>
</body>
</html>