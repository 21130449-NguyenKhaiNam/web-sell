$(document).ready(function () {
    $('.list-group-item-action').click(function () {
        $('.list-group-item-action').removeClass('active');
        $(this).addClass('active');
        const statusId = $(this).data('status');
        getOrders(statusId);
    });
    $('#orderList').DataTable({
        language: {
            "url": "https://cdn.datatables.net/plug-ins/2.0.3/i18n/vi.json"
        },
        "serverSide": true,
        "processing": true,
        "searching": true,
        "paging": true,
        data:orderList,
        columns: [
            { data: 'id' },
            { data: 'dateOrder' },
            { data: 'quantity' },
        ],
        "pageLength": 10, // Set default number of rows per page
        "order": [[0, "asc"]], // Set default sorting column and direction
        ajax: {
            url: '/api/user/order',
            dataType: 'json',
            type: 'GET',
        },
    });
});