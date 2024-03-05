// Star rating
function starRating() {
    const stars = document.querySelectorAll(".review__star");
    const text = document.querySelector(".review__rating p:last-child");let starCurrent = 4; //0->4
    const textArray = ["Tệ", "Không hài lòng", "Bình thường", "Rất hài lòng", "Tuyệt vời"]
    stars.forEach(function (star, index) {
        star.onclick = function () {
            resetClass();
            star.classList.add("review__star--choose");
            starCurrent = index;
            starToText(starCurrent);
            for (let i = 0; i < index; i++) {
                stars[i].classList.add("review__star--choose");
            }
        }
    });

    function resetClass() {
        stars.forEach(function (star) {
            if (star.classList.contains("review__star--choose"))
                star.classList.remove("review__star--choose");
        });
    }

    function starToText(star) {
        text.innerText = textArray[star];
    }
}

starRating();

function loadProduct(productReview) {
    const productImg = document.querySelector(".product__img img");
    const productInfo = document.querySelector(".product__info");
    const name = productInfo.querySelector(".product__name");
    const category = productInfo.querySelector(".product__category");
    const productColor = productInfo.querySelector(".color__code");
    const quantity = productInfo.querySelector(".product__quantity");
    let size;

    const product = getProduct(productReview.productId);


    productImg.src = `../assets/img/product_img/${product.imgSrc}`;
    name.innerText += " " + product.name;
    category.innerText += " " + getCategory(product.categoryId).nameType;
    productColor.style.backgroundColor = productReview.color;
    quantity.innerText += " " + productReview.quantity;
    recognizeSize()

    function recognizeSize() {

        try {
            const arraySize = JSON.parse(productReview.size);
            size = document.querySelector(".product__size--custom");
            const sizeHTML = arraySize.map(function (size) {
                return ` <li>
                           - ${size.name + ": " + size.value + " " + size.unit}
                        </li>`
            });
            size.innerHTML = sizeHTML.join("");
        } catch (e) {
            size = document.querySelector(".product__size--default");
            console.log(productReview.size)
            size.innerText += productReview.size;
        }
    }
}
var formObj = new Validation({
    formSelector: ".review__form",
    formBlockClass: "form__block",
    errorSelector: ".form__error",
    rules: [
        Validation.isRequired(`textarea[name="desc"]`),
    ],
    submitSelector: ".review__submit",

})
