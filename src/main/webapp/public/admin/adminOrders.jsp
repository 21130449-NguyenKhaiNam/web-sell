<jsp:useBean id="adminOrderServices" class="services.AdminOrderServices"/>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"--%>
    <%--          integrity="sha512-nMNlpuaDPrqlEls3IX/Q56H36qvBASwb3ipuo3MxeWbsQB1881ox0cRv7UPTgBlriqoynt35KjEwgGUeUXIPnw=="--%>
    <%--          crossorigin="anonymous" referrerpolicy="no-referrer"/>--%>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminOrders.css"/>">
    <title>Admin</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminProducts.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Sản
                        phẩm</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                       class="navbar__link button button button--hover navbar__link--clicked hvr-grow-shadow">Đơn
                        hàng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Người dùng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Nhận xét</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Phân loại</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/dashboard.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Thống kê</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
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
                                                            <input type="checkbox" name="deliveryMethod"
                                                                   <c:if test="${listCheckedDMS != null && listCheckedDMS.contains(String.valueOf(deliveryMethod.id))}">checked</c:if>
                                                                   value="${deliveryMethod.id}" class="filter__input"
                                                                   hidden="true">
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
                        </form>
                    </aside>
                </div>
                <div class="col-8">
                    <form id="process__order--form" action="ProcessOrderAdmin" method="post">
                        <div class="order__heading">
                            <h1><i class="fa-solid fa-list"></i> Danh sách đơn hàng</h1>
                            <div class="delete__cancel">
                                <div class="delete__wrapper hvr-bounce-out">
                                    <div class="button button__delete">
                                        <i class="fa-solid fa-trash"></i>
                                        Xóa đơn hàng
                                    </div>
                                </div>
                                <div class="cancel__wrapper hvr-bounce-out">
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

<script src="<c:url value="/js/paging.js"/>"></script>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"--%>
<%--        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="--%>
<%--        crossorigin="anonymous" referrerpolicy="no-referrer"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"
        integrity="sha512-2ImtlRlf2VVmiGZsjm9bEyhjGW4dU7B6TNwh/hx/iSByxNENtj3WVE6o/9Lj4TJeVXPi4bnOIMXFIJJAeufa0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
</script>
<script src="<c:url value="/js/admin/adminOrders.js" />"></script>
</body>
</html>