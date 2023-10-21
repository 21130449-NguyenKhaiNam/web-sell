document.addEventListener("DOMContentLoaded", function() {
    const slider = document.querySelector(".slider");
    const prevButton = document.querySelector(".nav_prev");
    const nextButton = document.querySelector(".nav_next");
    const slideWidth = slider.clientWidth;
    let currentIndex = 0;

    function slideTo(index) {
        slider.style.transform = `translateX(-${index * slideWidth}px)`;
    }

    function nextSlide() {
        if (currentIndex < slider.childElementCount - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        slideTo(currentIndex);
    }

    nextButton.addEventListener("click", function() {
        nextSlide();
    });

    prevButton.addEventListener("click", function() {
        if (currentIndex > 0) {
            currentIndex--;
            slideTo(currentIndex);
        }
    });

    let interval = setInterval(nextSlide, 2000);

    slider.addEventListener('mouseover',function (){
        clearInterval(interval);
    });

    slider.addEventListener("mouseout", function (){
        interval = setInterval(nextSlide, 2000);
    })
});
