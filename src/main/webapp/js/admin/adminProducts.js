// Date format (now)
const dateInputs = document.querySelectorAll(`input[type="date"]`);
dateInputs.forEach(function (dateInput) {
// Get today's date
    const today = new Date();

// Format today's date as yyyy-mm-dd (required by input type="date")
    const formattedToday = today.toISOString().split('T')[0];

    // Set the default value to today's date in dd/mm/yyyy format
    dateInput.value = formattedToday;
})

//Load page btn
document.querySelector(".reload__btn").onclick = function () {
    window.location.reload();
}

function getClose(modal) {
    return modal.querySelector(".modal__product-close");
}

<<<<<<< HEAD
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
=======
//Read product
const iframeRead = document.querySelector("#dialog-product-read .modal__product-iframe");
const dataViewElement = document.querySelectorAll(".table__data-view");
const modalRead = document.querySelector("#dialog-product-read");
const elementCloseRead = getClose(modalRead);

elementCloseRead.onclick = function () {
    modalRead.style.display = "none";
};

dataViewElement.forEach(function (element) {
    const pageTarget = `${window.location.origin}/adminProductForm.jsp`;
    element.onclick = function () {
        // Open dialog
        modalRead.style.display = "block";

        // Send via iframe
        const tableRow = this.parentNode;
        const productId = tableRow.querySelector(".table__data-id").textContent.trim();
        iframeRead.contentWindow.postMessage({
            productId: productId,
            state: 1,
        }, pageTarget);
    }
});

//Create product
const iframeCreate = document.querySelector("#dialog-product-create .modal__product-iframe");
const modalCreateBtn = document.querySelector("#button-create-product");
const modalCreate = document.querySelector("#dialog-product-create");
const elementCloseCreate = getClose(modalCreate);

elementCloseCreate.onclick = function () {
    modalCreate.style.display = "none";
}

modalCreateBtn.onclick = function () {
    const pageTarget = `${window.location.origin}/adminProductForm.jsp`;
    modalCreate.style.display = "block";
    // Send via iframe
    iframeCreate.contentWindow.postMessage({
        state: 0,
    }, pageTarget);
}

//Update product
const iframeUpdate = document.querySelector("#dialog-product-update .modal__product-iframe");
const dataUpdateElement = document.querySelectorAll(".table__data-edit");
const modalUpdate = document.querySelector("#dialog-product-update");
const elementCloseUpdate = getClose(modalUpdate);

elementCloseUpdate.onclick = function () {
    modalUpdate.style.display = "none";
};

dataUpdateElement.forEach(function (element) {
    element.onclick = function () {
        const pageTarget = `${window.location.origin}/adminProductUpdateForm.jsp`;
        // Open dialog
        modalUpdate.style.display = "block";

        // Send via iframe
        const tableRow = this.parentNode;
        const productId = tableRow.querySelector(".table__data-id").textContent.trim();
        iframeUpdate.contentWindow.postMessage({
            productId: productId,
            state: 2,
        }, pageTarget);
    }
});
>>>>>>> 2f2e062eb617b76e5cf7f6eee7de2166a2940dfa
