<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/policy.css" />">
    <title>Chính sách</title>
</head>

<body>
<!--Header-->
<c:import url="header.jsp" charEncoding="UTF-8"/>
<main class="main">
    <div class="container-xl">
        <div class="row">
            <div class="col-3">
                <ul class="policy__list">
                    <li class="policy__item policy__item--clicked hvr-shutter-in-vertical">Chính sách giao hàng</li>
                    <li class="policy__item hvr-shutter-in-vertical">Chính sách thanh toán</li>
                    <li class="policy__item hvr-shutter-in-vertical">Chính sách đổi trả</li>
                </ul>
            </div>
            <div class="col-9">
                <section class="policy__section policy__section--show">
                    <!--<h1 class="title">CHÍNH SÁCH GIAO HÀNG</h1>-->
                    <div class="policy__block">
                        <h2 class="policy__title">Phạm vi giao hàng</h2>
                        <p class="policy__text"><strong>YOUR STYLES</strong> Việt Nam hỗ trợ giao hàng cho khách hàng
                            trên toàn quốc.</p>
                        <p class="policy__text">
                            Trong các trường hợp có phát sinh dịch bệnh hoặc trong các trường hợp bất khả kháng tại thời
                            điểm phát sinh, khu vực giao hàng có thể thay đổi theo quy định của cơ quan quản lý nhà
                            nước. </p>
                    </div>
                    <div class="line__break"></div>
                    <div class="policy__block">
                        <h2 class="policy__title">Thời gian giao hàng</h2>
                        <p class="policy__text">Phục vụ giao hàng trong giờ hành chính từ thứ 2 đến thứ 6 (trừ thứ 7,
                            Chủ nhật và ngày Lễ, Tết theo quy định của Nhà nước).</p>
                        <div class="policy__block">
                            <h3 class="policy__sub-title">Chuyển phát nhanh</h3>
                            <p class="policy__text">Thời gian giao hàng từ 1-5 ngày làm việc Chi phí giao hàng hiển thị
                                tại bước đặt hàng Từ 1/10/2022: Miễn phí vận chuyển cho mọi đơn hàng</p>

                            <div class="line__break"></div>

                            <h3 class="policy__sub-title">Nhận tại cửa hàng</h3>
                            <p class="policy__text">Thời gian giao hàng từ 24-72h Miễn phí giao hàng tại hệ thống cửa
                                hàng Your Style</p>
                            <div class="policy__note">
                                <p class="">
                                    Lưu ý: Đây là thời gian giao hàng dự kiến. Thời gian này có thể thay đổi vì một số
                                    lý do ngoài ý muốn và sẽ được thông báo với quý khách nếu có. Thời gian giao hàng
                                    tại huyện, hoặc các khu vực Huyện đảo, xã đặc biệt theo quy định riêng có thể cộng
                                    thêm 1-3 ngày so với thời gian giao hàng dự kiến. </p>
                            </div>
                        </div>
                    </div>
                    <div class="line__break"></div>
                    <div class="policy__block">
                        <h2 class="policy__title">Quy định phí giao hàng</h2>
                        <p class="policy__text">Phí giao hàng của đơn hàng được tính dựa theo khoảng cách giữa kho hoặc
                            cửa hàng cung cấp với địa chỉ giao hàng cùng với trọng lượng của sản phẩm.</p>
                        <p class="policy__text">Chi tiết mức phí giao hàng của từng đơn hàng sẽ được thể hiện rõ tại
                            trang hoàn tất đơn hàng sau khi quý khách nhập địa chỉ giao hàng.</p>
                    </div>
                </section>
                <section class="policy__section">
                    <!--<h1 class="title">CHÍNH SÁCH THANH TOÁN</h1>-->
                    <div class="policy__block">
                        <p class="policy__text--intro">Nhằm mang đến Quý khách những trải nghiệm mua sắm trực tuyến
                            tuyệt vời nhất, tại Your Style, chúng tôi đưa ra 4 phương thức thanh toán để Quý khách dễ
                            dàng lựa chọn:</p>
                    </div>

                    <div class="policy__block">
                        <h2 class="policy__title">Thanh toán bằng tiền mặt(COD)</h2>

                        <p class="policy__text">Không hỗ trợ phương thức Thanh toán bằng tiền mặt khi nhận hàng (COD)
                            đối với đơn hàng có sản phẩm bắt buộc lưu chuyển trực tiếp từ cửa hàng để giao hàng</p>

                        <p class="policy__text">Nhân viên hỗ trợ đơn hàng sẽ liên hệ xác nhận thông tin đơn hàng cho quý
                            khách.</p>
                    </div>
                    <div class="line__break"></div>
                    <div class="policy__block">
                        <h2 class="policy__title">Thanh toán chuyển khoảng</h2>

                        <p class="policy__text">
                            Trong quá trình đặt hàng, Quý khách không muốn thanh toán bằng tiền mặt hoặc không thể thanh
                            toán trực tiếp, Quý khách có thể chọn hình thức thanh toán chuyển khoản trong phần Phương
                            thức thanh toán </p>
                        <p class="policy__text">Điều kiện để chọn hình thức thanh toán chuyển khoản là tài khoản ngân
                            hàng của Quý khách đã được đăng ký sử dụng Internet Banking.</p>
                    </div>
                    <div class="line__break"></div>
                    <div class="policy__block">
                        <h2 class="policy__title">Thanh toán bẳng thẻ ATM nội địa</h2>

                        <p class="policy__text">Trong quá trình đặt hàng, quý khách chọn hình thức Thanh toán bằng thẻ
                            ATM nội địa, quý khách sẽ được chuyển đến Cổng thanh toán trực tuyến bảo mật Onepay để thanh
                            toán.</p>
                        <p class="policy__text">Thẻ ATM của quý khách bắt buộc phải sử dụng Internet Banking để thực
                            hiện phương thức thanh toán này.</p>
                    </div>
                    <div class="line__break"></div>
                    <div class="policy__block">
                        <h2 class="policy__title">Thanh toán bằng Visa/Master card</h2>
                        <p class="policy__text">Trong quá trình đặt hàng, quý khách chọn hình thức Thanh toán bằng thẻ
                            Visa/Master card, quý khách sẽ được chuyển đến Cổng thanh toán trực tuyến bảo mật Onepay để
                            thanh toán</p>
                    </div>
                </section>
                <section class="policy__section">
                    <!--<h1 class="title">CHÍNH SÁCH ĐỔI TRẢ</h1>-->
                    <div class="policy__block">
                        <p class="policy__text--intro">Chính sách đổi hàng áp dụng cho khách hàng mua hàng và thanh toán
                            tại các cửa hàng Your style</p>
                        <p class="policy__text--intro">Phương thức đổi hàng: Đổi hàng trực tuyến (qua đơn vị vận
                            chuyển).</p>
                    </div>
                    <div class="policy__block">
                        <h2 class="policy__title">Nội dung chính sách đổi trả:</h2>

                        <p class="policy__text">Áp dụng đổi hàng cho các sản phẩm nguyên giá và các sản phẩm giảm giá
                            không quá 30% trong vòng 14 ngày.</p>
                        <p class="policy__text">Áp dụng đổi size (nếu có) cho các sản phẩm giảm giá trên 30% trong vòng
                            05 ngày.</p>
                        <p class="policy__text">Các thời hạn đổi hàng nêu trên được tính kể từ ngày khách hàng nhận được
                            sản phẩm được thể hiện trên hệ thống của đơn vị vận chuyển</p>
                        <p class="policy__text">Mỗi sản phẩm đủ điều kiện được đổi 01 lần duy nhất.</p>
                        <p class="policy__text">Tất cả các sản phẩm đổi hàng phải đảm bảo giữ nguyên vẹn với đầy đủ nhãn
                            mác, hộp/bao bì sản phẩm, phụ kiện đi kèm như ban đầu</p>
                        <p class="policy__text">Không chấp nhận đổi hàng với các sản phẩm đã qua sử dụng, đã chỉnh sửa,
                            đã qua giặt ủi/là, có mùi lạ, có sự hư hại hoặc bị lỗi do quá trình lưu giữ, vận chuyển của
                            người sử dụng</p>
                        <p class="policy__text">
                            Trong trường hợp việc đổi hàng được thực hiện không phải lỗi từ sản phẩm hoặc do nhà sản
                            xuất, và đến từ nhu cầu của khách hàng, khách hàng sẽ chịu toàn bộ chi phí vận chuyển hàng
                            hóa đổi và chịu trách nhiệm cho các rủi ro trong việc vận chuyển các sản phẩm đổi đến Your
                            Style </p>
                    </div>
                </section>
            </div>
        </div>
    </div>
</main
<c:import url="footer.jsp" charEncoding="UTF-8"/>
</body>
<script>
    var listPolicyBtn = document.querySelectorAll(".policy__item");
    var listPolicySection = document.querySelectorAll(".policy__section");
    listPolicyBtn.forEach(function (btn, index) {
        btn.onclick = function () {
            listPolicySection.forEach(function (section) {
                section.classList.remove("policy__section--show");
            });
            listPolicyBtn.forEach(function (btn) {
                btn.classList.remove("policy__item--clicked");
            });
            listPolicySection[index].classList.add("policy__section--show");
            btn.classList.add("policy__item--clicked");
        };
    });
</script>
<script src="<c:url value="/js/base.js" />"></script>

</html>