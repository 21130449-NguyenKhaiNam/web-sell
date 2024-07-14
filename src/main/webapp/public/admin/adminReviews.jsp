<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory"/>
<jsp:useBean id="userFactory" class="utils.UserFactory"/>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <style>
        .badge__color {
            display: inline-block;
            background-color: var(--badge-color, #fff);
            width: 30px;
            height: 20px;
            border-radius: 10px;
            border: 1px solid #aaa;
        }
    </style>
    <title>Quản lý nhận xét</title>
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
                <div class="col-12">
                    <div class="d-flex align-items-center justify-content-between pb-3">
                        <h1>Danh sách nhận xét</h1>
                        <form action="/exportExcelReview" method="POST">
                            <button class="btn_export">
                                <i class="fa-solid fa-file-export"></i>
                                Xuất file excel
                            </button>
                        </form>
                    </div>
                    <div>
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">#</th>
                                <th class="table__head">Mã khách hàng</th>
                                <th class="table__head">Tên sản phẩm</th>
                                <th class="table__head">Mã đơn hàng</th>
                                <th class="table__head">Số sao</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Hiển thị</th>
                                <th class="table__head">Xem</th>
                            </tr>
                            </thead>

                            <tbody class="body_table">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<!-- Modal -->
<div class="modal fade " id="modal" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-scrollable">
        <div id="form" class="modal-content needs-validation">
            <div class="modal-header">
                <h2 class="modal-title" id="staticBackdropLabel">Xem đánh giá</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" style="max-height: 60vh">
                <div class="row">
                    <div class="col-6">
                        <div style="height: 400px" class="img-thumbnail">
                            <img id="image" style="height: 100%; object-fit: cover"
                                 src="https://res.cloudinary.com/yourstyle/image/upload/c_scale/q_auto/f_auto/v1/product_img/1/product5.jpg"
                                 alt="">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="row">
                            <div class="col-12">
                                <h3 class="h3" id="name">Tên sản phẩm</h3>
                                <ul class="list-group">
                                    <li class="list-group-item d-flex align-items-center gap-2">Màu sắc: <span
                                            class="badge__color" id="color"></span></li>
                                    <li class="list-group-item d-flex align-items-center gap-2">Mã màu: <span
                                            id="color-code"></span></li>
                                    <li class="list-group-item">Kích thước: <span id="size"></span></li>
                                    <li class="list-group-item">Số lượng: <span id="quantity"></span></li>
                                    <li class="list-group-item">Đơn giá: <span id="price-unit"></span></li>
                                    <li class="list-group-item">Tổng tiền: <span id="total-price"></span></li>
                                </ul>
                            </div>
                            <div class="col-12 mt-2">
                                <h3 class="h3 mt-2">Đánh giá sản phẩm</h3>
                                <div class="d-flex justify-content-between p-2">
                                    <p id="date" class="badge text-bg-primary"></p>
                                    <span id="stars" class="badge text-bg-secondary">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </span>
                                </div>

                                <div id="description" class="border lh-base border-1 border-round p-2 fs-6">
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consectetur dolor
                                    ducimus est ipsum minima provident quaerat similique ut. Architecto
                                    asperiores distinctio dolores incidunt laudantium libero quae. Asperiores
                                    cumque minima nam.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <hr style="height: 1px; width: 100%; background-color: #aaa">
                    </div>
                    <div class="col-6 mt-2">
                        <h3 class="h3">Thông tin khách hàng</h3>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Tên: <span id="fullName"></span></li>
                            <li class="list-group-item">Email: <span id="email"></span></li>
                            <li class="list-group-item">Số điện thoại: <span id="phone"></span></li>
                        </ul>
                    </div>
                    <div class="col-6 mt-2">
                        <h3 class="h3">Địa chỉ</h3>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Tỉnh/Thành phố: <span id="province"></span></li>
                            <li class="list-group-item">Quận/Huyện: <span id="district"></span></li>
                            <li class="list-group-item">Xã/Phường: <span id="ward"></span></li>
                            <li class="list-group-item">Đường/Địa chỉ chi tiết: <span id="detail"></span></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script type="module" src="<c:url value="/js/admin/adminReviews.js" />"></script>
</body>
