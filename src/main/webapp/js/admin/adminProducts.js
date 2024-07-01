import {objectToQueryString} from "../base.js";

$(document).ready(() => {
    // Enable tooltip bootstrap
    $('[data-bs-toggle="tooltip"]').tooltip();
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    // config preview image
    $.fn.filepond.registerPlugin(FilePondPluginImagePreview);

    const size = $("#size");
    const category = $("#category");
    const color = $("#color")
    const moneyRange = $("#moneyRange");
    const createdAt = $("#createdAt")
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
            url: "/filterProductAdmin",
            data: function (d) {
                d.page = d.start / d.length + 1;
                delete d.start;
                delete d.length
                return d;
            },
            dataSrc: function (json) {
                json.draw = json.draw;
                json.recordsTotal = json.quantity * 10;
                json.recordsFiltered = json.quantity * 10;
                json.data = json.products.map(function (item) {
                    return {
                        id: item.product.id,
                        name: item.product.name,
                        category: item.nameCategory,
                        originalPrice: item.product.originalPrice,
                        salePrice: item.product.salePrice,
                        stars: item.stars,
                        reviewCounts: item.reviewCounts,
                        image: item.images.length > 0 ? item.images[0].nameImage : '',
                        state: item.product.visibility
                    };
                })
                return json.data;
            },
        }, columns: [
            {data: "id"},
            {data: "name"},
            {data: "category"},
            {
                data: "originalPrice",
                render: function (data, type, row) {
                    return formatter.format(data);
                }
            },
            {
                data: "salePrice",
                render: function (data, type, row) {
                    return formatter.format(data);
                }
            },
            {
                data: "image",
                render: function (data, type, row) {
                    let obj = {
                        icon: "",
                        className: ""
                    }
                    if (data.state == true)
                        obj = {
                            icon: `<i class="fa-solid fa-unlock"></i>`,
                            className: "btn btn-primary"
                        }
                    else
                        obj = {
                            icon: `<i class="fa-solid fa-lock"></i>`,
                            className: "btn btn-danger"
                        }
                    return `<button class="${obj.className}">${obj.icon}</button>`;
                }
            }
        ],
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
        },
        select: true,
        initComplete: function (settings, json) {
            setupFormSearch();
            handleSubmitFormSearch();
            initFileInput();
            initTextEditor();
        }
    }

    function setupFormSearch() {
        size.select2(
            {
                multiple: true,
                width: '100%',
                closeOnSelect: false,
                allowClear: true,
                placeholder: 'Kích thước',
            }
        );
        category.select2(
            {
                multiple: true,
                width: '100%',
                closeOnSelect: false,
                allowClear: true,
                placeholder: 'Thể loại',
            }
        );

        function formatColorOption(option) {
            if (!option.id) {
                return option.text;
            }
            var bg_color = $(option.element).val();
            var $option = $(
                '<span style="background-color: ' + bg_color + ';" class="select2-color-option">' + option.text + '</span>'
            );
            return $option;
        }

        color.select2({
            width: '100%',
            placeholder: 'Màu sắc',
            closeOnSelect: false,
            allowClear: true,
            templateResult: formatColorOption,
            templateSelection: formatColorOption
        });

        moneyRange.ionRangeSlider({
            skin: "round",
            type: "double",
            grid: true,
            grid_num: 2,
            min: 0,
            max: 3000000,
            from: 200000,
            to: 1000000,
            grid_margin: true,
            hide_min_max: true,
            step: 100000, // 100,000 VND
            prettify_enabled: true,
            prettify_separator: "-",
            prettify: function (num) {
                return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " đ";
            },
            onStart: function (data) {
                updateMoneyRangeInput(data);
            },
            onChange: function (data) {
                updateMoneyRangeInput(data);
            },
            onFinish: function (data) {
                updateMoneyRangeInput(data);
            },
            onUpdate: function (data) {
                updateMoneyRangeInput(data);
            }
        });

        function updateMoneyRangeInput(data) {
            const formattedValue = `${data.from} - ${data.to}`;
            moneyRange.val(formattedValue);
        }

        createdAt.daterangepicker({
            autoUpdateInput: false,
            showDropdowns: true,
            locale: {
                cancelLabel: 'Hủy ',
                applyLabel: 'Chọn',
                daysOfWeek: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
                monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6',
                    'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                firstDay: 1,
            }
        });

        createdAt.on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        });

        createdAt.on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    }

    const table = $('#table').DataTable(configDatatable);
    const searchForm = $('#form__filter');

    function handleSubmitFormSearch() {
        searchForm.submit(function (e) {
            e.preventDefault(); // Prevent the form from submitting normally
            // Serialize form data to an array of name-value pairs
            var formDataArray = $(this).serializeArray();
            // Convert array to JSON object
            var formDataJson = {};
            $.each(formDataArray, function () {
                // For multi-select elements, handle array of values
                if (formDataJson[this.name]) {
                    if (!formDataJson[this.name].push) {
                        formDataJson[this.name] = [formDataJson[this.name]];
                    }
                    formDataJson[this.name].push(this.value || '');
                } else {
                    formDataJson[this.name] = this.value || '';
                }
            });
            const queryString = objectToQueryString(formDataJson);
            table.ajax.url(`/filterProductAdmin?${queryString}`).load();
        });
    }

    $.validator.prototype.checkForm = function () {
        this.prepareForm();
        for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
            if (this.findByName(elements[i].name).length != undefined && this.findByName(elements[i].name).length > 1) {
                for (var cnt = 0; cnt < this.findByName(elements[i].name).length; cnt++) {
                    this.check(this.findByName(elements[i].name)[cnt]);
                }
            } else {
                this.check(elements[i]);
            }
        }
        return this.valid();
    }
    // Form add
    const configValidator = {
        ignore: [],
        rules: {
            name: {
                required: true,
            },
            idCategory: {
                required: true,
            },
            originalPrice: {
                required: true,
            },
            salePrice: {
                required: true,
            },
            description: {
                required: true,
            },
            'nameSize[]': {
                required: true,
            },
            'sizePrice[]': {
                required: true,
            },
            // color: {
            //     required: true,
            // },
        },
        messages: {
            name: {
                required: "Vui lòng nhập tên sản phẩm",
            },
            idCategory: {
                required: "Vui lòng chọn danh mục",
            },
            originalPrice: {
                required: "Vui lòng nhập giá gốc",
            },
            salePrice: {
                required: "Vui lòng nhập giá bán",
            },
            description: {
                required: "Vui lòng nhập mô tả",
            },
            'nameSize[]': {
                required: "Vui lòng nhập tên size",
            },
            'sizePrice[]': {
                required: "Vui lòng nhập giá size",
            },
            // color: {
            //     required: "Vui lòng nhập màu",
            // },
        },
        onblur: function (element) {
            $(element).valid();
        },
        validClass: 'is-valid',
        errorClass: 'is-invalid',
        errorPlacement: function (error, element) {
            $(element).parent().find(".valid-feedback , .invalid-feedback").text(error.text());
        },
        success: function (label) {
            label.remove(); // Remove the error message when input is valid
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
            $(element).parent().find(".valid-feedback").addClass("invalid-feedback");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
            $(element).find(".valid-feedback").text("");
        },
        submitHandler: function (form) {
            const formData = new FormData(form);
            // Thêm ảnh vào FormData
            const filePondFiles = FilePond.find(document.querySelector('#image')).getFiles();
            filePondFiles.forEach(file => {
                formData.append('files[]', file.file);
            });
            $.ajax({
                url: "/api/admin/product/create",
                type: "POST",
                contentType: false,
                processData: false,
                dataType: "json",
                data: formData,
                success: function (data) {
                    if (data.status === true) {
                        notifySuccess({
                            title: "Thêm sản phẩm thành công",
                            body: "Sản phẩm đã được thêm vào gian hàng.",
                        });
                    } else {
                        notifyFailed({
                            title: "Thêm sản phẩm không thành công",
                            body: "Sản phẩm đã có tên trên đã tồn tại vào gian hàng.",
                        });
                    }
                },
                error: function (error) {
                    console.log(error)
                },
            });
        }
    }
    const form = $("#form__add")
    const formSize = $("#form__size");
    const formColor = $("#form__color");
    const btnAddSize = $("#form__add-size");
    const btnAddColor = $("#form__add-color");
    let indexSize = 0;

    const validator = form.validate(configValidator)

    btnAddSize.on("click", function () {
        const html = $(`
            <div data-size-index="${indexSize}" class="row align-items-center mt-1">
                <div class="col-4 form__label">
                    <input type="text" name="nameSize[]" class="form-control">
                    <span class="form__error"></span>
                </div>
                <div class="col-4 form__label">
                    <input type="text" name="sizePrice[]" class="form-control">
                    <span class="form__error"></span>
                </div>
                <div class="col-4">
                    <div class="btn btn-danger form__remove-size">Xóa size</div>
                </div>
            </div>
        `);
        formSize.append(html);

        html.find(".form__remove-size").on("click", function () {
            $(this).closest("[data-size-index]").remove();
        });

        indexSize++;
    });

    createPicker("#color-input");
    btnAddColor.on("click", () => {
        const colorId = `color-${Date.now()}`;
        const html = $(`
         <div class="d-flex align-items-center gap-1 p-1 border border-1 round mb-1">
            <input  id="${colorId}" name="color" hidden="hidden" type="text" >
            <div class="color__remove">
                <i class="fa-solid fa-xmark"></i>
            </div>
        </div>
        `)
        formColor.append(html);
        createPicker(`#${colorId}`);
        html.find(".color__remove").on("click", () => {
            html.remove();
        })
    })

    function initFileInput() {
        const inputElement = $("#image")[0];
        const pond = FilePond.create(inputElement, {
            allowMultiple: true,
            allowImagePreview: true,
        });

        pond.on('addfile', (error, file) => {
            if (!error) {
                console.log('File added:', file);
            }
        });
    }

    function initTextEditor() {
        var editor = new FroalaEditor('#editor', {
            language: 'vi',
            events: {
                'contentChanged': function () {
                    const content = this.html.get();
                    $("#description").val(content);
                }
            }
        });
    }

    function createPicker(el) {
        $(el).spectrum({
            color: "#000000",
            showInput: true,
            preferredFormat: "hex",
            change: function (color) {
                const hexColor = color.toHexString();
                $(el).val(hexColor);
            }
        });
    }

//     function getClose(modal) {
//         return modal.querySelector(".modal__product-close");
//     }
//
// //Read product
//     const iframeRead = document.querySelector("#dialog-product-read .modal__product-iframe");
//     const dataViewElement = document.querySelectorAll(".table__data-view");
//     const modalRead = document.querySelector("#dialog-product-read");
//     const elementCloseRead = getClose(modalRead);
//
//     elementCloseRead.onclick = function () {
//         modalRead.style.display = "none";
//     };
//
//     dataViewElement.forEach(function (element) {
//         const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
//         element.onclick = function () {
//             // Open dialog
//             modalRead.style.display = "block";
//
//             // Send via iframe
//             const tableRow = this.parentNode;
//             const productId = tableRow.querySelector(".table__data-id").textContent.trim();
//             console.log(pageTarget)
//             iframeRead.contentWindow.postMessage({
//                 productId: productId,
//                 state: 1,
//             }, pageTarget);
//         }
//     });
//
// //Create product
//     const iframeCreate = document.querySelector("#dialog-product-create .modal__product-iframe");
//     const modalCreateBtn = document.querySelector("#button-create-product");
//     const modalCreate = document.querySelector("#dialog-product-create");
//     const elementCloseCreate = getClose(modalCreate);
//
//     elementCloseCreate.onclick = function () {
//         modalCreate.style.display = "none";
//     }
//
//     modalCreateBtn.onclick = function () {
//         const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
//         modalCreate.style.display = "block";
//         // Send via iframe
//         iframeCreate.contentWindow.postMessage({
//             state: 0,
//         }, pageTarget);
//     }
//
// //Update product
//     const iframeUpdate = document.querySelector("#dialog-product-update .modal__product-iframe");
//     const dataUpdateElement = document.querySelectorAll(".table__data-edit");
//     const modalUpdate = document.querySelector("#dialog-product-update");
//     const elementCloseUpdate = getClose(modalUpdate);
//
//     elementCloseUpdate.onclick = function () {
//         modalUpdate.style.display = "none";
//     };
//
//     dataUpdateElement.forEach(function (element) {
//         element.onclick = function () {
//             const pageTarget = `${window.location.origin}/public/admin/adminProductUpdateForm.jsp`;
//             // Open dialog
//             modalUpdate.style.display = "block";
//
//             // Send via iframe
//             const tableRow = this.parentNode;
//             const productId = tableRow.querySelector(".table__data-id").textContent.trim();
//             iframeUpdate.contentWindow.postMessage({
//                 productId: productId,
//                 state: 2,
//             }, pageTarget);
//         }
//     });
// //Hide product
//     const dataHideElement = document.querySelectorAll(".table__data-visibility .button");
//     dataHideElement.forEach(function (element) {
//         element.onclick = function () {
//             // Get name product
//             const tableRow = this.parentNode.parentNode;
//             console.log(tableRow.querySelector(".table__data-name .table__cell"))
//
//             const nameProduct = tableRow.querySelector(".table__data-name .table__cell").textContent.trim();
//             const idProduct = tableRow.querySelector(".table__data-id").textContent.trim();
//             // Show alert
//             if (element.classList.contains("button__hide")) {
//                 hideProductAlert(nameProduct, idProduct);
//             }
//             if (element.classList.contains("button__un-hide")) {
//                 unHideProductAlert(nameProduct, idProduct);
//             }
//
//         }
//     });
//
//     function hideProductAlert(nameProduct, idProduct) {
//         const message = `Bạn có muốn ẩn sản phẩm "${nameProduct}" không?`;
//         const result = window.confirm(message);
//         if (result) {
//             //     Handle
//             $.ajax({
//                 url: "/api/admin/product/hide",
//                 type: "POST",
//                 data: {
//                     id: idProduct
//                 },
//                 dataType: "json",
//                 cache: true,
//                 success: function (data) {
//                     const status = data.status;
//                     if (status) {
//                         alert(`Ẩn sản phẩm ${nameProduct} thành công`);
//                         window.location.reload();
//                     } else {
//                         alert(`Ẩn sản phẩm ${nameProduct} không thành công`);
//                     }
//                 },
//                 error: function (error) {
//                 },
//             });
//         }
//     }
//
//     function unHideProductAlert(nameProduct, idProduct) {
//         const message = `Bạn có muốn bỏ ẩn sản phẩm "${nameProduct}" không?`;
//         const result = window.confirm(message);
//         if (result) {
//             //     Handle
//             $.ajax({
//                 url: "/api/admin/product/un-hide",
//                 type: "POST",
//                 data: {
//                     id: idProduct
//                 },
//                 dataType: "json",
//                 success: function (data) {
//                     const status = data.status;
//                     if (status) {
//                         alert(`Bỏ ẩn sản phẩm ${nameProduct} thành công`);
//                         window.location.reload();
//                     } else {
//                         alert(`Bỏ ẩn sản phẩm ${nameProduct} không thành công`);
//                     }
//                 },
//                 error: function (error) {
//                 },
//             });
//         }
//     }
//
//     function changePage(aTag) {
//         if (aTag) {
//             productList.innerHTML = ``
//             callAjaxToPage(aTag.innerText)
//         }
//     }
//
//     function callAjaxToPage(pageNumber) {
//         $.ajax({
//             url: '/filterProductAdmin?page=' + pageNumber,
//             type: 'POST',
//             success: function (data) {
//                 generationPage(parseInt(pageNumber), data["quantity"])
//                 showProduct(data)
//             }, error: function (error) {
//                 console.log("loi khong hien thi san pham o trang admin")
//             },
//         });
//     }
//
//     function showProduct(data) {
//         const products = data["products"]
//
//         products.forEach(function (value) {
//             const product = value["product"]
//             const id = product["id"]
//             const name = product["name"]
//             const originalPrice = product["originalPrice"]
//             const salePrice = product["salePrice"]
//
//
//             const images = value["images"]
//
//             const nameImage = images[0]["nameImage"]
//             const visibility = product["visibility"]
//
//             const vndFormat = Intl.NumberFormat("vi-VI", {
//                 style: "currency", currency: "VND",
//             });
//
//             productList.innerHTML += `<tr class="table__row">
//             <td class="table__data-view">
//                 <label>
//                     <i class="fa-solid fa-eye"></i>
//                 </label>
//             </td>
//             <td class="table__data-edit">
//                 <label>
//                     <i class="fa-solid fa-pen-to-square"></i>
//                 </label>
//             </td>
//             <td class="table__data table__data-id">
//                 <p class="table__cell">${id}</p>
//             </td>
//             <td class="table__data table__data-name">
//                 <p class="table__cell line-clamp line-1">${name}</p>
//             </td>
//             <td class="table__data">
//                 <p class="table__cell">${value.nameCategory}</p>
//             </td>
//
//             <td class="table__data">
//                 <p class="table__cell">${vndFormat.format(originalPrice)}</p>
//             </td>
//             <td class="table__data">
//                 <p class="table__cell">${vndFormat.format(salePrice)}</p>
//             </td>
//
//             ${visibility ?
//                 `<td class="table__data table__data-visibility table__data-hide">
//                 <div class="button button--hover button__hide">Ẩn</div>
//             </td>` :
//                 `<td class="table__data table__data-visibility table__data-un-hide">
//                 <div class="button button--hover button__un-hide">Bỏ ẩn</div>
//             </td>`
//             }
//         </tr>`
//         })
//     }
//
//     function showBeforePaging(currentPage, minPage) {
//         if (currentPage > 3) {
//             productPages.innerHTML += `<a class="page access_page_quickly">...</a>`
//             const div = document.createElement('div')
//             for (let i = 1; i < minPage; i++) {
//                 const a = document.createElement('a')
//                 a.innerText = i
//                 a.classList.add("page")
//                 a.onclick = function () {
//                     changePage(a)
//                 }
//                 div.appendChild(a)
//             }
//
//             tippy('.access_page_quickly', {
//                 content: div, placement: 'top-start', interactive: true, duration: [500, 250], arrow: true,
//             });
//         }
//     }
//
//     function generationPage(currentPage, totalPage) {
//         var minPage = currentPage - 2
//         var maxPage = currentPage + 2;
//
//         if (maxPage > totalPage) {
//             maxPage = totalPage
//         }
//         if (currentPage < 3) {
//             minPage = 1;
//             maxPage = 5
//         }
//         productPages.innerHTML = ''
//         showBeforePaging(currentPage, minPage)
//         for (let i = minPage; i <= maxPage; i++) {
//             const a = document.createElement('a');
//             if (i === currentPage) {
//                 a.classList.add("page--current");
//             }
//             a.classList.add('page')
//             a.innerText = i;
//             a.onclick = function () {
//                 changePage(a)
//             }
//             productPages.appendChild(a);
//         }
//     }
//
//     const productList = document.querySelector('.product__list-admin')
//     const productPages = document.querySelector('.paging')
})