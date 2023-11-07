var bodyTable = document.querySelector(".table tbody");

function loadDataToTable(product) {
    return `<tr class="table__row">
                <td class="table__date-checkbox">
                    <label class="check">
                        <input type="checkbox" name="passing" class="filter__input" hidden="true">
                    </label>
                </td>
                <td class="table__data-edit">
                    <label class="" for="">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </label>
                </td>
                <td class="table__data">
                    <p class="table__cell">${product.id}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${product.name}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${getCategory(product.idCategory).nameType}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${product.basePrice}đ</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${product.salePrice}đ</p>
                </td>
        </tr>`
}

var htmls = listProduct.map(function (element) {
    return loadDataToTable(element);
});
bodyTable.innerHTML = htmls.join("");

// Paging for product cart
var pagingReview = new Paging({
    itemSelector: "tbody .table__row",
    displayShowType: "table-row",
    limit: 8,
    listPage: ".paging",
    tagNameItemPage: "li",
    classNameItemPage: "page",
    activeItemPage: "page--current",
    prevBtn: "page--prev",
    nextBtn: "page--next",
});

//Add new product
var addProductBtn = document.querySelector("#button-add-product");
console.log(addProductBtn)
var dialogProduct = document.querySelector("#dialog-product")
var dialogClose = document.querySelector("#dialog-product-close");
var blurDialog = document.querySelector(".modal__blur");
addProductBtn.onclick = function () {
    dialogProduct.style.display = "block";
}
dialogClose.onclick = function (){
    dialogProduct.style.display = "none";
}

blurDialog.onclick = function (){
    dialogProduct.style.display = "none";
}