// Lấy thông tin từ server
function locations() {
    return (['Việt Nam', 'English']);
}

// Giao diện dạng lựa chọn
function getLocations() {
    const selectCom = document.createElement('select')
    locations().forEach(location => {
        const optionCom = document.createElement('option')
        optionCom.value, optionCom.text = location
        selectCom.appendChild(optionCom)
    })
    return selectCom
}

export { getLocations }
export default locations;