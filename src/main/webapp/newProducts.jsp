<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="./assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">

    <!--Bootstrap-->
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="./assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="./assets/favicon/site.webmanifest">
    <!--Web font-->

    <link rel="stylesheet" href="./assets/font/webfonts/Montserrat.css">

    <!--CSS-->
    <link rel="stylesheet" href="./assets/css/reset.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="assets/css/trendingNewProducts.css">
    <title>Sản phẩm mới</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <div class="new__section container-xl">
        <h2 class="section__title">Sản phẩm mới</h2>
        <div class="product__items">
            <c:forEach items="${requestScope.listProductsPerPage}" var="newProduct">
                <div class="product__item">
                    <div class="product__content">
                        <div class="image--tag">
                            <c:set value="${productFactory.getListImagesByProductId(newProduct.id)}" var="listNewProductImages"/>
                            <img src="./assets/img/product_img/${listNewProductImages.get(0).nameImage}">
                            <c:if test="${fn:contains(sessionScope.listAllTrendingProducts, newProduct)}">
                                <span class="product__tag">Thịnh hành</span>
                            </c:if>
                            <form class="action__bar" action="AddToCart" method="post">
                                <input type="hidden" name="productId" value="${newProduct.id}">
                                <button type="submit" class="add__cart"><i class="fa-solid fa-cart-shopping"></i></button>
                                <a class="see__detail" href="showProductDetail?id=${newProduct.id}"><i class="fa-solid fa-eye"></i></a>
                            </form>
                        </div>
                        <div class="product__info">
                            <a class="product__name" href="showProductDetail?id=${newProduct.id}">${newProduct.name}</a>
                            <div class="product__review">
                                <div class="review__icon">
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                </div>
                                <a class="number__turns--ratting" href="showProductDetail?id=${newProduct.id}">1000 nhận xét</a>
                            </div>
                            <span class="product__price">
                                <fmt:setLocale value="vi_VN"/>
                                <c:choose>
                                    <c:when test="${newProduct.salePrice == null}">
                                        <strong class="priority__price">
                                            <fmt:formatNumber value="${newProduct.originalPrice}" type="currency"/>
                                        </strong>
                                    </c:when>
                                    <c:otherwise>
                                        <strong class="sale__price">
                                            <fmt:formatNumber value="${newProduct.salePrice}" type="currency"/>
                                        </strong>
                                        <s class="original__price">
                                            <fmt:formatNumber value="${newProduct.originalPrice}" type="currency"/>
                                        </s>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:set value="${requestScope.page}" var="page"/>
        <div class="pagination">
            <c:if test="${page > 1}">
                <c:url var="prevURLPage" value="newProducts">
                    <c:param name="page" value="${page - 1}"/>
                </c:url>
                <a href="${prevURLPage}" class="previous__page"><i class="fa-solid fa-chevron-left"></i></a>
            </c:if>
            <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">
                <c:url var="trURLPage" value="newProducts">
                    <c:param name="page" value="${i}"/>
                </c:url>
                <a class="${i == page ? "active" : "page__forward"}" href="${trURLPage}">${i}</a>
            </c:forEach>
            <c:if test="${page < requestScope.totalPage}">
                <c:url var="nextURLPage" value="newProducts">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <a href="${nextURLPage}" class="next__page"><i class="fa-solid fa-chevron-right"></i></a>
            </c:if>

        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>
<script src="js/base.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
    function addToCartAjax(){
        $(document).ready(function (){
            $('.action__bar').each(function (index, actionBar){
                $(actionBar).on('submit', function (event){
                    event.preventDefault();
                    let userLoggedIn;
                    <c:choose>
                        <c:when test="${sessionScope.auth == null}">
                            userLoggedIn = false
                        </c:when>
                        <c:otherwise>
                            userLoggedIn = true
                        </c:otherwise>
                    </c:choose>
                    if(userLoggedIn === false){
                        window.location.href = "signIn.jsp"
                    }else {
                        const form = $(actionBar);
                        let productId = form.find('input[name="productId"]').val();
                        $.ajax({
                            type: form.attr('method'),
                            url: form.attr('action'),
                            data: {productId: productId},
                            success: function (response) {
                                let addToCartSuccessHTML = `<div class="notification__cart">
                                                                <div class="status__success">
                                                                    <span><i class="fa-solid fa-circle-check icon__success"></i>Đã thêm vào giỏ hàng thành công</span>
                                                                    <span onclick="handleCloseNotificationCart()"><i class="fa-solid fa-xmark close__notification"></i></span>
                                                                </div>
                                                                <a class="view__cart" href="shoppingCart.jsp">Xem giỏ hàng và thanh toán</a>
                                                            </div>`;
                                $('.cart__wrapper').append(addToCartSuccessHTML)
                                $('.qlt__value').text(response);
                            },
                            error: function (error){
                                console.error('Lỗi khi thêm sản phẩm vào giỏ hàng', error);
                            }
                        })
                    }
                })
            })
        })
    }
    addToCartAjax();
</script>
</body>
</html>
