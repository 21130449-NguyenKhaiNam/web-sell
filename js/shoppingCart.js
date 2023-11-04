const cartItems = [
    {
        productItem: {
            name: 'Áo polo nam trơn basic form regular vải cá sấu',
            color: 'Đen',
            requiredSize: '{"Dài áo":50,"Rộng vai":50,"Rộng ngực":50, "Dài tay":50}',
            image: '../assets/img/product_img/product1.jpg'
        },
        unitPrice: 279000,
        quality: 1,
    },
    {
        productItem: {
            name: 'Áo polo thể thao unisex nam nữ',
            color: 'Xanh navy',
            requiredSize: '{"Dài áo":50,"Rộng vai":50,"Rộng ngực":50, "Dài tay":50}',
            image: '../assets/img/product_img/product2.jpg'
        },
        unitPrice: 239000,
        quality: 1,
    },
    {
        productItem: {
            name: 'Áo polo phối khóa kéo unisex nam nữ',
            color: 'Beige',
            requiredSize: '{"Dài áo":50,"Rộng vai":50,"Rộng ngực":50, "Dài tay":50}',
            image: '../assets/img/product_img/product3.jpg'
        },
        unitPrice: 289000,
        quality: 1,
    }
];

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
}