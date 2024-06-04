<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Xác nhận đơn hàng</title>
    <!-- Bootstrap core CSS -->
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/user/jumbotron-narrow.css" rel="stylesheet">
    <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet"/>
</head>

<body>
<style>
    .form-group {
        margin-bottom: 20px;
    }

    .font-weight-bold {
        font-weight: 600;
    }
</style>

<div class="container mt-2">
    <h3 class="display-4">Xác nhận đơn hàng</h3>
    <div class="table-responsive mt-2">
        <form action="/ajax_servlet" id="frmCreateOrder" method="POST">
            <div class="form-group">
                <label for="amount" class="font-weight-bold">Số tiền</label>
                <input readonly class="form-control" data-val="true"
                       data-val-number="The field Amount must be a number."
                       data-val-required="The Amount field is required." id="amount" max="100000000" min="1"
                       name="amount" type="number" value="<%=request.getAttribute("totalPrice")%>"/>
            </div>
            <h4 class="display-6">Chọn phương thức thanh toán</h4>
            <div class="form-group">
                <h5 class="mt-2 mb-2 font-weight-bold">Cách 1: Chuyển hướng sang Cổng VNPAY chọn phương thức thanh toán</h5>
                <input type="radio" Checked="True" id="bankCode" name="bankCode" value="">
                <label for="bankCode">Cổng thanh toán VNPAYQR</label><br>
            </div>
            <div class="form-group">
                <h5 class="display-6 mt-2 mb-2">Chọn ngôn ngữ giao diện thanh toán:</h5>
                <input type="radio" id="language" Checked="True" name="language" value="vn">
                <label for="language">Tiếng việt</label><br>
                <input type="radio" id="language" name="language" value="en">
                <label for="language">Tiếng anh</label><br>
            </div>
           <div>
               <button type="submit" class="btn-default btn btn-success" href>Thanh toán</button>
           </div>
        </form>
    </div>
    <p>
        &nbsp;
    </p>
    <footer class="footer mb-2">
        <p>&copy; VNPAY 2020</p>
    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>

<script type="text/javascript">
    $("#frmCreateOrder").submit(function () {
        var postData = $("#frmCreateOrder").serialize();
        var submitUrl = $("#frmCreateOrder").attr("action");
        console.log(submitUrl)
        $.ajax({
            type: "POST",
            url: submitUrl,
            data: postData,
            dataType: 'JSON',
            success: function (x) {
                if (x.code === '00') {
                    if (window.vnpay) {
                        vnpay.open({width: 768, height: 600, url: x.data});
                    } else {
                        location.href = x.data;
                    }
                    return false;
                } else {
                    alert(x.Message);
                }
            }
        });
        return false;
    });
</script>
</body>
</html>