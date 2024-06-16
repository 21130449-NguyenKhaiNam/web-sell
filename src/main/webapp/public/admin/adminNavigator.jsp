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
    <!--JQuery-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--Select 2 jquery-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"
          integrity="sha512-nMNlpuaDPrqlEls3IX/Q56H36qvBASwb3ipuo3MxeWbsQB1881ox0cRv7UPTgBlriqoynt35KjEwgGUeUXIPnw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"
            integrity="sha512-2ImtlRlf2VVmiGZsjm9bEyhjGW4dU7B6TNwh/hx/iSByxNENtj3WVE6o/9Lj4TJeVXPi4bnOIMXFIJJAeufa0A=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--JQuery validator-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js"
            integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="<c:url value="/js/modal.js"/>"></script>
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
        const isVisited = localStorage.getItem('hasVisited');
        if (isVisited) {
            localStorage.setItem('hasVisited', null);
        }
        return localStorage.getItem('hasVisited');
    }

    // Function to set the visit status
    function setVisited() {
        localStorage.setItem('hasVisited', 'true');
    }

    // Function to load content based on the URL
    function loadContentFromURL(url) {
        let ind = url.indexOf("#");
        if (ind < 0) {
            const defaultReload = $('.sidebar_active > .sidebar_item')[0];
            const path = defaultReload.href;
            url += path.substring(path.indexOf("#"), path.length);
        }
        const sub = url.substring(url.indexOf("#"), url.length);
        $('li > .sidebar_item').each(function () {
            let linkHref = this.href;
            linkHref = linkHref.substring(linkHref.indexOf("#"), linkHref.length);
            if (linkHref == sub) {
                $('.sidebar_active').removeClass('sidebar_active');
                $(this).parent().addClass('sidebar_active');
                const path = this.dataset.link;
                $("#contain").load(path);
                return false; // Exit loop
            }
        });
    }

    window.addEventListener('load', function () {
        if (isFirstVisit()) {
            const defaultReload = $('.sidebar_active > .sidebar_item')[0];
            const path = defaultReload.dataset.link;
            window.history.pushState(null, null, defaultReload.href);
        }
        // Set the visit status regardless of whether it's the first visit or not
        setVisited();
    });

    $(document).ready(function () {
        localStorage.setItem("link", window.location.href);
        loadContentFromURL(window.location.href);

        $('.sidebar_item').on('click', function (event) {
            // Ngăn sự kiện chuyển trang
            event.preventDefault();

            // Thay đổi trạng thái active cho tag được nhấn
            $('.sidebar_active').removeClass('sidebar_active');
            $(this).parent().addClass('sidebar_active');

            // Gọi tới đường dẫn
            const path = this.dataset.link;
            window.history.pushState(null, null, this.href);
            localStorage.setItem("link", window.location.href);
            $("#contain").load(path);
        });
    });

    // Lắng nghe sự kiện popstate để xử lý nút back/forward
    window.addEventListener('popstate', function () {
        loadContentFromURL(window.location.href);
    });

</script>
</body>
</html>
