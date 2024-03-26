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
<body><c:import url="/public/header.jsp"/>
<main class="main">
    <section class="products">
        <div class="container-xl">
            <div class="row ">
                <div class="col-3">
                    <form action="filterProductBuying" class="form__filter">
                        <div class="filter__group"><span class="filter__title">Phân loại sản phẩm</span>
                            <div class="filter__radio-list">
                                <c:forEach items="${pageContext.servletContext.getAttribute('categoryList')}"
                                           var="category">
                                    <label class="filter__radio-item">
                                        <input name="categoryId" type="checkbox" class="filter__input filter__radio"
                                               hidden="hidden" value="${category.id}">
                                        <span class="filter-radio__icon-wrapper">                                   <i
                                                class="fa-solid fa-check filter-radio__icon"></i>                                 </span> ${category.nameType}
                                    </label> </c:forEach></div>
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
                                                class="fa-solid fa-check filter-radio__icon"></i>                         </span> ${moneyFrom}
                                        - ${moneyTo}
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
                                        </span> ${item.nameSize}
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
                                        <span class="filter__color-show shadow rounded"
                                              style="background-color: ${item.codeColor}"></span>
                                    </label> </c:forEach>
                            </div>
                        </div>
                        <button class="filter__submit button--hover button" type="submit">Lọc</button>
                    </form>
                </div>
                <div class="col-9">
                    <div class="product__list">
                        <%for(Product item : (List<Product>)request.getAttribute("productCardList")){%>
                            <div class="product__item hvr-grow-shadow">

                                <%String image = productFactory.getListImagesByProductId(item.getId()).get(0).getNameImage();%>
                                <a href="/showProductDetail?id=<%=item.getId()%>">
                                    <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img/", image)%>"
                                         class="product__img" alt="" loading="lazy"/>
                                </a>

                                <div class="product__info">
                                    <a class="product__name" target="_blank" href="/showProductDetail?id=<%=item.getId()%>"><%=item.getName()%></a>
                                    <div class="product__review">
                                        <div class="product__review-stars">
                                            <%for(int starA = 0; starA < productFactory.calculateStar(item.getId());starA++){%>
                                                <i class="fa-solid fa-star"></i>
                                            <%}%>

                                            <%for(int starB = 0; starB < 5 - productFactory.calculateStar(item.getId());starB++){%>
                                                <i class="fa-regular fa-star"></i>
                                            <%}%>

                                        </div>
                                        <a class="product__review-num" target="_blank" href="/showProductDetail"><%=productFactory.getReviewCount(item.getId())%>
                                            nhận xét
                                        </a>

                                    </div>
                                    <span class="product__price">
                                        <fmt:formatNumber value="<%=item.getOriginalPrice()%>" type="currency"
                                                          currencyCode="VND"
                                                          var="originalPrice"/>

                                        <fmt:formatNumber value="<%=item.getSalePrice()%>" type="currency"
                                                          currencyCode="VND"
                                                          var="salePrice"/>

                                        <strong class="product__price--original">${originalPrice}</strong>
                                        <strong class="product__price--sale">${salePrice}</strong>
                                    </span>
                                </div>
                            </div>
                        <%}%>
                    </div>

<%--                    <c:if test="${empty list}">--%>
<%--                        <p class="product__list--empty">Không có sản phẩm nào ứng--%>
<%--                        với bộ lọc </p>--%>
<%--                    </c:if>--%>
                </div>
            </div>

            <jsp:include page="/public/paging.jsp"/>
        </div>
    </section>
</main>
<c:import
        url="/public/footer.jsp"/> <% List<String> inputChecked = (List<String>) request.getAttribute("listInputChecked");
    System.out.println("inputChecked (UI):" + inputChecked);%>
<script>       function checkedInputTag(name) {
    let inputElements = document.querySelectorAll("input");
    inputElements.forEach(function (element) {
        if (element.value === name) element.checked = true;
    })
}

<%       if (inputChecked != null && !inputChecked.isEmpty()) {                         for (String input : inputChecked) {       %>
checkedInputTag("<%=input%>");
<%}                       }%>
</script>
</body>
</html>