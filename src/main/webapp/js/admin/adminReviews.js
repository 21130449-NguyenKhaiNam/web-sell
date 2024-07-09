import {formatDate, formatCurrency, http} from "../base.js";

$(document).ready(function () {
    const configDatatable = {
        paging: true,
        processing: true,
        serverSide: true,
        page: 1,
        pageLength: 15,
        lengthChange: false,
        searching: false,
        ordering: false,
        ajax: {
            url: "/api/admin/review/datatable",
            data: function (d) {
                d.page = d.start / d.length + 1;
                delete d.start;
                delete d.length
                return d;
            },
            dataSrc: function (json) {
                json.draw = json.draw;
                json.recordsTotal = json.quantity * json.reviews.length;
                json.recordsFiltered = json.quantity * json.reviews.length;
                json.data = json.reviews.map(function (item) {
                    return {
                        id: item.id,
                        userId: item.userId,
                        productName: item.productName,
                        orderDetailId: item.orderDetailId,
                        stars: item.ratingStar,
                        reviewDate: item.reviewDate,
                        state: item.visibility
                    };
                })
                return json.data;
            },
        }, columns: [

            {data: "id"},
            {data: "userId"},
            {data: "productName"},
            {data: "stars"},
            {data: "orderDetailId"},
            {
                data: "reviewDate",
                render: function (data, type, row) {
                    return formatDate(data)
                }
            },
            {
                data: "state",
                render: function (data, type, row) {
                    let obj = {
                        icon: "",
                        className: ""
                    }
                    if (data)
                        obj = {
                            icon: `<i class="fa-solid fa-lock"></i>`,
                            className: "btn btn-visible btn-danger"
                        }
                    else
                        obj = {
                            icon: `<i class="fa-solid fa-unlock"></i>`,
                            className: "btn btn-visible btn-primary"
                        }
                    return `<button class="${obj.className}">${obj.icon}</button>`;
                }
            },
            {
                data: "id",
                render: function (data, type, row) {
                    return `
                            <button class="btn btn-info btn-detail" data-id="${data}" data-bs-toggle="modal" data-bs-target="#modal">
                                <i class="fa-solid fa-eye"></i>
                            </button>`;
                }
            },
        ],
        columnDefs: [
            {
                targets: 6,
                createdCell: function (td, cellData, rowData, row, col) {
                    $(td).find("button").attr("data-id", rowData.id);
                    $(td).find("button").attr("data-state", rowData.state);
                    $(td).find("button").attr("data-index", row);
                },
            },
        ],
        createdRow: function (row, data, dataIndex) {
            if (!data.state)
                $(row).addClass('table-danger');
        },
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
        },
        initComplete: function (settings, json) {
            handleEventDatatable();
            configModal();
        }
    }
    const table = $('#table');
    const datatable = table.DataTable(configDatatable);

    function handleEventDatatable() {
        table.find("tbody").on('click', 'button.btn-visible', function (e) {
            e.stopPropagation();
            const id = $(this).data("id");
            const state = $(this).data("state");
            const index = $(this).data("index");
            if (state) {
                handleEventVisible("hide", id, index);
            } else {
                handleEventVisible("visible", id, index);
            }
        }).on('click', 'button.btn-detail', function (e) {
            e.stopPropagation();
        });
    }

    function handleEventVisible(type, id, index) {
        Swal.fire({
            title: `Bạn có muốn ${type == "visible" ? "hiện thị" : "ẩn"} bình luận này không?`,
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            if (result.isConfirmed) {
                http({
                    url: "/api/admin/review/visible",
                    method: "POST",
                    data: {
                        id: id,
                        type: type
                    }
                }).then(function (data) {
                    if (data.success) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Cập nhập thành công ',
                        })
                        datatable.row(index).data({
                            ...datatable.row(index).data(),
                            state: type == "hide"
                        }).draw(false);
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Cập nhập thất bại',
                        })
                    }
                });
            }
        });
    }

    const modal = $("#modal");

    function configModal() {
        modal.on("hide.bs.modal", function () {
            clearData();
        })
        modal.on("show.bs.modal", function (event) {
            const button = $(event.relatedTarget);
            const id = button.data("id");
            getDetailComment(id);
        });
    }

    function getDetailComment(id) {
        http({
            url: "/api/admin/review/get",
            method: "GET",
            data: {
                id: id
            }
        }).then(response => {
            fieldData(response);
        })
    }

    function clearData() {
        modal.find("#image").attr("src", "");
        modal.find("#name").text("");
        modal.find("#color").css("--badge-color", "#fff");
        modal.find("#color-code").text("");
        modal.find("#size").text("");
        modal.find("#price-unit").text("");
        modal.find("#quantity").text("");
        modal.find("#stars").html("");
        modal.find("#date").text("");
        modal.find("#description").text("");
    }

    function fieldData(data) {
        const review = data.review;
        const orderDetail = data.orderDetail;
        const order = data.order;
        modal.find("#image").attr("src", orderDetail.image);
        modal.find("#name").text(orderDetail.productName);
        modal.find("#color").css("--badge-color", orderDetail.color);
        modal.find("#color-code").text(orderDetail.color);
        modal.find("#size").text(orderDetail.size);
        modal.find("#price-unit").text(formatCurrency(orderDetail.price));
        modal.find("#quantity").text(orderDetail.quantity);
        loadStars(review.ratingStar);
        modal.find("#date").text(formatDate(review.date));
        modal.find("#description").text(review.feedback);
        modal.find("#total-price").text(formatCurrency(orderDetail.price * orderDetail.quantity));
        modal.find("#email").text(order.email);
        modal.find("#phone").text(order.phone);
        modal.find('#fullName').text(order.fullName);
        modal.find("#province").text(order.province);
        modal.find("#district").text(order.district);
        modal.find("#ward").text(order.ward);
        modal.find("#detail").text(order.detail);
    }

    function loadStars(stars) {
        const reviewStars = modal.find("#stars");
        const htmls = [];
        let i;
        for (i = 0; i < stars; i++) {
            htmls.push(`<i class="star fa-solid fa-star"></i>`);
        }
        for (; i < 5; i++) {
            htmls.push(`<i class=" star fa-regular fa-star"></i>`);
        }
        reviewStars.html(htmls.join(""));
    }
});