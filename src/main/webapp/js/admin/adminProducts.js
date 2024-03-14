//Load page btn
document.querySelector(".reload__btn").onclick = function () {
    window.location.reload();
}

function getClose(modal) {
    return modal.querySelector(".modal__product-close");
}

//Read product
const iframeRead = document.querySelector("#dialog-product-read .modal__product-iframe");
const dataViewElement = document.querySelectorAll(".table__data-view");
const modalRead = document.querySelector("#dialog-product-read");
const elementCloseRead = getClose(modalRead);

elementCloseRead.onclick = function () {
    modalRead.style.display = "none";
};

dataViewElement.forEach(function (element) {
    const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
    element.onclick = function () {
        // Open dialog
        modalRead.style.display = "block";

        // Send via iframe
        const tableRow = this.parentNode;
        const productId = tableRow.querySelector(".table__data-id").textContent.trim();
        console.log(pageTarget)
        iframeRead.contentWindow.postMessage({
            productId: productId,
            state: 1,
        }, pageTarget);
    }
});

//Create product
if (role == 2){
    const iframeCreate = document.querySelector("#dialog-product-create .modal__product-iframe");
    const modalCreateBtn = document.querySelector("#button-create-product");
    const modalCreate = document.querySelector("#dialog-product-create");
    const elementCloseCreate = getClose(modalCreate);

    elementCloseCreate.onclick = function () {
        modalCreate.style.display = "none";
    }

    modalCreateBtn.onclick = function () {
        const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
        modalCreate.style.display = "block";
        // Send via iframe
        iframeCreate.contentWindow.postMessage({
            state: 0,
        }, pageTarget);
    }
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
        const pageTarget = `${window.location.origin}/public/admin/adminProductUpdateForm.jsp`;
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
//Hide product
const dataHideElement = document.querySelectorAll(".table__data-visibility .button");
dataHideElement.forEach(function (element) {
    element.onclick = function () {
        // Get name product
        const tableRow = this.parentNode.parentNode;
        console.log(tableRow.querySelector(".table__data-name .table__cell"))
        const nameProduct = tableRow.querySelector(".table__data-name .table__cell").textContent.trim();
        const idProduct = tableRow.querySelector(".table__data-id").textContent.trim();
        // Show alert
        if (element.classList.contains("button__hide")) {
            hideProductAlert(nameProduct, idProduct);
        }
        if (element.classList.contains("button__un-hide")) {
            unHideProductAlert(nameProduct, idProduct);
        }

    }
});

function hideProductAlert(nameProduct, idProduct) {
    const message = `Bạn có muốn ẩn sản phẩm "${nameProduct}" không?`;
    const result = window.confirm(message);
    if (result) {
        //     Handle
        $.ajax({
            url: "admin-hide-product?id=" + idProduct,
            type: "POST",
            contentType: false,
            processData: false,
            dataType: "json",
            cache: false,
            success: function (data) {
                const status = data.status;
                if (status) {
                    alert(`Ẩn sản phẩm ${nameProduct} thành công`);
                    window.location.reload();
                } else {
                    alert(`Ẩn sản phẩm ${nameProduct} không thành công`);
                }
            },
            error: function (error) {
            },
        });
    }
}

function unHideProductAlert(nameProduct, idProduct) {
    const message = `Bạn có muốn bỏ ẩn sản phẩm "${nameProduct}" không?`;
    const result = window.confirm(message);
    if (result) {
        //     Handle
        $.ajax({
            url: "admin-un-hide-product?id=" + idProduct,
            type: "POST",
            contentType: false,
            processData: false,
            dataType: "json",
            cache: false,
            success: function (data) {
                const status = data.status;
                if (status) {
                    alert(`Bỏ ẩn sản phẩm ${nameProduct} thành công`);
                    window.location.reload();
                } else {
                    alert(`Bỏ ẩn sản phẩm ${nameProduct} không thành công`);
                }
            },
            error: function (error) {
            },
        });
    }
}

