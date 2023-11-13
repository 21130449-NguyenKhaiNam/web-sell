
var listProductElement = document.querySelector(".product__list");
var htmls = listProduct.map(function (product) {
    return ` <div class = "model_product" >
                    <img src = "../assets/img/product_img/${product.imgSrc}" >
                    <div class = "information-product" >
                        <span class = "status" > Most required </span>
                        <a class="name-product" target="_blank" href="../htmls/productDetail.html">${product.name}</a>
                        <div class="review-product">
                            <div class="icon-review">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                            </div>
                            <a class="name-product" target="_blank" href="../htmls/productDetail.html">1000 nhận xét</a>
                        </div>
                        <span class="price"><strong class="sale-price">${product.salePrice}</strong> <s class="original-price">${product.salePrice}</s></span>
                    </div>
                </div>
`;
});
listProductElement.innerHTML = htmls.join("");

//Paging for product cart
var pagingReview = new Paging({
    itemSelector: `.model_product`,
    displayShowType: "flex",
    limit: 8,
    listPage: ".paging",
    tagNameItemPage: "li",
    classNameItemPage: "page",
    activeItemPage: "page--current",
    prevBtn: "page--prev",
    nextBtn: "page--next",
});


/*productSearch*/
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
searchButton.addEventListener("click", searchProduct);
function searchProduct() {
    const searchQuery = searchInput.value.toLowerCase();
    const filteredProducts = listProduct.filter(product => product.name.toLowerCase().includes(searchQuery));
    const htmls = filteredProducts.map(product => {
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
                             <a class="name-product" target="_blank" href="../htmls/productDetail.html">1000 nhận xét</a>
                        </div>
                        <span class="price"><strong class="sale-price">${product.salePrice}</strong> <s class="original-price">${product.salePrice}</s></span>
                    </div>
                </div>
          </div>
`;
    });
    listProductElement.innerHTML = htmls.join("");
}


//productKeyHint
let availableKeyWord = [
    // 'product 1',
    // 'product 2',
    // 'product 3',
    // 'product 4',
    // 'product 5',
    // 'product 6',
    // 'product 7',
    // 'product 8',
    // 'product 9',
    // 'product 10',
];
const resultBox = document.querySelector(".result-box");
const inputBox = document.getElementById("search-input");

inputBox.onkeyup = function () {
    let input = inputBox.value;
    if (input.length) {
        availableKeyWord =[];
        listProduct.forEach((product) => {
            if (product.name.toLowerCase().includes(input.toLowerCase()))  {
                availableKeyWord.push(product.name)
            }
        });
        console.log(availableKeyWord)
        display(availableKeyWord);
    }
    if (availableKeyWord.length==0) {
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
    const filteredProducts = listProduct.filter(product => getCategory(product.categoryId).nameType.toLowerCase().includes(category.toLowerCase()));
    const htmls = filteredProducts.map(product => {
        return `<div class = "model_product item4" >
                    <img src = "../assets/img/product_img/${product.imgSrc}" >
                    <div class = "information-product" >
                        <span class = "status" > Most required </span>
                        <a class="name-product" target="_blank" href="../htmls/productDetail.html">${product.name}</a>
                        <div class="review-product">
                            <div class="icon-review">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                            </div>
                        <a class="name-product" target="_blank" href="../htmls/productDetail.html">1000 nhận xét</a>
                        </div>
                        <span class="price"><strong class="sale-price">${product.salePrice}</strong> <s class="original-price">${product.salePrice}</s></span>
                    </div>
                </div>`;
    });
    listProductElement.innerHTML = htmls.join("");
}