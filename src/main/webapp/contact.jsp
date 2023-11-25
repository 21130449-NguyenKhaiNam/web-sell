<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">

    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/contact.css">
    <title>Liên hệ</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <div class="container-xl">
        <div class="contact_us row">
            <div class="title_method-contact col">
                <h2>Liên hệ</h2>
                <p>Bạn có muốn liên lạc với chúng tôi? Nếu có thì bạn hãy điền đầy đủ thông tin vào form và chúng sẽ cố gắng phản hồi sớm nhất trong vòng 24h. Hoặc bạn có thể liên hệ với chúng tôi thông qua các phương thức bên dưới</p>
                <div class="get-in-touch">
                    <h2>Phương thức liên hệ</h2>
                    <div class="contact-method">
                        <div class="icon-contact">
                            <i class="fa-solid fa-location-dot"></i>
                        </div>
                        <div class="contact-description">
                            <p>Địa chỉ</p>
                            <p>721 Huỳnh Tấn Phát, Phường Phú Thuận, Quận 7, Tp. Hồ Chí Minh</p>
                        </div>
                    </div>
                    <div class="contact-method">
                        <div class="icon-contact">
                            <i class="fa-solid fa-phone"></i>
                        </div>
                        <div class="contact-description">
                            <p>Số điện thoại</p>
                            <p>0703637448</p>
                        </div>
                    </div>
                    <div class="contact-method">
                        <div class="icon-contact">
                            <i class="fa-solid fa-envelope"></i>
                        </div>
                        <div class="contact-description">
                            <p>Email</p>
                            <p>yourstyle@support.com</p>
                        </div>
                    </div>
                </div>
                <img src="assets/img/contactus.svg">
            </div>

            <form method="post" id="contact_us-form" class="col">
                <div class="form__block">
                    <label for="fullname" class="form__label">Họ và tên *</label>
                    <input class="form__input" type="text" id="fullname" name="fullname">
                    <span class="error-notice"></span>
                </div>

                <div class="form__block">
                    <label for="email" class="form__label">Email *</label>
                    <input class="form__input" type="email" id="email" name="email">
                    <span class="error-notice"></span>
                </div>

                <div class="form__block">
                    <label for="message" class="form__label">Chúng tôi có thể giúp gì được cho bạn?</label>
                    <textarea id="message" name="message" class="form__textarea" rows="12"></textarea>
                </div>
                <!--                <button class="form__submit">Gửi</button>-->
                <input type="submit" class="form__submit" value="Gửi">
            </form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
<script src="js/base.js"></script>
<script src="js/validateContactForm.js"></script>
<script>
    ValidatorContactForm({
        form: '#contact_us-form',
        formBlockSelector: '.form__block',
        errorSelector: '.error-notice',
        rules: [
            ValidatorContactForm.isRequired('#fullname'),
            ValidatorContactForm.isRequired('#email'),
            ValidatorContactForm.isEmail('#email')
        ],
        onSubmit: function (data){
            console.log(data)
        }
    });
</script>
</html>