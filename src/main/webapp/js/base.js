// function handleCloseNotificationCart() {
//     let content = document.querySelector('.cart__wrapper .notification__cart');
//     content.classList.add('closed');
//
//     setTimeout(function () {
//         document.querySelector('.cart__wrapper').removeChild(content);
//     }, 300);
// }

export const addParam = (form, {key, value}) => {
    let formDataArray = $(form).serializeArray();

    // Adding new parameters
    formDataArray.push({name: key, value: value}); // Add your custom parameter

    return $.param(formDataArray);
}

export const convertFormToObject = (form) => {
    const formDataArray = $(form).serializeArray();

    const formDataJson = {};
    $.each(formDataArray, function (_, field) {
        formDataJson[field.name] = field.value;
    });
    return formDataJson;
}