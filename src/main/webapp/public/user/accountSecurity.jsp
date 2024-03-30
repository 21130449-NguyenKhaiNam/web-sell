<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Bảo mật</title>
        <style type="text/css">
            body {
                margin-top: 20px;
                background-color: #f2f6fc;
                color: #69707a;
            }

            .img-account-profile {
                height: 10rem;
            }

            .rounded-circle {
                border-radius: 50% !important;
            }

            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }

            .card .card-header {
                font-weight: 500;
            }

            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }

            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }

            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }

            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }

            .btn-danger-soft {
                color: #000;
                background-color: #f1e0e3;
                border-color: #f1e0e3;
            }

            .toast {
                position: fixed;
                bottom: 20px;
                right: -100%; /* Start position */
                transition: right 0.5s ease-in-out; /* Animation duration and timing function */
            }

            .toast.show {
                right: 20px; /* End position */
            }
        </style>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <a class="nav-link active ms-0" href="#">
                    Thông tin cá nhân
                </a>
                <a class="nav-link" href="/public/user/accountSecurity.jsp">
                    Bảo mật
                </a>
                <a class="nav-link" href="/public/user/accountOrder.jsp">
                    Đơn hàng
                </a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card mb-4">
                        <div class="card-header">Thay đổi mật khẩu</div>
                        <div class="card-body">
                            <form id="form" class="needs-validation">
                                <div class="mb-3">
                                    <label class="small mb-1" for="currentPassword"> Mật khẩu hiện tại </label>
                                    <input class="form-control " name="currentPassword" id="currentPassword" type="password">
                                    <div class="valid-feedback">

                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="newPassword">Mật khẩu mới</label>
                                    <input class="form-control " name="newPassword" id="newPassword" type="password" placeholder="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="confirmPassword">Nhập lại mật khẩu mới</label>
                                    <input class="form-control " name="confirmPassword" id="confirmPassword" type="password" placeholder="">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <button class="btn btn-primary" type="submit">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="toast-container"></div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js" integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="/js/accountSecurity.js"></script>
    </body>
</html>