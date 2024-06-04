<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminCategories.css"/>">
    <title>Trang quản lý voucher</title>
</head>

<body>
<main id="main">
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-lg-12">
                    <div>
                        <h1>Danh sách mã giảm giá</h1>

                        <button type="button" id="button-create-product" class="button button__add"
                                data-bs-toggle="modal" data-bs-target="#modal__create">
                            <i class="fa-solid fa-plus"></i>
                            Thêm mã giảm giá
                        </button>
                    </div>
                    <div class="table__wrapper">
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã giảm giá</th>
                                <th class="table__head">Lượt sử dụng còn lại</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Ngày hết hạn</th>
                                <th class="table__head">Trạng thái</th>
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

    <!-- Modal Create-->
    <div class="modal fade" id="modal__create" tabindex="-1" aria-labelledby="modal__create-label" aria-hidden="true">
        <div class="modal-dialog" style="max-width: 80%">
            <form id="form__create" class="modal-content needs-validation">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modal__create-label">Thêm mã giảm giá</h1>
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
                                            <option value="-1" id="defaultOption">-- Chọn trạng thái --
                                            </option>
                                            <option value="1">Hoạt động</option>
                                            <option value="2">Khóa</option>
                                        </select>
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-md-12 col-sm-12">
                                        <label for="productId" class="small py-1">Các sản phẩm áp
                                            dụng</label>
                                        <select id="productId" name="productId[]" class="form-select" aria-label="Chọn">
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
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script src="<c:url value="/js/admin/adminVoucher.js"/>"></script>
</main>
</body>
</html>