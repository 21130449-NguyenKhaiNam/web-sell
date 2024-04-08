$(document).ready(function () {
    $('.list-group-item-action').click(function () {
        $('.list-group-item-action').removeClass('active');
        $(this).addClass('active');
        const statusId = $(this).data('status');
        console.log(statusId)
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
                        <td><button class="btn btn-primary" data-id="${order.id}" data-bs-toggle="modal" data-bs-target="#modal">Xem chi tiet</button></td>
                    </tr>`
        })
        table.html(htmls.join(''));
    }
});