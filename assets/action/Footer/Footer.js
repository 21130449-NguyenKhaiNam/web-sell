import layoutCountries from "/assets/action/countries/layoutCountries.js"

$(document).ready(function () {
    const classCountry = '.Footer_country'

    const classMenu = '.Footer_popper_menu'
    const classMenuItem = '.Footer_popper_menu_item'

    // Lựa chọn ngôn ngữ
    layoutCountries(classCountry, [classMenu.substring(1)], [classMenuItem.substring(1)])
})