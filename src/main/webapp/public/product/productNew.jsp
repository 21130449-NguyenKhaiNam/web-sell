<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                <jsp:useBean id="productFactory" class="utils.ProductFactory" scope="page" />

                <html>

                <head>
                    <jsp:include page="/public/commonLink.jsp" />
                    <link rel="stylesheet" href="<c:url value=" /assets/css/trendingNewProducts.css" /> ">
                    <title>Sản phẩm mới</title>
                </head>

                <body>
                    <c:import url="/public/header.jsp" />
                    <main id="main">
                        <div class="new__section container-xl">
                            <h2 class="section__title">Sản phẩm mới</h2>
                            <div class="product__items">
                                <c:forEach items="${requestScope.listProductsPerPage}" var="newProduct">
                                    <c:url var="showProductDetail" value="showProductDetail">
                                        <c:param name="id" value="${newProduct.id}" />
                                    </c:url>
                                    <div class="product__item">
                                        <div class="product__content">
                                            <div class="image--tag">
                                                <c:set value="${productFactory.getListImagesByProductId(newProduct.id)}"
                                                    var="listNewProductImages" />
                                                <img src="<c:url value="
                                                    /assets/img/product_img/${listNewProductImages.get(0).nameImage}" />">
                                                <c:if
                                                    test="${fn:contains(sessionScope.listAllTrendingProducts, newProduct)}">
                                                    <span class="product__tag">Thịnh hành</span>
                                                </c:if>
                                                <form class="action__bar" action="AddToCart" method="post">
                                                    <input type="hidden" name="productId" value="${newProduct.id}">
                                                    <button type="submit" class="add__cart"><i
                                                            class="fa-solid fa-cart-shopping"></i>
                                                    </button>
                                                    <a class="see__detail" target="_blank"
                                                        href="${showProductDetail}"><i class="fa-solid fa-eye"></i></a>
                                                </form>
                                            </div>
                                            <div class="product__info">
                                                <a class="product__name" target="_blank"
                                                    href="${showProductDetail}">${newProduct.name}</a>
                                                <div class="product__review">
                                                    <div class="review__icon">
                                                        <c:forEach var="starA" begin="1" step="1"
                                                            end="${productFactory.calculateStar(newProduct.id)}">
                                                            <i class="fa-solid fa-star icon__item"></i>
                                                        </c:forEach>
                                                        <c:forEach var="starB" begin="1" step="1"
                                                            end="${5 - productFactory.calculateStar(newProduct.id)}">
                                                            <i class="fa-regular fa-star icon__item"></i>
                                                        </c:forEach>
                                                    </div>
                                                    <a class="number__turns--ratting" target="_blank"
                                                        href="${showProductDetail}">${productFactory.getReviewCount(newProduct.id)}
                                                        nhận
                                                        xét</a>
                                                </div>
                                                <span class="product__price">
                                                    <fmt:setLocale value="vi_VN" />
                                                    <c:choose>
                                                        <c:when test="${newProduct.salePrice == null}">
                                                            <strong class="priority__price">
                                                                <fmt:formatNumber value="${newProduct.originalPrice}"
                                                                    type="currency" />
                                                            </strong>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <strong class="sale__price">
                                                                <fmt:formatNumber value="${newProduct.salePrice}"
                                                                    type="currency" />
                                                            </strong>
                                                            <s class="original__price">
                                                                <fmt:formatNumber value="${newProduct.originalPrice}"
                                                                    type="currency" />
                                                            </s>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <c:set value="${requestScope.page}" var="page" />
                            <div class="pagination">
                                <c:if test="${page > 1}">
                                    <c:url var="prevURLPage" value="newProducts">
                                        <c:param name="page" value="${page - 1}" />
                                    </c:url>
                                    <a href="${prevURLPage}" class="previous__page"><i
                                            class="fa-solid fa-chevron-left"></i></a>
                                </c:if>
                                <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">
                                    <c:url var="trURLPage" value="newProducts">
                                        <c:param name="page" value="${i}" />
                                    </c:url>
                                    <a class="${i == page ? " active" : "page__forward" }" href="${trURLPage}">${i}</a>
                                </c:forEach>
                                <c:if test="${page < requestScope.totalPage}">
                                    <c:url var="nextURLPage" value="newProducts">
                                        <c:param name="page" value="${page + 1}" />
                                    </c:url>
                                    <a href="${nextURLPage}" class="next__page"><i
                                            class="fa-solid fa-chevron-right"></i></a>
                                </c:if>

                            </div>
                        </div>
                    </main>
                    <%@ include file="/public/footer.jsp" %>
                        <script src="<c:url value=" /js/base.js" />"></script>
                        <script type="text/javascript">
                            function addToCartAjax() {
                                $(document).ready(function () {
                                    $('.action__bar').each(function (index, actionBar) {
                                        $(actionBar).on('submit', function (event) {
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
                                            if (userLoggedIn === false) {
                                                window.location.href = "signIn.jsp"
                                            } else {
                                                const form = $(actionBar);
                                                let productId = form.find('input[name="productId"]').val();
                                                $.ajax({
                                                    type: form.attr('method'),
                                                    url: form.attr('action'),
                                                    data: { productId: productId },
                                                    success: function (response) {
                                                        let addToCartSuccessHTML = `<div class="notification__cart">
                                                                <div class="status__success">
                                                                    <span><i class="fa-solid fa-circle-check icon__success"></i>Đã thêm vào giỏ hàng thành công</span>
                                                                    <span onclick="handleCloseNotificationCart()"><i class="fa-solid fa-xmark close__notification"></i></span>
                                                                </div>
                                                                <a class="view__cart" href="../user/shoppingCart.jsp">Xem giỏ hàng và thanh toán</a>
                                                            </div>`;
                                                        $('.cart__wrapper').append(addToCartSuccessHTML)
                                                        $('.qlt__value').text(response);
                                                    },
                                                    error: function (error) {
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