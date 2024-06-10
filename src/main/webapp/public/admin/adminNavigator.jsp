<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <!--CK Editor-->
    <script src="<c:url value="/ckeditor/ckeditor.js"/>"></script>
    <!--Ck Finder-->
    <script src="<c:url value="/ckfinder/ckfinder.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminCategories.css"/>">
    <title>Trang quản trị hệ thống</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main class="main">
    <nav id="sidebar" class="ps ps--active-y">
        <div class="sidebar_blog_2">
            <h4 class="mt-2 display-6 text-center font-weight-bold">Chức năng</h4>
            <ul class="list-unstyled components">
                <li>
                    <a role="button">
                        <i class="fa-solid fa-shirt"></i>
                        <span>Sản phẩm</span></a>
                    <ul id="collapse_product">
                        <li class="sidebar_active">
                            <a href="#products" data-link="adminProducts.jsp" class="mb-2 pb-3 sidebar_item">
                                <i class="fa-solid fa-list-check"></i>
                                <span>Quản lý</span></a>
                        </li>

                        <li>
                            <a href="#reviews" data-link="adminReviews.jsp" class="mb-2 pb-3 sidebar_item">
                                <i class="fa-solid fa-square-poll-vertical"></i>
                                <span>Nhận xét</span></a>
                        </li>

                        <li>
                            <a href="#categories" data-link="adminCategories.jsp" class="pb-3 sidebar_item">
                                <i class="fa-solid fa-table-list"></i>
                                <span>Loại sản phẩm</span></a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#orders" data-link="adminOrders.jsp" class="sidebar_item">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <span>Đơn hàng</span></a>
                </li>

                <li>
                    <a href="#user" data-link="adminUsers.jsp" class="sidebar_item">
                        <i class="fa-solid fa-user"></i>
                        <span>Người dùng</span></a>
                </li>

                <li>
                    <%--                    <a href="#dashboard" data-link="dashboard.jsp" class="sidebar_item">--%>
                    <%--                        <i class="fa-solid fa-chart-simple"></i>--%>
                    <%--                        <span>Thống kê</span></a>--%>
                </li>
                <li>
                    <a href="#voucher" data-link="adminVoucher.jsp" class="sidebar_item">
                        <i class="fa-solid fa-ticket-simple"></i>
                        <span>Mã giảm giá</span></a>
                </li>
                <li>
                    <a href="#logs" data-link="adminLogs.jsp" class="sidebar_item">
                        <i class="fa-solid fa-book"></i>
                        <span>Nhật ký hệ thống</span></a>
                </li>
            </ul>
        </div>
        <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
            <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
        </div>
        <div class="ps__rail-y" style="top: 0px; height: 652px; right: 0px;">
            <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 562px;"></div>
        </div>
        <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
            <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
        </div>
        <div class="ps__rail-y" style="top: 0px; height: 652px; right: 0px;">
            <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 562px;"></div>
        </div>
    </nav>

    <div id="contain"></div>
</main>

<script>
    function isFirstVisit() {
        // Check LocalStorage for a specific item
        const isVisited = localStorage.getItem('hasVisited')
        if (isVisited) {
            localStorage.setItem('hasVisited', null);
        }
        return localStorage.getItem('hasVisited');
    }

    // Function to set the visit status
    function setVisited() {
        localStorage.setItem('hasVisited', 'true');
    }

    // Event handler for when the page is fully loaded
    window.addEventListener('load', function () {
        if (isFirstVisit()) {
            const defaultReload = $('.sidebar_active > .sidebar_item')[0]
            const path = defaultReload.dataset.link
            window.history.pushState(null, null, defaultReload.href);
        }
        // Set the visit status regardless of whether it's the first visit or not
        setVisited();
    });

    $(document).ready(function () {
        localStorage.setItem("link", window.location.href);

        let link = localStorage.getItem("link") || window.location.href
        let ind = link.indexOf("#")
        if (ind < 0) {
            const defaultReload = $('.sidebar_active > .sidebar_item')[0]
            const path = defaultReload.href
            link += path.substring(path.indexOf("#"), path.length)
        }
        const sub = link.substring(link.indexOf("#"), link.length)
        $('li > .sidebar_item').each(function () {
            let linkHref = this.href
            linkHref = linkHref.substring(linkHref.indexOf("#"), linkHref.length)
            if (linkHref == sub) {
                link = this
                $('.sidebar_active').removeClass('sidebar_active')
                this.parentElement.classList.add('sidebar_active')
                return;
            }
        })
        const defaultReload = link || $('.sidebar_active > .sidebar_item')[0]
        // Gọi tới đường dẫn
        const path = defaultReload.dataset.link
        window.history.pushState(null, null, defaultReload.href);
        $("#contain").load(path)


        $('.sidebar_item').on('click', function (event) {
            // Ngăn sự kiện chuyển trang
            event.preventDefault();

            // Thay đổi trạng thái active cho tag được nhấn
            $('.sidebar_active').removeClass('sidebar_active')
            this.classList.add('sidebar_active')

            // Gọi tới đường dẫn
            const path = this.dataset.link
            window.history.pushState(null, null, this.href);
            localStorage.setItem("link", window.location.href);
            $("#contain").load(path)
        })
    })
</script>
</body>
</html>
