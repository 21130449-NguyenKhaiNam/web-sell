<%@page contentType  = "text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta charset="UTF-8">
    <link rel="stylesheet" href="../assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="../assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/base.css">
    <link rel="stylesheet" href="../assets/css/profile.css">
    <title>Profile</title>
</head>
<body>
<header id="header">
    <nav class="nav">
        <div class="container-xl">
            <div class="nav__inner">
                <a href="../index.jsp" class="logo">
                </a>
                <ul class="nav__list">
                    <li class="nav__item">
                        <a href="../index.jsp" class="nav__link">Trang chủ</a>
                    </li>
                    <li class="nav__item">
                        <a href="productBuying.jsp" class="nav__link">Gian hàng</a>
                    </li>
                    <li class="nav__item">
                        <a href="contact.jsp" class="nav__link">Liên hệ</a>
                    </li>
                    <li class="nav__item">
                        <a href="about.jsp" class="nav__link">Về chúng tôi</a>
                    </li>
                </ul>
                <!--cta == call to action-->
                <div class="nav__cta">
                    <a href="signIn.jsp" class="nav__button nav__button--signIn">Đăng nhập</a>
                    <a href="signUp.jsp"
                       class="nav__button nav__button--signUp button button button--hover">Đăng ký</a>
                </div>
                <!--Account show (After log in success)-->
                <div class="account__wrapper">
                    <!--Giỏ hàng-->
                    <a href="shoppingCart.jsp" class="cart">
                        <i class="cart__icon  fa-solid fa-cart-shopping"></i>
                    </a>

                    <div class="account">
                        <i class="account__icon fa-regular fa-user"></i>
                        <div class="setting__list">
                            <div class="setting__item"><a href="#!" class="setting__link">
                                <div class="account__info">
                                    <i class="account__icon fa-regular fa-user"></i>
                                    <p class="account__name"></p>
                                </div>
                            </a></div>
                            <div class="setting__item"><a href="#!" class="setting__link">Đơn mua</a></div>
                            <div class="setting__item"><a href="../htmls/profile.html" class="setting__link">Tài khoản
                                của tôi</a>
                            </div>
                            <div class="setting__item "><a href="#!" class="setting__link setting__logOut">Đăng
                                xuất</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>
<div class="container-xl">
    <div class="containers">
        <div class="back">
            <button><i class=" fa-solid fa-angle-left"></i></button>
        </div>
        <div class="title">
            <h1>Edit profile</h1>
        </div>
        <div class="contains">
            <div class="contain">
                <div class="input__form">
                    <label for="Username">Tên người dùng</label>
                </div>
                <input type="text" id="Username">
            </div>
            <div class="contain">
                <div class="input__form">
                    <label for="Email">Email</label>
                </div>
                <input type="email" id="Email">
            </div>
            <div class="contain">
                <div class="input__form">
                    <label for="gender">Giới tính</label>
                </div>
                <div class="option__select">
                    <select name="" id="gender">
                        <option value="Male">Nam</option>
                        <option value="Female">Nữ</option>
                    </select>
                </div>
            </div>
            <div class="contain">
                <div class="input__form">
                    <label for="day">Ngày sinh</label>
                </div>
                <div class="option_select2">
                    <input type="number" id="day" pattern="[0-9]{1,2}" maxlength="2">
                    <select name="" id="month" pattern="[0-9]{1,2}" maxlength="2">
                        <option value="january">Tháng 1</option>
                        <option value="febuary">Tháng 2</option>
                        <option value="march">Tháng 3</option>
                        <option value="april">Tháng 4</option>
                        <option value="may">Tháng 5</option>
                        <option value="june">Tháng 6</option>
                        <option value="july">Tháng 7</option>
                        <option value="august">Tháng 8</option>
                        <option value="september">Tháng 9</option>
                        <option value="october">Tháng 10</option>
                        <option value="november">Tháng 11</option>
                        <option value="degitcember">Tháng 12</option>
                    </select>
                    <input type="number" id="year" pattern="[0-9]{4}" maxlength="4">
                </div>
            </div>
            <div class="contain">
                <div class="input__form">
                    <label for="Address">Địa chỉ</label>
                </div>
                <input type="email" id="Address">
            </div>
        </div>
        <div class="save">
            <button>Lưu thay đổi</button>
        </div>
    </div>
</div>
<footer id="footer">
    <div class="container-xl">
        <a href="#" class="logo"></a>
        <div class="row">
            <div class="col ">
                <div class="footer__block">
                    <p class="footer__title">Giờ làm việc</p>
                    <p class="footer__desc">9:00 - 17:00</p>
                </div>

                <div class="footer__block">
                    <p class="footer__title">Địa chỉ</p>
                    <a href="https://maps.app.goo.gl/RETcqrjaKeqTCfBE6" class="footer__desc">721 Huỳnh Tấn Phát,
                                                                                             Phường
                                                                                             Phú Thuận, Quận 7, Tp.
                                                                                             Hồ Chí Minh</a></div>
            </div>
            <div class="col">
                <ul class="footer__block">
                    <li class="footer__desc">
                        <a href="productBuying.jsp" class="footer__link">Gian hàng</a>
                    </li>
                    <li class="footer__desc">
                        <a href="contact.jsp" class="footer__link">Liên hệ</a>
                    </li>
                    <li class="footer__desc">
                        <a href="about.jsp" class="footer__link">Về chúng tôi</a>
                    </li>
                    <li class="footer__desc">
                        <a href="policy.jsp" class="footer__link">Chính sách mua hàng</a>
                    </li>
                </ul>
            </div>
            <div class="col">
                <div class="footer__block">
                    <div class="footer__title">Kết nối với chúng tôi thông qua</div>
                    <div class="footer__block social__list">
                        <a href="#!" class="social__item">
                            <i class="social__item-icon fa-brands fa-facebook-f"></i>
                        </a>
                        <a href="#!" class="social__item">
                            <i class="social__item-icon fa-brands fa-x-twitter"></i>
                        </a>
                        <a href="#!" class="social__item">
                            <i class="social__item-icon fa-brands fa-instagram"></i>
                        </a>
                        <a href="#!" class="social__item">
                            <i class="social__item-icon fa-brands fa-linkedin-in"></i>
                        </a>
                    </div>

                    <div class="footer__block">
                        <p class="footer__title">Nhận thêm thông tin thông qua</p>
                        <form action="#" class="footer__form">
                            <input placeholder="Email" type="email" class="footer__input" required>
                            <button type="submit" class="footer__submit button button--hover">
                                <i class="footer__submit-icon fa-regular fa-paper-plane"></i>
                                Gửi
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
<script>
    var backBtn = document.querySelector(".back button");
    backBtn.onclick = function () {
        window.history.back();
    }
</script>
</html>