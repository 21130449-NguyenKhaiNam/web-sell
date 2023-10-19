
const diamonds = document.querySelectorAll('.diamond');
const textCompos = document.querySelectorAll('.text__compo');
diamonds.forEach((diamond, index) => {
    diamond.addEventListener('click', () => {
        diamonds.forEach((item) => {
            item.classList.remove('active');
        });
        textCompos.forEach((item) => {
            item.style.display = 'none';
        });
        diamond.classList.add('active');
        textCompos[index].style.display = 'block';
    });
});