const listProductElement = document.querySelector(".product__list");

function loadProducts(arrayProducts) {
    const vndFormat = Intl.NumberFormat("vi-VI", {
        style: "currency",
        currency: "VND",
    });

    var htmls = arrayProducts.map(function (product) {
        return ` <div class = "product__item" >
                    <img src = "../assets/img/product_img/${product.imgSrc}" class="product__img" >
                    <div class = "product__info" >
                        <a class="product__name" target="_blank" href="../htmls/productDetail.jsp">${product.name}</a>
                        <div class="product__review">
                            <div class="product__review-stars">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                            </div>
                            <a class="product__review-num" target="_blank" href="../htmls/productDetail.jsp">1000 nhận xét</a>
                        </div>
                        <span class="product__price"><strong class="product__price--sale">${vndFormat.format(product.salePrice)}</strong> <strong class="product__price--original">${vndFormat.format(product.basePrice)}</strong></span>
                    </div>
                </div>`;
    });
    listProductElement.innerHTML = htmls.join("");

    //Paging for product cart
    const pagingReview = new Paging({
        itemSelector: ".product__item",
        displayShowType: "flex",
        limit: 8,
        listPage: ".paging",
        tagNameItemPage: "li",
        classNameItemPage: "page",
        activeItemPage: "page--current",
        prevBtn: "page--prev",
        nextBtn: "page--next",
    });
}

loadProducts(listProduct);

/*productSearch*/
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
searchButton.addEventListener("click", searchProduct);
function searchProduct() {
    const searchQuery = searchInput.value.toLowerCase();
    const filteredProducts = listProduct.filter(product => product.name.toLowerCase().includes(searchQuery));
    resultBox.innerHTML = "";
    loadProducts(filteredProducts);
}

//productKeyHint
let availableKeyWord = [
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
const listCategoryName = listCategories.map(function (category) {
    return category.nameType;
})
console.log(listCategoryName);
const listCategoryElement = document.querySelector(".category__list");
function loadCategory() {
    const htmls = listCategoryName.map(category => {
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
    loadProducts(filteredProducts);
}