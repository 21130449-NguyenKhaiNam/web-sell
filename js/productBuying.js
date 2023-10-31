const listProduct = [
    {
        product: "product21", name: "Sơ mi dài tay Café-DriS", category: "category06", imgSrc: "product21.jpg", desc: `Chất liệu: 50% S.Café + 50% Recycled PET
                Phù hợp với: đi làm, đi chơi
                Kiểu dáng: Regular fit dáng suông
                Người mẫu: 177 cm - 74 kg, mặc size XL
                Tự hào sản xuất tại Việt Nam`, basePrice: 499000, salePrice: 429000
    }, {
        product: "product22",
        name: "Áo dài tay Cotton Compact",
        category: "category03",
        imgSrc: "product22.jpg",
        desc: `Chất liệu: 95% Cotton Compact - 5% Spandex
                Kiểu dáng: áo thun dài tay
                Phù hợp với: giữ ấm cơ thể vào mùa đông
                Sản xuất tại xưởng 8 năm kinh nghiệm tại TP Hồ Chí Minh * Xem nhà máy >
                Người mẫu: 1m75, 69kg * Mặc áo XL, quần L
                Tự hào sản xuất tại VIệt Nam`,
        basePrice: 269000,
        salePrice: 229000
    }, {
        product: "product23", name: "Áo dài tay nỉ", category: "category03", imgSrc: "product23.jpg", desc: `Chất liệu 60% Cotton, 40% Polyester tạo cảm giác thông thoáng và thoải mái khi mặc
                Kiểu dáng: áo nỉ dài tay
                Phù hợp với: giữ ấm cơ thể vào mùa đông
                Người mẫu: 1m75, 69kg * Mặc áo XL, quần L
                Tự hào sản xuất tại Việt Nam
   `, basePrice: 339000, salePrice: 289000
    }, {
        product: "product24",
        name: "Áo dài tay Cotton Compact V2",
        category: "category01",
        imgSrc: "product24.jpg",
        desc: `Chất liệu: 95% Cotton Compact + 5% Spandex
                Bề mặt vải Cotton mềm mịn, cảm giác mát lần đầu chạm tay
                Thấm hút mồ hôi tốt
                Độ xù lông thấp
                Vải có độ bền cao, co giãn 4 chiều và hạn chế bai nhão
                Tự hào sản xuất tại Việt Nam`,
        basePrice: 269000,
        salePrice: 229000
    }, {
        product: "product25", name: "Áo nỉ chui đầu Lifewear", category: "category03 ", imgSrc: "product25.jpg", desc: `Chất liệu: Nỉ chân cua
            Thành phần: 60% Cotton + 40% Polyester
            Thoáng khí và thoải mái khi mặc, vẫn giữ ấm cho bạn trong trời thu đông
            Hạn chế xù lông, bền màu
            Form dáng: Regular, thoải mái
            Tự hào sản xuất tại Viêt Nam`, basePrice: 339000, salePrice: 309000
    }, {
        product: "product26", name: "Shorts thể thao 9", category: "category04", imgSrc: "product26.jpg", desc: `Chất liệu: 88% Polyester + 12% Spandex
                Vải có khả năng thấm hút tốt và nhanh khô
                Co giãn 4 chiều, thoải mái vận động
                Túi to và sâu tiện lợi, thoải mái đựng đồ cá nhân
                Có 1 túi khoá kéo ẩn, đựng vừa chìa khoá hay Airpods
                Độ dài đo từ đũng đến viền ống quần: 9"
                Tự hào sản xuất tại Việt Nam`, basePrice: 229000, salePrice: 249000,
    }
    ];
var listProductElement = document.querySelector(".product__list");
var htmls = listProduct.map(function (product) {
    return `<div class = "col-3" >
                <div class = "model_product item4" >
                    <img src = "../assets/img/product_img/${product.imgSrc}" >
                    <div class = "information-product" >
                        <span class = "status" > Most required </span>
                        <a class="name-product" href="../htmls/productDetail.html">${product.name}</a>
                        <div class="review-product">
                            <div class="icon-review">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                            </div>
                            <a href="#">1000 nhận xét</a>
                        </div>
                        <span class="price"><strong class="sale-price">${product.salePrice}</strong> <s class="original-price">${product.salePrice}</s></span>
                    </div>
                </div>
          </div>
`;
});
listProductElement.innerHTML = htmls.join("");

//Paging for product cart
var pagingReview = new Paging({
    itemSelector: `.product__list > [class ^= "col"]`,
    limit: 8,
    listPage: ".review__paging",
    tagNameItemPage: "li",
    classNameItemPage: "review__page",
    activeItemPage: "review__page--current",
    prevBtn: "review__page--prev",
    nextBtn: "review__page--next",
});


/*productSearch*/
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
searchButton.addEventListener("click", searchProduct);
function searchProduct() {
    const searchQuery = searchInput.value.toLowerCase();
    const filteredProducts = listProduct.filter(product => product.name.toLowerCase().includes(searchQuery));
    const htmls = filteredProducts.map(product => {
        return `
            <div class="col-3">
                <a href="#!">
                    <div class="model_product item4">
                        <img src="${product.img}">
                        <div class="information-product">
                            <span class="status">Most required</span>
                            <a class="name-product" href="#">${product.name}</a>
                            <div class="review-product">
                                <div class="icon-review">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </div>
                                <a href="#">1000 nhận xét</a>
                            </div>
                            <span class="price"><strong class="sale-price">${product.price}</strong> <s class="original-price">300,000₫</s></span>
                        </div>
                    </div> 
                </a>
            </div>`;
    });
    listProductElement.innerHTML = htmls.join("");
}


//productKeyHint
let availableKeyWord = [
    'product 1',
    'product 2',
    'product 3',
    'product 4',
    'product 5',
    'product 6',
    'product 7',
    'product 8',
    'product 9',
    'product 10',
];
const resultBox = document.querySelector(".result-box");
const inputBox = document.getElementById("search-input");

inputBox.onkeyup = function () {
    let result = [];
    let input = inputBox.value;
    if (input.length) {
        result = availableKeyWord.filter((keyword) => {
            return keyword.toLowerCase().includes(input.toLowerCase());
        });
        console.log(result)
    }
    display(result);
    if (!result.length) {
        resultBox.innerHTML = '';
    }
}
function display(result) {
    const content = result.map((list) => {
        return "<li onclick=selectInput(this)>" + list + "</li>";
    });
    resultBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}
function selectInput(list) {
    inputBox.value = list.innerHTML;
    resultBox.innerHTML = '';
}


/*categoryLoad*/
const listCategory = [
    'Áo dài tay',
    'Áo oversize',
    'Áo nỉ dài tay',
    'Quần short',
    'Áo Polo',
    'Áo sơ mi tay dài',
    'Áo sơ mi ngắn tay',
    'Quần jeans',
    'Quần Kaki',
    'Áo ba lỗ'
];
const listCategoryElement = document.querySelector(".category__list");
function loadCategory() {
    const htmls = listCategory.map(category => {
        return`<li class="category__item" onclick="filterProducts('${category}')">${category}</li>`;
    });
    htmls.unshift(` <li class="category__item" >
                                <span class="category__icon"></span>
                                Danh mục
                            </li>`)
    listCategoryElement.innerHTML = htmls.join("");
}
loadCategory();


// categorySearch
function filterProducts(category) {
    const filteredProducts = listProduct.filter(product => product.category.toLowerCase().includes(category.toLowerCase()));
    const htmls = filteredProducts.map(product => {
        return `
            <div class="col-3">
                <a href="#!">
                    <div class="model_product item4">
                        <img src="${product.img}">
                        <div class="information-product">
                            <span class="status">Most required</span>
                            <a class="name-product" href="#">${product.name}</a>
                            <div class="review-product">
                                <div class="icon-review">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </div>
                                <a href="#">1000 nhận xét</a>
                            </div>
                            <span class="price"><strong class="sale-price">${product.price}</strong> <s class="original-price">300,000₫</s></span>
                        </div>
                    </div> 
                </a>
            </div>`;
    });
    listProductElement.innerHTML = htmls.join("");
}