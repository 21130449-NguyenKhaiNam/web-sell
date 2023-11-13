var bodyTable = document.querySelector(".table tbody");

function productToHTML(product) {
    const vndFormat = Intl.NumberFormat("vi-VI", {
        style: "currency",
        currency: "VND",
    });
    return `<tr class="table__row">
                <td class="table__date-checkbox">
                    <label class="check">
                        <input type="checkbox" class="filter__input" hidden="true">
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
                    <p class="table__cell">${getCategory(product.categoryId).nameType}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${vndFormat.format(product.basePrice)}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${vndFormat.format(product.salePrice)}</p>
                </td>
        </tr>`;
}

function loadListToTable(listProduct) {
    const htmls = listProduct.map(function (product) {
        return productToHTML(product);
    });
    bodyTable.innerHTML = htmls.join("");

    const allRows = bodyTable.querySelectorAll(".table__row");
    allRows.forEach(function (row) {
        editProduct(row);
    });
    paging();
}

function paging() {
    // Paging for product cart
    const paging = new Paging({
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
}
loadListToTable(listProduct);

// Open/close dialog (add + edit dialog)
var addProductBtn = document.querySelector("#button-add-product");
var removeProductBtn = document.querySelector("#button-remove-product");
var productDialog = document.querySelector("#dialog-product-add");
var closeDialog = productDialog.querySelector(".modal__product-close");
var blurDialog = productDialog.querySelector(".modal__blur");
addProductBtn.onclick = function () {
    productDialog.style.display = "block";
};
closeDialog.onclick = function () {
    productDialog.style.display = "none";
};

blurDialog.onclick = function () {
    productDialog.style.display = "none";
};
loadCategoryModal(productDialog);
loadImg(productDialog);
addSize(productDialog);

// Add/remove form size
function addSize(dialog) {
    const addSizeBtn = dialog.querySelector(".form__add-size");
    const formSizes = dialog.querySelector(".form__sizes");
    addSizeBtn.onclick = function () {
        const formSizeHTML = `<div class="form__size" onclick="removeSize(this)">
                                        <input type="text" name="size" class="form__size-input">
                                            <i class="form__size-delete fa-solid fa-xmark" ></i>
                                    </div>`;
        formSizes.insertAdjacentHTML("beforeend", formSizeHTML);
    }
}

function removeSize(formSize) {
    const formSizeDelete = formSize.querySelector(".form__size-delete");
    formSizeDelete.onclick = function (e) {
        formSize.remove();
    }
}

//Add new product dialog
function loadCategoryModal(dialog) {
    const categorySelect = dialog.querySelector(" .form__select");
    let htmlsCategory = listCategories.map(function (category) {
        return `<option name="category" value="${category.id}" class="form__option">${category.nameType}</option>`
    });
    categorySelect.innerHTML = htmlsCategory.join("");
}


// Delete product
var listProductDeleted = [];
function deleteProduct(){
    var checkBoxCell = document.querySelectorAll(".table__row .table__date-checkbox input");
    checkBoxCell.forEach(function (element) {
        element.onchange = function () {
            const id = getParentNode(element, "table__row").querySelector(".table__data-id").innerText;
            const product = getProduct(id);
            if (element.checked) {
                // add
                listProductDeleted.push(product);
                console.log(1)
            } else {
                // remove
                removeProductInList(product, listProductDeleted);
            }
            showHideDeleteBtn();
        };
    });
}

deleteProduct();

function showHideDeleteBtn() {
    if (listProductDeleted.length != 0) {
        removeProductBtn.style.display = "block";
        addProductBtn.style.display = "none";
    } else {
        removeProductBtn.style.display = "none";
        addProductBtn.style.display = "block";
    }
}

removeProductBtn.onclick = function () {
    // Delete product in listProduct
    listProductDeleted.forEach(function (element) {
        removeProductInList(element, listProduct);
    });
    // Delete all listProductDeleted
    listProductDeleted = [];
    // Load product list
    loadListToTable(listProduct);
    deleteProduct();
    showHideDeleteBtn();
};

function removeProductInList(product, list) {
    for (let i = 0; i < list.length; i++) {
        if (product.id == list[i].id) {
            list.splice(i, 1);
            break;
        }
    }
}

// Edit product dialog
function editProduct(productRow) {
    let id = productRow.querySelector(".table__data-id");
    // Open/close dialog
    const editDialog = document.querySelector("#dialog-product-edit");
    const closeDialog = editDialog.querySelector(".modal__product-close");
    const blurDialog = editDialog.querySelector(".modal__blur");
    const editBtn = productRow.querySelector(".table__data-edit");
    editBtn.onclick = function () {
        editDialog.style.display = "block";

        const product = getProduct(id.innerText);

        // Query input
        id = editDialog.querySelector(`input[name="id"]`);
        const name = editDialog.querySelector(`input[name="name"]`);
        const originalPrice = editDialog.querySelector(`input[name="originalPrice"]`);
        const salePrice = editDialog.querySelector(`input[name="salePrice"]`);
        loadCategoryModal(editDialog);
        const category = editDialog.querySelector(`select[name="idCategory"]`);
        const sizes = editDialog.querySelector(".form__sizes");

        // fill product data to input
        id.value = product.id;
        name.value = product.name;
        originalPrice.value = product.basePrice;
        salePrice.value = product.salePrice;
        const categoryChoose = category.querySelector(`option[value="${product.categoryId}"]`);
        categoryChoose.setAttribute("selected", "true");

        const arraySize = JSON.parse(product.size);
        const sizeHTML = arraySize.map(function (size, index) {
            if (index == 0) {
                return `<div class="form__size">
                            <input type="text" name="size" value="${size}" class="form__size-input">
                        </div>`;
            }
            return `<div class="form__size" onclick="removeSize(this)">
                        <input type="text" name="size" value="${size}" class="form__size-input">
                            <i class="form__size-delete fa-solid fa-xmark" ></i>
                    </div>`;
        });
        sizes.innerHTML = sizeHTML.join("");
    }
    closeDialog.onclick = function () {
        editDialog.style.display = "none";
    }
    blurDialog.onclick = function () {
        editDialog.style.display = "none";
    }
    addSize(editDialog);
    loadImg(editDialog);
}

//load img product
function loadImg(dialog) {
    const formPreviewImg = dialog.querySelector(".form__preview-img");
    const imgPreview = formPreviewImg.querySelector("img");
    const imgInput = formPreviewImg.querySelector('.form__file');
    const icon = formPreviewImg.querySelector(".form__preview-icon");
    imgInput.addEventListener('change', function () {
        const chooseFile = this.files[0];
        if (chooseFile) {
            icon.style.display = "none";
            imgPreview.style.display = "block";
            const reader = new FileReader();
            reader.addEventListener('load', function () {
                imgPreview.setAttribute('src', reader.result);
            })
            reader.readAsDataURL(chooseFile);
        }
    })
}

//Search
search();
function search() {
    const searchObj = {
        keyword: "",
        type: "",
    };
    const formSearch = document.querySelector("#form-search")
    const buttonFilter = formSearch.querySelector(".filter__submit");
    let keyword, type;

    buttonFilter.onsubmit = function (e) {
        e.preventDefault();
    }
    buttonFilter.onclick = function (e) {
        e.preventDefault();
        keyword = formSearch.querySelector(`input[name="keyword"]`).value;
        const select = formSearch.querySelector(".search__select");
        type = select.options[select.selectedIndex].value;
        searchObj.keyword = keyword;
        searchObj.type = type;
        const listProductFiltered = filter();
        loadListToTable(listProductFiltered);
    }

    function filter() {
        return listProduct.filter(function (product) {
            const nameProduct = product[searchObj.type].toLowerCase();
            const nameKeyword = searchObj.keyword.toLowerCase();
            return nameProduct.includes(nameKeyword);
        });
    }
}

function getParentNode(childElement, parentSelector) {
    while (!childElement.classList.contains(parentSelector)) {
        childElement = childElement.parentElement;
    }
    return childElement;
}

