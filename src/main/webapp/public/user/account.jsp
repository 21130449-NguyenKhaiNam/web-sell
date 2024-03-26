<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="models.User" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/account.css"/>">
        <title>Tài khoản</title>
    </head>

    <body>
        <c:import url="/public/header.jsp" charEncoding="UTF-8" />
        <main class="main">
            <div class="container-xl">
                <div class="row">
                    <div class="col-3">
                        <div class="service__list">
                            <a class="service__item service__item--clicked">Chỉnh sửa tài khoản</a>
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/ChangePassword"/>">Đổi
                                mật khẩu
                            </a>
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/PurchaseHistory"/>">
                                Lịch sử mua hàng
                            </a>
                        </div>
                    </div>
                    <div class="col-9">
                        <section class="service__section service__section--show">
                            <c:set var="auth" value="${sessionScope.auth}" />
                            <c:set var="accountInfo" value="${requestScope.accountInfo}" />
                            <c:set var="selectedGender" value="${accountInfo.gender}" />
                            <c:set var="birthday" value="${accountInfo.birthDay}" />
                            <c:set var="birthdayParts" value="${fn:split(birthday, '-')}" />
                            <h1 class="title">Chỉnh sửa tài khoản</h1>

                            <div class="user__maininfo block_info">
                                <div class="user__img user">
                                    <p id="avatarInfo" style="display: none">${accountInfo.avatar}</p>
                                    <img id="photo" src="<%=CloudinaryUploadServices.getINSTANCE().getImage("user", "userDefaultAvatar")%>">

                                    <form id="uploadForm" action="<c:url value="/UploadAvatar"/>" method="post" enctype="multipart/form-data">
                                        <input type="file" id="file" name="userCoverPhoto" accept="image/*" onchange="uploadImage()">
                                        <label for="file" id="uploadbtn" class="fas fa-camera"></label>
                                    </form>
                                </div>
                                <form action="<c:url value="/UpdateAccount"/>" method="post" enctype="multipart/form-data" class="form__updateAccount">
                                    <div class="user__info user">
                                        <input type="hidden" name="userId" value="<c:out value='${auth.getId()}'/>">
                                        <div class="user__info--name info-compo">
                                            <div class="lable__name lable-compo">
                                                <label for="Username">Tên người dùng</label>
                                            </div>
                                            <input type="text" id="Username" class=" input-compo" name="userName" value="${accountInfo.username}">
                                        </div>
                                        <div class="user__info--email info-compo">
                                            <div class="lable__email lable-compo">
                                                <label for="Email">Email</label>
                                            </div>
                                            <input type="email" id="Email" class=" input-compo" name="email" value="${accountInfo.email}">
                                        </div>
                                        <div class="user__info--name info-compo">
                                            <div class="lable__name lable-compo">
                                                <label for="Username">Họ tên</label>
                                            </div>
                                            <input type="text" id="FullName" class=" input-compo" name="fullName" value="${accountInfo.fullName}">
                                        </div>
                                        <div class="user__info--email info-compo">
                                            <div class="lable__email lable-compo">
                                                <label for="Email">Số điện thoại</label>
                                            </div>
                                            <input type="number" id="Phone" class=" input-compo" name="phone" value="${accountInfo.phone}">
                                        </div>
                                        <div class="user__info--gender info-compo">
                                            <div class="lable__gender lable-compo">
                                                <label for="gender">Giới tính</label>
                                            </div>
                                            <div class="gender__option">
                                                <select id="gender" name="gender" onchange="hideDefaultOption()">
                                                    <c:choose> <c:when test="${not empty selectedGender}">
                                                        <option value="Nam" ${selectedGender eq 'Nam'
                                                                ? 'selected' : '' }>Nam
                                                        </option>
                                                        <option value="Nữ" ${selectedGender eq 'Nữ'
                                                                ? 'selected' : '' }>Nữ
                                                        </option>
                                                    </c:when> <c:otherwise>
                                                        <option value="" id="defaultOption">-- Chọn giới tính --
                                                        </option>
                                                        <option value="Nam">Nam</option>
                                                        <option value="Nữ">Nữ</option>
                                                    </c:otherwise> </c:choose></select>
                                            </div>
                                        </div>
                                        <div class="user__info--birthday info-compo">
                                            <div class="lable__birthday lable-compo">
                                                <label for="day">Ngày sinh</label>
                                            </div>
                                            <div class="birthday__option">
                                                <select type="text" class="input-compo" id="year" name="year">
                                                    <c:forEach var="year" begin="1900" end="2100">
                                                        <option value="${year}" ${year eq birthdayParts[0]
                                                                ? 'selected' : '' }>${year}</option>
                                                    </c:forEach></select>

                                                <select id="month" name="month">
                                                    <c:forEach var="month" begin="1" end="12">
                                                        <option value="${month}" ${month eq birthdayParts[1]
                                                                ? 'selected' : '' }>
                                                            Tháng ${month}</option>
                                                    </c:forEach></select>

                                                <select type="text" class="input-compo" id="day" name="day">
                                                    <c:forEach var="day" begin="1" end="31">
                                                        <option value="${day}" ${day eq birthdayParts[2]
                                                                ? 'selected' : '' }>${day}</option>
                                                    </c:forEach></select>
                                            </div>
                                        </div>
                                        <div class="user__info--address info-compo">
                                            <div class="lable__address lable-compo">
                                                <label for="Address">Địa chỉ</label>
                                            </div>
                                            <input type="text" class="input-compo" id="Address" name="address" value="<c:out value="
                                                            ${accountInfo.address}"/>">
                                        </div>
                                        <div class="save save__userInfo">
                                            <button>Lưu thay đổi</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="${initParam.contextPath}/public/footer.jsp" />
        <script src="<c:url value="/js/validateForm.js"/>"></script>
        <script src="<c:url value="/js/data.js"/>"></script>
        <script src="<c:url value="/js/account.js"/>"></script>
        <script>
            function hideDefaultOption() {
                var genderDropdown = document.getElementById("gender");
                var defaultOption = document.getElementById("defaultOption");

                if (genderDropdown.value !== "") {
                    defaultOption.style.display = "none";
                } else {
                    defaultOption.style.display = "block";
                }
            }
        </script>
        <script>
            function uploadImage() {
                var fileInput = document.getElementById('file');
                var uploadForm = document.getElementById('uploadForm');

                var formData = new FormData(uploadForm);

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "UploadAvatar", true);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // Xử lý phản hồi từ servlet nếu cần
                        console.log(xhr.responseText);
                    }
                };

                xhr.send(formData);
            }
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var avatarInfo = document.getElementById("avatarInfo");
                var photo = document.getElementById("photo");

                if (avatarInfo && avatarInfo.textContent.trim() !== "") {
                    // Nếu accountInfo.avatar có giá trị, sử dụng nó
                    var avatarPath = "/assets/img/user/" + avatarInfo.textContent.trim();
                    photo.src = avatarPath;
                }
                // Nếu accountInfo.avatar không có giá trị, giữ nguyên hình ảnh mặc định
            });
        </script>
    </body>


</html>