//Read dialog
const iframeRead = document.querySelector("#dialog-review-read .modal__product-iframe");
const dataViewElement = document.querySelectorAll(".table__data-view");
const modalRead = document.querySelector("#dialog-review-read");
const elementCloseRead = getClose(modalRead);

function getClose(modal) {
    return modal.querySelector(".modal__review-close");
}

elementCloseRead.onclick = function () {
    modalRead.style.display = "none";
};

dataViewElement.forEach(function (element) {
    const pageTarget = `${window.location.origin}/adminReviewForm.jsp`;
    element.onclick = function () {
        // Open dialog
        modalRead.style.display = "block";

        const tableRow = this.parentNode;
        const reviewId = tableRow.querySelector(".table__data-id").textContent.trim();
        iframeRead.contentWindow.postMessage({
            reviewId: reviewId,
        }, pageTarget);
    }
});

//Hide review
const dataHideElement = document.querySelectorAll(".table__data-visibility .button");
dataHideElement.forEach(function (element) {
    element.onclick = function () {
        // Get name product
        const tableRow = this.parentNode.parentNode;
        const idReview = tableRow.querySelector(".table__data-id").textContent.trim();
        // Show alert
        if (element.classList.contains("button__hide")) {
            hideProductAlert(idReview);
        }
        if (element.classList.contains("button__un-hide")) {
            unHideProductAlert(idReview);
        }

    }
});

function hideProductAlert(reviewId) {
    const message = `Bạn có muốn ẩn nhận xét này không?`;
    const result = window.confirm(message);
    if (result) {
        //     Handle
        $.ajax({
            url: "/admin-hide-review",
            type: "POST",
            data: {
                id: reviewId
            },
            dataType: "json",
            cache: true,
            success: function (data) {
                const status = data.status;
                if (status) {
                    alert(`Ẩn nhận xét thành công`);
                    window.location.reload();
                } else {
                    alert(`Ẩn nhận xét không thành công`);
                }
            },
            error: function (error) {
            },
        });
    }
}

function unHideProductAlert(reviewId) {
    const message = `Bạn có muốn bỏ ẩn nhận xét không?`;
    const result = window.confirm(message);
    if (result) {
        //     Handle
        $.ajax({
            url: "/admin-un-hide-review",
            type: "POST",
            data: {
                id: reviewId
            },
            dataType: "json",
            cache: true,
            success: function (data) {
                const status = data.status;
                if (status) {
                    alert(`Bỏ ẩn nhận xét thành công`)
                    window.location.reload();
                } else {
                    alert(`Bỏ ẩn nhận xét không thành công`);
                }
            },
            error: function (error) {
            },
        });
    }
}