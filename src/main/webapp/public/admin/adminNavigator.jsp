<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <a href="<c:url value="/public/admin/adminProducts.jsp"/>" data-link="adminProducts.jsp"
                           class="mb-2 pb-3 sidebar_item">
                            <i class="fa-solid fa-list-check"></i>
                            <span>Quản lý</span></a>
                    </li>

                    <li>
                        <a href="<c:url value="/public/admin/adminReviews.jsp"/>" data-link="adminReviews.jsp"
                           class="mb-2 pb-3 sidebar_item">
                            <i class="fa-solid fa-square-poll-vertical"></i>
                            <span>Nhận xét</span></a>
                    </li>

                    <li>
                        <a href="<c:url value="/public/admin/adminCategories.jsp"/>" data-link="adminCategories.jsp"
                           class="pb-3 sidebar_item">
                            <i class="fa-solid fa-table-list"></i>
                            <span>Loại sản phẩm</span></a>
                    </li>
<%--                    <li>--%>
<%--                        <a href="<c:url value="/public/admin/adminImportMaterial.jsp"/>"--%>
<%--                           data-link="adminImportMaterial.jsp" class="pb-3 sidebar_item">--%>
<%--                            <i class="fa-solid fa-table-list"></i>--%>
<%--                            <span>Nhập hàng</span></a>--%>
<%--                    </li>--%>
                </ul>
            </li>

            <li>
                <a href="<c:url value="/public/admin/adminOrders.jsp"/>" data-link="adminOrders.jsp"
                   class="sidebar_item">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <span>Đơn hàng</span></a>
            </li>

            <li>
                <a href="<c:url value="/public/admin/adminUsers.jsp"/>" data-link="adminUsers.jsp" class="sidebar_item">
                    <i class="fa-solid fa-user"></i>
                    <span>Người dùng</span></a>
            </li>

            <li>
                <a href="<c:url value="/public/admin/adminDashboard.jsp" />" data-link="adminDashboard.jsp" class="sidebar_item">
                    <i class="fa-solid fa-chart-simple"></i>
                    <span>Thống kê</span></a>
            </li>
            <li>
                <a href="<c:url value="/public/admin/adminVoucher.jsp"/>" data-link="adminVoucher.jsp"
                   class="sidebar_item">
                    <i class="fa-solid fa-ticket-simple"></i>
                    <span>Mã giảm giá</span></a>
            </li>
            <li>
                <a href="<c:url value="/public/admin/adminLogs.jsp"/>" data-link="adminLogs.jsp" class="sidebar_item">
                    <i class="fa-solid fa-book"></i>
                    <span>Nhật ký hệ thống</span></a>
            </li>
        </ul>
    </div>
    <%--    <div class="ps__rail-x" style="left: 0px; bottom: 0px;">--%>
    <%--        <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>--%>
    <%--    </div>--%>
    <%--    <div class="ps__rail-y" style="top: 0px; height: 652px; right: 0px;">--%>
    <%--        <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 562px;"></div>--%>
    <%--    </div>--%>
    <%--    <div class="ps__rail-x" style="left: 0px; bottom: 0px;">--%>
    <%--        <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>--%>
    <%--    </div>--%>
    <%--    <div class="ps__rail-y" style="top: 0px; height: 652px; right: 0px;">--%>
    <%--        <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 562px;"></div>--%>
    <%--    </div>--%>
</nav>

<script>
    $(document).ready(() => {
        const url = window.location.href;
        setNavActive(url);
    });

    function setNavActive(url) {
        const path = url.substring(url.lastIndexOf("/") + 1, url.length);
        console.log(path)
        $('li > .sidebar_item').each(function () {
            if (this.dataset.link == path) {
                $(this).parent().addClass('sidebar_active');
            } else {
                $(this).parent().removeClass('sidebar_active');
            }
        });
    }
</script>
<%--<script>--%>
<%--    function isFirstVisit() {--%>
<%--        // Check LocalStorage for a specific item--%>
<%--        const isVisited = localStorage.getItem('hasVisited');--%>
<%--        if (isVisited) {--%>
<%--            localStorage.setItem('hasVisited', null);--%>
<%--        }--%>
<%--        return localStorage.getItem('hasVisited');--%>
<%--    }--%>

<%--    // Function to set the visit status--%>
<%--    function setVisited() {--%>
<%--        localStorage.setItem('hasVisited', 'true');--%>
<%--    }--%>

<%--    // Function to load content based on the URL--%>
<%--    function loadContentFromURL(url) {--%>
<%--        let ind = url.indexOf("#");--%>
<%--        if (ind < 0) {--%>
<%--            const defaultReload = $('.sidebar_active > .sidebar_item')[0];--%>
<%--            const path = defaultReload.href;--%>
<%--            url += path.substring(path.indexOf("#"), path.length);--%>
<%--        }--%>
<%--        const sub = url.substring(url.indexOf("#"), url.length);--%>
<%--        $('li > .sidebar_item').each(function () {--%>
<%--            let linkHref = this.href;--%>
<%--            linkHref = linkHref.substring(linkHref.indexOf("#"), linkHref.length);--%>
<%--            if (linkHref == sub) {--%>
<%--                $('.sidebar_active').removeClass('sidebar_active');--%>
<%--                $(this).parent().addClass('sidebar_active');--%>
<%--                const path = this.dataset.link;--%>
<%--                $("#contain").load(path);--%>
<%--                return false; // Exit loop--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>

<%--    window.addEventListener('load', function () {--%>
<%--        if (isFirstVisit()) {--%>
<%--            const defaultReload = $('.sidebar_active > .sidebar_item')[0];--%>
<%--            const path = defaultReload.dataset.link;--%>
<%--            window.history.pushState(null, null, defaultReload.href);--%>
<%--        }--%>
<%--        // Set the visit status regardless of whether it's the first visit or not--%>
<%--        setVisited();--%>
<%--    });--%>

<%--    $(document).ready(function () {--%>
<%--        localStorage.setItem("link", window.location.href);--%>
<%--        loadContentFromURL(window.location.href);--%>

<%--        $('.sidebar_item').on('click', function (event) {--%>
<%--            // Ngăn sự kiện chuyển trang--%>
<%--            event.preventDefault();--%>

<%--            // Thay đổi trạng thái active cho tag được nhấn--%>
<%--            $('.sidebar_active').removeClass('sidebar_active');--%>
<%--            $(this).parent().addClass('sidebar_active');--%>

<%--            // Gọi tới đường dẫn--%>
<%--            const path = this.dataset.link;--%>
<%--            window.history.pushState(null, null, this.href);--%>
<%--            localStorage.setItem("link", window.location.href);--%>
<%--            $("#contain").load(path);--%>
<%--        });--%>
<%--    });--%>

<%--    // Lắng nghe sự kiện popstate để xử lý nút back/forward--%>
<%--    window.addEventListener('popstate', function () {--%>
<%--        loadContentFromURL(window.location.href);--%>
<%--    });--%>

<%--</script>--%>
