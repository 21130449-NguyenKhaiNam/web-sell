$(document).ready(function () {
    $('.list-group-item-action').click(function () {
        $('.list-group-item-action').removeClass('active');
        $(this).addClass('active');
        const statusId = $(this).data('status');
        getOrders(statusId);
    });
    // $('#orderList').DataTable({
    //     ajax: '/js/user/datatable.json',
    //     columns: [
    //         {
    //             data: 'id'
    //         },
    //         {
    //             data: 'dateOrder'
    //         },
    //         {
    //             data: 'price'
    //         },
    //         {
    //             data: 'status'
    //         }
    //         , {
    //             "data": null,
    //             "defaultContent": "<button class=\"btn btn-primary\">Chi tiết</button>", // Button content
    //             "searchable": false,
    //             "orderable": false
    //         }
    //     ],
    //     columnDefs: [
    //         {
    //             targets: [0, 1, 2, 3, 4, 5], // Apply to all columns
    //             render: function (data, type, full, meta) {
    //                 if (type === 'filter') {
    //                     var column = meta.col;
    //                     var select = $('<select><option value="">All</option></select>')
    //                         .appendTo($(column.header()))
    //                         .on('change', function () {
    //                             var val = $.fn.dataTable.util.escapeRegex(
    //                                 $(this).val()
    //                             );
    //                             column.search(val ? '^' + val + '$' : '', true, false).draw();
    //                         });
    //
    //                     column.data().unique().sort().each(function (d, j) {
    //                         select.append('<option value="' + d + '">' + d + '</option>')
    //                     });
    //                 }
    //                 return data;
    //             }
    //         }
    //     ]
    // });
});