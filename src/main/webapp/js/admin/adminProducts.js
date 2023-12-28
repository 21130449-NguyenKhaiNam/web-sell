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
