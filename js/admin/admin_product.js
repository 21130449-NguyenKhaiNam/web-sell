var bodyTable = document.querySelector(".table tbody");

function loadDataToTable(product) {
    return `<tr class="table__row">
                <td class="table__date-checkbox">
                    <label class="check">
                        <input type="checkbox" name="passing" class="filter__input" hidden="true">
                    </label>
                </td>
                <td class="table__data-edit">
                    <label class="">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </label>
                </td>
                <td class="table__data table__data-id">
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
        </tr>`;
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

var addProductBtn = document.querySelector("#button-add-product");
var removeProductBtn = document.querySelector("#button-remove-product");
//Add new product Dialog
var dialogProduct = document.querySelector("#dialog-product");
var dialogClose = document.querySelector("#dialog-product-close");
var blurDialog = document.querySelector(".modal__blur");
addProductBtn.onclick = function () {
    dialogProduct.style.display = "block";
};
dialogClose.onclick = function () {
    dialogProduct.style.display = "none";
};

blurDialog.onclick = function () {
    dialogProduct.style.display = "none";
};

// Search by name Product
var keyword = document.querySelector(`input[name="search"]`);
var searchType = document.querySelector(`select[name = "searchType"]`);

function search() {
    let listProductField = [];
    const name = keyword.value;
    console.log(name);
    const type = searchType[searchType.selectedIndex];
    listProduct.forEach(function (product) {
        if (product[type.value].toLowerCase().includes(name.toLowerCase())) listProductField.push(product);
    });
    loadProduct(listProductField);
}

function loadProduct(listProduct) {
    const htmls = listProduct.map(function (product) {
        return loadDataToTable(product);
    });
    bodyTable.innerHTML = htmls.join("");
}

keyword.onchange = function () {
    search();
};
searchType.onchange = function () {
    search();
};

// Delete
var listProducDeleted = [];
function deleteProduct(){
    var checkBoxCell = document.querySelectorAll(".table__row .table__date-checkbox input");
    checkBoxCell.forEach(function (element) {
        element.onchange = function () {
            const id = getParentNode(element, "table__row").querySelector(".table__data-id").innerText;
            const product = getProduct(id);
            if (element.checked) {
                // add
                listProducDeleted.push(product);
            } else {
                // remove
                removeProductInList(product, listProducDeleted);
            }

            if (listProducDeleted.length != 0) {
                removeProductBtn.style.display = "block";
                addProductBtn.style.display = "none";
            } else {
                removeProductBtn.style.display = "none";
                addProductBtn.style.display = "block";
            }
        };
        deleteProduct();
    });

}
removeProductBtn.onclick = function () {
    listProducDeleted.forEach(function (element) {
        removeProductInList(element, listProduct);
    });
    var htmls = listProduct.map(function (element) {
        return loadDataToTable(element);
    });
    bodyTable.innerHTML = htmls.join("");
};
function removeProductInList(product, list) {
    for (let i = 0; i < list.length; i++) {
        if (product.id == list[i].id) {
            list.splice(i, 1);
            break;
        }
    }
}

function getParentNode(childElement, parentSelector) {
    while (!childElement.classList.contains(parentSelector)) {
        childElement = childElement.parentElement;
    }
    return childElement;
}

//Filter
// var search = document.querySelector(`input[name="search"]`);
// var date = document.querySelector(`input[name="date"]`);
// var payment = document.querySelector(`input[name="payment"]`);
// var passing = document.querySelector(`input[name="passing"]`);
// var delivery = document.querySelector(`input[name="delivery"]`);
// var searchType = document.querySelector(`select[name = "searchType"]`);
// var buttonSearch = document.querySelector(".filter__submit");
//
// var statePassing = {
//     verify: "Đã xác nhận",
//     noVerify: "Chưa xác nhận",
// }
// var statePayment = {
//     atm: "Tiền mặt",
//     cash: "Chuyển khoản",
//     wallet: "Ví điện tử",
// }
// var stateDelivery = {
//     ordered: "Đơn đã đặt",
//     pending: "Đang chuẩn bị",
//     delivering: "Đang giao",
//     done: "Đã giao",
// }
//
// function filter() {
//     const listProduct = [];
//     const obj = {
//         search: search.value,
//         date: date.value,
//         payment: payment.value,
//         passing: passing.value,
//         delivery: delivery.value,
//         searchType: searchType.value,
//     };
//     // Nếu trường đó trống => undified, nếu trường đó sai thì trả về null
//     console.log(statePayment[obj.passing])
//     listProduct.forEach(function (product) {
//         getProductByField()
//     });
// }
//
// function getProductByField(field, value) {
//     if (field == undefined) return undefined;
//     listProduct.forEach(function (product) {
//         if (product[field] == value) {
//             return product;
//         }
//     })
//     return null;
// }
//
// buttonSearch.onclick = function (e) {
//     e.preventDefault();
//     filter();
// }
