<jsp:useBean id="adminOrderServices" class="services.admin.AdminOrderServices"/>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/modal.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminOrders.css"/>">
    <title>Quản lý đơn hàng</title>
</head>
<body>
<!--Header-->
<c:import url="/public/header.jsp"/>
<main id="main">
    <!--Navigate-->
    <c:import url="/public/admin/adminNavigator.jsp"/>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-4">
                    <aside class="filler">
                        <!--Form tìm kiếm đơn hàng-->
                        <form id="form-search" class="form" action="<c:url value="/api/admin/order/search"/>" method="get">
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
                    <form action="/exportExcelOrder" method="GET">
                        <button class="btn_export">
                            <i class="fa-solid fa-file-export"></i>
                            Xuất file excel
                        </button>
                    </form>
                    <!--Bảng thông tin đơn hàng -->
                    <form id="process__order--form" action="<c:url value="/api/admin/order"/>" method="post">
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
                                    <th class="table__head">Phương thức thanh toán</th>
                                    <th class="table__head">Tình trạng đơn hàng</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!--Paging-->
                        <div class="pagination">

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>

<div class="popup__deletion"></div>
<div class="popup__cancel"></div>
<div class="popup__update">

</div>
<div id="dialog__order" class="modal">
    <article class="modal__content modal__order">
    </article>
    <div class="modal__blur"></div>
</div>


<!-- Modal order-->
<%--<div class="modal fade modal-dialog-scrollable"  id="dialog__order" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog" style="max-width: 80%;">--%>
<%--        <div class="modal-content" >--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title h2" id="exampleModalLabel">Chi tiết đơn hàng</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body modal__order">--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<script src="<c:url value="/js/paging.js"/>"></script>
<script src="<c:url value="/js/admin/adminOrders.js" />"></script>
</body>
</html>