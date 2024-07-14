const cloudName = "yourstyle";

export function getImageProduct(nameImages) {
    return `https://res.cloudinary.com/${cloudName}/image/upload/c_scale/q_auto/f_webp,fl_awebp/v1711545951/product_img/${nameImages}`;
}

export function fetchImage(url) {
    return fetch(url).then(response => response.blob()).catch(error => console.error('Error fetching image:', error));
}