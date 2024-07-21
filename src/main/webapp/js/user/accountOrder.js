import {getImageProduct} from "../uploadImage.js";
import {getFeeAndLeadTime} from "../shipping.js";

$(document).ready(function () {
    // Lấy ra trạng thái đơn hàng chưa xác nhận khi mới vào trang
    let statusId = 1;
    let totalPrice = 0;
    getOrders(statusId);
    $('.list-group-item-action').click(function () {
        $('.list-group-item-action').removeClass('active');
        $(this).addClass('active');
        statusId = $(this).data('status');
        getOrders(statusId);
    });

    function getOrders(statusId) {
        $.ajax({
            url: '/api/user/order',
            type: 'GET',
            data: {
                statusId: statusId,
            },
            success: function (response) {
                loadDataToTable(response.data)
            }
        })
    }

    function loadDataToTable(data) {
        const table = $('#orderList tbody');
        table.empty();
        const htmls = data.map(function (order) {
            return `<tr>
                        <td>${order.id}</td>
                        <td>${order.dateOrder}</td>
                        <td>${order.quantity}</td>
                        <td><button class="btn btn-primary btn__order-detail" data-id="${order.id}" data-bs-toggle="modal" data-bs-target="#modal">Xem chi tiet</button></td>
                    </tr>`
        })
        table.html(htmls.join(''));
        addEventViewDetail();
    }

    const modal = $('#modal');

    function loadDataModal(order, orderId) {
        function loadItem(item) {
            return `<div class="order__item align-items-center mt-2 row">
                        <div class="item__thumbnail col-2">
                            <img src="${getImageProduct(item.thumbnail)}" class="rounded border img-thumbnail object-fit-cover" style="width: 100px; height: 100px" alt="...">
                        </div>
                        <div class="item__detail col-8">
                            <h3 class="h4 item__name pb-1s fw-4">${item.name}</h3>
                            <div class="d-flex pt-2 fw-6 align-items-center">
                                <span class="item__color d-block border border-black border-3 rounded" style="background: ${item.color}; width:20px; height: 20px"></span> 
                                <span class="vr mx-2"></span>
                                <span class="item__size text-uppercase">${item.size}</span>
                            </div>
                        </div>
                        <div class="col-2">
                            <p class="item__price pb-2 fs-5 fw-bold">Giá: <span>${formatCurrency(item.price)}</span></p>
                            <p class="item__quantity pt-2 fs-6">Số lượng: <span>${item.quantity}</span> cái</p>
                        </div>
                    </div>`
        }

        function loadAddress(address) {
            modal.find("#order__province").text(address.province);
            modal.find("#order__district").text(address.district);
            modal.find("#order__ward").text(address.ward);
            modal.find("#order__detail").text(address.detail);
        }

        function loadPrice(order) {
            const temporary = order.items.reduce(function (total, item) {
                return total + item.price * item.quantity;
            }, 0);
            totalPrice += temporary;
            modal.find("#order__temporary").text(formatCurrency(temporary));
        }

        function loadContact(order) {
            modal.find("#order__name").text(order.name ?? "");
            modal.find("#order__phone").text(order.phone ?? "");
            modal.find("#order__email").text(order.email ?? "");
        }

        const htmls = order.items.map(function (item) {
            return loadItem(item);
        });
        modal.find("#order__id").text(orderId);
        modal.find("#order__date").text(formatDate(order.dateOrder));
        modal.find("#order__status").text(order.status ?? 'Chưa xác nhận');
        modal.find("#order__list").html(htmls.join(''));
        loadAddress(order.address)
        loadPrice(order);
        loadContact(order);
        console.log(JSON.stringify(order.address))
        getFeeAndLeadTime(order.address).then(data => {
            console.log(data.feeShipping, data.leadDate);
            totalPrice -= data.feeShipping
            modal.find("#order__shipping-fee").text(formatCurrency(data.feeShipping));
            modal.find("#order__lead-date").text(convertUnixToDate(data.leadDate));
        })
        modal.find("#order__total").text(formatCurrency(totalPrice))
    }

    function addEventViewDetail() {
        $('.btn__order-detail').click(function () {
            const orderId = $(this).data('id');
            $.ajax({
                url: "/api/user/order/detail",
                data: {
                    orderId: orderId
                },
                type: 'GET',
                success: function (response) {
                    const success = response.success;
                    if (success) {
                        const data = response.data;
                        loadDataModal(data, orderId);
                    } else {

                    }
                }
            })
        })
    }

    function formatDate(dateString) {
        const components = dateString.split('-');
        return components[2] + '-' + components[1] + '-' + components[0];
    }

    function formatCurrency(value) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
        }).format(value);
    }

    function convertUnixToDate(timestamp) {
        const milliseconds = timestamp * 1000;
        const date = new Date();
        date.setTime(milliseconds);

        const day = date.getDate();
        const month = date.getMonth() + 1;
        const year = date.getFullYear();
        return `${day}-${month}-${year}`;
    }
});