const listProduct = [
    {
        id: 1,
        img: "https://example.com/product1.jpg",
        name: "Product 1",
        price: 19.99,
    },
    {
        img: "https://example.com/product2.jpg",
        name: "Product 2",
        price: 24.99,
    },
    {
        img: "https://example.com/product3.jpg",
        name: "Product 3",
        price: 14.99,
    },
    {
        img: "https://example.com/product4.jpg",
        name: "Product 4",
        price: 29.99,
    },
    {
        img: "https://example.com/product5.jpg",
        name: "Product 5",
        price: 39.99,
    },
    {
        img: "https://example.com/product6.jpg",
        name: "Product 6",
        price: 9.99,
    },
    {
        img: "https://example.com/product7.jpg",
        name: "Product 7",
        price: 49.99,
    },
    {
        img: "https://example.com/product8.jpg",
        name: "Product 8",
        price: 34.99,
    },
    {
        img: "https://example.com/product9.jpg",
        name: "Product 9",
        price: 19.99,
    },
    {
        img: "https://example.com/product10.jpg",
        name: "Product 10",
        price: 59.99,
    }
];
var listProductElement = document.querySelector(".product__list");
var htmls = listProduct.map(function (product) {
    return `<div class="col-3">
                <a href="#!">
                    <div class="model_product item4">
                        <img src="../assets/img/product_img/product4/img1.png">
                        <div class="information-product">
                            <span class="status">Most required</span>
                            <a class="name-product" href="#">${product.name}</a>
                            <div class="review-product">
                                <div class="icon-review">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </div>
                                <a href="#">1000 nhận xét</a>
                            </div>
                            <span class="price"><strong class="sale-price">${product.price}</strong> <s class="original-price">300,000₫</s></span>
                        </div>
                    </div> 
                </a>
           </div>`;
});
listProductElement.innerHTML = htmls.join("");