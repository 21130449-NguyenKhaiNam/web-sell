<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminCategories.css"/>">
    <c:import url="/public/filePond.jsp"/>
    <!--LightBox 2-->
    <link href="https://cdn.jsdelivr.net/npm/lightbox2@2.11.3/dist/css/lightbox.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/lightbox2@2.11.3/dist/js/lightbox.min.js"></script>
    <title>Quản lý phân loại</title>
</head>
<body>
<c:set var="listCategory" value="${requestScope.listCategory}"/>
<!--Header-->
<c:import url="/public/header.jsp"/>
<main id="main">
    <!--Navigate-->
    <c:import url="/public/admin/adminNavigator.jsp"/>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div class="d-flex justify-content-between align-items-center pb-3">
                        <h1>Danh sách phân loại</h1>
                        <span id="button" data-bs-toggle="modal" data-bs-target="#modal" class="button button__add">
                        <i class="fa-solid fa-plus"></i>Thêm phân loại
                    </span>
                    </div>
                </div>
                <div class="col-12">
                    <table id="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên phân loại</th>
                            <th>Ảnh kích thước</th>
                            <th>Chỉnh sửa</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>

</main>
<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-scrollable ">
        <form id="form" class="modal-content needs-validation">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Thêm Phân loại</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <label for="nameCategory" class="form-label" data-bs-toggle="tooltip"
                                   data-bs-placement="top"
                                   data-bs-title="Đặt tên cho phân loại sản phẩm">Tên phân loại </label>
                            <input type="text" class="form-control" name="nameCategory" id="nameCategory"
                                   value="">
                            <div class="valid-feedback">

                            </div>
                        </div>
                        <div class="col-12 mt-4">
                            <h2 class="mb-2 d-inline-block" data-bs-toggle="tooltip"
                                data-bs-placement="top"
                                data-bs-title="Ảnh dành cho các size có sẵn cho sản phẩm">Ảnh kích thước có
                                sẵn </h2>
                            <input id="sizeTableImage" name="sizeTableImage" type="file">
                            <div class="valid-feedback"></div>
                        </div>
                        <!--Parameter-->
                        <div class="col-12" id="parameter-form">
                            <div class="row ">
                                <div class="col-12 ">
                                    <label for="nameParameter" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Tên tham số, ví dụ: dài áo, ngang vai,...">Tên tham
                                        số </label>
                                    <input type="text" class="form-control" name="nameParameter[]" id="nameParameter"
                                           value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="unit" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối đa">Đơn vị tính
                                        toán </label>
                                    <input type="text" class="form-control" name="unit[]" id="unit" value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="minValue" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối thiểu">Giá trị tổi
                                        thiểu</label>
                                    <input type="text" class="form-control" name="minValue[]" id="minValue" value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="maxValue" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối đa">Giá trị tổi
                                        đa</label>
                                    <input type="text" class="form-control" name="maxValue[]" id="maxValue" value="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-12 mt-2">
                                    <label for="maxValue" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Ảnh hướng dẫn may đo cho thông số, chỉ được chọn 1 ảnh">Ảnh
                                        hướng dẫn may đo</label>
                                    <input id="guideImg" type="file" name="guideImg[]">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="btn-add-parameter" class="btn btn-primary w-100 mt-3"> Thêm thông số</div>
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

<script type="module" src="<c:url value="/js/admin/adminCategory.js" />"></script>
</body>
</html>
