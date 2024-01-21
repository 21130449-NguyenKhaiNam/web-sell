window.addEventListener('message', function (event) {
        const data = event.data;
        $.ajax({
            url: "admin-read-review?id=" + data.reviewId,
            type: "GET",
            contentType: false,
            processData: false,
            dataType: "json",
            cache: false,
            success: function (data) {
                console.log(data)
                applyData(data);
            },
            error: function (error) {
            },
        });

        function applyData(data) {
            const name = document.querySelector("#name");
            const image = document.querySelector("#image");
            const category = document.querySelector("#category");
            const color = document.querySelector("#color");
            const quantity = document.querySelector("#quantity");
            const feedback = document.querySelector("#feedback");

            name.innerText = data.name;
            image.src = `assets/img/product_img/${data.image}`;
            category.innerText = data.category;
            color.style.backgroundColor = data.colorRequired;
            quantity.innerText = data.quantityRequired;
            feedback.innerText = data.feedback;
            handleStars(data.stars);
            handleSizes(JSON.parse(data.sizeRequired));

            function handleStars(stars) {
                const reviewStars = document.querySelector(".review__stars");
                const htmls = [];
                let i;
                for (i = 0; i < stars; i++) {
                    htmls.push(`<div class="review__star review__star--choose"></div>`);
                }
                for (; i < 5; i++) {
                    htmls.push(`<div class="review__star "></div>`);
                }
                reviewStars.innerHTML = htmls.join("");
            }

            function handleSizes(sizes) {
                const size = document.querySelector("#size");
                const htmls = [];
                if (sizes.length == 1) {
                    htmls.push(` <p class="product__size">Kích thước:
                                 <span class="product__size--default">${sizes[0]}</span>
                            </p>`);
                } else {
                    htmls.push(` <ul class="product__size--custom">`);
                    for (let i = 0; i < sizes.length; i++) {
                        htmls.push(`<li></li>`)
                    }
                    htmls.push(`</ul>`)
                }
                size.innerHTML = htmls.join("")
            }
        }
    }
)
;