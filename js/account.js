//loadAvatar
const imgDiv = document.querySelector('.user__img');
const img = document.querySelector('#photo');
const file = document.querySelector('#file');
const  uploadbtn = document.querySelector('#uploadbtn');

file.addEventListener('change', function (){
    const chooseFile = this.files[0];
    if (chooseFile){
        const reader = new FileReader();
        reader.addEventListener('load', function (){
            img.setAttribute('src', reader.result);
        })
        reader.readAsDataURL(chooseFile);
    }
})

//close_open--eye
var inputForm = document.querySelectorAll(".input__form");
inputForm.forEach(function (input) {
    const eyeClose = input.querySelector(".icon__eye--close");
    const eyeOpen = input.querySelector(".icon__eye--open");
    const inputElement = input.querySelector("input")
    eyeClose.onclick = function (e) {
        inputElement.type = "text";
        eyeOpen.style.display = "block";
        eyeClose.style.display = "none";
    }
    eyeOpen.onclick = function (e) {
        inputElement.type = "password";
        eyeOpen.style.display = "none";
        eyeClose.style.display = "block";
    }
})

var listProductE = document.querySelector(".service__order");
var htmls = listProduct.slice(10,15).map(function (product) {
    return `<div class="block__product">
                <img class="img__product block__img" src = "../assets/img/product_img/${product.imgSrc}" >
                <div class="block__info">
                    <p class="info__product">${product.name}</p>
                    <p class="info__product">${getCategory(product.categoryId).nameType}</p>
                    <p class="info__product">Số lượng: 1</p>
                    <p class="info__product">${product.salePrice}</p>
                    <p class="info__product">Ngày: 2/11/2023</p>
                </div>
                <button class="btn"><a href="../htmls/review.html">Đánh giá</a></button>
            </div>
`
});
htmls.unshift(` <h1 class="title">Lịch sử mua hàng</h1>`)
listProductE.innerHTML = htmls.join("");