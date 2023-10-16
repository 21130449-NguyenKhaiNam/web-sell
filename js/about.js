// Lấy tất cả các ô hình thoi và bảng tương ứng
const diamonds = document.querySelectorAll('.diamond');
const textCompos = document.querySelectorAll('.text__compo');

// Lặp qua tất cả các ô hình thoi
diamonds.forEach((diamond, index) => {
    // Thêm sự kiện click cho từng ô hình thoi
    diamond.addEventListener('click', () => {
        // Loại bỏ lớp "active" khỏi tất cả các ô hình thoi và bảng
        diamonds.forEach((item) => {
            item.classList.remove('active');
        });
        textCompos.forEach((item) => {
            item.style.display = 'none';
        });

        // Thêm lớp "active" cho ô hình thoi được nhấp vào
        diamond.classList.add('active');

        // Hiển thị bảng tương ứng
        textCompos[index].style.display = 'block';
    });
});