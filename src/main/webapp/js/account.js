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

var listProductE = document.querySelector(".service__order ");
var htmls = listProduct.slice(10,15).map(function (product) {
    return `<div class="block__product">
                <img class="img__product block__img" src = "../assets/img/product_img/${product.imgSrc}" >
                <div class="block__info">
                    <p class="info__product info__product--name">${product.name}</p>
                    <p class="info__product">Phân loại: ${getCategory(product.categoryId).nameType}</p>
                    <p class="info__product">Số lượng: 1</p>
                    <p class="info__product">Giá: ${formatCurrency(product.basePrice)}</p>               
                </div>
                <button class="btn"><a href="../htmls/review.jsp">Đánh giá</a></button>
            </div>
`
});
listProductE.innerHTML = htmls.join("");

var listProductElement = document.querySelector(".service__order--done ");
var htmlsOther = listProduct.slice(10,15).map(function (product) {
    return `<div class="block__product">
                <img class="img__product block__img" src = "../assets/img/product_img/${product.imgSrc}" >
                <div class="block__info">
                    <p class="info__product info__product--name">${product.name}</p>
                    <p class="info__product">Phân loại: ${getCategory(product.categoryId).nameType}</p>
                    <p class="info__product">Số lượng: 1</p>
                    <p class="info__product">Giá: ${formatCurrency(product.basePrice)}</p>
                </div>
                <button class="btn"><a href="../htmls/review.jsp">Đánh giá</a></button>
            </div>
`
});
listProductElement.innerHTML = htmls.join("");

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
}


const day = document.getElementById("day");
const month = document.querySelector("#month");
const year = document.querySelector("#year");

function showDay(days){
    var arrs=[];
    for(let i=0; i<days; i++){
        arrs.push(`<option value="${i+1}">${i+1}</option>`);
    }
    day.innerHTML=arrs.join('');
}

let yearValue=1950;
var monthValue =1;
let dayValue =1;


// let yearStart = 1950;
// const  arr=[];
// for (let i = 0; i < 100; i++) {
//     arr.push(`<option value="${yearStart}">${yearStart}</option>`);
//     yearStart++;
// }
// showDay(getDays(yearValue,monthValue));

// year.innerHTML=arr.join('');
year.addEventListener('change', function() {
    yearValue = this.value;
    showDay(getDays(yearValue,monthValue));
    console.log(yearValue,monthValue,dayValue);

});

month.addEventListener('change', function() {
    monthValue = this.value;

    showDay(getDays(yearValue,monthValue));
    console.log(yearValue,monthValue,dayValue);
});

day.addEventListener('change', function() {
    dayValue = this.value;
});

function getDays (year, month){
    var date = new Date(year, month, 1);
    var lastDay = new Date(year, month  , 0);
    console.log(lastDay)
    return lastDay.getDate();
}


