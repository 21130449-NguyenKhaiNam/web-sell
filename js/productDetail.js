var product = {
    name: `Sơ mi dài tay Café-DriS`,
    basePrice: 499000,
    salePrice: 429000,
    parameter: [
        {
            name: "Dài áo",
            min: 65,
            max: 87,
            unit: "cm",
        },
        {
            name: "Ngang ngực",
            min: 45,
            max: 60,
            unit: "cm",
        },
        {
            name: "Dài tay",
            min: 55,
            max: 75,
            unit: "cm",
        },
        {
            name: "Rộng vai",
            min: 40,
            max: 60,
            unit: "cm",
        },

    ],
    desc: "Chất liệu: 50% S.Café + 50% Recycled PET\n" +
        "Phù hợp với: đi làm, đi chơi\n" +
        "Kiểu dáng: Regular fit dáng suông\n" +
        "Người mẫu: 177 cm - 74 kg, mặc size XL\n" +
        "Tự hào sản xuất tại Việt Nam",
    reviews: [
        {
            "nameUser": "John Doe",
            "star": 5,
            "comment": "Excellent product! I'm really happy with my purchase.",
            "date": "2023-10-31"
        }
        , {
            "nameUser": "Alice Smith",
            "star": 4,
            "comment": "Great value for the price, but there's room for improvement.",
            "date": "2023-10-30"
        }, {
            "nameUser": "Mark Johnson",
            "star": 3,
            "comment": "Not bad, but it could use more features.",
            "date": "2023-10-29"
        }

        , {
            "nameUser": "Sarah Williams",
            "star": 2,
            "comment": "Disappointed with the quality. It broke after a few uses.",
            "date": "2023-10-28"
        }
        , {
            "nameUser": "Michael Brown",
            "star": 5,
            "comment": "Outstanding customer service! They went above and beyond to assist me.",
            "date": "2023-10-27"
        }

    ],
    srcImg: "product25.jpg",

    
}

// Load product detail
function loadProduct() {
    let name = document.querySelector("h1");
    let basePrice = document.querySelector(".product__price--base");
    let salePrice = document.querySelector(".product__price--sale");
    let formParameter = document.querySelector(".form__parameter");
    let desc = document.querySelector(".product__desc .desc__text");
    let reviews = document.querySelector(".review__list");

    name.innerText = product.name;
    basePrice.innerText = product.basePrice;
    salePrice.innerText = product.salePrice;
    desc.innerText = product.desc;

    let parameterHTML = product.parameter.map(function (element, index) {
        return ` <label class="form__block form__label">
                   ${element.name}
                    <div class="form__block-inner">
                        <input name="${element.name}" type="text" class="form__input">
                        <span class="form__unit">${element.unit}</span>
                    </div>
                    <span class="form__error"></span>
                </label>`;
    });
    formParameter.innerHTML = parameterHTML.join("");

    function renderStar(quantity) {
        let star = "";
        for (let i = 1; i <= 5; i++) {
            if (i <= quantity) {
                star += ` <li class="review__star review__start--archive"></li>`;
            } else {
                star += ` <li class="review__star "></li>`;
            }
        }
        return star;
    }

    let reviewsHTML = product.reviews.map(function (element) {
        return `<article class="review">
                                <div class="review__avatar">
                                    <img src="../assets/img/user/user__demo.jpg" alt="">
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
}

loadProduct();
// Get code color
var codeColor;
var colorChooseElement = document.querySelectorAll(".form__color-check");
var colorResult = document.querySelector(".form__input-color-code");
colorResult.value = "#000";

//Effect when click
colorChooseElement.forEach(function (element, index) {
    element.onclick = function (e) {
        if (element.classList.contains("form__color--checked")) {
            element.classList.remove("form__color--checked")
        } else {
            element.classList.add("form__color--checked");
            codeColor = window.getComputedStyle(element).getPropertyValue("background-color");
            colorResult.value = codeColor;
            colorChooseElement.forEach((elementOther, indexOther) => {
                if (indexOther != index) {
                    elementOther.classList.remove("form__color--checked");
                }
            })
        }
    }
});

getColorCustom();

function getColorCustom() {
    const colorElement = document.querySelector(".form__color-check--custom")
    const colorInput = colorElement.querySelector(`input`);
    colorElement.addEventListener("mouseover", function () {
        colorInput.removeAttribute("disabled");
        colorInput.addEventListener("input", function () {
            codeColor = colorInput.value
            colorResult.value = codeColor;
        })
    });
    colorElement.addEventListener("mouseout", function () {
        colorInput.setAttribute("disabled", "");
    })
}

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
    limit: 3,
    listPage: ".paging",
    tagNameItemPage: "li",
    classNameItemPage: "page",
    activeItemPage: "page--current",
    prevBtn: "page--prev",
    nextBtn: "page--next",
});

//Validation Form
var validation = new Validation({
    formSelector: ".product__form",
    formBlockClass: "form__block",
    errorSelector: ".form__error",
    rules: [
        Validation.isRequired("#color"),
        Validation.isRequired("#parameter_1"),
        Validation.range("#parameter_1",),
        Validation.isRequired("#parameter_2"),
        Validation.range("#parameter_2"),
        Validation.isRequired("#parameter_3"),
        Validation.range("#parameter_3"),
        Validation.isRequired("#parameter_4"),
        Validation.range("#parameter_4"),
    ],
    // submitSelector: "#form__submit",
})