$(document).ready(() => {
// các sản phẩm khi vừa vào trang sản phẩm
    callAjaxToPage(1);
    const modalRead = document.querySelector("#dialog-review-read");
    const elementCloseRead = getClose(modalRead);

    const dataViewElement = document.querySelectorAll(".table__data-view")
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

    function getClose(modal) {
        return modal.querySelector(".modal__review-close");
    }

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
            //     Handle
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

// phương thức này truyền vào số trang hiện tại
// sẽ trả về sản phẩm của trang đó
    function callAjaxToPage(pageNumber) {
        $.ajax({
            url: "/api/admin/review/page?page=" + pageNumber,
            type: "GET",
            success: function (data) {
                generationPage(parseInt(pageNumber), data["quantity"])
                showProduct(data)
            },
            error: function (error) {
                console.log("loi khong hien thi san pham")
            },
        });
    }

// phương thức này dùng để xử lý sự kiện click vào trang nào đó
// sẽ gọi method callAjaxToPage(currentPage)
    function changePage(aTag) {
        if (aTag) {
            productList.innerHTML = ``
            callAjaxToPage(parseInt(aTag.innerText))
            // handleFilterProduct(aTag.innerText)
        }
    }

// khi lấy được data của product dưới dạng json thì show ra giao diện
    function showProduct(data) {
        const reviews = data["reviews"]
        reviews.forEach(function (review) {
            productList.innerHTML += `
            <tr class="table__row">
                <td class="table__data-view">
                    <label>
                        <i class="fa-solid fa-eye"></i>
                    </label>
                </td>
                
                <td class="table__data table__data-id">
                    <p class="table__cell">${review.id}</p>
                </td>
                
                <td class="table__data">
                    <p class="table__cell">${review.userId}</p>
                </td>
                
                <td class="table__data">
                    <p class="table__cell">${review.productName}</p>
                </td>
                
                <td class="table__data">
                    <p class="table__cell">${review.orderDetailId}</p>
                </td>
                
                <td class="table__data">
                    <p class="table__cell">${review.ratingStar}</p>
                </td>
                
                <td class="table__data">
                    <p class="table__cell">${(new Date(review.reviewDate)).toLocaleDateString("en-US")}</p>
                </td>
                
                
                    ${review.visibility ?
                `<td class="table__data table__data-visibility table__data-hide">
                            <div class="button button--hover button__hide">Ẩn</div>
                        </td>` :
                `<td class="table__data table__data-visibility table__data-un-hide">
                            <div class="button button--hover button__un-hide">Bỏ ẩn</div>
                        </td>`}
            </tr>`
        })
        const dataHideElement = document.querySelectorAll(".table__data-visibility .button");
        implementHideOrUnHide(dataHideElement);
    }

    function showBeforePaging(currentPage, minPage) {
        if (currentPage > 3) {
            productPages.innerHTML += `<a class="page access_page_quickly">...</a>`
            const div = document.createElement('div')
            div.style.width = '200px'
            div.style.height = '50px'
            div.style.overflow = 'auto'

            for (let i = 1; i < minPage; i++) {
                const a = document.createElement('a')
                a.innerText = i
                a.classList.add("page")
                a.onclick = function () {
                    changePage(a)
                }
                div.appendChild(a)
            }

            tippy('.access_page_quickly', {
                content: div,
                placement: 'top-start',
                interactive: true,
                duration: [500, 250],
                arrow: true,
            });
        }
    }

    function generationPage(currentPage, totalPage) {
        var minPage = currentPage - 2
        var maxPage = currentPage + 2;

        if (currentPage < 3) {
            minPage = 1;
            maxPage = 5
        }

        if (maxPage > totalPage) {
            maxPage = totalPage
        }

        productPages.innerHTML = ''
        showBeforePaging(currentPage, minPage)
        for (let i = minPage; i <= maxPage; i++) {
            const a = document.createElement('a');
            if (i === currentPage) {
                a.classList.add("page--current");
            }
            a.classList.add('page')
            a.innerText = i;
            a.onclick = function () {
                changePage(a)
            }
            productPages.appendChild(a);
        }
    }

    function implementHideOrUnHide(btnActive) {
        console.log(btnActive)
        btnActive.forEach(function (element) {
            element.onclick = function () {
                // Get name product
                const tableRow = this.parentNode.parentNode;
                console.log(tableRow.querySelector(".table__data-id"))
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
    }


    const productList = document.querySelector('.body_table')
    const productPages = document.querySelector('.paging')
    const iframeRead = document.querySelector("#dialog-review-read .modal__product-iframe");


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

        function handleColor(color) {
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
})

