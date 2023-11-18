// Load product detail
function loadProduct() {
    let name = document.querySelector("h1");
    let basePrice = document.querySelector(".product__price--base");
    let salePrice = document.querySelector(".product__price--sale");
    let size = document.querySelector(".form__size-list");
    let desc = document.querySelector(".product__desc .desc__text");
    let reviews = document.querySelector(".review__list");
    let formParameter = document.querySelector(".form__parameter");

    const vndFormat = Intl.NumberFormat("vi-VI", {
        style: "currency",
        currency: "VND",
    });

    name.innerText = productDetail.name;
    basePrice.innerText = vndFormat.format(productDetail.basePrice);
    salePrice.innerText = vndFormat.format(productDetail.salePrice);
    desc.innerText = productDetail.desc;

    function renderStar(quantity) {
        let star = "";
        for (let i = 1; i <= 5; i++) {
            if (i <= quantity) {
                star += ` <li class="review__star review__start--archive"></li>`;
            } else {
                star += ` <li class="review__star"></li>`;
            }
        }
        return star;
    }

    let reviewsHTML = productDetail.reviews.map(function (element) {
        return `<article class="review">
                    <div class="review__avatar">
                        <img src="../assets/img/user/${element.avatar}" alt="">
                    </div>
                    <div class="review__account">
                        <h4 class="review__name">${element.nameUser}</h4>
                        <ul class="review__stars">
                           ${renderStar(element.star)}
                              <li class="review__date">${element.date}</li>
                        </ul>
                    
                        <p class="review__para line-clamp">${element.comment}
                        </p>
                    </div>
                </article>`;
    });
    reviews.innerHTML = reviewsHTML.join("");

    let parameterHTML = productDetail.parameter.map(function (element, index) {
        return ` <label class="form__block form__label">
                   ${element.name}
                    <div class="form__block-inner">
                        <input id="parameter-${index}" type="text" class="form__input">
                        <span class="form__unit">${element.unit}</span>
                    </div>
                    <span class="form__error"></span>
                </label>`;
    });
    formParameter.innerHTML = parameterHTML.join("");

    let sizeHTML = productDetail.size.map(function (element) {
        return ` <div class="form__size-item">
                    <label>
                        <input type="radio" name="size" class="form__radio" hidden="hidden">
                        ${element}
                    </label>
                </div>`;
    });
    size.innerHTML = sizeHTML.join("");
}

loadProduct();
// Form quantity
var quantityCurrent = 1;
var quantityInput = document.querySelector("#quantity");
var quantityDecrease = document.querySelector(".form___quantity--decrease");
var quantityIncrease = document.querySelector(".form___quantity--increase");
quantityDecrease.onclick = function () {
    if (quantityCurrent > 1) {
        quantityCurrent -= 1;
    }
    quantityInput.value = quantityCurrent;
}
quantityIncrease.onclick = function () {
    quantityCurrent += 1;
    quantityInput.value = quantityCurrent;
}

// show/hide description and review product
function showHideDescAndReview() {
    var productDesc = document.querySelector(".product__desc");
    var productReview = document.querySelector(".product__review");
    var productPage = document.querySelectorAll(".product__page");
    var productPageDesc = productPage[0];
    var productPageReview = productPage[1];

    productPageDesc.onclick = function () {
        this.classList.add("product__page--clicked");
        productPageReview.classList.remove("product__page--clicked");
        productReview.style.display = "none";
        productDesc.style.display = "block";
    }
    productPageReview.onclick = function () {
        this.classList.add("product__page--clicked");
        productPageDesc.classList.remove("product__page--clicked");
        productDesc.style.display = "none";
        productReview.style.display = "block";
    }
    var reviewPara = document.querySelectorAll(".review__para");
    reviewPara.forEach(function (element) {
        element.onclick = function () {
            this.classList.toggle("line-clamp");
        }
    });
}

showHideDescAndReview();

//Paging for review
var pagingReview = new Paging({
    itemSelector: ".review",
    displayShowType: "flex",
    limit: 3,
    listPage: ".paging",
    tagNameItemPage: "li",
    classNameItemPage: "page",
    activeItemPage: "page--current",
    prevBtn: "page--prev",
    nextBtn: "page--next",
});

function openModal(button, modal) {
    button.onclick = function () {
        modal.style.display = "block";
    }
    let modalBlur = modal.querySelector(".modal__blur");
    if (modalBlur) {
        modalBlur.onclick = function () {
            modal.style.display = "none";
        }
    }
}

const buttonOpenGuide = document.querySelector(".form__guide");
const buttonOpenParameter = document.querySelector(".form__submit--order");
const modalGuide = document.querySelector(".modal:has(.modal__guide)");
const modalParameter = document.querySelector(".modal:has(.modal__parameter)");
openModal(buttonOpenGuide, modalGuide);
openModal(buttonOpenParameter, modalParameter);

// when user choose size
function applyValidateProductForm() {
    var formObj = {
        formSelector: "#form__product",
        formBlockClass: "form__block",
        errorSelector: ".form__error",
        rules: [
            Validation.isRequiredRadio(".form__radio"),
            Validation.isRequiredRadio(`input[name="color"]`),
        ],
        submitSelector: ".form__submit--add",
        funcAfterSubmit: function () {
        }
    };
    var validation = new Validation(formObj);
}

applyValidateProductForm();

// When user customize size
function applyValidateParameterForm() {
    var formObj = {
        formSelector: "#form__parameter",
        formBlockClass: "form__block",
        errorSelector: ".form__error",
        rules: [],
        submitSelector: "#form__parameter button",
        funcAfterSubmit: function () {

        }
    };
    productDetail.parameter.forEach(function (value, index) {
        formObj.rules.push(
            Validation.isRequired(`input[id="parameter-${index}"]`),
            Validation.range(`input[id="parameter-${index}"]`, value.min, value.max)
        );
    })

    var validation = new Validation(formObj);
}

applyValidateParameterForm();

function clickTransImg() {
    let imgCurrrent;
    const productImg = document.querySelector(".product__img");
    const productImgItems = document.querySelectorAll(".product__img-item");
    productImgItems.forEach(function (productItem, index) {
        productItem.onclick = function () {
            if (productItem.classList.contains("product__img-item--clicked") == false) {
                productItem.classList.add("product__img-item--clicked");
                imgCurrrent = productItem.querySelector("img").src;
                productImgItems.forEach(function (productItemOther, indexOther) {
                    if (indexOther != index) {
                        productItemOther.classList.remove("product__img-item--clicked")
                    }
                });
                productImg.src= imgCurrrent;
            }
        }
    })
}

clickTransImg();