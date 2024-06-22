<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminCategories.css"/>">

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <title>Nhập hàng</title>
</head>
<body>
<!--Header-->
<c:import url="/public/header.jsp"/>
<main class="main">
    <!--Navigate-->
    <c:import url="/public/admin/adminNavigator.jsp"/>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div style="display: flex;justify-content: space-between">
                        <h1>Danh sách hàng hóa</h1>
                        <div style="display: flex">
                            <form action="${pageContext.request.contextPath}/importMaterial" method="POST" enctype="multipart/form-data" style="display: flex; margin-right: 10px; justify-content: center;align-items: center">
                                <input type="file" name="file">
                                <button class="btn_export">
                                    <i class="fa-solid fa-file-export"></i>
                                    Nhập hàng
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/exportExcelMaterial" method="POST">
                                <button class="btn_export">
                                    <i class="fa-solid fa-file-export"></i>
                                    Xuất file excel
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="table__wrapper">
                        <table class="table" id="table">
                            <thead>
                                <tr class="table__row">
                                    <th class="table__head">ID</th>
                                    <th class="table__head">Loại vải</th>
                                    <th class="table__head">Số lượng nhập</th>
                                    <th class="table__head">Ngày nhập</th>
                                </tr>
                            </thead>

                            <tbody class="body_table">
                            </tbody>
                        </table>
                    </div>
                    <ul class="paging"></ul>
                </div>
            </div>
        </div>
    </section>
</main>
</body>

<script>
    $(document).ready(function () {
        const table = new DataTable('#table', {
            processing: true,
            serverSide: true,
            scrollX: false,
            scrollY: false,
            ajax:{
                url: '/readMaterial',
                method: 'POST',
                dataSrc: 'data'
            },
            columns: [
                {data: 'id'},
                {data: 'name'},
                {data: 'remain'},
                {data: 'createdat'}
            ],
            createdRow: function (row, data, dataIndex) {
                var dateCell = $('td', row).eq(3);
                var date = new Date(data.createdat).toLocaleDateString("vi-VN", { year: "numeric", month: "2-digit", day: "2-digit" })
                dateCell.text(date);
            },
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
            }
        });
    })
</script>
</html>
