document.addEventListener("DOMContentLoaded", function() {
    const slider = document.querySelector(".slider");
    const prevButton = document.querySelector(".nav_prev");
    const nextButton = document.querySelector(".nav_next");
    const slideWidth = slider.clientWidth;
    let currentIndex = 0;

    function slideTo(index) {
        slider.style.transform = `translateX(-${index * slideWidth}px)`;
    }

    nextButton.addEventListener("click", function() {
        if (currentIndex < slider.childElementCount - 1) {
            currentIndex++;
            slideTo(currentIndex);
        }
    });

    prevButton.addEventListener("click", function() {
        if (currentIndex > 0) {
            currentIndex--;
            slideTo(currentIndex);
        }
    });
});
