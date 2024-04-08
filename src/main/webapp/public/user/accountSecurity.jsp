<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="/assets/css/user/account.css">
        <link rel="stylesheet" href="/assets/css/user/accountSecurity.css">
        <title>Bảo mật</title>

    </head>
    <body>
        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <a class="nav-link " href="/public/user/accountInfo.jsp">
                    Thông tin cá nhân
                </a>
                <a class="nav-link active ms-0" href="#">
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.css" integrity="sha512-OWGg8FcHstyYFwtjfkiCoYHW2hG3PDWwdtczPAPUcETobBJOVCouKig8rqED0NMLcT9GtE4jw6IT1CSrwY87uw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.js" integrity="sha512-csaTzpLFmF+Zl81hRtaZMsMhaeQDHO8E3gBkN3y3sCX9B1QSut68NxqcrxXH60BXPUQ/GB3LZzzIq9ZrxPAMTg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="/js/user/accountSecurity.js"></script>
    </body>
</html>