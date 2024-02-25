// Lấy thông tin từ server
function categories() {
    return (['Nồi cơm điện', 'Bếp từ', 'Máy giặt', 'Bếp ga',]);
}

// Lấy phần tử ra dưới dạng danh sách
function getCategories({ classUl = [], classItem = [] } = {}) {
    const ulCom = document.createElement('ul')
    if (classUl)
        ulCom.classList.add(...classUl)
    categories().forEach(category => {
        const liCom = document.createElement('li')
        liCom.innerHTML = category
        if (classItem)
            liCom.classList.add(...classItem)
        ulCom.appendChild(liCom)
    })
    return ulCom
}

export { getCategories }
export default categories;