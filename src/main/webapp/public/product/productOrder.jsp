<%@ page import="models.Product" %>
<%@ page import="models.Category" %>
<%@ page import="models.Parameter" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session" />
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp" />
    <link rel="stylesheet" href="<c:url value="/assets/css/productDetail.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productOrder.css"/>">
    <title>May đồ theo yêu cầu</title>
</head>
<body>
<%Product product = (Product) request.getAttribute("product");
    Category category = (Category) request.getAttribute("category");
    List<Parameter> listParameter = (List<Parameter>) request.getAttribute("listParameter");
%>
<c:import url="/public/header.jsp" />
<form class="form" id="form__product">
    <div class="container-xl order__log-padding"> <%--So do --%>
        <div class="col-12"><h2 class="order__heading">Bảng số đo</h2>
            <div class="order__guide-size">
                <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("size_table", category.getSizeTableImage())%>" alt="">
            </div>
        </div>

        <%int i = 0;
            for(Parameter parameter : listParameter){%>
        <div class="col-12">
            <div class="separate"></div>
        </div>
        <div class="col-12"><h2 class="order__heading"><%=parameter.getName()%></h2>
            <input type="text" name="nameParameter" hidden="hidden" value="<%=parameter.getName()%>">
            <div class="row align-items-center">
                <div class="col-5">
                    <label class="order__group">
                        <label class="order__parameter">
                            <input id="parameter<%=i%>" class="order__input form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" type="text" name="valueParameter" placeholder="Hãy nhập số đo của <%=parameter.getName()%>" required>
                            <c:set var="unit" value="<%=parameter.getUnit()%>"/>
                            <span class="order__unit">${unit}</span>
                        </label>
                        <p class="order__parameter-valid ps-2"> Giá trị khả dụng chấp nhận từ
                            <strong><span id="minValueParameter<%=i%>" class="order__parameter-min"><%=parameter.getMinValue()%></span> ${unit}
                            </strong> đến
                            <strong><span id="maxValueParameter<%=i%>" class="order__parameter-max"><%=parameter.getMaxValue()%></span> ${unit}
                            </strong></p>
                        <p class="order__error ps-2"></p></label>
                </div>
                <%--Image--%>
                <div class="offset-1 col-6">
                    <div class="order__guide-parameter">
                        <img src="<%=CloudinaryUploadServices.getINSTANCE().getImage("parameter_guide", parameter.getGuideImg())%>" alt="">
                    </div>
                </div>
            </div>
        </div>
        <%i++;}%>
    </div>

    <div class="order__log">
        <div class="order__info">
            <div class="order__media">
                <%String firstImage = productFactory.getListImagesByProductId(product.getId()).get(0).getNameImage();%>
                <img src="<%=firstImage%>" alt="<%=firstImage%>" />
            </div>
            <div class="order__content"><h1 class="order__name"><%=product.getName()%></h1>
                <input type="text" value="<%=product.getId()%>" hidden="hidden" name="productId">
                <div class=" form__block order__group">
                    <c:set var="colors" value="<%=productFactory.getListColorsByProductId(product.getId())%>" />
                    <div class="form__choose-color"><p class="form__title">Màu sắc</p>
                        <c:forEach var="color" items="${colors}">
                            <label class="form__color-check" style="background-color: ${color.codeColor}">
                                <input type="radio" name="color" hidden="hidden" value="${color.codeColor}">
                            </label>
                        </c:forEach>
                    </div>
                    <p class="order__error"></p>
                </div>

                <div class="form__block order__group">
                    <div class="form__quantity-wrapper"><p class="form__title">Số lượng</p>
                        <div class="form__quantity">
                            <div class="form__quantity-inner">
                                <div class="form___quantity-btn form___quantity--decrease"></div>
                                <input id="quantity" type="text" name="quantity" value="1" readonly>
                                <div class="form___quantity-btn form___quantity--increase"></div>
                            </div>
                        </div>
                    </div>
                    <p class="order__error"></p></div>
            </div>
        </div>
        <button id="order__submit" type="submit" class="order__submit-cart button--hover button"> Thêm vào giỏ
            hàng
        </button>
    </div>
</form>
<c:import url="/public/footer.jsp" />
</body>
<script src="<c:url value="/js/validateForm.js"/>"></script>
<script src="<c:url value="/js/productOrder.js"/>"></script>
</html>