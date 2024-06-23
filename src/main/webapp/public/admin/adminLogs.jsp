<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="services.LogService" %>
<%@ page import="models.Log" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css" />">
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
                <div class="col-lg-12">
                    <div>
                        <h1>Thông tin</h1>
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>

                        <form class="export__excel" action="/exportExcelLog" method="POST" style="margin-left: 12px;">
                            <button class="btn_export">
                                <i class="fa-solid fa-file-export"></i>
                                Xuất file excel
                            </button>
                        </form>
                    </div>
                    <div class="table__wrapper">
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã số</th>
                                <th class="table__head">IP</th>
                                <th class="table__head">Mức độ</th>
                                <th class="table__head">Tác động</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Giá trị trước</th>
                                <th class="table__head">Giá trị hiện tại</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json"></script>
<script>
    $(document).ready(function () {
        const table = new DataTable('#table', {
            processing: true,
            serverSide: true,
            scrollX: false,
            scrollY: false,
            ajax: '/api/admin/logAdmin',
            columns: [
                {data: 'id'},
                {data: 'ip'},
                {data: 'level'},
                {data: 'resource'},
                {data: 'dateCreated'},
                {
                    data: 'previous',
                    defaultContent: "", // Thêm defaultContent để xử lý trường hợp không có dữ liệu
                },
                {data: 'current'},
            ],
            createdRow: (row, data, index) => {
                row.querySelector(':nth-child(3)').dataset.level = data['level'];
                row.querySelector(':nth-child(6)').classList.add('data');
                row.querySelector(':nth-child(7)').classList.add('data');
            },
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
            }
        });

        table.on('draw', function () {
            handelTextTippy()
        })

        table.on('preDraw', function () {
            // Lưu vị trí của trang
            var scrollPos = $(document).scrollTop();
            sessionStorage.setItem('scrollPos', scrollPos);
        });

        table.on('draw.dt', function () {
            // Khôi phục vị trí của trang
            var scrollPos = sessionStorage.getItem('scrollPos');
            $(document).scrollTop(scrollPos);
        });

        function handelTextTippy() {
            $('td.data').each(function (index) {
                let text = $(this)[0].innerHTML
                if (text.length > 30) {
                    tippy(this, {
                        theme: 'light',
                        content: text,
                        interactive: true,
                        placement: 'left-start'
                    })
                    $(this)[0].innerHTML = text.substring(0, 29) + "..."
                }
            })
        }

        $('.export__excel').on('submit', (e) => {
            e.preventDefault();

            Swal.fire({
                title: "Bạn có muốn xóa nội dung sau khi xuất file?",
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: "Xóa tất cả",
                denyButtonText: `Không xóa`,
                cancelButtonText: "Hủy bỏ"
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    const form = e.target;
                    form.action += "?delete=true";
                    form.submit();
                    const idInterval = setInterval(() => {
                        table.clear();
                        table.draw();
                    }, 1000)
                } else if (result.isDenied) {
                    const form = e.target;
                    form.action += "?delete=false";
                    form.submit();
                }
            });
        })
    })
</script>
</body>
</html>
