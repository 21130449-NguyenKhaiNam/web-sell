<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="services.ContactServices" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="${initParam.contextPath}/public/commonLink.jsp"/>
    <link rel="stylesheet" href="${initParam.contextPath}/assets/css/contact.css">
    <title>Liên hệ</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <div class="container-xl">
        <div class="contact_us row">
            <div class="contact__container col">
                <div class="contact__content">
                    <h2 class="title__contact">Liên hệ</h2>
                    <p class="subtitle__contact">Bạn có muốn liên lạc với chúng tôi? Nếu có thì bạn hãy điền đầy đủ
                        thông tin vào form và chúng tôi sẽ cố gắng phản hồi sớm nhất trong vòng 24h. Hoặc bạn có thể
                        liên hệ thông qua các thông tin về công ty của chúng tôi</p>
                    <span class="success__notification"></span>
                    <form method="post" action="Contact" id="contact__form">
                        <div class="form__block">
                            <label for="fullName" class="form__label">Họ và tên <span class="compulsory__mark">*</span></label>
                            <input class="form__input" type="text" id="fullName" name="fullName" placeholder="Họ và tên của bạn" value="<c:if test="${requestScope.fullName != null}">${requestScope.fullName}</c:if>">
                            <span id="fullNameError" class="error-notice"></span>
                        </div>
                        <div class="form__block">
                            <label for="phone" class="form__label">Số điện thoại <span class="compulsory__mark">*</span></label>
                            <input class="form__input" type="text" id="phone" name="phone" placeholder="Số điện thoại của bạn" value="<c:if test="${requestScope.phone != null}">${requestScope.phone}</c:if>">
                            <span id="phoneError" class="error-notice"></span>
                        </div>
                        <div class="form__block">
                            <label for="email" class="form__label">Email <span class="compulsory__mark">*</span></label>
                            <input class="form__input" type="text" id="email" name="email" placeholder="Email của bạn" value="<c:if test="${requestScope.email != null}">${requestScope.email}</c:if>">
                            <span id="emailError" class="error-notice"></span>
                        </div>
                        <div class="form__block">
                            <label for="subject" class="form__label">Chủ đề <i
                                    class="subject__in fo fa-solid fa-circle-info"></i></label>
                            <select class="select__box" name="subject" id="subject">
                                <c:forEach items="${requestScope.listContactSubjects}" var="subjectOption">
                                <option value="${subjectOption.subjectName}" <c:if test="${subjectOption.subjectName eq requestScope.subject}"> selected </c:if> class="option">${subjectOption.subjectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form__block">
                            <label for="message" class="form__label">Chúng tôi có thể giúp gì được cho bạn?</label>
                            <textarea id="message" name="message" class="form__textarea" rows="8" placeholder="Lời nhắn của bạn"></textarea>
                        </div>
                        <!--                <button class="form__submit">Gửi</button>-->
                        <input type="submit" class="form__submit" value="Gửi liên hệ của bạn">
                    </form>
                </div>

                <div class="get-in-touch">
                    <h2 class="title__contact">Công ty TNHH Your Style</h2>
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
                    <div class="contact-method">
                        <div class="icon-contact">
                            <i class="fa-solid fa-business-time"></i>
                        </div>
                        <div class="contact-description">
                            <p>Thời gian làm việc</p>
                            <p>9:00 - 17:00 (Tất cả các ngày trong tuần)</p>
                        </div>
                    </div>
                </div>
                <img src="../assets/img/contactus.svg">
            </div>
            <iframe class="company__map col"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.984095084102!2d106.7308157!3d10.735709000000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175257b7549e31f%3A0xf46aadadf7106fd2!2zNzIxIEh14buzbmggVOG6pW4gUGjDoXQsIFBow7ogVGh14bqtbiwgUXXhuq1uIDcsIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaA!5e0!3m2!1svi!2s!4v1701618968334!5m2!1svi!2s"
                    style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">
            </iframe>

        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
<script src="../js/base.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"
      integrity="sha512-nMNlpuaDPrqlEls3IX/Q56H36qvBASwb3ipuo3MxeWbsQB1881ox0cRv7UPTgBlriqoynt35KjEwgGUeUXIPnw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"
        integrity="sha512-2ImtlRlf2VVmiGZsjm9bEyhjGW4dU7B6TNwh/hx/iSByxNENtj3WVE6o/9Lj4TJeVXPi4bnOIMXFIJJAeufa0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
</script>
<script src="${initParam.contextPath}/js/validateContactForm.js"></script>
<script>
    $(document).ready(function() {
        $('#subject').select2();
    });
</script>
</html>