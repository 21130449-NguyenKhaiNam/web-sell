window.addEventListener('message', function (event) {
        const data = event.data;
        $.ajax({
            url: "/api/admin/review/read",
            type: "GET",
            data: {
                id: data.reviewId
            },
            dataType: "json",
            cache: true,
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
            image.src = `${data.image}`;
            category.innerText = data.category;
            color.style.backgroundColor = data.colorRequired;
            quantity.innerText = data.quantityRequired;
            feedback.innerText = data.feedback;
            handleStars(data.stars);
            handleSizes(data.sizeRequired);

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
                size.innerHTML = "";
                const htmls = [];
                const measurementsArray = sizes.split(', ');
                if (measurementsArray.length == 1) {
                    htmls.push(` <p class="product__size">Kích thước:
                                 <span class="product__size--default">${sizes}</span>
                            </p>`);
                    size.innerHTML = htmls.join("")
                    return;
                }
                const measurementsObject = {};
                measurementsArray.forEach(measurement => {
                    const [key, value] = measurement.split(': ');
                    measurementsObject[key] = value;
                });
                htmls.push(` <ul class="product__size--custom">`);
                for (const key in measurementsObject) {
                    if (measurementsObject.hasOwnProperty(key)) {
                        const value = measurementsObject[key];
                        htmls.push(`<li>${key}: ${value}</li>`)
                    }
                }
                htmls.push(`</ul>`)
                size.innerHTML = htmls.join("")
            }
        }
    }
)