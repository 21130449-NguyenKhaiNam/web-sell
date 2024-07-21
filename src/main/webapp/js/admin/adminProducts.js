import {
    http,
    objectToQueryString,
    convertFormDataToObject,
    configSweetAlert2,
    startLoading,
    endLoading
} from "../base.js";
import {deleteImage, uploadImage} from "../uploadImage.js";

$(document).ready(() => {
    // Enable tooltip bootstrap
    $('[data-bs-toggle="tooltip"]').tooltip();

    // format currency vietnamdong
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });

    const modalLabel = $("#modal-label");
    const button = $("#button-modal");
    const size = $("#size");
    const category = $("#category");
    const color = $("#color")
    const moneyRange = $("#moneyRange");
    const createdAt = $("#createdAt")

    const modal = $("#modal-create");
    const form = $("#form__add");
    const formSize = $("#form__size");
    const formColor = $("#form__color");
    const btnAddSize = $("#form__add-size");
    const btnAddColor = $("#form__add-color");
    const modalFilter = $("#modal-filter");
    let editor;
    let dataSizeIndex = [];
    let dataColorIndex = [];
    let pond;//trình thêm ảnh
    const images = {
        productId: undefined,
        exist: [],
        added: [],
        deleted: [],
    };

    let selected = {
        index: undefined,
        id: undefined
    };
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
                json.recordsTotal = json.quantity * json.products.length;
                json.recordsFiltered = json.quantity * json.products.length;
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
                data: "state",
                render: function (data, type, row) {
                    let obj = {
                        icon: "",
                        className: ""
                    }
                    if (data)
                        obj = {
                            icon: `<i class="fa-solid fa-lock"></i>`,
                            className: "btn btn-danger"
                        }
                    else
                        obj = {
                            icon: `<i class="fa-solid fa-unlock"></i>`,
                            className: "btn btn-primary"
                        }
                    return `<button class="${obj.className}">${obj.icon}</button>`;
                }
            }
        ],
        columnDefs: [
            {
                targets: 5,
                createdCell: function (td, cellData, rowData, row, col) {
                    $(td).addClass('text-center');
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
        select: true,
        initComplete: function (settings, json) {
            initEventDatatable();
            setupFormSearch();
            handleSubmitFormSearch();
            configModal();
            initFileInput();
            initTextEditor();
        }
    }

    function initEventDatatable() {
        table.find("tbody").on('click', 'button', function (e) {
            e.stopPropagation();
            const id = $(this).data("id");
            const state = $(this).data("state");
            const index = $(this).data("index");
            if (state == true) {
                handleVisible("hide", id, index);
            } else {
                handleVisible("visible", id, index);
            }
        });
        datatable.on('select', function (e, dt, type, indexes) {
            selected = {
                index: indexes[0],
                id: datatable.row(indexes).data().id
            }
            button.text("Cập nhật sản phẩm");
            modalLabel.text("Cập nhật sản phẩm")
        }).on('deselect', function (e, dt, type, indexes) {
            selected = {
                index: undefined,
                id: undefined
            }
            modalLabel.text("Thêm sản phẩm")
            button.text("Thêm sản phẩm")
        });
    }

    function setupFormSearch() {
        size.select2(
            {
                multiple: true,
                width: '100%',
                closeOnSelect: false,
                allowClear: true,
                placeholder: 'Kích thước',
                dropdownParent: modalFilter,
            }
        );
        category.select2(
            {
                multiple: true,
                width: '100%',
                closeOnSelect: false,
                allowClear: true,
                placeholder: 'Thể loại',
                dropdownParent: modalFilter,
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
            templateSelection: formatColorOption,
            dropdownParent: modalFilter,
        });

        moneyRange.ionRangeSlider({
            skin: "round",
            type: "double",
            grid: true,
            grid_num: 2,
            min: 0,
            max: 1500000,
            from: 50000,
            to: 300000,
            grid_margin: true,
            hide_min_max: true,
            step: 10000,
            prettify_enabled: true,
            prettify_separator: "-",
            prettify: function (num) {
                return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " đ";
            },
        }).data("ionRangeSlider");

        // Set initial value manually
        const initialSlider = moneyRange.data("ionRangeSlider");
        moneyRange.val(`${initialSlider.options.from} - ${initialSlider.options.to}`);

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

    const table = $('#table');
    const datatable = table.DataTable(configDatatable);
    const searchForm = $('#form-filter');

    function handleSubmitFormSearch() {
        searchForm.submit(function (e) {
            e.preventDefault();
            const formDataArray = $(this).serializeArray();
            const formDataJson = {};
            $.each(formDataArray, function () {
                if (formDataJson[this.name]) {
                    if (!formDataJson[this.name].push) {
                        formDataJson[this.name] = [formDataJson[this.name]];
                    }
                    formDataJson[this.name].push(this.value || '');
                } else {
                    formDataJson[this.name] = this.value || '';
                }
            });
            formDataJson.moneyRange = formDataJson.moneyRange.replace(";", '-');
            const queryString = objectToQueryString(formDataJson);
            datatable.ajax.url(`/filterProductAdmin?${queryString}`).load();
            modalFilter.modal("hide");
            Swal.fire({
                ...configSweetAlert2,
                icon: 'success',
                title: "Áp dụng bộ lọc tìm kiếm thành công",
                showConfirmButton: false,
                timer: 1500
            });
        });
    }

    // ----------------------------------------------------------------
    // Bắt đàu phần thêm sản phẩm

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
            handleSubmitForm(form);
        }
    }

    function handleSubmitForm(form) {
        const title = selected.id ? "Cập nhật sản phẩm" : "Thêm sản phẩm";
        if (pond.getFiles().length == 0) {
            Swal.fire({
                title: "Các trường có ảnh lỗi",
                text: "Vui lòng không để trống các trường chọn ảnh!",
                icon: "warning",
            });
        } else
            Swal.fire({
                title: `Bạn có chắc muốn ${title} này vào cửa hàng không?`,
                text: "Bạn sẽ không thể hoàn nguyên điều này!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Có",
                cancelButtonText: "Không",
            }).then((result) => {
                if (result.isConfirmed) {
                    if (selected.id) {
                        const listIdSizes = $('[data-size-id]').map((index, element) => $(element).attr('data-size-id')).get();
                        const listIdColors = $('[data-color-id]').map((index, element) => $(element).attr('data-color-id')).get();
                        handleUpdate(form, selected.id, listIdSizes, listIdColors).then();
                    } else
                        handleCreate(form).then();
                }
            });
    }

    const formValidator = form.validate(configValidator)

    btnAddSize.on("click", function () {
        addSize();
    });

    createPicker("#color-input");
    btnAddColor.on("click", () => {
        addColor();
    })

    function initFileInput() {
        const inputElement = $("#image")[0];
        pond = FilePond.create(inputElement, {
            allowMultiple: true,
            allowImagePreview: true,
            allowFileRename: true,
            allowFileMetadata: true,
            labelIdle: 'Kéo và thả tệp của bạn vào đây hoặc <span class="filepond--label-action">Chọn tệp</span>',
            // fileValidateTypeDetectType: (source, type) => new Promise((resolve, reject) => {
            //     console.log(source, type)
            //     console.log("upload")
            // })
            // fileRenameFunction: (file) => {
            //     console.log(file)
            //     if (rename) {
            //         rename = false
            //         return file.name = `image_${Date.now()}`;
            //     }
            //     return file.name;
            // }
        });

        pond.on('addfile', (error, fileItem) => {
            const metadata = fileItem.getMetadata();
            if (!error) {
                if (!metadata.id) {
                    fileItem.setMetadata('index', images.added.length);
                    images.added.push({
                        folder: `product_img/${images.productId}/`,
                        name: `product_${images.productId}_${Date.now()}`,
                        file: fileItem.file,
                        fileExtension: fileItem.fileExtension,
                    });
                }
            }
            console.log(pond.getFiles())
        });
        pond.on('removefile', (error, fileItem) => {
            if (!error) {
                const metadata = fileItem.getMetadata();
                if (metadata.id)
                    images.deleted.push(metadata.id);
                else if (metadata.index != undefined)
                    images.added = images.added.filter(image => image.file != fileItem.file);
            } else
                console.log("error deleted")
            // console.log(pond.getFiles())
        });
    }

    function initTextEditor() {
        editor = new FroalaEditor('#editor', {
            language: 'vi',
            events: {
                'contentChanged': function () {
                    const content = this.html.get();
                    $("#description").val(content);
                    $("#description").valid();
                }
            }
        });
    }

    function createPicker(el, colorHex) {
        $(el).spectrum({
            color: colorHex || "#000000",
            showInput: true,
            showAlpha: true,
            preferredFormat: "hex",
            move: function (color) {
                const hexColor = color.toHexString();
                $(el).val(hexColor);
            },
        });
        if (colorHex)
            $(el).val(colorHex);
        else
            $(el).val("#000000");
    }

    function configModal() {
        modal.on("hidden.bs.modal", function () {
            resetForm();
        })
            .on("show.bs.modal", function () {
                if (selected.id)
                    handleRead(selected.id);
            });
    }

    // -------------------------------
    // Thực hiện thêm color sản phẩm (trên UI)
    function addColor(color) {
        const colorId = `color-${Date.now()}`;
        dataColorIndex.push(colorId);
        const html = $(`
         <div data-color-index=${colorId} ${color ? ("data-color-id=" + color.id) : ""} class="d-flex align-items-center gap-1 p-1 border border-1 round mb-1 me-2">
            <input id="${colorId}" name="color" hidden="hidden" type="text" >
            <div type="button"  class="color__remove btn-close" aria-label="Close">
            </div>
        </div>
        `)
        formColor.append(html);
        createPicker(`#${colorId}`, color && color.codeColor);
        html.find(".color__remove").on("click", () => {
            html.remove();
        })
    }

    // -------------------------------
    // Thực hiện thêm color sản phẩm (trên UI)
    function addSize(size) {
        const idSize = `size-${Date.now()}`;
        dataSizeIndex.push(idSize);
        const html = $(`
            <div data-size-index="${idSize}" ${size ? ("data-size-id=" + size.id) : ""} class="row align-items-center mt-2">
                <div class="col-4 form__label">
                    <input type="text" name="nameSize[]" class="form-control" value=${(size && size.nameSize) || ""}>
                    <div class="valid-feedback"> </div>
                </div>
                <div class="col-4 form__label">
                    <input type="text" name="sizePrice[]" class="form-control" value=${(size && size.sizePrice) || ""}>
                    <div class="valid-feedback"></div>
                </div>
                <div class="col-4">
                    <div class="btn btn-danger form__remove-size w-100">Xóa size</div>
                </div>
            </div>
        `);
        formSize.append(html);

        html.find(".form__remove-size").on("click", function () {
            $(this).closest("[data-size-index]").remove();
        });
    }

    // -------------------------------
    // Thực hiện reset data form
    function resetForm() {
        form.find("input, textarea, select").val("")
        editor.html.set("");
        formValidator.resetForm();
        if (dataSizeIndex.length > 0) {
            dataSizeIndex.forEach(id => {
                $(`[data-size-index=${id}]`).remove();
            });
            dataSizeIndex = [];
        }
        if (dataColorIndex.length > 0) {
            dataColorIndex.forEach(id => {
                $(`[data-color-index=${id}]`).remove();
            });
            dataColorIndex = [];
        }
        // Xóa ảnh trong filepond
        const files = pond.getFiles();
        files.forEach((file, index) => {
            pond.removeFile(index);
        });
        images.productId = undefined;
        images.exist = [];
        images.added = [];
        images.deleted = [];
    }

    // -------------------------------
    // Thực hiện ẩn hoặc hiện sản phẩm
    function handleVisible(type, id, index) {
        Swal.fire({
            title: `Bạn có muốn ${type == "visible" ? "hiện thị" : "ẩn"} sản phẩm này không?`,
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            if (result.isConfirmed) {
                http({
                    url: "/api/admin/product/visible",
                    data: {
                        id: id,
                        type: type,
                    },
                    type: "POST",
                }).then(data => {
                    if (data.success) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Cập nhập thành công ',
                        })
                        datatable.row(index).data({
                            ...datatable.row(index).data(),
                            state: type != "hide"
                        }).draw(false);
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Cập nhập thất bại',
                        })
                    }
                }).catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Có lỗi xảy ra',
                        text: error.message
                    });
                })
            }
        });
    }

    // --------------------------------
    // Thực hiện thêm sản phẩm
    async function handleCreate(form) {
        startLoading();
        const formData = new FormData(form);
        // Thêm ảnh vào FormData
        const filePondFiles = FilePond.find(document.querySelector('#image')).getFiles();
        filePondFiles.forEach(file => {
            formData.append('images[]', file.file);
        });
        try {
            const response = await uploadImage(images.added, false);
            const nameImageAdded = response.map(response => response.public_id.split('/').slice(1).join('/') + '.' + response.format);
            nameImageAdded.forEach(nameImage => {
                formData.append('nameImageAdded[]', nameImage);
            });
            http({
                url: "/api/admin/product/create",
                type: "POST",
                contentType: false,
                processData: false,
                dataType: "json",
                data: formData,
            }, false).then((data) => {
                endLoading();
                if (data.code == 200) {
                    Swal.fire({
                        title: "Thêm sản phẩm thành công",
                        text: "Sản phẩm đã được thêm vào gian hàng.",
                        icon: "success",
                    });
                    datatable.row.add(
                        {
                            ...convertFormDataToObject(form)
                        }
                    ).draw(false);
                    modal.modal("hide");
                } else {
                    Swal.fire({
                        title: "Thêm sản phẩm không thành công",
                        text: "Sản phẩm đã có tên trên đã tồn tại vào gian hàng.",
                        icon: "error",
                    });
                }
            }).catch((error) => {
                endLoading();
                Swal.fire({
                    title: "Thêm sản phẩm không thành công",
                    text: "Đã có lỗi xảy ra",
                    icon: "warning",
                });
            });
        } catch (e) {
            endLoading();
            Swal.fire({
                title: "Thêm sản phẩm không thành công",
                text: "Đã có lỗi xảy ra",
                icon: "warning",
            })
        }
    }

    // -------------------------------
    // Thực thi xem sản phẩm
    function handleRead(id) {
        http({
            url: "/api/admin/product/read",
            type: "GET",
            data: {
                id: id
            },
        }).then((response) => {
            handleFieldData(response);
        });
    }

    // -------------------------------
    // Thực thi field data sản phẩm vào form
    function handleFieldData(data) {
        const product = data.product;
        form.find("input[name=name]").val(product.name);
        form.find("select[name=idCategory]").val(product.categoryId);
        form.find("input[name=originalPrice]").val(product.originalPrice);
        form.find("input[name=salePrice]").val(product.salePrice);
        form.find("input[name=quantity]").val(product.quantity);
        form.find("input[name=discount]").val(product.discount);
        form.find("textarea[name=description]").val(product.description);
        editor.html.set(product.description);
        // Thêm size đầu tiên vào form (mỗi sản phẩm phải có ít nhất 1 size)
        form.find(`input[name="nameSize[]"]`).val(data.sizes[0].nameSize);
        form.find(`input[name="sizePrice[]"]`).val(data.sizes[0].sizePrice);
        form.find(`[data-size-id]`).attr("data-size-id", data.sizes[0].id);
        data.sizes.slice(1).forEach(size => addSize(size));
        // Thêm color đầu tiên vào form (mỗi sản phẩm phải có ít nhất 1 color)
        createPicker("#color-input", data.colors[0].codeColor);
        form.find(`[data-color-id]`).attr("data-color-id", data.colors[0].id);
        data.colors.slice(1).forEach(color => addColor(color));


        // Thêm ảnh vào filepond
        images.productId = product.id;
        const urls = data.images.map(image => {
            images.exist.push({
                id: image.id,
                nameImage: image.nameImage.lastIndexOf("/") !== -1 ? image.nameImage.split("/").pop() : image.nameImage
            });
            return image.nameImage
        });

        urls.forEach((url, index) => {
            fetch(url).then(response => response.blob()).then(blob => {
                const file = new File([blob], url.split('/').pop(), {type: blob.type});
                pond.addFile(file).then((fileItem) => {
                    fileItem.setMetadata('renamed', true);
                    fileItem.setMetadata('id', images.exist[index].id);
                    fileItem.setMetadata('nameImage', images.exist[index].nameImage);
                });
            }).catch(error => {
                console.error('Error fetching or adding file:', error);
            })
        });
    }

    // -------------------------------
    // Thực thi cập nhập sản phẩm
    async function handleUpdate(form, id, listIdSizes, listIdColors) {
        const formData = new FormData(form);
        listIdSizes.forEach(idSize => formData.append('sizeId[]', idSize));
        listIdColors.forEach(idColor => formData.append('colorId[]', idColor));
        formData.append("id", id);
        try {
            startLoading();
            if (images.added.length > 0) {
                const response = await uploadImage(images.added, false);
                const nameImageAdded = response.map(response => response.public_id.split('/').slice(1).join('/') + '.' + response.format);
                nameImageAdded.forEach(nameImage => {
                    formData.append('nameImageAdded[]', nameImage);
                });
            }

            if (images.deleted.length > 0) {
                const idImageDeleted = images.exist.filter(image => images.deleted.includes(image.id)).map(images => images.id);
                idImageDeleted.forEach(idImage => {
                    formData.append('idImageDeleted[]', idImage);
                });
            }
            const updateResponse = await http({
                url: "/api/admin/product/update",
                type: "POST",
                data: formData,
                contentType: false,
                processData: false,
                dataType: "json",
            }, false)
            endLoading();
            if (updateResponse.code == 200) {
                Swal.fire({
                    title: "Cập nhập sản phẩm thành công",
                    text: "Sản phẩm đã được cập nhập vào gian hàng.",
                    icon: "success",
                });
                // Cập nhập dòng mới vào bảng
                const objectAfterUpdate = {...convertFormDataToObject(form)}
                let dataRow, indexRow;
                datatable.rows({selected: true}).data().each(function (value, index) {
                    dataRow = value
                    indexRow = index;
                });
                const category = $(`#idCategory option[value=${objectAfterUpdate.category}]`).text();
                // console.log(dataRow, indexRow)
                // console.log(objectAfterUpdate)
                // console.log({
                //     id: id,
                //     name: objectAfterUpdate.name,
                //     category: category,
                //     originalPrice: objectAfterUpdate.originalPrice,
                //     salePrice: objectAfterUpdate.salePrice,
                //     state: dataRow.state,
                // })
                datatable.row(indexRow).data(
                    {
                        id: id,
                        name: objectAfterUpdate.name,
                        category: category,
                        originalPrice: objectAfterUpdate.originalPrice,
                        salePrice: objectAfterUpdate.salePrice,
                        state: dataRow.state,
                    }
                ).draw(false);
                modal.modal("hide");
            } else {
                Swal.fire({
                    title: "Cập nhập sản phẩm không thành công",
                    text: "Sản phẩm đã có tên trên đã tồn tại vào gian hàng.",
                    icon: "error",
                });
            }
        } catch (e) {
            endLoading();
            console.error(e);
            Swal.fire({
                title: "Cập nhập sản phẩm không thành công",
                text: "Đã có lỗi xảy ra",
                icon: "warning",
            });
        }
    }
})