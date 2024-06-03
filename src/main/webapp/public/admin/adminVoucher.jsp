<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ducvui2003
  Date: 24/05/2024
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminVoucher.css" />">
    <title>Quản lý mã giảm giá </title>
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
                    <table id="table">
                        <thead>
                        <tr>
                            <th>Mã giảm giá</th>
                            <th>Lượt sử dụng còn lại</th>
                            <th>Ngày tạo</th>
                            <th>Ngày hết hạn</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>

    <!-- Modal Create-->
    <div class="modal fade" id="modal__create" tabindex="-1" aria-labelledby="modal__create-label" aria-hidden="true">
        <div class="modal-dialog" style="max-width: 80%">
            <form id="form__create" class="modal-content" method="post">
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
                                        <input type="text" class="form-control" name="code" id="code">
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
                                        <input type="text" class="form-control" name="minimumPrice" id="minimumPrice">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>

                                    <div class="col-4 mt-2">
                                        <label for="discountPercent" class="form-label">Phần trăm giảm giá </label>
                                        <input type="text" class="form-control" id="discountPercent" value="0"
                                               name="discountPercent">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-4 mt-2">
                                        <label for="availableTurns" class="form-label text-nowrap">Số lượt sử
                                            dụng</label>
                                        <input type="text" class="form-control" id="availableTurns"
                                               name="availableTurns">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-6 mt-2">
                                        <label for="expiryDate" class="form-label text-nowrap">Ngày hết hạn</label>
                                        <input type="text" class="form-control" name="expiryDate" id="expiryDate">
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
    <script src="<c:url value="/js/admin/adminVoucher.js"/>"></script>
</main>
</body>
</html>
