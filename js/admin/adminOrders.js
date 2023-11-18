function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
}

function formatLetter(letter) {
    return letter.charAt(0).toUpperCase() + letter.slice(1).toLowerCase();
}

function renderOrderTable(listOrders) {
    const bodyTable = document.querySelector(".table tbody");
    let orderHtmlRows = listOrders.map(order => {
        return `<tr class="table__row">
                                    <td class="table__date-checkbox">
                                        <label class="check">
                                            <input type="checkbox" name="passing" class="filter__input" hidden="true">
                                        </label>
                                    </td>
                                    <td class="table__data-update">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${order.id}</p>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${order.dateOrder}</p>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${order.fullName}</p>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${formatLetter(order.paymentMethod)}</p>
                                    </td>                                                          
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${formatLetter(order.statusOrder)}</p>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${formatLetter(order.statusTransaction)}</p>
                                    </td>
                                    
                                    <td class="table__data">
                                        <p class="table__cell">${formatCurrency(1316000)}</p>
                                    </td>
                                </tr>`
    })
    bodyTable.innerHTML = orderHtmlRows.join('');
}

renderOrderTable(listOrders);

function handleUpdateStatusSelection() {
    const optionMenus = document.querySelectorAll(".select-menu");
    optionMenus.forEach(optionMenu => {
        const selectBtn = optionMenu.querySelector(".select-btn");
        const options = optionMenu.querySelectorAll(".option");
        const sBtn_text = optionMenu.querySelector(".sBtn-text");
        selectBtn.addEventListener("click", () => optionMenu.classList.toggle("active"));
        options.forEach(option => {
            option.addEventListener("click", () => {
                let selectedOption = option.innerText;
                sBtn_text.innerText = selectedOption;
                optionMenu.classList.remove("active");
            });
        });
    })
}

handleUpdateStatusSelection();

const dialogUpdateOrder = document.querySelector("#dialog-order-update")

function handleShowAndUpdateDialogOrder(dialogUpdateOrder){
    const orderRows = document.querySelectorAll("tbody .table__row");
    orderRows.forEach(orderRow =>{
        const updateOrderButton = orderRow.querySelector(".table__data-update")
        updateOrderButton.addEventListener("click",()=>{
            dialogUpdateOrder.style.display = "block"
        })
    })
}
handleShowAndUpdateDialogOrder(dialogUpdateOrder);

function handleCloseDialogOrder(dialogUpdateOrder){
    const modalBlurArea = document.querySelector(".modal__blur");
    const closeUpdateButton = document.querySelector(".modal__order-close");
    modalBlurArea.onclick = ()=>{
        dialogUpdateOrder.style.display = "none"
    }
    closeUpdateButton.onclick = ()=>{
        dialogUpdateOrder.style.display = "none"
    }
}
handleCloseDialogOrder(dialogUpdateOrder);

