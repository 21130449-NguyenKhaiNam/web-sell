<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminVoucher.css"/>">
    <style>
        <!--Ẩn thông tin dòng được chọn -->
        .select-info {
            display: none;
        }
    </style>
    <title>Quản lý mã giảm giá</title>
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
                <div class="col-lg-12">
                    <div>
                        <h1>Danh sách mã giảm giá</h1>
                        <button type="button" id="button" class="button button__add"
                                data-bs-toggle="modal" data-bs-target="#modal">
                            <i class="fa-solid fa-plus"></i>
                            Thêm mã giảm giá
                        </button>
                    </div>
                    <div class="table__wrapper">
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head" scope="col">Mã giảm giá</th>
                                <th class="table__head" scope="col">Lượt sử dụng còn lại</th>
                                <th class="table__head" scope="col">Ngày tạo</th>
                                <th class="table__head" scope="col">Ngày hết hạn</th>
                                <th class="table__head" scope="col">Trạng thái</th>
                                <th class="table__head" scope="col">Mở/khóa</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>

</main>
<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="staticBackdropLabel"  aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-centered">
        <form id="form" class="modal-content needs-validation">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Thêm mã giảm giá</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-12">
                                    <label for="code" class="form-label">Code</label>
                                    <input type="text" class="form-control" name="code" id="code" value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-12 mt-2">
                                    <label for="description" class="form-label">Mô tả </label>
                                    <textarea class="form-control" name="description" id="description"></textarea>
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="minimumPrice" class="form-label">Giá trị tối thiểu áp dụng</label>
                                    <input type="text" class="form-control" name="minimumPrice" id="minimumPrice"
                                           value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>

                                <div class="col-4 mt-2">
                                    <label for="discountPercent" class="form-label">Phần trăm giảm giá </label>
                                    <input type="text" class="form-control" id="discountPercent" value=""
                                           name="discountPercent">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="availableTurns" class="form-label text-nowrap">Số lượt sử
                                        dụng</label>
                                    <input type="text" class="form-control" id="availableTurns"
                                           name="availableTurns" value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-6 mt-2">
                                    <label for="expiryDate" class="form-label text-nowrap">Ngày hết hạn</label>
                                    <input type="date" class="form-control" name="expiryDate" id="expiryDate">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-6 mt-2">
                                    <label for="state" class="form-label text-nowrap">Trạng thái </label>
                                    <select id="state" name="state" class="form-select" aria-label="Chọn">
                                        <option value="-1" id="">-- Chọn trạng thái --
                                        </option>
                                        <option value="1">Hoạt động</option>
                                        <option value="2">Khóa</option>
                                    </select>
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-md-12 col-sm-12 mt-2 pb-4">
                                    <label for="productId" class="form-label  py-1">Các sản phẩm áp
                                        dụng</label>
                                    <select id="productId" name="productId" class="form-select" aria-label="Chọn"
                                            style="width: 100%" multiple="multiple">
                                        <option value=""></option>
                                    </select>
                                    <div class="valid-feedback">

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
        </form>
    </div>
</div>
<script type="module" src="<c:url value="/js/admin/adminVoucher.js"/>">
</script>

</body>
</html>