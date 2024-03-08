<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<jsp:useBean id="userFatory" class="utils.UserFactory" scope="session"/>
<fmt:setLocale value="vi_VN"/>
<c:set var="product" value="${requestScope.product}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/productDetail.css"/>">
    <title>${product.name}</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main class="main">
    <section class="product__detail">
        <div class="container-xl">
            <div class="row">
                <div class="col-6 ">
                    <div class="product__media">
                        <c:set var="firstImage"
                               value="${productFactory.getListImagesByProductId(product.id).get(0).nameImage}"/>
                        <img class="product__img" src="<c:url value="/assets/img/product_img/${firstImage}"/>"
                             alt="" loading="lazy">
                        <ul class="product__img-list">
                            <c:forEach var="image" varStatus="loop"
                                       items="${productFactory.getListImagesByProductId(product.id)}">
                                <c:choose>
                                    <c:when test="${loop.index == 0}">
                                        <li class="product__img-item product__img-item--clicked">
                                            <img src="<c:url value="/assets/img/product_img/${image.nameImage}"/>"
                                                 alt="" loading="lazy">
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="product__img-item">
                                            <img src="<c:url value="/assets/img/product_img/${image.nameImage}"/> "
                                                 alt="" loading="lazy">
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="offset-1 col-5">
                    <div class="product__info">
                        <form action="AddToCart" method="post" id="form__product" class="product__form">
                            <h1 class="product__name">${product.name}</h1>
                            <input type="text" hidden="hidden" name="productId" value="${product.id}">
                            <c:forEach var="starA" begin="1" step="1"
                                       end="${productFactory.calculateStar(product.id)}">
                                <i class="product__star fa-solid fa-star"></i>
                            </c:forEach>
                            <c:forEach var="starB" begin="1" step="1"
                                       end="${5 - productFactory.calculateStar(product.id)}">
                                <i class="product__star fa-regular fa-star"></i>
                            </c:forEach>

                            <div class="product__price-wrapper">
                                <fmt:formatNumber value="${product.originalPrice}" type="currency" currencyCode="VND"
                                                  var="originalPrice"/>
                                <fmt:formatNumber value="${product.salePrice}" type="currency" currencyCode="VND"
                                                  var="salePrice"/>
                                <c:choose>
                                    <c:when test="${product.salePrice == 0}">
                                        <p class="product__price product__price--sale">${originalPrice}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="product__price product__price--sale">${originalPrice}</p>
                                        <p class="product__price product__price--base">${salePrice}</p>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <!--
                            - size guide: button, when click will open sizeGuide
                            - Size: input
                            - color: input
                            -->
                            <div class="separate"></div>

                            <div class="form__block">
                                <p class="form__title">Màu sắc</p>
                                <c:set var="colors" value="${productFactory.getListColorsByProductId(product.id)}"/>
                                <div class="form__choose-color">
                                    <c:forEach var="color" items="${colors}">
                                        <label class="form__color-check" style="background-color: ${color.codeColor}">
                                            <input type="radio" name="color" hidden="hidden" value="${color.codeColor}">
                                        </label>
                                    </c:forEach>
                                </div>
                                <p class="form__error"></p>
                            </div>

                            <div class="separate"></div>

                            <p class="form__title">Kích thước</p>
                            <div class="form__block">
                                <c:set var="sizes" value="${productFactory.getListSizesByProductId(product.id)}"/>
                                <div class="form__size-list">
                                    <c:forEach var="size" items="${sizes}">
                                        <div class="form__size-item">
                                            <label>
                                                <input type="radio" name="size" class="form__radio" hidden="hidden"
                                                       value="${size.nameSize}"
                                                       size-price="${size.sizePrice}" onclick="addSizePrice(this)">
                                                    ${size.nameSize}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <span class="size__price">

                                </span>
                                <span class="form__error"></span>
                            </div>

                            <div class="separate"></div>

                            <p class="form__title ">Số lượng</p>
                            <div class="form__block">
                                <div class="form__quantity">
                                    <div class="form__quantity-inner">
                                        <div class=" form___quantity-btn form___quantity--decrease"></div>
                                        <input id="quantity" type="text" name="quantity" value="1" readonly>
                                        <div class=" form___quantity-btn form___quantity--increase"></div>
                                    </div>
                                    <p class="form__error"></p>
                                </div>
                            </div>

                            <a href="${initParam.contextPath}/showProductOrder?id=${product.id}" type="submit"
                               class="form__submit form__submit--order button "
                               data="Đặt may theo số đo">
                            </a>
                            <button type="submit" class="form__submit form__submit--add button "
                                    data="Thêm vào giỏ hàng">
                            </button>
                        </form>
                    </div>
                </div>
                <div class="col-12">

                    <div class="product__desc-review">
                        <div class="product__page product__page--clicked">Mô tả</div>
                        <div class="product__page">Đánh giá</div>
                    </div>

                    <!--Desc product-->
                    <div class="product__desc">
                        <h3 class="desc__title">Thông tin sản phẩm</h3>
                        <p class="desc__text">${product.description}
                        </p>
                    </div>

                    <!--Reviews-->
                    <div class="product__review">

                        <c:choose>
                            <c:when test="${not empty requestScope.listReview}">
                                <div class="review__list">
                                    <c:forEach var="review" items="${requestScope.listReview}">
                                        <c:set var="user"
                                               value="${userFatory.getUserByIdProductDetail(review.orderDetailId)}"/>
                                        <article class="review">
                                            <div class="review__avatar">
                                                <img src="<c:url value="/assets/img/user/${user.avatar}" />"
                                                     alt="${user.avatar}"
                                                     loading="lazy">
                                            </div>
                                            <div class="review__account">
                                                <h4 class="review__name">${user.fullName}</h4>
                                                <ul class="review__stars">
                                                    <c:forEach var="starA" begin="1" step="1"
                                                               end="${review.ratingStar}">
                                                        <li class="review__star review__start--archive"></li>
                                                    </c:forEach>
                                                    <c:if test="${review.ratingStar < 5}">
                                                        <c:forEach var="starB" begin="1" step="1"
                                                                   end="${5 - review.ratingStar}">
                                                            <li class="review__star "></li>
                                                        </c:forEach>
                                                    </c:if>
                                                    <fmt:formatDate var="reviewDate" value="${review.reviewDate}"
                                                                    type="date"
                                                                    pattern="dd/MM/yyyy"/>
                                                    <span class="review__date">${reviewDate}</span>
                                                </ul>
                                                <p class="review__para line-clamp">${review.feedback}
                                                </p>
                                            </div>
                                        </article>
                                    </c:forEach>
                                </div>
                                <ul class="paging">
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <p class="review__empty">
                                    Sản phẩm này hiện chưa có nhận xét.
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="col-12">
                    <div class="product__related">
                        <h3>Các sản phẩm liên quan</h3>
                        <a href="${initParam.contextPath}/productBuying.jsp" class="product__more">Xem thêm
                            <i class="product__more-icon fa-solid fa-arrow-right"></i>
                        </a>
                    </div>
                    <div class="product__list">
                        <c:forEach var="item" items="${requestScope.listProductRelated}">
                            <div class="product__item">
                                <c:set value="${productFactory.getListImagesByProductId(item.id)}"
                                       var="listProductImage"/>
                                <img src="<c:url value="/assets/img/product_img/${productFactory.getListImagesByProductId(item.id).get(0).getNameImage()}" />"
                                     class="product__img" alt="" loading="lazy"/>
                                <div class="product__info">
                                    <c:url var="linkProductDetail" value="${initParam.contextPath}/showProductDetail">
                                        <c:param name="id" value="${item.id}"/>
                                    </c:url>
                                    <a class="product__name" target="_self"
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
                                           href="${linkProductDetail}">${productFactory.getReviewCount(item.id)} nhận
                                            xét</a>
                                    </div>
                                    <fmt:formatNumber value="${item.originalPrice}" type="currency" currencyCode="VND"
                                                      var="originalPrice"/>
                                    <fmt:formatNumber value="${item.salePrice}" type="currency" currencyCode="VND"
                                                      var="salePrice"/>
                                    <span class="product__price">
                                        <strong class="product__price--sale">
                                                ${salePrice}
                                        </strong>
                                        <strong class="product__price--original">
                                                ${originalPrice}
                                        </strong>
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
    </section>
</main>
<%@ include file="/public/footer.jsp" %>
<article class="dialog__size-guide"></article>
<script src="<c:url value="/js/base.js"/>"></script>
<script>
    let useLoggedIn = false;
    <c:if test="${sessionScope.auth != null}">
    useLoggedIn = true;
    </c:if>
</script>
<script src="<c:url value="/js/validateForm.js"/>"></script>
<script>
    const isLogin = "<%=session.getAttribute("auth") != null%>";
</script>
<script src="<c:url value="/js/productDetail.js"/>"></script>
</body>
</html>