import {
    addKeyFormData,
    addParam,
    convertFormDataToObject,
    deleteKeyFormData, endLoading,
    getValuesOfKeyFormData,
    http, startLoading
} from "../base.js";
import {uploadImage, fetchImage} from "../uploadImage.js";

$(document).ready(function () {
    // Enable tooltip bootstrap
    $('[data-bs-toggle="tooltip"]').tooltip();

    const configValidator = {
        ignore: [],
        rules: {
            nameCategory: {
                required: true,
            },
            'nameParameter[]': {
                required: true,
            },
            'unit[]': {
                required: true,
            },
            'minValue[]': {
                required: true,
            },
            'maxValue[]': {
                required: true,
            },
        },
        messages: {
            nameCategory: {
                required: "Vui lòng nhập tên sản phẩm",
            },
            'nameParameter[]': {
                required: "Vui lòng nhập tên tham số",
            },
            'unit[]': {
                required: "Vui lòng nhập đơn vị tính",
            },
            'minValue[]': {
                required: "Vui lòng nhập giá trị tối thiểu",
            },
            'maxValue[]': {
                required: "Vui lòng nhập giá trị tối đa",
            },
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
            handleFormSubmit(form);
        }
    }

    const form = $("#form");
    const formValidator = form.validate(configValidator)
    const formParameter = $(`#parameter-form`)
    let dataIndexParameter = [];
    const modal = $("#modal");
    const table = $("#table");
    let selected = undefined;
    const filePondCollect = {
        category: undefined,
        parameters: [],
    }
    const configDataTable = {
        autoWidth: false,
        paging: true,
        page: 1,
        pageLength: 5,
        lengthChange: false,
        searching: false,
        ordering: false,
        ajax: {
            url: "/api/admin/categories",
            dataSrc: "",
            dataType: "json"
        },
        columns: [
            {
                data: "id",
                width: "10%"
            },
            {data: "nameType", width: "30%"},
            {
                data: "sizeTableImage", render: function (data, type, row) {
                    return `
                    <a href="${data}" data-lightbox="${data}" data-title="My caption">
                        <img src="${data}" width="100%", height="150px">
                    </a>
                    `
                },
                width: "50%",
            },
            {
                data: "id", render: function (data, type, row) {
                    return `<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal" data-id="${data}">Sửa</button>`
                },
                width: "10%%"
            }
        ],
        createdRow: function (row, data, dataIndex) {
        },
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
        },
        initComplete: function (settings, json) {
            init();
        }
    }

    const datatable = table.DataTable(configDataTable);

    function addParameter(parameter) {
        const {name, unit, minValue, maxValue} = parameter || {};
        const parameterId = `parameter-${Date.now()}`;
        dataIndexParameter.push(parameterId)
        const html = $(`<div data-parameter-index="${parameterId}" class="row border border-1 rounded mt-3 py-3 px-1 position-relative">
                                <button type="button" class="btn-close position-absolute  top-0 end-0" aria-label="Close"></button>
                                <div class="col-12">
                                    <label for="nameParameter" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Tên tham số, ví dụ: dài áo, ngang vai,...">Tên tham số </label>
                                    <input type="text" class="form-control" name="nameParameter[]" id="nameParameter"
                                           value="${name || ""}">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="unit" class="form-label" data-bs-toggle="tooltip" data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối đa">Đơn vị tính
                                        toán </label>
                                    <input type="text" class="form-control" name="unit[]" id="unit" value="${unit || ""}">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="minValue" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối thiểu">Giá trị tổi
                                        thiểu</label>
                                    <input type="text" class="form-control" name="minValue[]" id="minValue" value="${minValue || ""}">
                                     <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-4 mt-2">
                                    <label for="maxValue" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Đơn vị của giá trị tối thiểu và giá trị tối đa">Giá trị tổi
                                        đa</label>
                                    <input type="text" class="form-control" name="maxValue[]" id="maxValue" value="${maxValue || ""}">
                                     <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-12 mt-2" >
                                    <label for="" class="form-label" data-bs-toggle="tooltip"
                                           data-bs-placement="top"
                                           data-bs-title="Ảnh hướng dẫn may đo cho thông số, chỉ được chọn 1 ảnh">Ảnh hướng dẫn may đo</label>
                                    <input type="file" name="guideImg[]">
                                      <div class="valid-feedback">

                                    </div>
                                </div>
                            </div>`)
        formParameter.append(html);
        const fileInput = html.find("input[type='file']");
        addFilePondParameter({id: parameterId, fileInput: fileInput});
        html.find("button").on("click", function () {
            removeFilePondParameter(parameterId);
            html.remove();
        })
    }

    function addFilePondParameter({id, fileInput}) {
        filePondCollect.parameters.push({
            id: id,
            findPond: configFilePond(fileInput)
        })
        console.log(filePondCollect)
    }

    function removeFilePondParameter(id) {
        const index = filePondCollect.parameters.findIndex(p => p.id == id);
        if (index !== -1) {
            filePondCollect.parameters[index].findPond.destroy();
            filePondCollect.parameters.splice(index, 1);
        }
        console.log(filePondCollect)
    }

    function configModal() {
        modal.on("hidden.bs.modal", function () {
            resetForm();
        })
        modal.on("show.bs.modal", function (e) {
            const button = e.relatedTarget
            const id = $(button).data("id")
            if (id)
                getDetail(id);
        })
    }

    function configFilePond(fileInput) {
        const filePond = FilePond.create($(fileInput)[0], {
                allowImagePreview: true,
                allowFileRename: true,
                allowFileMetadata: true,
                maxFiles: 1,
                labelIdle: 'Kéo và thả tệp của bạn vào đây hoặc <span class="filepond--label-action">Chọn tệp</span>',

            }
        );
        // filePond.on("addfile", (error, fileItem) => {
        //     console.log(selected)
        //     if (selected) {
        //         const file = fileItem.file;
        //         const fileExtension = file.name.split('.').pop();
        //         const newName = `${Date.now()}.${fileExtension}`;
        //         const newFile = new File([file], newName, {type: file.type});
        //
        //         filePond.removeFiles()
        //         filePond.addFile(newFile).then(newFileItem => {
        //             newFileItem.setMetadata("exist", true);
        //             console.log(`File renamed and added as: ${newName}`);
        //         });
        //     }
        // });

        return filePond;
    }

    function init() {
        $("#btn-add-parameter").on("click", function () {
            addParameter();
        })
        configModal();
        filePondCollect.category = configFilePond("input#sizeTableImage");
        addFilePondParameter({id: "0", fileInput: "input#guideImg"})
    }

    function validateImage() {
        const imageCategory = filePondCollect.category.getFiles();
        if (imageCategory.length == 0) return false;
        filePondCollect.parameters.forEach(item => {
            if (item.findPond.getFiles().length == 0) return false;
        });
        return true;
    }

    function handleFormSubmit(form) {
        if (!validateImage())
            Swal.fire({
                title: "Các trường có ảnh lỗi",
                text: "Vui lòng không để trống các trường chọn ảnh!",
                icon: "warning",
            });
        else {
            const title = selected ? "cập nhật sản phẩm" : "thêm sản phẩm";
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
                    if (selected) {
                        // handleUpdate(form, selected);
                    } else {
                        handleCreate(form);
                    }
                }
            });
        }
    }

    function getDetail(id) {
        http({
            url: "/api/admin/category/get",
            type: "GET",
            data: {
                id: id
            }
        }).then(function (data) {
            fieldData(id, data);
        }).catch(function (error) {
            //error
            console.log(error)
        });
    }

    function fieldData(id, data) {
        const category = data.category;
        const parameters = data.parameters;
        form.find("#nameCategory").val(category.nameType);
        const urlCategory = category.sizeTableImage;

        // thêm ảnh cho category
        fetchImage(urlCategory).then((file) => {
            filePondCollect.category.addFile(file).then((fileItem) => {
            });
        }).catch(error => {
            console.error('Error fetching or adding file:', error);
        });

        // Thêm ảnh cho các parameter

        const firstParameter = parameters[0];
        form.find("#nameParameter").val(firstParameter.name);
        form.find("#unit").val(firstParameter.unit);
        form.find("#minValue").val(firstParameter.minValue);
        form.find("#maxValue").val(firstParameter.maxValue);

        // Thêm ảnh cho parameter đầu tiên
        fetchImage(firstParameter.guideImg).then((file) => {
            filePondCollect.parameters[0].findPond.addFile(file).then((fileItem) => {
                console.log("added", fileItem.getMetadata())
                fileItem.setMetadata("id", firstParameter.id);
                fileItem.setMetadata("exist", true);
            });
            filePondCollect.parameters[0].id = firstParameter.id;
        });

        // Thêm ảnh cho các parameter còn lại
        if (parameters.length > 1)
            parameters.slice(1).forEach((item, index) => {
                addParameter(item);
                fetchImage(item.guideImg).then((file => {
                    filePondCollect.parameters[index + 1].findPond.addFile(file).then((fileItem) => {
                        fileItem.setMetadata("id", item.id);
                        fileItem.setMetadata("exist", true);
                    });
                })).catch(error => {
                    console.error('Error fetching or adding file:', error);
                });
            })
    }

    function fetchImage(url) {
        return fetch(url)
            .then(response => response.blob()).then(blob => (new File([blob], url.split('/').pop(), {type: blob.type})))
    }

    function resetForm() {
        selected = undefined
        form.find("input, textarea, select").val("")
        formValidator.resetForm();
        if (dataIndexParameter.length > 0) {
            dataIndexParameter.forEach(id => {
                $(`[data-parameter-index=${id}]`).remove();
            });
            dataIndexParameter = [];
        }
        filePondCollect.category.removeFiles();
        filePondCollect.parameters = filePondCollect.parameters.slice(0, 1)
        filePondCollect.parameters[0].findPond.removeFiles();
    }

    async function handleCreate(form) {
        startLoading();
        const formData = new FormData(form);
        const fileCollect = {};
        fileCollect.category = filePondCollect.category.getFile().file;
        fileCollect.parameters = filePondCollect.parameters.map((item) => (
            {
                id: item.id,
                file: item.findPond.getFile().file
            }
        ));
        const response = await uploadImage([{
            folder: "size_table",
            name: Date.now(),
            file: fileCollect.category
        }], false);
        const nameImageCategory = response.map(response => response.public_id.split('/')[1] + "." + response.format)[0];
        const fileImageParameters = fileCollect.parameters.map((item) => ({
            folder: "parameter_guide",
            name: Date.now(),
            file: item.file
        }))
        const nameImageParameters = await uploadImage(fileImageParameters, false);
        deleteKeyFormData(formData, "sizeTableImage");
        deleteKeyFormData(formData, "guideImg[]");
        addKeyFormData(formData, "sizeTableImage", nameImageCategory)
        nameImageParameters.forEach((item, index) => {
            const nameImage = item.public_id.split('/')[1] + "." + item.format;
            addKeyFormData(formData, "guideImg[]", nameImage);
        });


        http({
            url: "/api/admin/category/create",
            type: "POST",
            dataType: "json",
            contentType: false,
            processData: false,
            data: formData,
        }, false).then(function (data) {
            endLoading();
            if (data.code == 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'Thêm thể loại thành công',
                    showConfirmButton: false,
                    timer: 1500
                })
                datatable.ajax.reload();
                modal.modal("hide");
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Thêm thể loại thất bại',
                    messages: "Vui lòng kiểm tra lại thông tin đã nhập",
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }).catch(function (error) {
            endLoading();
            Swal.fire({
                icon: 'error',
                title: 'Thêm thể loại thất bại',
                messages: "Vui lòng kiểm tra lại thông tin đã nhập",
                showConfirmButton: false,
                timer: 1500
            })
        });
    }

    async function handleUpdate(form, {id, data}) {
        const formData = new FormData(form);
        startLoading();
        // Upload image
        try {
            const imageExist = {
                category: {
                    id: data.category.id,
                    image: getNameFromURL(data.category.sizeTableImage),
                },
                parameters: data.parameters.map(parameter => ({
                    id: parameter.id,
                    image: getNameFromURL(parameter.guideImg),
                }))
            }
            const imageUploaded = [];
            const imageDeleted = [];

            // Image cho category
            const categoryFile = filePondCollect.category.getFile().file;
            if (categoryFile.getFile().file.name != imageExist.category.image) {
                imageUploaded.push({
                    id: imageExist.category.id,
                    folder: "parameter_guide",
                    name: imageExist.category.image,
                    file: filePondCollect.category.file,
                })
                imageDeleted.push({
                    id: imageExist.category.id,
                    folder: "parameter_guide",
                    name: imageExist.category.image
                })
            }

            // Image cho parameters
            filePondCollect.parameters.forEach((item, index) => {
                const file = item.getFile().file;
                const parameterId = file.getMetadata().id;
                if (file.name != imageExist.parameters[index].image) {
                    imageUploaded.push({
                        folder: "parameter_guide",
                        name: Date.now(),
                        file: file,
                        id: parameterId,
                    })
                    imageDeleted.push({
                        folder: "parameter_guide",
                        name: Date.now(),
                        file: file,
                        id: parameterId,
                    })
                }
            })

            const responseCategoryUpload = await uploadImage(imageUploaded, false);
            const nameImageCategory = responseCategoryUpload.map(response => response.public_id.split('/')[1] + "." + response.format)[0];
            const nameImageParameters = responseCategoryUpload.map(response => response.public_id.split('/')[1] + "." + response.format);

            const dataUpdate = getObjUpdate(form);
            dataUpdate.sizeTableImage = nameImageCategory;
            dataUpdate.parameters.forEach((parameter, index) => {
                parameter.guideImg = nameImageParameters[index];

            });
        } catch (e) {

        }
        // http({
        //     url: "/api/admin/category/update",
        //     type: "POST",
        //     dataType: "json",
        //     contentType: false,
        //     processData: false,
        //     data: formData,
        // }, false).then(function (data) {
        //     endLoading();
        //     if (data.code == 200) {
        //         Swal.fire({
        //             icon: 'success',
        //             title: 'Cập nhật thể loại thành công',
        //             showConfirmButton: false,
        //             timer: 1500
        //         })
        //         datatable.ajax.reload();
        //         modal.modal("hide");
        //     } else {
        //         Swal.fire({
        //             icon: 'error',
        //             title: 'Cập nhật thể loại thất bại',
        //             messages: "Vui lòng kiểm tra lại thông tin đã nhập",
        //             showConfirmButton: false,
        //             timer: 1500
        //         })
        //     }
        // }).catch(function (error) {
        //     endLoading();
        //     Swal.fire({
        //         icon: 'error',
        //         title: 'Cập nhật thể loại thất bại',
        //         messages: "Vui lòng kiểm tra lại thông tin đã nhập",
        //         showConfirmButton: false,
        //         timer: 1500
        //     })
        // });
    }


    function getObjUpdate(form) {
        // {nameCategory: "Áo", nameParameter: ["dài áo", "ngang vai"], unit: ["cm", "cm"], minValue: ["10", "20"], maxValue: ["20", "30"]}
        const result = convertFormDataToObject(form);
        const parameters = result['nameParameter[]'].map((item, index) => ({
            id: result['idParameter[]'][index] || null,
            name: item,
            unit: result['unit[]'][index],
            minValue: result['minValue[]'][index],
            maxValue: result['maxValue[]'][index],
        }))
        result.parameters = parameters;
        delete result['guideImg[]'];
        delete result['nameParameter[]'];
        delete result['unit[]'];
        delete result['minValue[]'];
        delete result['maxValue[]'];
        delete result['idParameter[]'];
        return result;
    }

    function getNameFromURL(url) {
        // Split the URL by slashes to get the parts
        let parts = url.split('/');
        // Get the last part (the filename with extension)
        let filenameWithExtension = parts[parts.length - 1];
        // Split the filename by dots to separate the name and extension
        let filenameParts = filenameWithExtension.split('.');
        // The name will be all parts except the last one (which is the extension)
        let name = filenameParts.slice(0, -1).join('.');
        // The extension is the last part
        let extension = filenameParts[filenameParts.length - 1];

        return name + "." + extension;
    }
})
