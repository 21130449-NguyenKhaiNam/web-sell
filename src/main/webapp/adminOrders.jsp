<jsp:useBean id="adminOrderServices" class="services.AdminOrderServices"/>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">

    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/admin/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"
          integrity="sha512-nMNlpuaDPrqlEls3IX/Q56H36qvBASwb3ipuo3MxeWbsQB1881ox0cRv7UPTgBlriqoynt35KjEwgGUeUXIPnw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="assets/css/admin/adminOrders.css">
    <title>Admin</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="adminProducts.jsp"
                                                class="navbar__link button button button--hover navbar__link--clicked">Sản phẩm</a>
                </li>
                <li class="navbar__item"><a href="adminOrders.jsp"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="AdminUser"
                                            class="navbar__link button button button--hover ">Khách hàng</a>
                </li>
                <li class="navbar__item"><a href="adminReviews.jsp"
                                            class="navbar__link button button button--hover ">Nhận xét</a>
                </li>
                <li class="navbar__item"><a href="adminCategories.jsp"
                                            class="navbar__link button button button--hover ">Phân loại</a>
                </li>
                <li class="navbar__item"><a href="Dashboard"
                                            class="navbar__link button button button--hover ">Thống kê</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <%--                                <div class="col-3">--%>
                <%--                                    <aside class="filler">--%>
                <%--                                        <form class="form" action="FilterOrderAdmin" method="get">--%>
                <%--                                            <article action="#!" class="filler__block">--%>
                <%--                                                <h2 class="filler__heading">Thời gian đặt hàng</h2>--%>
                <%--                                                <div class="interval__filler">--%>
                <%--                                                    <div class="filler__date">--%>
                <%--                                                        <span class="filler__title">Từ:</span>--%>
                <%--                                                        <input type="date" name="startDate"--%>
                <%--                                                               <c:if test="${requestScope.startDateFiltered != null}">value="${requestScope.startDateFiltered}"</c:if>--%>
                <%--                                                               id="date-start">--%>
                <%--                                                    </div>--%>
                <%--                                                    <div class="filler__date">--%>
                <%--                                                        <span class="filler__title">Đến:</span>--%>
                <%--                                                        <input type="date" name="endDate"--%>
                <%--                                                               <c:if test="${requestScope.endDateFiltered != null}">value="${requestScope.endDateFiltered}"</c:if>--%>
                <%--                                                               id="date-end">--%>
                <%--                                                    </div>--%>
                <%--                                                </div>--%>
                <%--                                            </article>--%>

                <%--                                            <article class="filler__block">--%>
                <%--                                                <h2 class="filler__heading">Phương thức vận chuyển</h2>--%>
                <%--                                                <c:set var="listCheckedDMS" value="${requestScope.listCheckedDeliveryMethods}"/>--%>
                <%--                                                <c:forEach items="${requestScope.listAllDeliveryMethodManage}" var="deliveryMethod">--%>
                <%--                                                    <label class="filter__label check">--%>
                <%--                                                        <input type="checkbox" name="deliveryMethod"--%>
                <%--                                                               <c:if test="${listCheckedDMS != null && listCheckedDMS.contains(String.valueOf(deliveryMethod.id))}">checked</c:if>--%>
                <%--                                                               value="${deliveryMethod.id}" class="filter__input" hidden="true">--%>
                <%--                                                        <span>${deliveryMethod.typeShipping}</span>--%>
                <%--                                                    </label>--%>
                <%--                                                </c:forEach>--%>
                <%--                                            </article>--%>

                <%--                                            <article class="filler__payment-method filler__block">--%>
                <%--                                                <h2 class="filler__heading">Phương thức thanh toán</h2>--%>
                <%--                                                <c:set var="listCheckedPMS" value="${requestScope.listCheckedPaymentMethods}"/>--%>
                <%--                                                <c:forEach items="${requestScope.listAllPaymentMethodManage}" var="paymentMethod">--%>
                <%--                                                    <label class="filter__label check">--%>
                <%--                                                        <input type="checkbox" name="paymentMethod"--%>
                <%--                                                               <c:if test="${listCheckedPMS != null && listCheckedPMS.contains(String.valueOf(paymentMethod.id))}">checked</c:if>--%>
                <%--                                                               value="${paymentMethod.id}" class="filter__input" hidden="true">--%>
                <%--                                                        <span>${paymentMethod.typePayment}</span>--%>
                <%--                                                    </label>--%>
                <%--                                                </c:forEach>--%>
                <%--                                            </article>--%>

                <%--                                            <article class="filler__block">--%>
                <%--                                                <h2 class="filler__heading">Tình trạng đơn hàng</h2>--%>
                <%--                                                <c:set var="listCheckedOS" value="${requestScope.listCheckedOrderStatus}"/>--%>
                <%--                                                <c:forEach items="${requestScope.listAllOrderStatus}" var="orderStatus">--%>
                <%--                                                    <label class="filter__label check">--%>
                <%--                                                        <input type="checkbox" name="orderStatus"--%>
                <%--                                                               <c:if test="${listCheckedOS != null && listCheckedOS.contains(String.valueOf(orderStatus.id))}">checked</c:if>--%>
                <%--                                                               value="${orderStatus.id}" class="filter__input" hidden="true">--%>
                <%--                                                        <span>${orderStatus.typeStatus}</span>--%>
                <%--                                                    </label>--%>
                <%--                                                </c:forEach>--%>
                <%--                                            </article>--%>

                <%--                                            <article class="filler__block">--%>
                <%--                                                <h2 class="filler__heading">Tình trạng giao dịch</h2>--%>
                <%--                                                <c:set var="listCheckedTS" value="${requestScope.listCheckedTransactionStatus}"/>--%>
                <%--                                                <c:forEach items="${requestScope.listAllTransactionStatus}" var="transactionStatus">--%>
                <%--                                                    <label class="filter__label check">--%>
                <%--                                                        <input type="checkbox" name="transactionStatus"--%>
                <%--                                                               <c:if test="${listCheckedTS != null && listCheckedTS.contains(String.valueOf(transactionStatus.id))}">checked</c:if>--%>
                <%--                                                               value="${transactionStatus.id}"--%>
                <%--                                                               class="filter__input" hidden="true">--%>
                <%--                                                        <span>${transactionStatus.typeStatus}</span>--%>
                <%--                                                    </label>--%>
                <%--                                                </c:forEach>--%>
                <%--                                            </article>--%>
                <%--                                            <div class="filter__buttons">--%>
                <%--                                                <input type="reset" class="button filter__button button--hover" value="Hoàn tác">--%>
                <%--                                                <button class="button filter__button button--hover" type="submit">Lọc</button>--%>
                <%--                                            </div>--%>
                <%--                                        </form>--%>
                <%--                                    </aside>--%>
                <%--                                </div>--%>
                <%--                                <div class="col-9">--%>
                <%--                                    <form action="SearchOrderAdmin" method="get" class=form__search>--%>
                <%--                                        <article action="#!" class="form__search-block">--%>
                <%--                                            <i class="search__icon fa-solid fa-magnifying-glass"></i>--%>
                <%--                                            <input type="text"--%>
                <%--                                                   <c:if test="${requestScope.contentSearched != null}">value="${requestScope.contentSearched}"</c:if>--%>
                <%--                                                   name="contentSearch">--%>
                <%--                                            <select name="searchSelect" class="search__select">--%>
                <%--                                                <option value="orderId" <c:if test="${requestScope.searchSelected eq 'orderId'}">selected</c:if> class="search__option">Mã đơn hàng</option>--%>
                <%--                                                <option value="customerName" <c:if test="${requestScope.searchSelected eq 'customerName'}">selected</c:if> class="search__option">Tên khách hàng</option>--%>
                <%--                                            </select>--%>
                <%--                                        </article>--%>
                <%--                                        &lt;%&ndash;                        <div class="button button__delete">&ndash;%&gt;--%>
                <%--                                        &lt;%&ndash;                            <i class="fa-solid fa-minus"></i>&ndash;%&gt;--%>
                <%--                                        &lt;%&ndash;                            Xóa đơn hàng&ndash;%&gt;--%>
                <%--                                        &lt;%&ndash;                        </div>&ndash;%&gt;--%>
                <%--                                    </form>--%>
                <%--                                    &lt;%&ndash;                    ${pageContext.servletContext.getAttribute("listOrderStatus")}&ndash;%&gt;--%>
                <%--                                    <div class="table__wrapper">--%>

                <%--                                        <h1>Danh sách đơn hàng<i class="fa-solid fa-arrows-rotate"></i></h1>--%>
                <%--                                        <table class="table table__order">--%>
                <%--                                            <thead>--%>
                <%--                                            <tr class="table__row">--%>
                <%--                                                <th class="table__checkbox"></th>--%>
                <%--                                                <th class="table__head">Cập nhật</th>--%>
                <%--                                                <th class="table__head">Mã đơn hàng</th>--%>
                <%--                                                <th class="table__head">Ngày tạo</th>--%>
                <%--                                                <th class="table__head">Khách hàng</th>--%>
                <%--                                                <th class="table__head">Phương thức vận chuyển</th>--%>
                <%--                                                <th class="table__head">Phương thức thanh toán</th>--%>
                <%--                                                <th class="table__head">Tình trạng đơn hàng</th>--%>
                <%--                                                <th class="table__head">Tình trạng giao dịch</th>--%>
                <%--                                                <th class="table__head">Tổng cộng</th>--%>
                <%--                                            </tr>--%>
                <%--                                            </thead>--%>
                <%--                                            <tbody>--%>
                <%--                &lt;%&ndash;                            <c:set var="listOrders" value="${requestScope.listAllOrders}"/>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            <c:if test="${requestScope.listOrderFiltered != null}">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                                <c:set var="listOrders" value="${requestScope.listOrderFiltered}"/>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            <c:if test="${requestScope.listOrderById != null}">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                                <c:set var="listOrders" value="${requestScope.listOrderById}"/>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            <c:if test="${requestScope.listOrderByCustomerName != null}">&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                                <c:set var="listOrders" value="${requestScope.listOrderByCustomerName}"/>&ndash;%&gt;--%>
                <%--                &lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
                <%--                                            <c:forEach items="${requestScope.listOrdersPerPage}" var="order">--%>
                <%--                                                <tr class="table__row">--%>
                <%--                                                    <td class="table__data"></td>--%>
                <%--                                                    <td class="table__data"><i class="fa-solid fa-pen-to-square"></i></td>--%>
                <%--                                                    <td class="table__data">${order.id}</td>--%>
                <%--                                                    <td class="table__data">${order.dateOrder}</td>--%>
                <%--                                                    <td class="table__data">${order.fullName}</td>--%>
                <%--                                                    <td class="table__data">${adminOrderServices.getDeliveryMethodManageById(order.deliveryMethodId).typeShipping}</td>--%>
                <%--                                                    <td class="table__data">${adminOrderServices.getPaymentMethodMangeById(order.paymentMethodId).typePayment}</td>--%>
                <%--                                                    <td class="table__data">${adminOrderServices.getOrderStatusById(order.orderStatusId).typeStatus}</td>--%>
                <%--                                                    <td class="table__data">${adminOrderServices.getTransactionStatusById(order.transactionStatusId).typeStatus}</td>--%>
                <%--                                                    <td class="table__data"></td>--%>
                <%--                                                </tr>--%>
                <%--                                            </c:forEach>--%>
                <%--                                            </tbody>--%>
                <%--                                        </table>--%>
                <%--                                        <!--Paging-->--%>
                <%--                                        <c:set value="${requestScope.page}" var="page"/>--%>
                <%--                                        <div class="pagination">--%>
                <%--                                            ${requestScope.queryStringFilter}--%>
                <%--                                            <c:if test="${page > 1}">--%>
                <%--                                                <c:url var="prevURLPage" value="${requestScope.servletProcess}?${requestScope.queryStringFilter}">--%>
                <%--                                                    <c:param name="page" value="${page - 1}"/>--%>
                <%--                                                </c:url>--%>
                <%--                                                <a href="${prevURLPage}" class="previous__page"><i class="fa-solid fa-chevron-left"></i></a>--%>
                <%--                                            </c:if>--%>
                <%--                                            <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">--%>
                <%--                                                <c:url var="trURLPage" value="${requestScope.servletProcess}?${requestScope.queryStringFilter}">--%>
                <%--                                                    <c:param name="page" value="${i}"/>--%>
                <%--                                                </c:url>--%>
                <%--                                                <a class="${i == page ? "active" : "page__forward"}" href="${trURLPage}">${i}</a>--%>
                <%--                                            </c:forEach>--%>
                <%--                                            <c:if test="${page < requestScope.totalPage}">--%>
                <%--                                                <c:url var="nextURLPage" value="${requestScope.servletProcess}?${requestScope.queryStringFilter}">--%>
                <%--                                                    <c:param name="page" value="${page + 1}"/>--%>
                <%--                                                </c:url>--%>
                <%--                                                <a href="${nextURLPage}" class="next__page"><i class="fa-solid fa-chevron-right"></i></a>--%>
                <%--                                            </c:if>--%>
                <%--                                        </div>--%>
                <%--                                    </div>--%>
                <%--                                </div>--%>
                <div class="col-4">
                    <aside class="filler">
                        <form class="form" action="SearchFilterOrderAdmin" method="get">
                            <div class="search__filter">
                                <article class="form__search-block">
                                    <i class="search__icon fa-solid fa-magnifying-glass"></i>
                                    <input type="text" name="contentSearch"
                                           <c:if test="${requestScope.contentSearched != null}">value="${requestScope.contentSearched}"</c:if>
                                           placeholder="Nhập nội dung tìm kiếm">
                                    <select name="searchSelect" class="search__select">
                                        <option value="orderId"
                                                <c:if test="${requestScope.searchSelected eq 'orderId'}">selected</c:if>
                                                class="search__option">Mã đơn hàng
                                        </option>
                                        <option value="customerName"
                                                <c:if test="${requestScope.searchSelected eq 'customerName'}">selected</c:if>
                                                class="search__option">Tên khách hàng
                                        </option>
                                    </select>
                                </article>
                                <%--                                <div class="filter__swapper">--%>
                                <%--                                    <span class="filter__menu"><i class="fa-solid fa-filter"></i>Tìm kiếm nâng cao với bộ lọc</span>--%>
                                <%--                                    <div class="group__filter">--%>
                                <%--                                        <div class="sections__filter">--%>
                                <%--                                            <article class="filler__block">--%>
                                <%--                                                <div class="filler__container">--%>
                                <%--                                                    <h2 class="filler__heading">Thời gian đặt hàng</h2>--%>
                                <%--                                                    <div class="interval__filler">--%>
                                <%--                                                        <div class="filler__date">--%>
                                <%--                                                            <span class="filler__title">Từ ngày</span>--%>
                                <%--                                                            <input type="date" name="startDate"--%>
                                <%--                                                                   <c:if test="${requestScope.startDateFiltered != null}">value="${requestScope.startDateFiltered}"</c:if>--%>
                                <%--                                                                   id="date-start">--%>
                                <%--                                                        </div>--%>
                                <%--                                                        <div class="filler__date">--%>
                                <%--                                                            <span class="filler__title">Đến ngày</span>--%>
                                <%--                                                            <input type="date" name="endDate"--%>
                                <%--                                                                   <c:if test="${requestScope.endDateFiltered != null}">value="${requestScope.endDateFiltered}"</c:if>--%>
                                <%--                                                                   id="date-end">--%>
                                <%--                                                        </div>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </article>--%>
                                <%--                                            <article class="filler__block">--%>
                                <%--                                                <div class="filler__container">--%>
                                <%--                                                    <h2 class="filler__heading">Phương thức vận chuyển</h2>--%>
                                <%--                                                    <c:set var="listCheckedDMS" value="${requestScope.listCheckedDeliveryMethods}"/>--%>
                                <%--                                                    <div class="filter__content">--%>
                                <%--                                                        <c:forEach items="${requestScope.listAllDeliveryMethodManage}" var="deliveryMethod">--%>
                                <%--                                                            <label class="filter__label check">--%>
                                <%--                                                                <input type="checkbox" name="deliveryMethod"--%>
                                <%--                                                                       <c:if test="${listCheckedDMS != null && listCheckedDMS.contains(String.valueOf(deliveryMethod.id))}">checked</c:if>--%>
                                <%--                                                                       value="${deliveryMethod.id}" class="filter__input" hidden="true">--%>
                                <%--                                                                <span>${deliveryMethod.typeShipping}</span>--%>
                                <%--                                                            </label>--%>
                                <%--                                                        </c:forEach>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </article>--%>
                                <%--                                            <article class="filler__block">--%>
                                <%--                                                <div class="filler__container">--%>
                                <%--                                                    <h2 class="filler__heading">Phương thức thanh toán</h2>--%>
                                <%--                                                    <c:set var="listCheckedPMS" value="${requestScope.listCheckedPaymentMethods}"/>--%>
                                <%--                                                    <div class="filter__content">--%>
                                <%--                                                        <c:forEach items="${requestScope.listAllPaymentMethodManage}" var="paymentMethod">--%>
                                <%--                                                            <label class="filter__label check">--%>
                                <%--                                                                <input type="checkbox" name="paymentMethod"--%>
                                <%--                                                                       <c:if test="${listCheckedPMS != null && listCheckedPMS.contains(String.valueOf(paymentMethod.id))}">checked</c:if>--%>
                                <%--                                                                       value="${paymentMethod.id}" class="filter__input" hidden="true">--%>
                                <%--                                                                <span>${paymentMethod.typePayment}</span>--%>
                                <%--                                                            </label>--%>
                                <%--                                                        </c:forEach>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </article>--%>
                                <%--                                            <article class="filler__block">--%>
                                <%--                                                <div class="filler__container">--%>
                                <%--                                                    <h2 class="filler__heading">Tình trạng đơn hàng</h2>--%>
                                <%--                                                    <c:set var="listCheckedOS" value="${requestScope.listCheckedOrderStatus}"/>--%>
                                <%--                                                    <div class="filter__content">--%>
                                <%--                                                        <c:forEach items="${requestScope.listAllOrderStatus}" var="orderStatus">--%>
                                <%--                                                            <label class="filter__label check">--%>
                                <%--                                                                <input type="checkbox" name="orderStatus"--%>
                                <%--                                                                       <c:if test="${listCheckedOS != null && listCheckedOS.contains(String.valueOf(orderStatus.id))}">checked</c:if>--%>
                                <%--                                                                       value="${orderStatus.id}" class="filter__input" hidden="true">--%>
                                <%--                                                                <span>${orderStatus.typeStatus}</span>--%>
                                <%--                                                            </label>--%>
                                <%--                                                        </c:forEach>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </article>--%>
                                <%--                                            <article class="filler__block">--%>
                                <%--                                                <div class="filler__container">--%>
                                <%--                                                    <h2 class="filler__heading">Tình trạng giao dịch</h2>--%>
                                <%--                                                    <c:set var="listCheckedTS" value="${requestScope.listCheckedTransactionStatus}"/>--%>
                                <%--                                                    <div class="filter__content">--%>
                                <%--                                                        <c:forEach items="${requestScope.listAllTransactionStatus}" var="transactionStatus">--%>
                                <%--                                                            <label class="filter__label check">--%>
                                <%--                                                                <input type="checkbox" name="transactionStatus"--%>
                                <%--                                                                       <c:if test="${listCheckedTS != null && listCheckedTS.contains(String.valueOf(transactionStatus.id))}">checked</c:if>--%>
                                <%--                                                                       value="${transactionStatus.id}"--%>
                                <%--                                                                       class="filter__input" hidden="true">--%>
                                <%--                                                                <span>${transactionStatus.typeStatus}</span>--%>
                                <%--                                                            </label>--%>
                                <%--                                                        </c:forEach>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </article>--%>
                                <%--                                        </div>--%>
                                <%--                                        <div class="button__control">--%>
                                <%--                                            <input type="button" class="close__filter" value="Đóng bộ lọc">--%>
                                <%--                                            <input type="reset" class="reset__filter" value="Hoàn tác tất cả"/>--%>
                                <%--                                            <input type="submit" class="apply__filter" value="Tìm kiếm và lọc đơn hàng"/>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>
                                <div class="group__filter">
                                    <div class="sections__filter">
                                        <article class="filler__block">
                                            <div class="filler__container">
                                                <h2 class="filler__heading">Thời gian đặt hàng</h2>
                                                <div class="interval__filler">
                                                    <div class="filler__date">
                                                        <span class="filler__title">Từ ngày</span>
                                                        <input type="date" name="startDate"
                                                               <c:if test="${requestScope.startDateFiltered != null}">value="${requestScope.startDateFiltered}"</c:if>
                                                               id="date-start">
                                                    </div>
                                                    <div class="filler__date">
                                                        <span class="filler__title">Đến ngày</span>
                                                        <input type="date" name="endDate"
                                                               <c:if test="${requestScope.endDateFiltered != null}">value="${requestScope.endDateFiltered}"</c:if>
                                                               id="date-end">
                                                    </div>
                                                </div>
                                            </div>
                                        </article>
                                        <article class="filler__block">
                                            <div class="filler__container">
                                                <h2 class="filler__heading">Phương thức vận chuyển</h2>
                                                <c:set var="listCheckedDMS"
                                                       value="${requestScope.listCheckedDeliveryMethods}"/>
                                                <div class="filter__content">
                                                    <c:forEach items="${requestScope.listAllDeliveryMethodManage}"
                                                               var="deliveryMethod">
                                                        <label class="filter__label check">
                                                            <input type="checkbox" name="deliveryMethod" <c:if test="${listCheckedDMS != null && listCheckedDMS.contains(String.valueOf(deliveryMethod.id))}">checked</c:if>
                                                                   value="${deliveryMethod.id}" class="filter__input" hidden="true">
                                                            <span>${deliveryMethod.typeShipping}</span>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </article>
                                        <article class="filler__block">
                                            <div class="filler__container">
                                                <h2 class="filler__heading">Phương thức thanh toán</h2>
                                                <c:set var="listCheckedPMS"
                                                       value="${requestScope.listCheckedPaymentMethods}"/>
                                                <div class="filter__content">
                                                    <c:forEach items="${requestScope.listAllPaymentMethodManage}"
                                                               var="paymentMethod">
                                                        <label class="filter__label check">
                                                            <input type="checkbox" name="paymentMethod"
                                                                   <c:if test="${listCheckedPMS != null && listCheckedPMS.contains(String.valueOf(paymentMethod.id))}">checked</c:if>
                                                                   value="${paymentMethod.id}" class="filter__input"
                                                                   hidden="true">
                                                            <span>${paymentMethod.typePayment}</span>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </article>
                                        <article class="filler__block">
                                            <div class="filler__container">
                                                <h2 class="filler__heading">Tình trạng đơn hàng</h2>
                                                <c:set var="listCheckedOS"
                                                       value="${requestScope.listCheckedOrderStatus}"/>
                                                <div class="filter__content">
                                                    <c:forEach items="${requestScope.listAllOrderStatus}"
                                                               var="orderStatus">
                                                        <label class="filter__label check">
                                                            <input type="checkbox" name="orderStatus"
                                                                   <c:if test="${listCheckedOS != null && listCheckedOS.contains(String.valueOf(orderStatus.id))}">checked</c:if>
                                                                   value="${orderStatus.id}" class="filter__input"
                                                                   hidden="true">
                                                            <span>${orderStatus.typeStatus}</span>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </article>
                                        <article class="filler__block">
                                            <div class="filler__container">
                                                <h2 class="filler__heading">Tình trạng giao dịch</h2>
                                                <c:set var="listCheckedTS"
                                                       value="${requestScope.listCheckedTransactionStatus}"/>
                                                <div class="filter__content">
                                                    <c:forEach items="${requestScope.listAllTransactionStatus}"
                                                               var="transactionStatus">
                                                        <label class="filter__label check">
                                                            <input type="checkbox" name="transactionStatus"
                                                                   <c:if test="${listCheckedTS != null && listCheckedTS.contains(String.valueOf(transactionStatus.id))}">checked</c:if>
                                                                   value="${transactionStatus.id}"
                                                                   class="filter__input" hidden="true">
                                                            <span>${transactionStatus.typeStatus}</span>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </article>
                                    </div>
                                    <div class="button__control">
                                        <input type="submit" class="apply__filter" value="Tìm kiếm và lọc đơn hàng"/>
                                        <input type="reset" class="reset__filter" value="Hoàn tác tất cả"/>
                                    </div>
                                </div>
                            </div>

                            <!-- <div class="filter__buttons">
                                <input type="reset" class="button filter__button button--hover" value="Chọn lại">
                                <button class="button filter__button button--hover" type="submit">Lọc</button>
                            </div> -->
                        </form>
                    </aside>
                </div>
                <div class="col-8">
                    <!-- <form action="" class=form__search>
                        <article action="#!" class="form__search-block">
                            <i class="search__icon fa-solid fa-magnifying-glass"></i>
                            <input type="text" name="search">
                            <select name="" id="" class="search__select">
                                <option value="" class="search__option">Mã đơn hàng</option>
                                <option value="" class="search__option">Tên khách hàng</option>
                            </select>
                        </article>
                        <div class="button button__delete">
                            <i class="fa-solid fa-minus"></i>
                            Xóa đơn hàng
                        </div>
                    </form> -->
                    <%--                    <div class="table__wrapper">--%>
                    <%--                        <table class="table table__order">--%>
                    <%--                            <thead>--%>
                    <%--                            <tr class="table__row">--%>
                    <%--                                <th class="table__checkbox"></th>--%>
                    <%--                                <th class="table__head">Cập nhật</th>--%>
                    <%--                                <th class="table__head">Mã đơn hàng</th>--%>
                    <%--                                <th class="table__head">Ngày tạo</th>--%>
                    <%--                                <th class="table__head">Tên khách hàng</th>--%>
                    <%--                                <th class="table__head">Phương thức thanh toán</th>--%>
                    <%--                                <th class="table__head">Trạng thái đơn hàng</th>--%>
                    <%--                                <th class="table__head">Trạng thái giao dịch</th>--%>
                    <%--                                <!--                                <th class="table__head">Trạng thái vận chuyển</th>-->--%>
                    <%--                                <th class="table__head">Tổng tiền</th>--%>
                    <%--                            </tr>--%>
                    <%--                            </thead>--%>
                    <%--                            <tbody>--%>

                    <%--                            </tbody>--%>
                    <%--                        </table>--%>
                    <%--                        <!--Paging-->--%>
                    <%--                        <ul class="paging"></ul>--%>
                    <%--                    </div>--%>
                    <form id="process__order--form" action="ProcessOrderAdmin" method="post">
                        <div class="order__heading">
                            <h1><i class="fa-solid fa-list"></i> Danh sách đơn hàng</h1>
                            <div class="delete__cancel">
                                <div class="delete__wrapper">
                                    <div class="button button__delete">
                                        <i class="fa-solid fa-trash"></i>
                                        Xóa đơn hàng
                                    </div>
                                </div>
                                <div class="cancel__wrapper">
                                    <div class="button button__cancel">
                                        <i class="fa-solid fa-ban"></i>
                                        Hủy đơn hàng
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table__wrapper">
                            <table class="table table__order">
                                <thead>
                                <tr class="table__row">
                                    <th class="table__checkbox"></th>
                                    <th class="table__head">Xem chi tiết</th>
                                    <th class="table__head">Cập nhật</th>
                                    <th class="table__head">Mã đơn hàng</th>
                                    <th class="table__head">Ngày tạo</th>
                                    <th class="table__head">Khách hàng</th>
                                    <th class="table__head">Phương thức vận chuyển</th>
                                    <th class="table__head">Phương thức thanh toán</th>
                                    <th class="table__head">Tình trạng đơn hàng</th>
                                    <th class="table__head">Tình trạng giao dịch</th>
                                    <th class="table__head">Tổng cộng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--                            <c:set var="listOrders" value="${requestScope.listAllOrders}"/>--%>
                                <%--                            <c:if test="${requestScope.listOrderFiltered != null}">--%>
                                <%--                                <c:set var="listOrders" value="${requestScope.listOrderFiltered}"/>--%>
                                <%--                            </c:if>--%>
                                <%--                            <c:if test="${requestScope.listOrderById != null}">--%>
                                <%--                                <c:set var="listOrders" value="${requestScope.listOrderById}"/>--%>
                                <%--                            </c:if>--%>
                                <%--                            <c:if test="${requestScope.listOrderByCustomerName != null}">--%>
                                <%--                                <c:set var="listOrders" value="${requestScope.listOrderByCustomerName}"/>--%>
                                <%--                            </c:if>--%>
                                <c:forEach items="${requestScope.listOrdersPerPage}" var="order">
                                    <c:set var="notAllow" value="${order.orderStatusId == 5 ? 'not__allow' : ''}"/>
                                    <tr class="table__row ${notAllow}">
                                        <td class="table__data">
                                            <label class="filter__label check">
                                                <input type="checkbox" name="multipleOrderId" value="${order.id}"
                                                       class="filter__input" hidden="true">
                                            </label>
                                        </td>
                                        <td class="table__data">
                                            <button type="button" name="seeDetailId" data-action="seeDetail"
                                                    value="${order.id}" class="table__cell see__detail">
                                                <i class="fa-solid fa-eye"></i>
                                            </button>
                                        </td>
                                        <td class="table__data">
                                            <button type="button" name="showDialogUpdateId" value="${order.id}"
                                                    data-action="showDialogUpdate" class="table__cell show__update">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </button>
                                        </td>
                                        <td class="table__data"><p class="table__cell">${order.id}</p></td>
                                        <td class="table__data"><p class="table__cell">${order.dateOrder}</p></td>
                                        <td class="table__data"><p class="table__cell">${order.fullName}</p></td>
                                        <td class="table__data"><p
                                                class="table__cell">${adminOrderServices.getDeliveryMethodManageById(order.deliveryMethodId).typeShipping}</p>
                                        </td>
                                        <td class="table__data"><p
                                                class="table__cell">${adminOrderServices.getPaymentMethodMangeById(order.paymentMethodId).typePayment}</p>
                                        </td>
                                        <td class="table__data data__status order"><p
                                                class="table__cell">${adminOrderServices.getOrderStatusById(order.orderStatusId).typeStatus}</p>
                                        </td>
                                        <td class="table__data data__status transaction"><p
                                                class="table__cell">${adminOrderServices.getTransactionStatusById(order.transactionStatusId).typeStatus}</p>
                                        </td>
                                        <td class="table__data">${adminOrderServices.getTotalPriceFormatByOrderId(order.id)}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!--Paging-->
                        <c:set value="${requestScope.page}" var="page"/>
                        <c:set value="${requestScope.queryStringFilter}" var="queryStringFilter"/>
                        <div class="pagination">
                            <c:if test="${page > 1}">
                                <c:url var="prevURLPage"
                                       value="${requestScope.servletProcess}?${queryStringFilter != null ? queryStringFilter : ''}">
                                    <c:param name="page" value="${page - 1}"/>
                                </c:url>
                                <a href="${prevURLPage.replaceAll('&$', '')}" class="previous__page"><i
                                        class="fa-solid fa-chevron-left"></i></a>
                            </c:if>
                            <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">
                                <c:url var="trURLPage"
                                       value="${requestScope.servletProcess}?${queryStringFilter != null ? queryStringFilter : ''}">
                                    <c:param name="page" value="${i}"/>
                                </c:url>
                                <a class="${i == page ? "active" : "page__forward"}"
                                   href="${trURLPage.replaceAll('&$', '')}">${i}</a>
                            </c:forEach>
                            <c:if test="${page < requestScope.totalPage}">
                                <c:url var="nextURLPage"
                                       value="${requestScope.servletProcess}?${queryStringFilter != null ? queryStringFilter : ''}">
                                    <c:param name="page" value="${page + 1}"/>
                                </c:url>
                                <a href="${nextURLPage.replaceAll('&$', '')}" class="next__page"><i
                                        class="fa-solid fa-chevron-right"></i></a>
                            </c:if>
                        </div>
                        <div class="popup__deletion"></div>
                        <div class="popup__cancel"></div>
                        <div class="popup__update">

                        </div>
                        <div id="dialog__order" class="modal">
                            <article class="modal__content modal__order">

                            </article>
                            <div class="modal__blur"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>
<%--<div class="popup__deletion"></div>--%>

<%--<div id="dialog-order-update" class="modal">--%>
<%--    <article class="modal__content modal__product modal__order">--%>
<%--        <div class="head__dialog--bar">--%>
<%--            <h1>Chỉnh sửa  thôngtin</h1>--%>
<%--            <i class="modal__product-close modal__order-close fa-solid fa-xmark"></i>--%>
<%--        </div>--%>
<%--        <form action="#!" class="order__update--form">--%>
<%--            <div class="grid__template">--%>
<%--                <div class="grid__block grid__productsBought">--%>
<%--                    <span class="grid__area--title">Thông tin đơn hàng</span>--%>
<%--                    <div class="products__bought--container">--%>
<%--                        <span class="order__id">Mã đơn hàng: 2</span>--%>
<%--                        <span class="order__date">Ngày tạo: 29-10-2022</span>--%>
<%--                        <span class="order__payment">Phương thức thanh toán: Chuyển khoản ngân hàng</span>--%>
<%--                        <span class="order__voucher">Mã giảm giá áp dụng: MGG50</span>--%>
<%--                        <span class="order__products">Các sản phẩm đã đặt</span>--%>
<%--                        <div class="order__swapper">--%>
<%--                            <table class="order__table">--%>
<%--                                <thead>--%>
<%--                                <tr class="row__header">--%>
<%--                                    <th class="thead__item"></th>--%>
<%--                                    <th class="thead__item">Sản phẩm</th>--%>
<%--                                    <th class="thead__item">Màu sắc</th>--%>
<%--                                    <th class="thead__item">Kích thước</th>--%>
<%--                                    <th class="thead__item">Số lượng</th>--%>
<%--                                    <th class="thead__item">Giá may</th>--%>
<%--                                </tr>--%>
<%--                                </thead>--%>
<%--                                <tbody>--%>
<%--                                <tr class="row__content">--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">--%>
<%--                                        <div class="product__item">--%>
<%--                                            <img src="assets/img/product_img/product1.jpg">--%>
<%--                                            <p class="product__name">Áo polo nam trơn basic form regular vải cá sấu</p>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                    <td class="td__item">Đen</td>--%>
<%--                                    <td class="td__item">XXL</td>--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">279.000&nbsp;₫</td>--%>
<%--                                </tr>--%>
<%--                                <tr class="row__content">--%>
<%--                                    <td class="td__item">2</td>--%>
<%--                                    <td class="td__item">--%>
<%--                                        <div class="product__item">--%>
<%--                                            <img src="assets/img/product_img/product2.jpg">--%>
<%--                                            <p class="product__name">Áo polo thể thao unisex nam nữ</p>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                    <td class="td__item">Xanh navy</td>--%>
<%--                                    <td class="td__item">XXL</td>--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">239.000&nbsp;₫</td>--%>
<%--                                </tr>--%>

<%--                                <tr class="row__content">--%>
<%--                                    <td class="td__item">3</td>--%>
<%--                                    <td class="td__item">--%>
<%--                                        <div class="product__item">--%>
<%--                                            <img src="assets/img/product_img/product3.jpg">--%>
<%--                                            <p class="product__name">Áo polo phối khóa kéo unisex nam nữ</p>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                    <td class="td__item">Beige</td>--%>
<%--                                    <td class="td__item">XXL</td>--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">289.000&nbsp;₫</td>--%>
<%--                                </tr>--%>
<%--                                <tr class="row__content">--%>
<%--                                    <td class="td__item">4</td>--%>
<%--                                    <td class="td__item">--%>
<%--                                        <div class="product__item">--%>
<%--                                            <img src="assets/img/product_img/product4.jpg">--%>
<%--                                            <p class="product__name">Áo hoodie form rộng unisex nam nữ nỉ chân cua</p>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                    <td class="td__item">Đen</td>--%>
<%--                                    <td class="td__item">XL</td>--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">259.000&nbsp;₫</td>--%>
<%--                                </tr>--%>
<%--                                <tr class="row__content">--%>
<%--                                    <td class="td__item">5</td>--%>
<%--                                    <td class="td__item">--%>
<%--                                        <div class="product__item">--%>
<%--                                            <img src="assets/img/product_img/product5.jpg">--%>
<%--                                            <p class="product__name">Áo hoodie zip form boxy unisex nỉ bông dày</p>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                    <td class="td__item">Xanh lá</td>--%>
<%--                                    <td class="td__item">X</td>--%>
<%--                                    <td class="td__item">1</td>--%>
<%--                                    <td class="td__item">250.000&nbsp;₫</td>--%>
<%--                                </tr>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <button class="update__button">Cập nhật</button>--%>
<%--                </div>--%>
<%--                <div class=" grid__block grid__DeliveryInfo">--%>
<%--                    <span class="grid__area--title">Thông tin giao hàng</span>--%>
<%--                    <div class="info__items">--%>
<%--                        <div class="info__item">--%>
<%--                            <p class="info__text">Mã người dùng:</p>--%>
<%--                            <p class="info__value">7</p>--%>
<%--                        </div>--%>
<%--                        <div class="info__item">--%>
<%--                            <p class="info__text">Họ và tên:</p>--%>
<%--                            <p class="info__value">Cáp Hữu Ðạt</p>--%>
<%--                        </div>--%>
<%--                        <div class="info__item">--%>
<%--                            <p class="info__text">Email:</p>--%>
<%--                            <p class="info__value">HuuDat@gmail.com</p>--%>
<%--                        </div>--%>
<%--                        <div class="info__item">--%>
<%--                            <p class="info__text">Số điện thoại:</p>--%>
<%--                            <p class="info__value">0328012964</p>--%>
<%--                        </div>--%>
<%--                        <div class="info__item">--%>
<%--                            <p class="info__text">Địa chỉ:</p>--%>
<%--                            <p class="info__value">Xã Vĩnh Thạnh Trung, Huyện Châu Phú, An Giang</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--                <div class=" grid__block grid__updateStatus">--%>
<%--                    <span class="grid__area--title">Cập nhật trạng thái</span>--%>
<%--                    <div id="update__status--container">--%>
<%--                        <div class="select-menu order__status">--%>
<%--                            <div class="select-btn">--%>
<%--                                <span class="sBtn-text">Chọn trạng thái</span>--%>
<%--                                <i class="fa-solid fa-chevron-down"></i>--%>
<%--                            </div>--%>
<%--                            <ul class="options">--%>
<%--                                <li class="option">Đơn hàng mới</li>--%>
<%--                                <li class="option">Đã xác nhận</li>--%>
<%--                                <li class="option">Đang vận chuyển</li>--%>
<%--                                <li class="option">Hoàn thành</li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>

<%--                        <div class="select-menu transaction__status">--%>
<%--                            <div class="select-btn">--%>
<%--                                <span class="sBtn-text">Chọn trạng thái</span>--%>
<%--                                <i class="fa-solid fa-chevron-down"></i>--%>
<%--                            </div>--%>
<%--                            <ul class="options">--%>
<%--                                <li class="option">Chưa thanh toán</li>--%>
<%--                                <li class="option">Đã thanh toán</li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <p class="update__noted">Quản trị viên phải cập nhật trạng thái đơn hàng và trạng thái giao dịch để--%>
<%--                        khách hàng đễ dàng theo dõi tiến độ đơn hàng</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </article>--%>
<%--    <div class="modal__blur"></div>--%>
<%--</div>--%>


<%--<div id="dialog__order" class="modal">--%>
<%--    <article class="modal__content modal__order">--%>
<%--        <div class="head__dialog--bar">--%>
<%--            <h1 class="dialog__title"></h1>--%>
<%--            <span class="dialog__close"><i class="fa-solid fa-xmark"></i></span>--%>
<%--        </div>--%>
<%--        <div class="dialog__container">--%>

<%--        </div>--%>
<%--    </article>--%>
<%--    <div class="modal__blur"></div>--%>
<%--</div>--%>

<script src="js/data.js"></script>
<script src="js/paging.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"
        integrity="sha512-2ImtlRlf2VVmiGZsjm9bEyhjGW4dU7B6TNwh/hx/iSByxNENtj3WVE6o/9Lj4TJeVXPi4bnOIMXFIJJAeufa0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    // function handelUpdateStatusProcess(){
    //     $(document).ready(function (){
    //         $('.table__cell.update').on('click', function (){
    //             $.ajax({
    //                 url: '',
    //             })
    //         })
    //     })
    // }
    //
    // handelUpdateStatusProcess()
</script>
<script src="js/admin/adminOrders.js"></script>
</body>
</html>