<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    <link rel="stylesheet" href="assets/css/productDetail.css">
    <title>Chi tiết sản phẩm</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<c:set var="product" value="${requestScope.product}"/>
<main class="main">
    <section class="product__detail">
        <div class="container-xl">
            <div class="row">
                <div class="col-6 ">
                    <div class="product__media">
                        <img class="product__img" src="assets/img/product_img/product21.jpg" alt="">
                        <ul class="product__img-list">
                            <li class="product__img-item product__img-item--clicked ">
                                <img src="assets/img/product_img/product21.jpg" alt="">
                            </li>
                            <li class="product__img-item">
                                <img src="assets/img/product_img/product25_1.jpg" alt="">
                            </li>
                            <li class="product__img-item">
                                <img src="assets/img/product_img/product25_2.jpg" alt="">
                            </li>
                            <li class="product__img-item">
                                <img src="assets/img/product_img/product25_3.jpg" alt="">
                            </li>
                        </ul>
                    </div>

                </div>
                <div class="offset-1 col-5">
                    <div class="product__info">

                        <form action="#!" id="form__product" class="product__form">
                            <h1 class="product__name">${product.name}</h1>

                            <c:forEach var="starA" begin="1" step="1"
                                       end="${productFactory.calculateStar(product.id)}">
                                <i class="product__star fa-solid fa-star"></i>
                            </c:forEach>
                            <c:forEach var="starB" begin="1" step="1"
                                       end="${5 - productFactory.calculateStar(product.id)}">
                                <i class="product__star fa-regular fa-star"></i>
                            </c:forEach>

                            <%--                            <div class="product__stars">--%>
                            <%--                                <i class="product__star fa-solid fa-star"></i>--%>
                            <%--                                <i class="product__star fa-solid fa-star"></i>--%>
                            <%--                                <i class="product__star fa-solid fa-star"></i>--%>
                            <%--                                <i class="product__star fa-solid fa-star"></i>--%>
                            <%--                                <i class="product__star fa-solid fa-star"></i>--%>
                            <%--                            </div>--%>

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
                                            <input type="radio" name="color" hidden="hidden">
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
                                        <input id="quantity" type="text" name="quantity" value="1" disabled>
                                        <div class=" form___quantity-btn form___quantity--increase"></div>
                                    </div>
                                    <p class="form__error"></p>
                                </div>
                                <!--Size Guide-->
                                <div class="form__guide">Hướng dẫn tùy chỉnh thông số</div>
                            </div>

                            <button type="submit" class="form__submit form__submit--order button "
                                        data="Đặt may theo số đo">
                            </button>
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
                        <div class="review__list">
                            <c:forEach var="review" items="${requestScope.listReview}">
                                <article class="review">
                                    <div class="review__avatar">
                                        <img src="../assets/img/user/user_avatar-5.jpg" alt="">
                                    </div>
                                    <div class="review__account">
                                        <h4 class="review__name">User</h4>
                                        <ul class="review__stars">
                                            <c:forEach var="starA" begin="1" step="1"
                                                       end="${review.ratingStar}">
                                                <li class="review__star review__start--archive"></li>
                                            </c:forEach>
                                            <c:forEach var="starB" begin="1" step="1"
                                                       end="${5 - review.ratingStar}">
                                                <li class="review__star "></li>
                                            </c:forEach>
                                            <fmt:formatDate var="reviewDate" value="${review.reviewDate}" type="date"
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
                    </div>
                </div>

                <div class="col-12">
                    <div class="product__related">
                        <h3>Các sản phẩm liên quan</h3>
                        <a href="productBuying.jsp" class="product__more">Xem thêm
                            <i class="product__more-icon fa-solid fa-arrow-right"></i>
                        </a>
                    </div>
                    <div class="product__list">
                        <div class="product__item" style="display: flex;">
                            <img src="../assets/img/product_img/product1.jpg" class="product__img">
                            <div class="product__info">
                                <a class="product__name" target="_blank" href="../htmls/productDetail.html">Áo polo nam
                                                                                                            trơn basic
                                                                                                            form regular
                                                                                                            vải cá
                                                                                                            sấu</a>
                                <div class="product__review">
                                    <div class="product__review-stars">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                    <a class="product__review-num" target="_blank" href="../htmls/productDetail.html">1000
                                                                                                                      nhận
                                                                                                                      xét</a>
                                </div>
                                <span class="product__price"><strong
                                        class="product__price--sale">279.000&nbsp;₫</strong> <strong
                                        class="product__price--original">350.000&nbsp;₫</strong></span>
                            </div>
                        </div>
                        <div class="product__item" style="display: flex;">
                            <img src="../assets/img/product_img/product1.jpg" class="product__img">
                            <div class="product__info">
                                <a class="product__name" target="_blank" href="../htmls/productDetail.html">Áo polo nam
                                                                                                            trơn basic
                                                                                                            form regular
                                                                                                            vải cá
                                                                                                            sấu</a>
                                <div class="product__review">
                                    <div class="product__review-stars">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                    <a class="product__review-num" target="_blank" href="../htmls/productDetail.html">1000
                                                                                                                      nhận
                                                                                                                      xét</a>
                                </div>
                                <span class="product__price"><strong
                                        class="product__price--sale">279.000&nbsp;₫</strong> <strong
                                        class="product__price--original">350.000&nbsp;₫</strong></span>
                            </div>
                        </div>
                        <div class="product__item" style="display: flex;">
                            <img src="../assets/img/product_img/product1.jpg" class="product__img">
                            <div class="product__info">
                                <a class="product__name" target="_blank" href="../htmls/productDetail.html">Áo polo nam
                                                                                                            trơn basic
                                                                                                            form regular
                                                                                                            vải cá
                                                                                                            sấu</a>
                                <div class="product__review">
                                    <div class="product__review-stars">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                    <a class="product__review-num" target="_blank" href="../htmls/productDetail.html">1000
                                                                                                                      nhận
                                                                                                                      xét</a>
                                </div>
                                <span class="product__price"><strong
                                        class="product__price--sale">279.000&nbsp;₫</strong> <strong
                                        class="product__price--original">350.000&nbsp;₫</strong></span>
                            </div>
                        </div>
                        <div class="product__item" style="display: flex;">
                            <img src="../assets/img/product_img/product1.jpg" class="product__img">
                            <div class="product__info">
                                <a class="product__name" target="_blank" href="../htmls/productDetail.html">Áo polo nam
                                                                                                            trơn basic
                                                                                                            form regular
                                                                                                            vải cá
                                                                                                            sấu</a>
                                <div class="product__review">
                                    <div class="product__review-stars">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                    <a class="product__review-num" target="_blank" href="../htmls/productDetail.html">1000
                                                                                                                      nhận
                                                                                                                      xét</a>
                                </div>
                                <span class="product__price"><strong
                                        class="product__price--sale">279.000&nbsp;₫</strong> <strong
                                        class="product__price--original">350.000&nbsp;₫</strong></span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>

    <!--Size Guide-->
    <%--        <div class="modal">--%>
    <%--            <div class="modal__content modal__guide">--%>
    <%--                <img src="assets/img/sizeGuide/aoBaLoSizeGuide.png" alt="" class="guide__img">--%>
    <%--            </div>--%>
    <%--            <div class="modal__blur"></div>--%>
    <%--        </div>--%>
    <!--Form Parameter-->
    <%--        <div class="modal">--%>
    <%--            <div class="modal__content modal__parameter">--%>
    <%--                <h2>Đặt May Theo Số Đo--%>
    <%--                </h2>--%>
    <%--                <form id="form__parameter" action="#!">--%>
    <%--                    <p>Vui lòng nhập đủ thông tin bên dưới. Chúng tôi sẽ gọi xác nhận và tư vấn size trước khi may.</p>--%>
    <%--                    <div class="form__parameter">--%>
    <%--                        <label class="form__block form__label">--%>
    <%--                            Dài áo--%>
    <%--                            <div class="form__block-inner">--%>
    <%--                                <input id="parameter_1" type="text" class="form__input">--%>
    <%--                                <span class="form__unit">cm</span>--%>
    <%--                            </div>--%>
    <%--                            <span class="form__error"></span>--%>
    <%--                        </label>--%>
    <%--                        <label class="form__block form__label">--%>
    <%--                            Ngang ngực--%>
    <%--                            <div class="form__block-inner">--%>
    <%--                                <input id="parameter_2" type="text" class="form__input">--%>
    <%--                                <span class="form__unit">cm</span>--%>
    <%--                            </div>--%>
    <%--                            <span class="form__error"></span>--%>
    <%--                        </label>--%>
    <%--                        <label class="form__block form__label">--%>
    <%--                            Dài tay--%>
    <%--                            <div class="form__block-inner">--%>
    <%--                                <input id="parameter_3" type="text" class="form__input">--%>
    <%--                                <span class="form__unit">cm</span>--%>
    <%--                            </div>--%>
    <%--                            <span class="form__error"></span>--%>
    <%--                        </label>--%>
    <%--                        <label class="form__block form__label">--%>
    <%--                            Rộng vai--%>
    <%--                            <div class="form__block-inner">--%>
    <%--                                <input id="parameter_4" type="text" class="form__input">--%>
    <%--                                <span class="form__unit">cm</span>--%>
    <%--                            </div>--%>
    <%--                            <span class="form__error"></span>--%>
    <%--                        </label>--%>
    <%--                    </div>--%>
    <%--                    <div class="form__submits">--%>
    <%--                        <button type="submit" class="form__submit form__submit--add button "--%>
    <%--                                data="Thêm vào giỏ hàng">--%>
    <%--                        </button>--%>
    <%--                    </div>--%>
    <%--                </form>--%>
    <%--            </div>--%>
    <%--            <div class="modal__blur"></div>--%>
    <%--        </div>--%>

</main>
<%@include file="footer.jsp"%>
<article class="dialog__size-guide"></article>
<script src="js/base.js"></script>
<%--<script src="js/data.js"></script>--%>
<script src="js/validateForm.js"></script>
<%--<script src="js/paging.js"></script>--%>
<script src="js/productDetail.js"></script>
</body>
</html>