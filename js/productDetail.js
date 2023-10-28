// Get code color
var codeColor;
var colorChooseElement = document.querySelectorAll(".form__color-check");
var colorResult = document.querySelector(".form__input-color-code");
console.log(colorResult);
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

// show/hide description and review product
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
})
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
//Paging for review
var pagingReview = new Paging({
    itemSelector: ".review",
    limit: 3,
    listPage: ".review__paging",
    tagNameItemPage: "li",
    classNameItemPage: "review__page",
    activeItemPage: "review__page--current",
    prevBtn: "review__page--prev",
    nextBtn: "review__page--next",
});