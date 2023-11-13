document.addEventListener("DOMContentLoaded", function () {
    function formatCurrency(amount) {
        return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
    }

    function getAmountPrice(amountCurrency) {
        return parseFloat(amountCurrency.replaceAll('₫', '').replaceAll('.', ''));
    }

    const trendingProductItems = [
        {
            name: "Áo polo nam trơn basic form regular vải cá sấu",
            image: "../assets/img/product_img/product1.jpg",
            originPrice: 350000,
            salePrice: 279000,
            ratingStart: 4
        },
        {
            name: "Áo polo thể thao unisex nam nữ",
            image: "../assets/img/product_img/product2.jpg",
            originPrice: 299000,
            salePrice: 239000,
            ratingStart: 4
        },
        {
            name: "Áo polo phối khóa kéo unisex nam nữ",
            image: "../assets/img/product_img/product3.jpg",
            originPrice: 350000,
            salePrice: 289000,
            ratingStart: 4
        },
        {
            name: "Áo hoodie form rộng unisex nam nữ nỉ chân cua",
            image: "../assets/img/product_img/product4.jpg",
            originPrice: 490000,
            salePrice: 259000,
            ratingStart: 5
        },
        {
            name: "Áo hoodie zip form boxy unisex nỉ bông dày dặn",
            image: "../assets/img/product_img/product5.jpg",
            originPrice: 300000,
            salePrice: 250000,
            ratingStart: 5
        },
        {
            name: "Áo sweater unisex nam nữ basic nỉ chân cua",
            image: "../assets/img/product_img/product6.jpg",
            originPrice: 350000,
            salePrice: 288000,
            ratingStart: 5
        },
        {
            name: "Áo sweater nam trơn basic vải da cá",
            image: "../assets/img/product_img/product7.jpg",
            originPrice: 250000,
            salePrice: 200000,
            ratingStart: 5
        },
        {
            name: "Quần jean unisex nam nữ cạp cao ống suông rộng",
            image: "../assets/img/product_img/product8.jpg",
            originPrice: 290000,
            salePrice: 260000,
            ratingStart: 4
        }
    ];


    function renderTrendingProductItems(trendingProductItems) {
        const productItemsElement = document.querySelector('.product__items');
        const trendingProductList = trendingProductItems.map(productItem => {
            return `<div class="product__item">
                    <div class="product__content">
                        <img src= ${productItem.image}>
                        <div class="product__info">
                            <span class="product__status">Thịnh hành</span>
                            <a class="product__name" href="#">${productItem.name}</a>
                            <div class="product__review">
                                <div class="review__icon">
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                    <i class="fa-solid fa-star icon__item"></i>
                                </div>
                                <a class="number__turns--ratting" href="#">1000 nhận xét</a>
                            </div>
                            <span class="product__price"><strong class="sale__price">${formatCurrency(productItem.salePrice)}</strong> <s class="original__price">${formatCurrency(productItem.originPrice)}</s></span>
                        </div>
                    </div>
                </div>`;
        })
        const trendingProductsHTML = trendingProductList.join('');
        productItemsElement.innerHTML = trendingProductsHTML;
    }
    renderTrendingProductItems(trendingProductItems)

    function sliderImageController() {
        const sliderItemsElement = document.querySelector(".slider__items");
        const prevButtonElement = document.querySelector(".nav__prev");
        const nextButtonElement = document.querySelector(".nav__next");
        const sliderWidth = sliderItemsElement.clientWidth;
        let currentIndex = 0;
        const indicators = document.querySelectorAll(".indicator");

        function slideTo(index) {
            sliderItemsElement.style.transform = `translateX(-${index * sliderWidth}px)`;
            updateIndicators(index);
        }

        window.addEventListener("resize", () => {
            const imageItems = sliderItemsElement.querySelectorAll(".slider__item")
            imageItems.forEach((item) => {
                item.style.width = `${sliderWidth}px`;
            });
        });

        function updateIndicators(index) {
            indicators.forEach((indicator, i) => {
                if (i === index) {
                    indicator.classList.add("active");
                } else {
                    indicator.classList.remove("active");
                }
            });
        }

        indicators.forEach((indicator, index) => {
            indicator.addEventListener("click", function () {
                currentIndex = index;
                slideTo(currentIndex);
            });
        });

        function nextSlide() {
            if (currentIndex < sliderItemsElement.childElementCount - 1) {
                currentIndex++;
            } else {
                currentIndex = 0;
            }
            slideTo(currentIndex);
        }

        nextButtonElement.addEventListener("click", function () {
            nextSlide();
        });

        prevButtonElement.addEventListener("click", function () {
            if (currentIndex > 0) {
                currentIndex--;
                slideTo(currentIndex);
            }
        });

        let interval = setInterval(nextSlide, 2000);

        sliderItemsElement.addEventListener('mouseover', function () {
            clearInterval(interval);
        });

        sliderItemsElement.addEventListener("mouseout", function () {
            interval = setInterval(nextSlide, 2000);
        })
    }

    sliderImageController();

    function animationShowCategory() {
        const categoryItems = document.querySelectorAll(".category__item")
        let currentIndex = 0;

        function displayCategoryItem() {
            if (currentIndex < categoryItems.length) {
                categoryItems[currentIndex].style.display = "flex";
                currentIndex++;
                setTimeout(displayCategoryItem, 800)
            } else {
                currentIndex = 0;
                categoryItems.forEach(category_item => {
                    category_item.style.display = "none"
                });
                setTimeout(displayCategoryItem, 100);
            }
        }

        displayCategoryItem()

    }

    animationShowCategory();

    function sliderProductController() {
        const productItemsElement = document.querySelector(".product__items");
        const modelProductItem = productItemsElement.querySelector(".product__item");
        const leftButtonElement = document.getElementById("left__button");
        const rightButtonElement = document.getElementById("right__button");
        let modelProductItemWidth = modelProductItem.clientWidth + 20;
        let currentPosition = 0

        leftButtonElement.addEventListener("click", () => {
            if (currentPosition > 0) {
                currentPosition -= modelProductItemWidth
            }
            productItemsElement.style.transform = `translateX(-${currentPosition}px)`;

        })

        rightButtonElement.addEventListener("click", () => {
            if (currentPosition < productItemsElement.scrollWidth - productItemsElement.clientWidth) {
                currentPosition += modelProductItemWidth
            }
            productItemsElement.style.transform = `translateX(-${currentPosition}px)`;
        })
    }

    sliderProductController();
});
