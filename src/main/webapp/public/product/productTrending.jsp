<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/trendingNewProducts.css" />">
        <title>Sản phẩm thịnh hành</title>
    </head>

    <body>
        <c:import url="/public/header.jsp" />
        <main id="main">
            <div class="popular__section container-xl">
                <h2 class="section__title">Sản phẩm thịnh hành</h2>
                <div class="product__items">
                    <c:forEach items="${requestScope.listProductsPerPage}" var="trendProduct">
                        <c:url var="showProductDetail" value="showProductDetail">
                            <c:param name="id" value="${trendProduct.id}" /> </c:url>
                        <div class="product__item">
                            <div class="product__content">
                                <div class="image--tag">
                                    <c:set value="${productFactory.getListImagesByProductId(trendProduct.id)}"
                                           var="listTrendProductImages" />
                                    <img src="${listTrendProductImages.get(0).nameImage}" alt="">
                                    <span class="product__tag">Thịnh hành</span>
                                    <form class="action__bar" action="<c:url value="/api/cart/add"/>" method="post">
                                        <input type="hidden" name="productId" value="${trendProduct.id}">
                                        <button type="submit" class="add__cart"><i
                                                class="fa-solid fa-cart-shopping"></i></button>
                                        <a class="see__detail" target="_blank" href="${showProductDetail}"><i
                                                class="fa-solid fa-eye"></i></a>
                                    </form>
                                </div>
                                <div class="product__info">
                                    <a class="product__name" target="_blank"
                                       href="${showProductDetail}">${trendProduct.name}</a>
                                    <div class="product__review">
                                        <div class="review__icon">
                                            <c:forEach var="starA" begin="1" step="1"
                                                       end="${productFactory.calculateStar(newProduct.id)}">
                                                <i class="fa-solid fa-star icon__item"></i> </c:forEach>
                                            <c:forEach var="starB" begin="1" step="1"
                                                       end="${5 - productFactory.calculateStar(newProduct.id)}">
                                                <i class="fa-regular fa-star icon__item"></i> </c:forEach>
                                        </div>
                                        <a class="number__turns--ratting" target="_blank"
                                           href="${showProductDetail}">${productFactory.getReviewCount(trendProduct.id)}
                                            nhận xét
                                        </a>
                                    </div>
                                    <span class="product__price">
                                        <fmt:setLocale value="vi_VN" />
                                        <c:choose> <c:when test="${trendProduct.salePrice== null}">
                                                <strong class="priority__price">
                                                    <fmt:formatNumber value="${trendProduct.originalPrice}"
                                                                      type="currency" />
                                                </strong> </c:when> <c:otherwise>
                                                <strong class="sale__price">
                                                    <fmt:formatNumber value="${trendProduct.salePrice}"
                                                                      type="currency" />
                                                </strong> <s class="original__price">
                                                    <fmt:formatNumber value="${trendProduct.originalPrice}"
                                                                      type="currency" />
                                                </s> </c:otherwise> </c:choose>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:set value="${requestScope.page}" var="page" />
                <div class="pagination">
                    <c:if test="${page > 1}"> <c:url var="prevURLPage" value="trendingProducts">
                        <c:param name="page" value="${page - 1}" /> </c:url>
                        <a href="${prevURLPage}" class="previous__page"><i
                                class="fa-solid fa-chevron-left"></i></a>
                    </c:if> <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">
                    <c:url var="trURLPage" value="trendingProducts"> <c:param name="page" value="${i}" /> </c:url>
                    <a class="${i == page ? " active" : "page__forward" }" href="${trURLPage}">${i}</a>
                </c:forEach> <c:if test="${page < requestScope.totalPage}">
                    <c:url var="nextURLPage" value="trendingProducts"> <c:param name="page" value="${page + 1}" />
                    </c:url>
                    <a href="${nextURLPage}" class="next__page"><i
                            class="fa-solid fa-chevron-right"></i></a>
                </c:if>

                </div>
            </div>
        </main>
        <c:import url="/public/footer.jsp" />
        <script src="<c:url value="/js/base.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
                integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
                crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script type="text/javascript">
            function addToCartAjax() {
                $(document).ready(function () {
                    $('.action__bar').each(function (index, actionBar) {
                        $(actionBar).on('submit', function (event) {
                            event.preventDefault();
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
                                                                <a class="view__cart" href="/public/user/shoppingCart.jsp">Xem giỏ hàng và thanh toán</a>
                                                            </div>`;
                                    $('.cart__wrapper').append(addToCartSuccessHTML)
                                    $('.qlt__value').text(response);
                                },
                                error: function (error) {
                                    console.error('Lỗi khi thêm sản phẩm vào giỏ hàng', error);
                                }
                            })
                        })
                    })
                })
            }
            addToCartAjax();
        </script>
    </body>

</html>