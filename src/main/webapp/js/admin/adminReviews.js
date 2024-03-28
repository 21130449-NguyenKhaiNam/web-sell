$('.table__data-visibility .button').each((index, item) => {
    $(item).on('click', () => {
        const idReview = $(item).data("id-review")
        console.log(idReview)
        if ($(item).hasClass("button__hide")) {
            hideProductAlert(idReview);
        }
        if ($(item).hasClass("button__un-hide")) {
            unHideProductAlert(idReview);
        }
    });
});

function hideProductAlert(reviewId) {
    const message = `Bạn có muốn ẩn nhận xét này không?`;
    const result = window.confirm(message);
    if (result) {
        $.ajax({
            url: "/api/admin/review/hide",
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
        $.ajax({
            url: "/api/admin/review/un-hide",
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

$('.review__detail').each((index, item) => {
    $(item).on('click', () => {
        const idReview = $(item).data("id-review")
        showDialog(idReview);
    })
});

function showDialog(idReview) {
    $.ajax({
        url: "/api/admin/review/read",
        type: "GET",
        data: {
            id: idReview
        },
        dataType: "json",
        cache: true,
        success: function (data) {
            console.log(data)
            applyDataDialog(data);
        },
        error: function (error) {
        },
    });
}

const folderProduct = "/assets/img/product_img/"

function applyDataDialog(review) {
    const model = $("#model");

    function loadStars(stars) {
        const reviewStars = model.find("#staticStars");
        const htmls = [];
        let i;
        for (i = 0; i < stars; i++) {
            htmls.push(`<i class="star fa-solid fa-star"></i>`);
        }
        for (; i < 5; i++) {
            htmls.push(`<i class=" star fa-regular fa-star"></i>`);
        }
        reviewStars.html(htmls.join(""));
    }

    function handleColor(color){
        model.find("#staticColor").text(color).css("background-color", color);
    }

    model.find("#staticImageProduct").attr("src", folderProduct + review.image);
    model.find("#staticNameProduct").text(review.name);
    model.find("#staticCategory").text(review.category);
    model.find("#staticDate").text(review.date);
    model.find("#staticQuantity").text(review.quantityRequired);
    model.find("#staticSize").text(review.sizeRequired);
    loadStars(review.stars);
    handleColor(review.colorRequired)
    model.find("#staticReview").text(review.feedback);
    model.find("#staticDate").text(review.date);
}

