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
    const productWrapperElements = document.querySelectorAll(".product__wrapper");
    productWrapperElements.forEach(productWrapperElement =>{
        const productItemsElement = productWrapperElement.querySelector(".product__items");
        const firstProductWidth = productItemsElement.querySelector(".product__item").offsetWidth;
        const arrowButtons = productWrapperElement.querySelectorAll("button");
        const productItemChildren = [...productItemsElement.children];

        let isDragging = false, isAutoPlay = true, startX, startScrollLeft, timeoutId;
        let productPerView = Math.round(productItemsElement.offsetWidth / firstProductWidth);

        productItemChildren.slice(-productPerView).reverse().forEach(product => {
            productItemsElement.insertAdjacentHTML("afterbegin", product.outerHTML);
        });

        productItemChildren.slice(0, productPerView).forEach(product => {
            productItemsElement.insertAdjacentHTML("beforeend", product.outerHTML);
        });

        productItemsElement.classList.add("no-transition");
        productItemsElement.scrollLeft = productItemsElement.offsetWidth;
        productItemsElement.classList.remove("no-transition");

        arrowButtons.forEach(btn => {
            btn.addEventListener("click", () => {
                productItemsElement.scrollLeft += btn.className === "left__button" ? -firstProductWidth : firstProductWidth;
            });
        });

        const dragStart = (e) => {
            isDragging = true;
            productItemsElement.classList.add("dragging");
            startX = e.pageX;
            startScrollLeft = productItemsElement.scrollLeft;
        }

        const dragging = (e) => {
            if (!isDragging) return;
            productItemsElement.scrollLeft = startScrollLeft - (e.pageX - startX);
        }

        const dragStop = () => {
            isDragging = false;
            productItemsElement.classList.remove("dragging");
        }

        const infiniteScroll = () => {
            if (productItemsElement.scrollLeft === 0) {
                productItemsElement.classList.add("no-transition");
                productItemsElement.scrollLeft = productItemsElement.scrollWidth - (2 * productItemsElement.offsetWidth);
                productItemsElement.classList.remove("no-transition");
            } else if (Math.ceil(productItemsElement.scrollLeft) === productItemsElement.scrollWidth - productItemsElement.offsetWidth) {
                productItemsElement.classList.add("no-transition");
                productItemsElement.scrollLeft = productItemsElement.offsetWidth;
                productItemsElement.classList.remove("no-transition");
            }
            clearTimeout(timeoutId);
            if (!productWrapperElement.matches(":hover")) autoPlay();
        }

        const autoPlay = () => {
            if (window.innerWidth < 800 || !isAutoPlay) return;
            timeoutId = setTimeout(() => productItemsElement.scrollLeft += firstProductWidth, 2500);
        }
        autoPlay();

        productWrapperElement.addEventListener("mousedown", dragStart);
        productItemsElement.addEventListener("mousemove", dragging);
        document.addEventListener("mouseup", dragStop);
        productItemsElement.addEventListener("scroll", infiniteScroll);
        productWrapperElement.addEventListener("mouseenter", () => clearTimeout(timeoutId));
        productWrapperElement.addEventListener("mouseleave", autoPlay);
    })
}
sliderProductController();