<%@page contentType  = "text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="../assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="../assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="../assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="../assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="../assets/font/webfonts/Montserrat.css">

    <!--CSS-->
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/base.css">
    <link rel="stylesheet" href="../assets/css/logIn.css">

    <title>Đăng ký</title>
</head>
<body>
<main class="main">
    <div class="frame">
        <div class="frame__media">

        </div>
        <article>
            <form action="#!" class="form form--signUp">
                <div class="form__block">
                    <label for="username" class="form__label">Tên đăng nhập</label>
                    <input id="username" type="text" class="form__input">
                    <p class="form__error"></p>
                </div>
                <div class="form__block">
                    <label for="email" class="form__label">Email</label>
                    <input id="email" type="email" class="form__input">
                    <p class="form__error"></p>
                </div>
                <div class="form__block">
                    <label for="password" class="form__label">Mật khẩu</label>
                    <input id="password" type="password" class="form__input">
                    <p class="form__error"></p>
                </div>
                <div class="form__block">
                    <label for="confirm-password" class="form__label">Xác nhận lại mật khẩu</label>
                    <input id="confirm-password" type="password" class="form__input">
                    <p class="form__error"></p>
                </div>
                <button id="form__submit" type="submit" class="form__submit button button--hover">Đăng ký</button>
            </form>
            <a href="signIn.jsp" id="form__link--signIn" class="form__link ">Đăng nhập</a>
        </article>
    </div>
    <input type="checkbox" id="modal__hide" class="modal__hide" hidden="hidden" checked>
    <div class="modal">
        <div class="modal__notify">
            <i class="model__checked-icon fa-regular fa-circle-check"></i>
            <p class="modal__text">Đăng kí thành công, vui lòng kiểm tra email bạn đã đăng kí.</p>
            <a href="signIn.jsp" class="button modal__button button--hover">Đăng nhập</a>
            <p class="modal__resend ">Nếu bạn chưa nhận được email xác nhận, hãy <span>nhấn vào đây.</span></p>
        </div>
        <label for="modal__hide" class="modal__blur"></label>
    </div>
</main>
<!--JS validate-->
<script src="../js/validateForm.js"></script>
<script>
    var validation = new Validation({
        formSelector: ".form",
        formBlockClass: "form__block",
        errorSelector: ".form__error",
        rules: [
            Validation.isRequired("#username"),
            Validation.isRequired("#email"),
            Validation.isEmail("#email"),
            Validation.isRequired("#password"),
            Validation.minLength("#password", 6),
            Validation.isRequired("#confirm-password"),
            Validation.isConfirm("#confirm-password", function () {
                return document.querySelector("#password").value;
            })
        ],
        submitSelector: "#form__submit",
        funcAfterSubmit: function () {
            document.querySelector("#modal__hide").checked = false;
        }
    })
</script>
</body>
</html>