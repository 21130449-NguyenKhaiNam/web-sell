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


const pageTarget = window.location.origin + "/adminProductForm.jsp";

//Read product
const iframeRead = document.querySelector("#dialog-product-read .modal__product-iframe");
const dataViewElement = document.querySelectorAll(".table__data-view");
const modalRead = document.querySelector("#dialog-product-read");
const elementCloseRead = getClose(modalRead);

elementCloseRead.onclick = function () {
    modalRead.style.display = "none";
};

dataViewElement.forEach(function (element) {
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
    modalCreate.style.display = "block";

    // Send via iframe
    iframeCreate.contentWindow.postMessage({
        state: 0,
    }, pageTarget);
}

// Listen for messages from the iframe
window.addEventListener('message', function (event) {
    // Check the origin of the iframe sending the message
    if (event.origin === `${window.location.origin}` + "/adminProductForm.jsp") {
        // Log the message received from the iframe
        console.log('Message from iframe:', event.data);

        // Do something with the received message
    }
});