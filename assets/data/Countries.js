// Lấy thông tin từ server
function countries() {
    return ([{ name: 'Việt Nam', flag: 'vn', selected: true }, { name: 'English', flag: 'sh' }]);
}

// Giao diện lựa chọn dài
function getCountries({ classUl = [], classItem = [] } = {}) {
    const generalName = 'check'
    const ulCom = document.createElement('ul')
    if (classUl)
        ulCom.classList.add(...classUl)
    countries().forEach(({ name, flag, selected }) => {
        const inputCom = document.createElement('input')
        inputCom.value = name
        inputCom.id = name
        inputCom.type = 'radio'
        inputCom.name = generalName
        inputCom.checked = selected

        const flagCom = document.createElement('span')
        flagCom.classList.add('fi', 'fi-' + flag)

        const labelCom = document.createElement('label')
        labelCom.setAttribute('for', name)
        labelCom.innerText = name
        labelCom.appendChild(flagCom)

        const liCom = document.createElement('li')
        if (classItem)
            liCom.classList.add(...classItem)
        liCom.appendChild(inputCom)
        liCom.appendChild(labelCom)
        ulCom.appendChild(liCom)
    })
    return ulCom
}

export { getCountries }
export default countries;