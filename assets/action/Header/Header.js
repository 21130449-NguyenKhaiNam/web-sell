import { getCategories } from '/assets/data/Categories.js'
import { getLocations } from '/assets/data/Locations.js'
import { getCountries } from '/assets/data/Countries.js'

const classLocation = '.Header_location'
const classCategories = '.Header_categories'
const classCountry = '.Header_country'
const classAccess = '.Header_account'
const classMenu = '.Header_popper_menu'
const classMenuItem = '.Header_popper_menu_item'
const classWrapperAccess = '.Header_wrapper_access'
const classLogin = '.Header_login'
const classRegister = '.Header_register'

/**
 * Công việc mở rộng:
 * + Check kiểu truyền vào
 */

// Thông tin cho vị trí
tippy(classLocation, {
    content: 'Viet Nam',
    theme: 'light',
});

// Phần menu các loại sản phẩm
tippy(classCategories, {
    content: () => getCategories({
        // Loại bỏ dấu chấm phía trước
        classUl: [classMenu.substring(1)],
        classItem: [classMenuItem.substring(1)],
    }),
    trigger: 'click',
    theme: 'light',
    interactive: true,
});

// Hiển thị model box cho lựa chọn vị trí
$(classLocation).click(() => {
    Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success",
        },
        buttonsStyling: false
    }).fire({
        icon: 'info',
        title: "Lựa chọn vị trí của bạn?",
        html: `
            <div>
            <h4>Lựa chọn vị trí mà chúng tôi có hỗ trợ ở phía dưới</h4>
            ${getLocations().outerHTML}
            </div>
        `,
        confirmButtonText: "Đồng ý",
        width: 600,
        showCloseButton: true,
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.mixin({
                customClass: {
                    confirmButton: "btn btn-success",
                },
                buttonsStyling: false
            }).fire({
                title: "Hoàn tất!",
                text: `Bạn đã cập nhật thành công`,
                icon: "success",
                showCloseButton: true,
            });
        }
    });
})

// Hiển thị menu lựa chọn ngôn ngữ
tippy(classCountry, {
    content: () => getCountries({
        // Loại bỏ dấu chấm phía trước
        classUl: [classMenu.substring(1)],
        classItem: [classMenuItem.substring(1)],
    }),
    theme: 'light',
    interactive: true,
})

// Hiển thị popup login
// layout cho login
function displayLogin({ classWrapper = [], classLogIn = [], hrefLogin = '/', classRegister = [], hrefRegister = '/' } = {}) {
    const divCom = document.createElement('div')
    if (classWrapper)
        divCom.classList.add(...classWrapper)

    const loginIn = document.createElement('a')
    loginIn.innerText = 'Đăng nhập'
    if (classLogIn)
        loginIn.classList.add(...classLogIn)
    if (hrefLogin)
        loginIn.setAttribute('href', hrefLogin)

    const register = document.createElement('a')
    register.innerText = 'Đăng ký ngay'
    if (classRegister)
        register.classList.add(...classRegister)
    if (hrefRegister)
        register.setAttribute('href', hrefRegister)

    const pCom = document.createElement('p')
    pCom.innerHTML = 'Bạn là người mới ' + register.outerHTML

    divCom.appendChild(loginIn)
    divCom.appendChild(pCom)

    return divCom
}

tippy(classAccess, {
    content: () => displayLogin({
        classWrapper: [classWrapperAccess.substring(1)],
        classLogIn: [classLogin.substring(1)],
        hrefLogin: '/',
        classRegister: [classRegister.substring(1)],
        hrefRegister: '/',
    }),
    theme: 'light',
    interactive: true,
})