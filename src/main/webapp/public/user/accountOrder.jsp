<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="/assets/css/user/account.css">
        <link rel="stylesheet" href="/assets/css/user/accountOrder.css">
        <title>Lịch sử mua hàng</title>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">
            <nav class="nav nav-borders">
                <a class="nav-link " href="/public/user/accountInfo.jsp">
                    Thông tin cá nhân
                </a>
                <a class="nav-link " href="/public/user/accountSecurity.jsp">
                    Bảo mật
                </a>
                <a class="nav-link active ms-0" href="#">
                    Đơn hàng
                </a>
            </nav>
            <hr class="mt-0 mb-4">

            <div class="row">
                <div class="col-3">
                    <div class="list-group">
                        <div class="py-3 list-group-item list-group-item-action active" aria-current="true" data-status="1">
                            <i class="fa-solid fa-wallet me-3"></i>Chờ xác nhận
                        </div>
                        <div class="py-3 list-group-item list-group-item-action" data-status="2">Đã xác nhận</div>
                        <div class="py-3 list-group-item list-group-item-action">Đang sản xuất</div>
                        <div class="py-3 list-group-item list-group-item-action">
                            <i class="fa-solid fa-truck me-3" data-status="3"></i>Đang vận chuyển
                        </div>
                        <div class="py-3 list-group-item list-group-item-action" data-status="4">
                            Hoàn thành
                        </div>
                        <div class="py-3 list-group-item list-group-item-action" data-status="5">Hủy</div>
                    </div>
                </div>
                <div class="col-9">
                    <table id="orderList" class="table table-bordered table-hover table-light">
                        <thead>
                            <tr>
                                <th> Mã đơn hàng</th>
                                <th>Ngày đặt</th>
                                <th>Giá</th>
                                <th>Tình trạng đơn hàng</th>
                                <th>Xem chi tiết</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/themes/base/jquery-ui.min.css" integrity="sha512-ELV+xyi8IhEApPS/pSj66+Jiw+sOT1Mqkzlh8ExXihe4zfqbWkxPRi8wptXIO9g73FSlhmquFlUOuMSoXz5IRw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!--Select 2 jquery-->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js" integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js" integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--JQuery validate-->
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/additional-methods.js"></script>
    <!--JQuery Datatable-->
    <link href="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.3/datatables.min.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.3/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/2.0.3/js/dataTables.bootstrap5.js"></script>
    <script src="https://cdn.datatables.net/plug-ins/2.0.3/i18n/vi.json"></script>
    <script src="/js/user/accountOrder.js"></script>
</html>
