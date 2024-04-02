// const listProductElement = document.querySelector(".product__list");
// function loadProducts(arrayProducts) {
//     const vndFormat = Intl.NumberFormat("vi-VI", {
//         style: "currency",
//         currency: "VND",
//     });
//     listProductElement.innerHTML = "";
//     let html = arrayProducts.map(function (product) {
//         return ` <div class = "product__item" >
//                     <img src = "assets/img/product_img/${product.nameImage}" class="product__img" >
//                     <div class = "product__info" >
//                         <a class="product__name" target="_blank" href="../htmls/productDetail.jsp">${product.name}</a>
//                         <div class="product__review">
//                             <div class="product__review-stars">
//                                 <i class="fa-solid fa-star"></i>
//                                 <i class="fa-solid fa-star"></i>
//                                 <i class="fa-solid fa-star"></i>
//                                 <i class="fa-solid fa-star"></i>
//                                 <i class="fa-solid fa-star"></i>
//                             </div>
//                             <a class="product__review-num" target="_blank" href="../htmls/productDetail.jsp">${product.reviews} nhận xét</a>
//                         </div>
//                         <span class="product__price"><strong class="product__price--sale">${vndFormat.format(product.salePrice)}</strong> <strong class="product__price--original">${vndFormat.format(product.originalPrice)}</strong></span>
//                     </div>
//                 </div>`;
//     });
//     listProductElement.innerHTML = html.join("");
//     console.log("Load success")
// }
//
// var form = document.querySelector(".form__filter");
// var formSubmit = document.querySelector(".filter__submit");
// const objFilter = {};
//
// form.onsubmit = function (e) {
//     e.preventDefault();
// }
// formSubmit.onclick = function () {
//     objFilter.nameProduct = form.querySelector(`input[name="nameProduct"]`).value;
//     putArrayToProperties(objFilter, "categoryId", form.querySelectorAll(`input[name="categoryId"]:checked`))
//     putArrayToProperties(objFilter, "moneyRange", form.querySelectorAll(`input[name="moneyRange"]:checked`))
//     putArrayToProperties(objFilter, "size", form.querySelectorAll(`input[name="size"]:checked`))
//     putArrayToProperties(objFilter, "color", form.querySelectorAll(`input[name="color"]:checked`))
//     callAjax();
// }
//
// function putArrayToProperties(obj, nameProperties, arrayInput) {
//     obj[nameProperties] = [];
//     Array.from(arrayInput).forEach(function (element) {
//         obj[nameProperties].push(element.value);
//     })
// }
//
// function callAjax() {
//     let baseURL = "/filterProduct";
//     let queryString = $.param(objFilter); // Convert objFilter object to query string
//     let finalURL = `${baseURL}?${queryString}`;
//     $.ajax({
//         url: "filterProduct",
//         type: "get",
//         dataType: 'json',
//         data: {
//             objFilter: objFilter,
//             requestType: 'json' // Adding a custom requestType parameter
//         },
//         success: function (arrayProductCard) {
//             console.log(arrayProductCard);
//             loadProducts(arrayProductCard);
//             history.pushState(null, null, finalURL);
//             console.log("URL changed to:", finalURL);
//         },
//         error: function (error) {
//             console.log(error)
//         }
//     })
// }
const productList = document.querySelector(".product__list");

function handleShowProduct() {
    const pageNumber = document.querySelectorAll('.page')
    pageNumber.forEach(function (value) {
        value.onclick = function () {
            productList.innerHTML = ``
            $.ajax({
                url: "/filterProductBuying?page=" + value.innerText,
                type: "GET",
                success: function (data) {
                    showProduct(data)
                },
                error: function (error) {
                    console.log("loi khong hien thi san pham")
                },
            });
        }
    })
}

function showProduct(data) {
    const products = data["products"]


    products.forEach((value) => {
        const star = value["stars"]
        const reviewCounts = value["reviewCounts"]
        console.log(value)
        const product = value["product"]
        const id = product["id"]
        const name = product["name"]
        const originalPrice = product["originalPrice"]
        const salePrice = product["salePrice"]

        const quantity = data["quantity"]
        const currentPage = data["page"]

        const images = value["images"]
        const nameImage = images[0]["nameImage"]
        productList.innerHTML += `
            <div class="product__item hvr-grow-shadow">
                <a href="/showProductDetail?id=${id}">
                    <img src="${nameImage}" class="product__img" alt="" loading="lazy"/>
                </a>
                
                <div class="product__info">
                    <a class="product__name" target="_blank" href="/showProductDetail?id=${id}">${name}</a>
                </div>
                
                <div class="product__review">
                    <div class="product__review-stars">
                        // for(var starA = 0; starA < ${star};startA++){
                        //     <i class="fa-solid fa-star"></i>
                        // }
                        //                
                        // for(var starB = 0; starB < 5 - ${star};startB++){
                        //     <i class="fa-regular fa-star"></i>
                        // }
                    </div>
                    <div>
                       <a class="product__review-num" target="_blank" href="/showProductDetail"${reviewCounts}
                           nhận xét
                       </a>
                       
                       <span class="product__price">
                           <strong class="product__price--original">${originalPrice}</strong>
                           <strong class="product__price--sale">${salePrice}</strong>
                       </span>
                    </div>
                </div>
            </div>`
    })
}

handleShowProduct();