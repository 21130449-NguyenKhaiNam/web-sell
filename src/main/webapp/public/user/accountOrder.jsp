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
                            <i class="fa-solid fa-hourglass-half me-4"></i>Chờ xác nhận
                        </div>
<%--                        <div class="py-3 list-group-item list-group-item-action" data-status="2">--%>
<%--                            <i class="fa-regular fa-circle-check me-3"></i>--%>
<%--                            Đã xác nhận</div>--%>
                        <div class="py-3 list-group-item list-group-item-action">
                            <i class="fa-solid fa-spinner me-3"></i>
                            Đang sản xuất</div>
                        <div class="py-3 list-group-item list-group-item-action">
                            <i class="fa-solid fa-truck me-3" data-status="3"></i>Đang vận chuyển
                        </div>
                        <div class="py-3 list-group-item list-group-item-action" data-status="4">
                            <i class="fa-regular fa-circle-check me-3"></i>
                            Hoàn thành
                        </div>
                        <div class="py-3 list-group-item list-group-item-action" data-status="5">
                            <i class="fa-regular fa-circle-xmark me-3"></i>
                            Hủy</div>
                    </div>
                </div>
                <div class="col-9">
                    <table id="orderList" class="table table-bordered table-hover table-striped text-center">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Ngày đặt</th>
                                <th>So luong</th>
                                <th>Xem chi tiết</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!--Modal-->
        <div class="modal fade text-black" id="modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div id="model" class="modal-dialog modal-dialog-scrollable" style="max-width: 80%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Thông tin đơn hàng</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-6">
                                <h1 class="h2">Mã đơn hàng <span id="order__id">...</span></h1>
                            </div>
                            <div class="col-12 mt-3">
                                <div class="d-flex">
                                    <p class="">Ngày đặt hàng <span id="order__date" class="fw-bold">...</span></p>
                                    <p class="vr mx-2 fs-6">
                                    <p class="text-success">
                                        <i class="fa-solid fa-truck"></i> Ngày giao hàng dự kiến
                                        <span id="order__date-success">...</span>
                                    <p class="ms-auto text-success fw-bold">
                                        Trạng thái đơn hàng: <span id="order__status">...</span>
                                    </p>

                                </div>
                            </div>
                        </div>
                        <hr class="border border-success border-1 opacity-75">
                        <div class="row pt-1">
                            <div class="col-12">
                                <div class="d-flex order__item align-items-center">
                                    <div class="item__thumbnail">
                                        <img src="/assets/img/product.png" class="rounded border img-thumbnail object-fit-cover" style="width: 100px; height: 100px" alt="...">
                                    </div>
                                    <div class="item__detail ms-3">
                                        <h3 class="h4 item__name pb-3 fw-4">Ao khoac</h3>
                                        <div class="d-flex pt-2 fw-6">
                                            <span class="item__color">Do</span> <span class="vr mx-2"></span>
                                            <span class="item__size">XL</span>
                                        </div>
                                    </div>
                                    <div class="ms-auto">
                                        <div class="item__price pb-2 fs-5 fw-bold">Giá: <span>100.000đ</span></div>
                                        <div class="item__quantity pt-2 fs-6">Số lượng: <span>x1</span></div>
                                    </div>
                                </div>
                                <div class="my-4"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <hr class="border border-1 opacity-75 my-4">
                                <div class="row">
                                    <div class="col-6">
                                        <p class="fs-5 text-bold">Thanh toán</p>
                                    </div>
                                    <div class="col-6">
                                        <p class="fs-5 text-bold">Người nhận</p>
                                        <ul>
                                            <li class="order__name">....</li>
                                            <li class="order__phone">....</li>
                                        </ul>
                                    </div>
                                </div>
                                <hr class="border border-1 opacity-75 my-4">
                                <div class="row">
                                    <div class="col-6">
                                        <p class="fs-5 text-bold">Địa chỉ</p>
                                        <ul>
                                            <li class="order__province">...</li>
                                            <li class="order__district">....</li>
                                            <li class="order__ward">...</li>
                                            <li class="order__detail">...</li>
                                        </ul>
                                    </div>
                                    <div class="col-6">
                                        <p class="fs-5 text-bold my-3">Tổng tiền</p>
                                        <div class="row g-2">
                                            <div class="col-6">Tạm tính</div>
                                            <div class="col-6 text-end">Auto-column</div>
                                            <div class="col-6">Giảm giá</div>
                                            <div class="text-end col-6"> Auto-column</div>
                                            <div class="col-6">Vận chuyển</div>
                                            <div class="text-end col-6">Auto-column</div>
                                        </div>
                                        <hr class=" my-2">
                                        <div class="row " style="--bs-columns: 2;">
                                            <div class="col-6">Tổng cộng</div>
                                            <div class="text-end text-bold col-6">$123</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
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
    <script src="/js/user/accountOrder.js"></script>
</html>
