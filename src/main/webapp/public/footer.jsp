<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Footer-->
<footer id="footer">
    <div class="container-xl">
        <div class="row d-flex">
            <div class="col-sm-6">
                <a href="<c:url value="/public/index.jsp"/>" class="logo"></a>
            </div>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-12 ml-auto d-flex justify-content-end">
                        <a href="#" class="hvr-icon-up display-6">
                            <i class="fa-regular fa-circle-up hvr-icon"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col ">
                <div class="footer__block">
                    <p class="footer__title">Giờ làm việc</p>
                    <p class="footer__desc">9:00 - 17:00</p>
                </div>

                <div class="footer__block">
                    <p class="footer__title">Địa chỉ</p>
                    <a target="_blank" href="https://maps.app.goo.gl/RETcqrjaKeqTCfBE6" class="footer__desc">721 Huỳnh
                        Tấn Phát, Phường Phú Thuận, Quận 7, Tp. Hồ Chí Minh
                    </a>
                </div>
            </div>
            <div class="col">
                <ul class="footer__block">
                    <li class="footer__desc hvr-sweep-to-right">
                        <a href="<c:url value="/public/product/productBuying.jsp"/>" class="footer__link">Gian hàng</a>
                    </li>
                    <li class="footer__desc hvr-sweep-to-right">
                        <a href="<c:url value="/public/contact.jsp"/>" class="footer__link">Liên hệ</a>
                    </li>
                    <li class="footer__desc hvr-sweep-to-right">
                        <a href="<c:url value="/public/about.jsp" />" class="footer__link">Về chúng tôi</a>
                    </li>
                    <li class="footer__desc hvr-sweep-to-right">
                        <a href="<c:url value="/public/policy.jsp"/>" class="footer__link">Chính sách mua hàng</a>
                    </li>
                </ul>
            </div>
            <div class="col">
                <div class="footer__block">
                    <div class="footer__title">Kết nối với chúng tôi thông qua</div>
                    <div class="footer__block social__list">
                        <a href="#!" class="social__item p-1 hvr-wobble-to-top-right">
                            <i class="social__item-icon fa-brands fa-facebook-f"></i>
                        </a>
                        <a href="#!" class="social__item p-1 hvr-wobble-to-top-right">
                            <i class="social__item-icon fa-brands fa-x-twitter"></i>
                        </a>
                        <a href="#!" class="social__item p-1 hvr-wobble-to-top-right">
                            <i class="social__item-icon fa-brands fa-instagram"></i>
                        </a>
                        <a href="#!" class="social__item p-1 hvr-wobble-to-top-right">
                            <i class="social__item-icon fa-brands fa-linkedin-in"></i>
                        </a>
                    </div>

                    <div class="footer__block">
                        <p class="footer__title">Nhận thêm thông tin thông qua</p>
                        <form action="${pageContext.request.contextPath}" class="footer__form">
                            <input placeholder="Email" type="email" class="footer__input" required />
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