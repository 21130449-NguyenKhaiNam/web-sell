const pageTarget = `${window.location.origin}/adminCategoryForm.jsp`;
const iframeCreate = document.querySelector("#dialog-category-create .modal__product-iframe");
const modalCreateBtn = document.querySelector("#button-create-category");
const modalCreate = document.querySelector("#dialog-category-create");
const elementCloseCreate = getClose(modalCreate);

elementCloseCreate.onclick = function () {
    modalCreate.style.display = "none";
}

function getClose(modal) {
    return modal.querySelector(".modal__product-close");
}

modalCreateBtn.onclick = function () {
    modalCreate.style.display = "block";
    // Send via iframe
    iframeCreate.contentWindow.postMessage({
        state: 0,
    }, pageTarget);
}

//Update & read
const iframeRead = document.querySelector("#dialog-category-read .modal__product-iframe");
const modalRead = document.querySelector("#dialog-category-read");
const elementCloseRead = getClose(modalRead);
elementCloseRead.onclick = function () {
    modalRead.style.display = "none";
}
const categoryItemElements = document.querySelectorAll(".category__item");
categoryItemElements.forEach(function (parameterItemElement) {

    parameterItemElement.onclick = function () {
        const categoryId = parameterItemElement.getAttribute("category-id");
        modalRead.style.display = "block";
        // Send via iframe
        iframeRead.contentWindow.postMessage({
            state: 1,
            categoryId: categoryId,
        }, pageTarget);
    }
});