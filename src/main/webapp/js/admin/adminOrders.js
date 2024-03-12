// function handleUpdateStatusSelection() {
//     const optionMenus = document.querySelectorAll(".select-menu");
//     optionMenus.forEach(optionMenu => {
//         const selectBtn = optionMenu.querySelector(".select-btn");
//         const options = optionMenu.querySelectorAll(".option");
//         const sBtn_text = optionMenu.querySelector(".sBtn-text");
//         selectBtn.addEventListener("click", () => optionMenu.classList.toggle("active"));
//         options.forEach(option => {
//             option.addEventListener("click", () => {
//                 let selectedOption = option.innerText;
//                 sBtn_text.innerText = selectedOption;
//                 optionMenu.classList.remove("active");
//             });
//         });
//     })
// }
//
// handleUpdateStatusSelection();

function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

const dialogUpdateOrder = document.querySelector("#dialog__order")

function handleResetFilter(){
    $('.button__control .reset__filter').on('click', function (){
        let listCheckedDeliveryMethod = $('input[name=deliveryMethod][type=checkbox]:checked.filter__input');
        let listCheckedPayMethod = $('input[name=paymentMethod][type=checkbox]:checked.filter__input');
        let listCheckedOrderStatus = $('input[name=orderStatus][type=checkbox]:checked.filter__input');
        let listCheckedTransactionStatus = $('input[name=transactionStatus][type=checkbox]:checked.filter__input');
        let dateInputted = $('.filler__date input[type=date]');
        let contentSearched = $('.form__search-block input[name=contentSearch]')
        let optionSearchSelected = $('select.search__select option.search__option')

        contentSearched.attr('value', '');

        listCheckedDeliveryMethod.each(function (){
            $(this).attr('checked', false)
        })

        listCheckedPayMethod.each(function (){
            $(this).attr('checked', false)
        })

        listCheckedOrderStatus.each(function (){
            $(this).attr('checked', false)
        })

        listCheckedTransactionStatus.each(function (){
            $(this).attr('checked', false)
        })

        dateInputted.each(function (){
            $(this).attr('value', '');
        })

        optionSearchSelected.each(function (){
            $(this).attr('selected', false)
        })
    })
}
handleResetFilter()

function handleShowDialogOrder(dialogUpdateOrder){
    const orderRows = document.querySelectorAll(".table__order tbody .table__row");
    console.log(orderRows)
    orderRows.forEach(orderRow =>{
        const updateOrderButton = orderRow.querySelector(".table__data .table__cell.see__detail")
        updateOrderButton.addEventListener("click",()=>{
            dialogUpdateOrder.style.display = "block"
        })
    })
}
handleShowDialogOrder(dialogUpdateOrder);

function handleCloseDialogOrder(dialogUpdateOrder){
    const modalBlurArea = document.querySelector(".modal__blur");
    const closeUpdateButton = document.querySelector(".dialog__close");
    modalBlurArea.onclick = ()=>{
        dialogUpdateOrder.style.display = "none"
    }
    closeUpdateButton.onclick = ()=>{
        dialogUpdateOrder.style.display = "none"
    }
}


function handelAbleButtonDelete() {
    document.querySelectorAll('.table__data .check:not(input:checked)').forEach((check) => {
        check.addEventListener('click', () => {
            document.querySelector('.button__cancel').classList.add('active')
            //handleOpenPopupRemoveOrder();
            handleOpenPopupCancelOrder();
            let checkedNumber = document.querySelectorAll('.table__data .check:has(input:checked)').length
            if (checkedNumber === 0) {
                document.querySelector('.button__cancel').classList.remove('active')
            }
        })
    })
}

handelAbleButtonDelete()

function handleOpenPopupRemoveOrder() {
    let buttonShowDeleteOrder = document.querySelector('.button__delete.active');
    buttonShowDeleteOrder.addEventListener('click', () => {
        const deleteOrderPopup = document.querySelector('.popup__deletion');
        deleteOrderPopup.innerHTML = `<div class="popup__container">
                                        <div class="popup__content">
                                            <div class="title__header">
                                                <span class="title"><i class="fa-solid fa-triangle-exclamation"></i> Xóa đơn hàng khỏi danh sách quản lý</span>
                                                <span class="subtitle">Bạn muốn xóa đơn hàng đang chọn? Xóa đơn hàng là thao tác nghiêm trọng, sau khi thực hiện đơn hàng được xóa sẽ không phục hồi lại được</span>
                                            </div>
                                            <div class="button__control">
                                                <button type="submit" name="action" value="removeOrder" class="agree__button remove__order">Xác nhận</button>
                                                <button class="cancel__button">Hủy</button>
                                            </div>
                                        </div>
                                    </div>`;

        let processOrderForm = $("#process__order--form");
        let removeOrderButton = $(processOrderForm).find('.agree__button.remove__order');

        handleRemoveOrCancelOrderAdmin(processOrderForm, removeOrderButton, deleteOrderPopup)

        deleteOrderPopup.querySelector('.cancel__button').addEventListener('click', function () {
            deleteOrderPopup.querySelector('.popup__container').remove();
        })
    })
}
function handleOpenPopupCancelOrder() {
    let buttonShowCancelOrderPopup = document.querySelector('.button__cancel.active');
    buttonShowCancelOrderPopup.addEventListener('click', () => {
        const cancelOrderPopup = document.querySelector('.popup__cancel');
        cancelOrderPopup.innerHTML = `<div class="popup__container">
                                        <div class="popup__content">
                                            <div class="title__header">
                                                <span class="title"><i class="fa-solid fa-triangle-exclamation"></i>Hủy đơn hàng trong danh sách quản lý</span>
                                                <span class="subtitle">Bạn muốn hủy đơn hàng đang chọn? Hủy đơn hàng là thao tác nghiêm trọng, sau khi thực hiện đơn hàng được hủy sẽ không phục hồi lại được</span>
                                            </div>
                                            <div class="button__control">
                                                <button type="submit" name="action" value="cancelOrder" class="agree__button cancel__order">Xác nhận</button>
                                                <button class="cancel__button">Hủy</button>
                                            </div>
                                        </div>
                                    </div>`;

        let processOrderForm = $("#process__order--form");
        let cancelOrderButton = $(processOrderForm).find('.agree__button.cancel__order');

        handleRemoveOrCancelOrderAdmin(processOrderForm, cancelOrderButton, cancelOrderPopup)

        cancelOrderPopup.querySelector('.cancel__button').addEventListener('click', function () {
            cancelOrderPopup.querySelector('.popup__container').remove();
        })
    })
}

function handleRemoveOrCancelOrderAdmin(processOrderForm, processOrderButton, processOrderPopup){
    let orderCheckbox = $(processOrderForm).find('input[type=checkbox][name=multipleOrderId]:checked')

    let dataRemoveOrder = $(orderCheckbox).serializeArray();
    let action = processOrderButton.val();
    dataRemoveOrder.push({name: 'action', value: action});

    $(processOrderButton).on('click', function (event){
        event.preventDefault();
        $.ajax({
            url: processOrderForm.attr('action'),
            type: processOrderForm.attr('method'),
            contentType: "application/x-www-form-urlencoded",
            data : dataRemoveOrder,
            success: function (response){
                alert(response.successProcess)
                $(processOrderPopup).find('.popup__container').remove()
                $(orderCheckbox).each(function (){
                    $(this).prop('checked', false)
                    $('.cancel__wrapper .button__cancel').removeClass('active')
                    let tableRowTarget = $(this).closest('.table__row')
                    if(action === response.cancelOrderAction && response.cancelOrderAction !== undefined){
                        tableRowTarget.addClass('not__allow');
                        $(tableRowTarget).find('.table__data.data__status.order .table__cell').text(response.cancelStatus)

                    }else if(action === response.removeOrderAction && response.removeOrderAction !== undefined){
                        tableRowTarget.remove();
                    }
                })
            }
        })
    })
}

function handleOpenPopupUpdateStatus(){
    $(document).ready(function (){
        const popupUpdate = $('.popup__update');
        let processOrderForm = $('#process__order--form');
        let showUpdateStatusButton = $('.table__cell.show__update');
        showUpdateStatusButton.on('click', function (){
            let showUSButtonTarget = $(this);
            let orderId = $(this).val()
            let action = $(this).data('action')
            $.ajax({
                url: processOrderForm.attr('action'),
                type: processOrderForm.attr('method'),
                data: {
                    action: action,
                    orderId: orderId
                },
                success: function (response){
                    let arrAllOrderStatus = response.listAllOrderStatus;
                    let arrAllTransactionStatus = response.listAllTransactionStatus;
                    let orderStatusTarget = JSON.parse(response.orderStatusTarget);
                    let transactionStatusTarget = JSON.parse(response.transactionStatusTarget);

                    $(popupUpdate).html(`<div class="update__status--modal">
                                                    <div class="popup__container">
                                                        <div class="popup__heading">
                                                            <h1 class="popup__title">Cập nhật trạng thái</h1>
                                                            <span class="popup__close"><i class="fa-solid fa-xmark"></i></span>
                                                        </div>
                                                        <div class="popup__body">
                                                            <div class="popup__content">
                                                                <div class="update__status">
                                                                    <div class="section__content">
                                                                        <span class="content__no">1</span>
                                                                        <span class="content__title">Tình trạng đơn hàng</span>
                                                                    </div>
                                                                    <select class="select__status" name="orderStatus">
                                                                        
                                                                    </select>
                                                                </div>
                                                                <div class="update__status">
                                                                    <div class="section__content">
                                                                        <span class="content__no">2</span>
                                                                        <span class="content__title">Tình trạng giao dịch</span>
                                                                    </div>
                                                                    <select class="select__status" name="transactionStatus">
                                                                        
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="button__control">
                                                                <button type="submit" class="save__update" name="action" value="saveUpdateStatus" data-order-id="">Lưu cập nhật</button>
                                                                <button type="button" class="cancel__update" name="action" value="cancelUpdate">Hủy</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>`) ;

                    $.each(arrAllOrderStatus, function (index, value){
                        let selectStatus = $('.update__status:first-child .select__status');
                        $(selectStatus).append(`<option class="option__status" value="${value.id}">${value.typeStatus}</option>`)
                        if(orderStatusTarget.typeStatus === value.typeStatus) {
                            $(selectStatus).find('.option__status').prop('selected', true)
                        }
                    })

                    $.each(arrAllTransactionStatus, function (index, value){
                        let selectStatus = $('.update__status:last-child .select__status');
                        $(selectStatus).append(`<option class="option__status" value="${value.id}">${value.typeStatus}</option>`)
                        if(value.typeStatus === transactionStatusTarget.typeStatus){
                            $(selectStatus).find('.option__status').prop('selected', true)
                        }
                    })

                    $('.select__status').select2();
                    $(popupUpdate).find('.popup__close').on('click', function () {
                        $(popupUpdate).find('.update__status--modal').remove();
                    })

                    $(popupUpdate).find('.cancel__update').on('click', function () {
                        $(popupUpdate).find('.update__status--modal').remove();
                    })


                    function handleUpdateStatusOrderTran(){
                        let processOrderForm = $('#process__order--form');
                        let updateStatusButton = $('.save__update');
                        updateStatusButton.on('click', function (event){
                            event.preventDefault();
                            let action = $(updateStatusButton).val();
                            let orderStatusId = $('select[name=orderStatus].select__status').val();
                            let transactionStatusId = $('select[name=transactionStatus].select__status').val();
                            $.ajax({
                                url: processOrderForm.attr("action"),
                                type: processOrderForm.attr("method"),
                                data: {
                                    action: action,
                                    orderId: orderId,
                                    orderStatusId: orderStatusId,
                                    transactionStatusId: transactionStatusId
                                },
                                success: function (response) {
                                    $(popupUpdate).find('.update__status--modal').remove();
                                    $(showUSButtonTarget).closest('.table__row').find('.data__status.order .table__cell').text(response.orderStatusUpdate);
                                    $(showUSButtonTarget).closest('.table__row').find('.data__status.transaction .table__cell').text(response.transactionStatusUpdate)
                                    $('.order__status--section').find('.info__value').text(response.orderStatusUpdate);
                                    $('.transaction__status--section').find('.info__value').text(response.transactionStatusUpdate)
                                }
                            })
                        })
                    }
                    handleUpdateStatusOrderTran()
                }
            })
        })
    })
}
handleOpenPopupUpdateStatus()


function handelShowListDetailOfOrder() {
    $(document).ready(function () {
        let processOrderForm = $('#process__order--form');
        let seeDetailButton = $('.table__cell.see__detail');
        seeDetailButton.on('click', function () {
            let orderId = $(this).val();
            let action = $(this).data("action");
            $.ajax({
                url: processOrderForm.attr('action'),
                type: processOrderForm.attr('method'),
                data: {
                    orderId: orderId,
                    action: action
                },
                dataType: 'json',
                success: function (response) {
                    const dialogContainer = $('.dialog__container');
                    let arrOrderDetail = response.listOrderDetailByOrderId;
                    let orderTarget = JSON.parse(response.orderTarget);
                    let deliveryMethodTarget = JSON.parse(response.deliveryMethodTarget);
                    let paymentMethodTarget = JSON.parse(response.paymentMethodTarget);
                    let orderStatusTarget = JSON.parse(response.orderStatusTarget);
                    let transactionStatusTarget = JSON.parse(response.transactionStatusTarget)
                    let voucherTarget = JSON.parse(response.voucherTarget);

                    let voucherContent;

                    if(voucherTarget.code === null){
                        voucherContent = '';
                    }else{
                        voucherContent = voucherTarget.code + " (Giảm giá " + `${voucherTarget.discountPercent * 100}%` + ` cho đơn hàng từ ${formatCurrency(voucherTarget.minimumPrice)})`;
                    }

                    let modalOrder = document.querySelector('.modal__order');
                    $(modalOrder).html(`
                                            <div class="head__dialog--bar">
                                                <h1 class="dialog__title"></h1>
                                                <span class="dialog__close"><i class="fa-solid fa-xmark"></i></span>
                                            </div>
                                            <div class="dialog__container">
                                                <div class="section__dialog">
                                                    <div class="section__heading">
                                                        <span class="section__no">1</span>
                                                        <h1 class="section__title">Thông tin giao hàng của khách hàng</h1>
                                                    </div>
                                                    <table class="section__content">
                                                        <tbody>
                                                        <tr class="section__info">
                                                            <td class="info__text">Tên khách hàng</td>
                                                            <td class="info__value">${orderTarget.fullName}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Email</td>
                                                            <td class="info__value">${orderTarget.email}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Số điện thoại</td>
                                                            <td class="info__value">${orderTarget.phone}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Địa chỉ</td>
                                                            <td class="info__value">${orderTarget.address}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="section__dialog">
                                                    <div class="section__heading">
                                                        <span class="section__no">2</span>
                                                        <h1 class="section__title">Thông tin đơn hàng của khách hàng</h1>
                                                    </div>
                                                    <table class="section__content">
                                                        <tbody>
                                                        <tr class="section__info">
                                                            <td class="info__text">Mã đơn hàng</td>
                                                            <td class="info__value">${orderTarget.id}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Ngày đặt hàng</td>
                                                            <td class="info__value">${orderTarget.dateOrder}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Mã giảm giá đã áp dụng</td>
                                                            <td class="info__value">${voucherContent}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Phương thức vận chuyển</td>
                                                            <td class="info__value">${deliveryMethodTarget.typeShipping}</td>
                                                        </tr>
                                                        <tr class="section__info">
                                                            <td class="info__text">Phương thức thanh toán</td>
                                                            <td class="info__value">${paymentMethodTarget.typePayment}</td>
                                                        </tr>
                                                        <tr class="section__info order__status--section">
                                                            <td class="info__text">Tình trạng đơn hàng</td>
                                                            <td class="info__value">${orderStatusTarget.typeStatus}</td>
                                                        </tr>
                                                        <tr class="section__info transaction__status--section">
                                                            <td class="info__text">Tình trạng giao dịch</td>
                                                            <td class="info__value">${transactionStatusTarget.typeStatus}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="section__dialog">
                                                    <div class="section__heading">
                                                        <span class="section__no">3</span>
                                                        <h1 class="section__title">Danh sách sản phẩm đã đặt trong đơn hàng</h1>
                                                    </div>
                                                    <div class="section__wrapper">
                                                        <table class="products__order">
                                                            <thead>
                                                            <tr class="table__row">
                                                                <th class="table__head"></th>
                                                                <th class="table__head">Sản phẩm</th>
                                                                <th class="table__head">Màu sắc</th>
                                                                <th class="table__head">Kích thuớc</th>
                                                                <th class="table__head">Số lượng</th>
                                                                <th class="table__head">Gíá may</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>`)

                    $('.dialog__title').text('Chi tiết đơn hàng');

                    $.each(arrOrderDetail, function (index, value){
                        let orderDetailObject = value;
                        $('.products__order tbody').append(`<tr class="table__row">
                                                                <td class="table__data"></td>
                                                                <td class="table__data" style="text-align: left">${orderDetailObject.productName}</td>
                                                                <td class="table__data">${orderDetailObject.colorRequired}</td>
                                                                <td class="table__data">${orderDetailObject.sizeRequired}</td>
                                                                <td class="table__data">${orderDetailObject.quantityRequired}</td>
                                                                <td class="table__data">${formatCurrency(orderDetailObject.price)}</td>
                                                            </tr>`)
                    })

                    handleCloseDialogOrder(dialogUpdateOrder);


                }
            })
        })
    })
}
handelShowListDetailOfOrder();
