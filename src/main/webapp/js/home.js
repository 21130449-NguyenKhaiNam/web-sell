function addToCartAjax() {
    $(document).ready(function () {
        $('.action__bar').each(function (index, actionBar) {
            $(actionBar).on('submit', function (event) {
                event.preventDefault();
                const form = $(actionBar);
                let productId = form.find('input[name="productId"]').val();
                $.ajax({
                    type: form.attr('method'),
                    url: form.attr('action'),
                    data: {productId: productId},
                    success: function (response) {
                        let addToCartSuccessHTML = `<div class="notification__cart">
                                                        <div class="status__success">
                                                            <span>
                                                            <i class="fa-solid fa-circle-check icon__success"></i>
                                                            Đã thêm vào giỏ hàng thành công
                                                            </span>
                                                            <span onclick="handleCloseNotificationCart()">
                                                            <i class="fa-solid fa-xmark close__notification"></i>
                                                            </span>
                                                        </div>
                                                        <a class="view__cart" href="/public/user/shoppingCart.jsp">Xem giỏ hàng và thanh toán</a>
                                                    </div>`;
                        $('.cart__wrapper').append(addToCartSuccessHTML)
                        $('.qlt__value').text(response);
                    },
                    error: function (error) {
                        console.error('Lỗi khi thêm sản phẩm vào giỏ hàng', error);
                    }
                })
            })
        })
    })
}


let ulCom = $('.search__box')[0]

function handleSearch() {
    let debounceTimer;
    $('.search__inp').keydown(function () {

        var formData = $(this).serialize();

        clearTimeout(debounceTimer);

        debounceTimer = setTimeout(() => {
            $.ajax({
                url: '/searchProduct',
                method: 'GET',
                data: formData,
                success: function (response) {
                    console.log(response)
                    ulCom.innerHTML = ""
                    for (let i = 0; i < response.length; ++i) {
                        const li = document.createElement("li")
                        li.setAttribute("class", "mb-1")
                        const a = document.createElement("a")
                        a.setAttribute("class", "text-dark mb-2 search__box-item")
                        a.setAttribute("href", `/showProductDetail?id=${response[i].id}`)
                        a.setAttribute("target", "_blank");
                        a.style.cursor = "pointer";
                        a.innerText = response[i].name
                        li.appendChild(a)
                        ulCom.appendChild(li)
                    }
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                }
            })
        }, 800);
    })
}

$('.search__inp').on('focus', function () {
    $('.search__box').addClass('focused');
    $('.modal_hidden_search__box').css('display', 'block');
});

$('.modal_hidden_search__box').on('click', function () {
    $('.search__box').removeClass('focused');
    $('.modal_hidden_search__box').css('display', 'none');
});

$('#header').addClass("animate__animated animate__backInDown")
handleSearch()
addToCartAjax()


