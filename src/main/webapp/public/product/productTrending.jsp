<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/trendingNewProducts.css" />">
    <title>Sản phẩm thịnh hành</title>
</head>

<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <div class="popular__section container-xl">
        <h2 class="section__title">Sản phẩm thịnh hành</h2>
        <div class="product__items">
            <c:forEach items="${requestScope.listProductsPerPage}" var="trendProduct">
                <c:url var="showProductDetail" value="showProductDetail">
                    <c:param name="id" value="${trendProduct.id}"/> </c:url>
                <div class="product__item">
                    <div class="product__content">
                        <div class="image--tag">
                            <c:set value="${productFactory.getListImagesByProductId(trendProduct.id)}"
                                   var="listTrendProductImages"/>
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
                                        <fmt:setLocale value="vi_VN"/>
                                        <c:choose> <c:when test="${trendProduct.salePrice== null}">
                                                <strong class="priority__price">
                                                    <fmt:formatNumber value="${trendProduct.originalPrice}"
                                                                      type="currency"/>
                                                </strong> </c:when> <c:otherwise>
                                                <strong class="sale__price">
                                                    <fmt:formatNumber value="${trendProduct.salePrice}"
                                                                      type="currency"/>
                                                </strong> <s class="original__price">
                                                    <fmt:formatNumber value="${trendProduct.originalPrice}"
                                                                      type="currency"/>
                                                </s> </c:otherwise> </c:choose>
                                    </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <ul class="paging" style="position: relative"></ul>
    </div>
</main>
<c:import url="/public/footer.jsp"/>
<script src="<c:url value="/js/base.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
    const productPages = document.querySelector('.paging')
    generationPage(${requestScope.page}, ${requestScope.totalPage})

    function showBeforePaging(currentPage, minPage) {
        if (currentPage > 3) {
            productPages.innerHTML += `<a class="page access_page_quickly">...</a>`
            const div = document.createElement('div')
            div.style.width = '200px'
            div.style.height = '50px'
            div.style.overflow = 'auto'

            for (let i = 1; i < minPage; i++) {
                const a = document.createElement('a')
                a.innerText = i
                a.classList.add("page")
                a.href = "/newProducts?page=" + i
                div.appendChild(a)
            }
            tippy('.access_page_quickly', {
                content: div,
                placement: 'auto',
                interactive: true,
                duration: [500, 250],
                arrow: true,
            });
        }
    }

    function generationPage(currentPage, totalPage) {
        var minPage = currentPage - 2
        var maxPage = currentPage + 2;

        if (maxPage > totalPage) {
            maxPage = totalPage
        }
        if (currentPage < 3) {
            minPage = 1;
            maxPage = 5
        }
        productPages.innerHTML = ''
        showBeforePaging(currentPage, minPage)
        for (let i = minPage; i <= maxPage; i++) {
            const a = document.createElement('a');
            if (i === currentPage) {
                a.classList.add("page--current");
            }
            a.classList.add('page')
            a.innerText = i;
            a.href = "/newProducts?page=" + i
            productPages.appendChild(a);
        }
    }

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