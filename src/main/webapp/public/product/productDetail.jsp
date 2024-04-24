<%@ page import="models.Product" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<%@ page import="models.Image" %>
<%@ page import="models.Review" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session" />
<jsp:useBean id="userFatory" class="utils.UserFactory" scope="session" />
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="en">

    <head>
        <%Product product = (Product)request.getAttribute("product");%>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/productDetail.css"/>">
        <title><%=product.getName()%></title>
    </head>

    <body>
        <c:import url="/public/header.jsp" />
        <main class="main">
            <section class="product__detail">
                <div class="container-xl">
                    <div class="row">
                        <div class="col-6 ">
                            <div class="product__media">
                                <%String firstImage = productFactory.getListImagesByProductId(product.getId()).get(0).getNameImage();%>

                                <img class="product__img" src="<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img", firstImage)%>"
                                     alt="" loading="lazy">

                                <ul class="product__img-list">
                                    <%int i = 0;%>
                                    <%for(Image image : productFactory.getListImagesByProductId(product.getId())){%>
                                        <c:choose>
                                            <c:when test="<%=i == 0%>">
                                                <li class="product__img-item product__img-item--clicked">
                                                    <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img", image.getNameImage())%>"
                                                         alt="" loading="lazy">
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="product__img-item">
                                                    <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img", image.getNameImage())%>"
                                                         alt="" loading="lazy">
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <%i++;%>
                                    <%}%>

                                </ul>
                            </div>
                        </div>
                        <div class="offset-1 col-5">
                            <div class="product__info">
                                <form action="/api/cart/add" method="post" id="form__product" class="product__form">
                                    <h1 class="product__name" id="product__name"><%=product.getName()%></h1>
                                    <input type="text" hidden="hidden" name="productId" value="<%=product.getId()%>">

                                    <%for(int starA = 1; starA <= productFactory.calculateStar(product.getId());starA++){%>
                                        <i class="product__star fa-solid fa-star"></i>
                                    <%}%>

                                    <%for(int starB = 1; starB <= 5 - productFactory.calculateStar(product.getId());starB++){%>
                                        <i class="product__star fa-regular fa-star"></i>
                                    <%}%>

                                    <div class="product__price-wrapper">
                                        <fmt:formatNumber value="<%=product.getOriginalPrice()%>" type="currency"
                                                          currencyCode="VND" var="originalPrice" />
                                        <fmt:formatNumber value="<%=product.getSalePrice()%>" type="currency"
                                                          currencyCode="VND" var="salePrice" />
                                        <c:choose>
                                            <c:when test="<%=product.getSalePrice() == 0%>">
                                                <p class="product__price product__price--sale hvr-grow">
                                                        ${originalPrice}</p>
                                            </c:when>

                                            <c:otherwise>
                                                <p class="product__price product__price--sale hvr-grow">
                                                        ${originalPrice}</p>
                                                <p class="product__price product__price--base hvr-bubble-left">
                                                        ${salePrice}</p>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>

                                    <div class="separate"></div>

                                    <div class="form__block">
                                        <p class="form__title">Màu sắc</p>
                                        <c:set var="colors"
                                               value="<%=productFactory.getListColorsByProductId(product.getId())%>" />
                                        <div class="form__choose-color">
                                            <c:forEach var="color" items="${colors}">
                                                <label class="form__color-check shadow rounded"
                                                       style="background-color: ${color.codeColor}">
                                                    <input type="radio" name="color" hidden="hidden"
                                                           value="${color.codeColor}">
                                                </label>
                                            </c:forEach>
                                        </div>
                                        <p class="form__error"></p>
                                    </div>

                                    <div class="separate"></div>

                                    <p class="form__title">Kích thước</p>
                                    <div class="form__block">
                                        <c:set var="sizes" value="<%=productFactory.getListSizesByProductId(product.getId())%>" />
                                        <div class="form__size-list">
                                            <c:forEach var="size" items="${sizes}">
                                                <div class="form__size-item hvr-skew-forward">
                                                    <label> <input type="radio" name="size" class="form__radio"
                                                                   hidden="hidden" value="${size.nameSize}"
                                                                   size-price="${size.sizePrice}"
                                                                   onclick="addSizePrice(this)"> ${size.nameSize}
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>

                                        <span class="size__price"></span>
                                        <span class="form__error"></span>
                                    </div>

                                    <div class="separate"></div>

                                    <p class="form__title ">Số lượng</p>
                                    <div class="form__block">
                                        <div class="form__quantity">
                                            <div class="form__quantity-inner">
                                                <div class=" form___quantity-btn form___quantity--decrease hvr-bounce-out">
                                                </div>

                                                <input id="quantity" type="text" name="quantity" value="1" readonly>

                                                <div class=" form___quantity-btn form___quantity--increase hvr-bounce-in">
                                                </div>
                                            </div>

                                            <p class="form__error"></p>
                                        </div>
                                    </div>

                                    <a href="/showProductOrder?id=<%=product.getId()%>"
                                       type="submit"
                                       class="form__submit form__submit--order button text-secondary"
                                       data="Đặt may theo số đo">
                                    </a>

                                    <button type="submit" class="form__submit form__submit--add button "
                                            data="Thêm vào giỏ hàng">
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div class="col-12 mb-5">
                            <hr />

                            <div class="product__desc-review">
                                <div class="product__page product__page--clicked hvr-float-shadow">Mô tả</div>
                                <div class="product__page hvr-float-shadow">Đánh giá</div>
                            </div>

                            <!--Desc product-->
                            <div class="product__desc">
                                <h3 class="desc__title">Thông tin sản phẩm</h3>
                                <p class="desc__text"><%=product.getDescription()%>
                                </p>
                            </div>

                            <!--Reviews-->
                            <div class="product__review">

                                <c:choose>
                                    <c:when test="${not empty requestScope.listReview}">
                                        <div class="review__list">
                                            <%for(Review review : (List<Review>)request.getAttribute("listReview")){%>
                                                <%User user = userFatory.getUserByIdProductDetail(review.getOrderDetailId());%>
                                                <article class="review">
                                                    <div class="review__avatar">
                                                        <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("user", user.getAvatar())%>"
                                                             alt="<%=user.getAvatar()%>"
                                                             loading="lazy">
                                                    </div>
                                                    <div class="review__account">
                                                        <h4 class="review__name"><%=user.getFullName()%></h4>
                                                        <ul class="review__stars">
                                                            <%for(int starA  = 1; starA <= review.getRatingStar();starA++){%>
                                                                <li class="review__star review__start--archive">
                                                            <%}%>

                                                            <c:if test="<%=review.getRatingStar() < 5%>">
                                                                <%for(int starB  = 1; starB <= 5- review.getRatingStar();starB++){%>
                                                                    <li class="review__star "></li>
                                                                <%}%>
                                                            </c:if>
                                                            <fmt:formatDate var="reviewDate" value="<%=review.getReviewDate()%>" type="date"
                                                                                                 pattern="dd/MM/yyyy" />

                                                            <span class="review__date"><%=review.getReviewDate()%></span>
                                                        </ul>
                                                        <p class="review__para line-clamp"><%=review.getFeedback()%>
                                                        </p>
                                                    </div>
                                                </article>
                                            <%}%>
                                        </div>

                                        <ul class="paging">
                                        </ul>
                                    </c:when>

                                    <c:otherwise>
                                        <p class="review__empty">
                                            Hãy trở thành người đầu tiên đánh giá sản phẩm.
                                        </p>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                        <div class="col-12">
                            <div class="product__related">
                                <h3 id="suggest__title">Các sản phẩm liên quan</h3>
                                <a href="/public/product/productBuying.jsp"
                                   class="product__more hvr-forward">Xem thêm
                                    <i class="product__more-icon fa-solid fa-arrow-right"></i></a>
                            </div>

                            <div class="product__list">
                                <%for(Product item : (List<Product>)request.getAttribute("listProductRelated")){%>
                                    <div class="product__item hvr-grow-shadow">

                                        <c:set value="<%=productFactory.getListImagesByProductId(item.getId())%>" var="listProductImage" />

                                        <a href="/showProductDetail?id=<%=item.getId()%>">
                                            <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img", productFactory.getListImagesByProductId(item.getId()).get(0).getNameImage())%>"
                                                 class="product__img" alt="" loading="lazy" />
                                        </a>
                                        <div class="product__info">
                                            <a class="product__name" target="_self"
                                               href="/showProductDetail?id=<%=item.getId()%>"><%=item.getName()%>
                                            </a>

                                            <div class="product__review">
                                                <div class="product__review-stars">
                                                    <%for(int starA = 1; starA <= productFactory.calculateStar(item.getId());starA++){%>
                                                        <i class="fa-solid fa-star"></i>
                                                    <%}%>

                                                    <%for(int starB = 1; starB <= 5 - productFactory.calculateStar(item.getId());starB++){%>
                                                    <i class="fa-regular fa-star"></i>
                                                    <%}%>
                                                </div>
                                                <a class="product__review-num" target="_blank"
                                                   href="${linkProductDetail}"><%=productFactory.getReviewCount(item.getId())%>
                                                    nhận xét
                                                </a>
                                            </div>

                                            <fmt:formatNumber value="<%=item.getOriginalPrice()%>" type="currency"
                                                              currencyCode="VND" var="originalPrice" />
                                            <fmt:formatNumber value="<%=item.getSalePrice()%>" type="currency"
                                                              currencyCode="VND" var="salePrice" />

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
                                <%}%>
                            </div>
                        </div>

                    </div>
                </div>
            </section>
        </main>
        <%@ include file="/public/footer.jsp" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/typeit/5.0.2/typeit.min.js"
                integrity="sha512-izh01C0sD66AuIVp4kRaEsvCSEC5bgs3n8Bm8Db/GhqJWei47La76LGf8Lbm8UHdIOsn+I7SxbeVLKb1k2ExMA=="
                crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <article class="dialog__size-guide"></article>
        <script src="<c:url value="/js/base.js"/>"></script>
        <script>
            let useLoggedIn = false;
            <c:if test="${sessionScope.auth != null}">
            useLoggedIn = true;
            </c:if>
        </script>
        <script src="<c:url value="/js/validateForm.js"/>"></script>
        <script src="<c:url value="/js/productDetail.js"/>"></script>
    </body>

</html>