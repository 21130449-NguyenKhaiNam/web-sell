<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/css/ion.rangeSlider.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/js/ion.rangeSlider.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">

    <style>
        #excelData {
            overflow-x: scroll;
            text-wrap: nowrap;
        }
    </style>
    <title>Quản lý sản phẩm</title>
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
                <h1 class="h3">Thêm sản phầm thông qua excel</h1>
                <div class="d-flex justify-content-between my-3">
                    <a href="<c:url value="/assets/excel/products.xlsm"/>" dowload
                       class="btn btn-primary d-flex align-items-center gap-1">
                        <i class="fa-solid fa-file-excel"></i>
                        <span>Lấy file mẫu</span>
                    </a>
                    <form class="d-flex gap-2" id="form-upload" action="<c:url value="/admin/import-product"/>"
                          method="post"
                          enctype="multipart/form-data">
                        <button type="submit" class="btn btn-primary d-flex align-items-center gap-1">
                            <i class="fa-solid fa-upload"></i>
                            <span>Tải sản phẩm</span>
                        </button>
                        <label
                                class="btn btn-primary d-flex align-items-center gap-1">
                            <input id="input-upload" type="file" hidden="hidden" name="file">
                            <i class="fa-solid fa-file-excel"></i>
                            <span>Tải file excel</span>
                        </label>
                    </form>
                </div>
                <div class="col-12">
                    <div id="excelData"></div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
<script type="module">
    import {http} from "../../js/base.js";

    $(document).ready(() => {
        let excelFile;
        $("#input-upload").on("change", (e) => {
            excelFile = e.target.files[0];
            if (excelFile) {
                const reader = new FileReader();
                reader.onload = function (event) {
                    const data = new Uint8Array(event.target.result);
                    const workbook = XLSX.read(data, {type: 'array'});
                    const sheetName = workbook.SheetNames[0];
                    const worksheet = workbook.Sheets[sheetName];
                    const html = XLSX.utils.sheet_to_html(worksheet);
                    $('#excelData').html(html);
                    $('#excelData table').addClass('table table-bordered table-striped');
                };
                reader.readAsArrayBuffer(excelFile);
            }
        })

        $("#form-upload").on("submit", (e) => {
            e.preventDefault();
            if (!excelFile) {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi',
                    text: 'Vui lòng chọn file excel'
                });
                return;
            }
            const formData = new FormData();
            formData.append('file', excelFile);
            http({
                url: "/admin/import-product",
                method: "POST",
                data: formData,
                processData: false, // Prevent jQuery from automatically processing the data
                contentType: false, // Prevent jQuery from setting the Content-Type header
            }).then((response) => {
                if (response.code) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công',
                        text: 'Tải sản phẩm thành công'
                    });
                }
                else{
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi',
                        text: 'Tải sản phẩm thất bại'
                    });
                }
            }).catch((error) => {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi',
                    text: 'Tải sản phẩm thất bại'
                });
            })
        });
    });
</script>
</body>
</html>
