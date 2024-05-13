const cloudName = "yourstyle";

export function getImageProduct(nameImages) {
    const url = `https://res.cloudinary.com/${cloudName}/image/upload/c_scale/q_auto/f_webp,fl_awebp/v1711545951/product_img/${nameImages}`;
    return url
}