
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.ArrayList" %>

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
    <title>Gian hàng</title></head>
<body>
<c:import url="/public/header.jsp"/>
<main class="main">
    <div class="modal_hidden_search__box"></div>
    <div class="p-5 animate__animated animate__slideInDown" style="position: relative; z-index: 10;">
        <div class="p-5 search">
            <form class="form-inline my-2 my-lg-0 d-flex">
                <input style="z-index: 2;" class="search__inp form-control mr-sm-2 p-3 me-2" type="search" placeholder="Search"
                       aria-label="Search" name="keyword">
                <button class="search__btn btn btn-outline-success my-2 my-sm-0 ps-4 pe-4 hvr-rectangle-out"
                        type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
                <ul class="search__box shadow"></ul>
            </form>
        </div>
    </div>

    <section class="products">
        <div class="container-xl">
            <div class="row ">
                <div class="col-3 animate__animated animate__slideInUp tab_right">
                    <form class="form__filter" id="form__filter">
                        <div class="filter__group"><span class="filter__title">Phân loại sản phẩm</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('categoryList')}"
                                           var="category">
                                    <label class="filter__radio-item">
                                        <input name="categoryId" type="checkbox" class="filter__input filter__radio"
                                               hidden="hidden" value="${category.id}">
                                        <span class="filter-radio__icon-wrapper">

                                            <i class="fa-solid fa-check filter-radio__icon"></i>
                                        </span> ${category.nameType}
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
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <button class="filter__submit button--hover button p-2" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9 animate__animated animate__fadeInRight tab_left">
                    <div class="product__notification"></div>
                    <div class="product__list">
                        <%--  sản phẩm được hiển thị ở đây--%>
                    </div>
                </div>
            </div>
        </div>

        <ul class="paging"></ul>
    </section>
</main>
<c:import url="/public/footer.jsp"/>
</body>

<!--tippy tooltip-->
<script src="https://unpkg.com/popper.js@1"></script>
<script src="https://unpkg.com/tippy.js@5/dist/tippy-bundle.iife.js"></script>

<script src="<c:url value="/js/productBuying.js"/>"></script>

</html>