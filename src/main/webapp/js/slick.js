import Splide from 'https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/+esm';

$(document).ready(() => {
    const main = new Splide('#main-slider', {
        type: 'slide',
        pagination: false,
        arrows: true,
        cover: true,
        heightRatio: 1,
        autoplay: true, // Enable autoplay
        interval: 3000, // Set autoplay interval to 3 seconds
        rewind: false,
    });

    const thumbnails = new Splide('#thumbnail-slider', {
        fixedWidth: 100,
        fixedHeight: 100,
        gap: 10,
        rewind: true,
        pagination: false,
        cover: true,
        isNavigation: true,
        arrows: false,
        focus: 'center',
    });

    main.sync(thumbnails);
    main.mount();
    thumbnails.mount();
});