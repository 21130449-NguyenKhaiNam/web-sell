import { getCountries } from '/assets/data/countries.js'
/**
 * Hiển thị ra layout của country 
 * Phải import thư viện tippy
 */
function layoutCountries(classCountry = '', classMenu = [], classMenuItem = []) {
    // Hiển thị menu lựa chọn ngôn ngữ
    if (classCountry) {
        tippy(classCountry, {
            content: () => getCountries({
                classUl: [...classMenu],
                classItem: [...classMenuItem],
            }),
            theme: 'light',
            interactive: true,
        })
    }
}

export default layoutCountries;
