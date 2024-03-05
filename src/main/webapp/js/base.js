function handleCloseNotificationCart(){
    let content = document.querySelector('.cart__wrapper .notification__cart');
    content.classList.add('closed');

    setTimeout(function() {
        document.querySelector('.cart__wrapper').removeChild(content);
    }, 300);
}
