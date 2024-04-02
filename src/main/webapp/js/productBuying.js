document.addEventListener("DOMContentLoaded", function () {
    generationPage(1,1)

    // các sản phẩm khi vừa vào trang sản phẩm
    callAjaxToPage(1);
});
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

// phương thức này truyền vào số trang hiện tại
// sẽ trả về sản phẩm của trang đó
function callAjaxToPage(pageNumber) {
    $.ajax({
        url: "/filterProductBuying?page=" + pageNumber,
        type: "GET",
        success: function (data) {
            generationPage(parseInt(pageNumber), parseInt(pageNumber), data["quantity"])
            showProduct(data)
        },
        error: function (error) {
            console.log("loi khong hien thi san pham")
        },
    });
}

// phương thức này dùng để xử lý sự kiện click vào trang nào đó
// sẽ gọi method callAjaxToPage(currentPage)
function handleShowProduct(aTag) {
    if(aTag){
        productList.innerHTML = ``
        callAjaxToPage(aTag.innerText)
    }
}

// khi lấy được data của product dưới dạng json thì show ra giao diện
function showProduct(data) {
    const products = data["products"]

    products.forEach(function (value) {
        const star = value["stars"]
        const noStar = []
        const hasStar = []

        for (let starA = 0; starA < star; starA++) {
            hasStar[starA] = (`<i class="fa-solid fa-star"></i>`)
        }
        for (let starB = 0; starB < 5 - star; starB++) {
            noStar[starB] = (`<i class="fa-regular fa-star"></i>`)
        }

        const noStarHtml = noStar.map((value, index) => value)
        const noStarResult = noStarHtml.join("")

        const hasStarHtml = hasStar.map((value, index) => value)
        const hasStarResult = hasStarHtml.join("")

        const reviewCounts = value["reviewCounts"]
        const product = value["product"]
        const id = product["id"]
        const name = product["name"]
        const originalPrice = product["originalPrice"]
        const salePrice = product["salePrice"]

        const quantity = data["quantity"]

        const currentPage = data["page"]
        const images = value["images"]

        const nameImage = images[0]["nameImage"]
        const vndFormat = Intl.NumberFormat("vi-VI", {
            style: "currency",
            currency: "VND",
        });
        productList.innerHTML += `
            <div class="product__item hvr-grow-shadow">
                <a href="/showProductDetail?id=${id}">
                    <img src="${nameImage}" class="product__img" alt="" loading="lazy"/>
                </a>
                
                <div class="product__info">
                    <a class="product__name" target="_blank" href="/showProductDetail?id=${id}">${name}</a>
                
                    <div class="product__review">
                        <div class="product__review-stars">
                            ${hasStarResult}
                            ${noStarResult}
                        </div>
                        
                           <a class="product__review-num" target="_blank" href="/showProductDetail?id=${id}">
                               ${reviewCounts} nhận xét
                           </a>
                    </div> 
                
                    <span class="product__price">
                        <strong class="product__price--original">${vndFormat.format(originalPrice)}</strong>
                        <strong class="product__price--sale">${vndFormat.format(salePrice)}</strong>
                    </span>
                </div>
            </div>`

    })
}

function generationPage(pageNumber, currentPage, totalPage) {
    var minPage = currentPage - 2
    var maxPage = currentPage + 2;
    if(maxPage > totalPage){
        maxPage = totalPage
    }

    productPages.innerHTML = ''

    if (currentPage > 3) {
        productPages.innerHTML += `<a class="page access_page_quickly">...</a>`
        const div = document.createElement('div')
        div.style.width = '200px'
        div.style.height = '50px'
        div.style.overflow = 'auto'

        for (let i = 1; i < minPage; i++) {
            const a = document.createElement('a')
            a.innerText = i
            if(i === pageNumber){
                a.classList.add("page--current");
            }
            a.classList.add("page")
            a.onclick = function (){handleShowProduct(a)}
            div.appendChild(a)
        }

        tippy('.access_page_quickly', {
            content: div,
            placement: 'top-start',
            interactive: true,
            duration: [500, 250],
            arrow: true,
        });
    }

    if (minPage < 1) {
        minPage = 1;
        maxPage = 5
    }

    for (let i = minPage; i <= maxPage; i++) {
        const a = document.createElement('a');
        if(i === pageNumber){
            a.classList.add("page--current");
        }
        a.classList.add('page')
        a.innerText = i;

        a.onclick = function (){handleShowProduct(a)}
        productPages.appendChild(a);
    }
}

const productList = document.querySelector('.product__list')
const productPages = document.querySelector('.paging')



