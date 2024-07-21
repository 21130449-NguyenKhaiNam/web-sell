import {getDistrict, getProvince, getWard} from "./shipping.js";
import {configSweetAlert2, http} from "./base.js";

const deliveryInfoRadioButtons = document.querySelectorAll(".delivery__info--container .radio__button");
const paymentMethodRadioButtons = document.querySelectorAll(".payment__method--container .radio__button");
const deliveryMethodRadioButtons = document.querySelectorAll(".delivery__method--container .radio__button");
const customizeDeliveryInfo = document.getElementById("customize__info--form");

function ValidatorCustomizeDeliveryForm(options) {
    let selectorRules = {};

    function getRightParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            } else {
                element = element.parentElement;
            }
        }
    }

    function validate(inputElement, rule, errorMessageElement) {
        let errorMessage;
        let arrayRules = selectorRules[rule.selector]
        for (let i = 0; i < arrayRules.length; i++) {
            errorMessage = arrayRules[i](inputElement.value);
            if (errorMessage) {
                break;
            }
        }

        if (errorMessage) {
            errorMessageElement.innerText = errorMessage;
            errorMessageElement.style.color = '#E40F0A';
            inputElement.classList.add('input__invalid')
        } else {
            errorMessageElement.innerText = '';
            inputElement.classList.remove('input__invalid')
        }
        return !errorMessage;
    }

    let customizeInfoForm = document.querySelector(options.form);
    if (customizeInfoForm) {
        customizeInfoForm.onsubmit = (event) => {
            event.preventDefault();
            let isFormValid = true;
            options.rules.forEach(rule => {
                let inputElement = customizeInfoForm.querySelector(rule.selector);
                let errorMessageElement = getRightParent(inputElement, options.formBlockSelector).querySelector(options.errorSelector);
                let isValid = validate(inputElement, rule, errorMessageElement);
                if (!isValid) {
                    isFormValid = false;
                }
            });

            if (isFormValid) {
                if (typeof options.onSubmit === 'function') {
                    let enableInput = customizeInfoForm.querySelectorAll('[name]:not([disabled])');
                    let formValues = Array.from(enableInput).reduce(function (values, input) {
                        values[input.name] = input.value
                        return values;
                    }, {});
                    options.onSubmit(formValues);
                } else {
                    customizeDeliveryInfo.submit();
                }
            }
        }
        options.rules.forEach(rule => {
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }
            const inputElement = customizeInfoForm.querySelector(rule.selector);
            let errorMessageElement = getRightParent(inputElement, options.formBlockSelector).querySelector(options.errorSelector);
            if (inputElement) {
                inputElement.onblur = () => {
                    validate(inputElement, rule, errorMessageElement);
                }
                inputElement.oninput = () => {
                    errorMessageElement.innerText = '';
                    inputElement.classList.remove('input__invalid')
                }
            }
        })


        options.rules.forEach(function (rule) {
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }
            let inputElement = customizeInfoForm.querySelector(rule.selector);
            let errorMessageElement = getRightParent(inputElement, options.formBlockSelector).querySelector(options.errorSelector);
            if (inputElement) {
                inputElement.onblur = function () {
                    validate(inputElement, rule, errorMessageElement);
                }
                inputElement.oninput = function () {
                    errorMessageElement.innerText = '';
                    inputElement.classList.remove('input-invalid')
                }

                inputElement.onfocus = function () {
                    errorMessageElement.innerText = '';
                }
            }
        });
    }
}

function handleDisplayDescriptionMethodOptionChecked(typeMethodRadioButtons) {
    let previousSelectedButton = null;
    typeMethodRadioButtons.forEach((typeMethodRadioButton) => {
        if (typeMethodRadioButton.checked) {
            let methodContent = typeMethodRadioButton.closest(".method__content");
            let methodItem = methodContent.querySelector('.method__item');
            let noteTypeMethodOption = methodContent.querySelector(".description__method")
            methodItem.classList.add("method__checked");
            noteTypeMethodOption.style.display = "grid";
            previousSelectedButton = typeMethodRadioButton;
        }
    })

    typeMethodRadioButtons.forEach((typeMethodRadioButton) => {
        let methodContent = typeMethodRadioButton.closest(".method__content");
        let methodItem = methodContent.querySelector('.method__item');
        let noteTypeMethodOption = methodContent.querySelector(".description__method")

        typeMethodRadioButton.addEventListener("click", () => {

            if (previousSelectedButton) {
                let methodContent = previousSelectedButton.closest(".method__content");
                let methodItem = methodContent.querySelector('.method__item');
                let noteTypeMethodOption = methodContent.querySelector(".description__method")
                noteTypeMethodOption.style.display = "none";
                methodItem.classList.remove("method__checked")
                previousSelectedButton = null
            }

            if (typeMethodRadioButton.checked) {
                noteTypeMethodOption.style.display = "grid";
                methodItem.classList.add("method__checked");
                previousSelectedButton = typeMethodRadioButton;
            }
        })
    })
}

handleDisplayDescriptionMethodOptionChecked(deliveryMethodRadioButtons);

// handleDisplayDescriptionMethodOptionChecked(paymentMethodRadioButtons);

function handleCustomizeDeliveryInfo() {
    const form = $('#customize__info--form');
    let fullNameField = form.find('input[name="fullName"]');
    let emailField = form.find('input[name="email"]');
    let phoneField = form.find('input[name="phone"]');
    let addressField = form.find('textarea[name="address"]');
    let buttonCustom = form.find('.button__custom');

    $(document).ready(function () {
        form.on('submit', function (event) {
            event.preventDefault();
            let fullName = fullNameField.val().trim();
            let email = emailField.val().trim();
            let phone = phoneField.val().trim();
            let address = addressField.val().trim();
            let action = buttonCustom.val();

            let objectData = {
                action: action,
                fullName: fullName,
                email: email,
                phone: phone,
                address: address,
            }

            let deliveryInfoKey
            if (action === 'editDeliveryInfo') {
                let deliveryInfoKeyTarget = form.find('input[name="deliveryInfoTarget"]');
                deliveryInfoKey = deliveryInfoKeyTarget.val();
                objectData.deliveryInfoKey = deliveryInfoKey;
            }

            $.ajax({
                type: "POST",
                url: "/api/checkout",
                data: objectData,
                dataType: 'json',
                success: function (response) {
                    if (response.isRegisterValid || response.isUpdateValid) {
                        let duplicateError = response.duplicateError;
                        if (duplicateError !== undefined && duplicateError !== null) {
                            alert(duplicateError);
                        } else {
                            $('.popup__bg').css('display', 'none');
                            if (action === 'addDeliveryInfo') {
                                let deliInfoKey = response.deliInfoKey;
                                let newDeliveryInfo = `
                                            <div class="delivery__info">
                                                <input data-customer-name="${fullName}"
                                                       data-customer-email="${email}"
                                                       data-customer-phone="${phone}"
                                                       data-customer-address="${address}" 
                                                       type="hidden" name="deliveryInfoKey" value="${deliInfoKey}">
                                                <div class="info__header">
                                                    <h3>Giao tới <i class="fa-solid fa-turn-down"></i></h3>
                                                    <span class="edit__delivery" onclick="showCustomizeDeliveryInfoForm(this, 'Chỉnh sửa thông tin giao hàng')">Chỉnh sửa</span>
                                                </div>
                                                <ul class="info__items">
                                                    <li class="info__item customer__name">${fullName}</li>
                                                    <li class="info__item">Email: ${email}</li>
                                                    <li class="info__item">Số điện thoại: ${phone}</li>
                                                    <li class="info__item">Địa chỉ: ${address}</li>
                                                </ul>
                                                <div class="choice__remove">
                                                    <button type="submit" class="button__choice" name="typeEdit" value="choiceDeliveryInfo">Chọn</button>
                                                    <button type="submit" class="button__remove" name="typeEdit" value="removeDeliveryInfo">Xóa</button>
                                                </div>
                                            </div>`;
                                $('#delivery__info--form').append(newDeliveryInfo);
                            } else if (action === 'editDeliveryInfo') {
                                let deliveryInfoKeyTarget = $(document).find('input[name=deliveryInfoKey][value="' + deliveryInfoKey + '"]');
                                deliveryInfoKeyTarget.data('customerName', response.newFullName)
                                deliveryInfoKeyTarget.data('customerEmail', response.newEmail)
                                deliveryInfoKeyTarget.data('customerPhone', response.newPhone)
                                deliveryInfoKeyTarget.data('customerAddress', response.newAddress)
                                let infoItems = deliveryInfoKeyTarget.closest(".delivery__info").find('.info__items');
                                let newInfoItemsContent = `
                                                                    <li class="info__item customer__name">${response.newFullName}</li>
                                                                    <li class="info__item">Email: ${response.newEmail}</li>
                                                                    <li class="info__item">Số điện thoại: ${response.newPhone}</li>
                                                                    <li class="info__item">Địa chỉ: ${response.newAddress}</li>
                                                                `;
                                infoItems.html(newInfoItemsContent);
                            }
                        }
                    } else {
                        $.each(response.errorFields, function (errorField, errorMessage) {
                            errorField = `#${errorField}`;
                            $(errorField).text(errorMessage).show();
                            $(errorField).parent().find('.form__input').addClass('input-invalid');
                        });
                    }
                },
            })
        })
    })
}

handleCustomizeDeliveryInfo();

function showCustomizeDeliveryInfoForm(elementOpenForm, title) {
    document.querySelectorAll('.field__content').forEach(fieldContent => {
        fieldContent.classList.remove("input-invalid");
        fieldContent.parentElement.querySelector('.error__notice').style.display = 'none'
    })

    const fieldFullName = document.querySelector('.field__content#fullName');
    const fieldEmail = document.querySelector('.field__content#email');
    const fieldPhone = document.querySelector('.field__content#phone');
    const fieldAddress = document.querySelector('.field__content#address');
    document.querySelector('.button__custom').value = 'addDeliveryInfo';

    fieldFullName.value = '';
    fieldEmail.value = '';
    fieldPhone.value = '';
    fieldAddress.value = '';

    document.querySelector('.popup__bg').style.display = 'flex';
    document.querySelector('.form__header .form__title').innerText = title;
    document.querySelector(".button__custom").innerText = title;

    if (elementOpenForm.classList.contains("edit__delivery")) {
        const deliveryInfoTarget = elementOpenForm.closest('.delivery__info');
        let datasetDelivery = deliveryInfoTarget.querySelector('input[type=hidden][name=deliveryInfoKey]')
        document.querySelector('.button__custom').value = 'editDeliveryInfo';

        let customerName = datasetDelivery.dataset.customerName;
        let customerEmail = datasetDelivery.dataset.customerEmail;
        let customerPhone = datasetDelivery.dataset.customerPhone;
        let customerAddress = datasetDelivery.dataset.customerAddress;
        let deliveryInfoKey = datasetDelivery.value;
        document.querySelector("input[type=hidden][name=deliveryInfoTarget]").value = deliveryInfoKey;

        fieldFullName.value = customerName;
        fieldEmail.value = customerEmail;
        fieldPhone.value = customerPhone;
        fieldAddress.value = customerAddress;
    }


    document.querySelector('.button__close').addEventListener('click', function () {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.button__cancel').addEventListener('click', function () {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.button__close').addEventListener('click', function () {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.popup__bg').addEventListener('click', function (event) {
        if (event.target === this) {
            document.querySelector('.popup__bg').style.display = 'none';
        }
    });
}

function handleRemoveErrorInputting() {
    let fieldContents = document.querySelectorAll('.field__content');
    fieldContents.forEach(fieldContent => {
        fieldContent.addEventListener('input', () => {
            fieldContent.classList.remove('input-invalid');
            fieldContent.parentElement.querySelector('.error__notice').innerText = '';
        })
    })
}

handleRemoveErrorInputting();

function handleAddress() {
    const modalAddress = $("#modal-address");
    const provinceInput = $("#inputProvince");
    const districtInput = $("#inputDistrict");
    const wardInput = $("#inputWard");

    setupAddress();

    function setupAddress() {
        const configSelect2 = {
            width: '100%',
            closeOnSelect: true,
            allowClear: true,
            language: 'vi',
            dropdownParent: modalAddress,
            data: [],
        };

        provinceInput.select2({
            ...configSelect2,
            placeholder: 'Chọn tỉnh/thành phố',
        })

        districtInput.select2({
            ...configSelect2,
            placeholder: 'Chọn quận/huyện',
        });

        wardInput.select2({
            ...configSelect2,
            placeholder: 'Chọn phường/xã',
        });
        const validator = handleFormAddress();
        handleProvince();

        modalAddress.on('hidden.bs.modal', function () {
            resetProvince();
            validator.resetForm();
        });

    }

    function handleProvince() {
        getProvince().then(province => {
            province.forEach(item => {
                const newOption = new Option(item.text, item.id);
                provinceInput.append(newOption)
            })
            provinceInput.on("change", () => {
                const selectedValue = provinceInput.val();
                handleDistrict(selectedValue);
            })
        });
    }

    function handleDistrict(id) {
        getDistrict(id).then(district => {
            resetDistrict();
            district.forEach(item => {
                const newOption = new Option(item.text, item.id);
                districtInput.append(newOption)
            });
            districtInput.on("change", () => {
                const selectedValue = districtInput.val();
                handleWard(selectedValue);
            })
        });
    }


    function handleWard(id) {
        getWard(id).then(ward => {
            resetWard();
            ward.forEach(item => {
                const newOption = new Option(item.text, item.id);
                wardInput.append(newOption)
            })
        })

    }

    function resetProvince() {
        provinceInput.empty().append(new Option('', ''));
        resetDistrict();
    }

    function resetDistrict() {
        districtInput.empty().append(new Option('', ''));
        resetWard();
    }

    function resetWard() {
        wardInput.empty().append(new Option('', ''));
    }

    function handleFormAddress() {
        const addressValidator = {
            rules: {
                province: {
                    required: true,
                },
                district: {
                    required: true,
                },
                ward: {
                    required: true,
                },
                detail: {
                    required: true,
                }
            },
            messages: {
                province: {
                    required: "Vui lòng chọn tỉnh/thành phố",
                },
                district: {
                    required: "Vui lòng chọn quận/huyện",
                },
                ward: {
                    required: "Vui lòng chọn phường/xã",
                },
                detail: {
                    required: "Vui lòng nhập địa chỉ chi tiết",
                }
            },
            onkeyup: function (element) {
                $(element).valid();
            },
            onfocusout: function (element) {
                $(element).valid();
            },
            onblur: function (element) {
                $(element).valid();
            },
            validClass: 'is-valid',
            errorClass: 'is-invalid',
            errorPlacement: function (error, element) {
                $(element).parent().children().last().text(error.text());
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
                $(element).parent().children().last().addClass("invalid-feedback");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
                $(element).parent().children().last().text("");
            },
            submitHandler: function (form) {
                const formDataArray = $(form).serializeArray();
                formDataArray.push(
                    {
                        name: "provinceName",
                        value: provinceInput.find("option:selected").text()
                    },
                    {
                        name: "districtName",
                        value: districtInput.find("option:selected").text()
                    },
                    {
                        name: "wardName",
                        value: wardInput.find("option:selected").text()
                    },
                    {
                        name: "action",
                        value: "create"
                    }
                );

                const formData = $.param(formDataArray);
                Swal.fire({
                    ...configSweetAlert2,
                    title: "Bạn có muốn thêm địa chỉ này không?",
                    showDenyButton: true,
                    confirmButtonText: "Lưu",
                    denyButtonText: "Không lưu",
                }).then((result) => {
                    /* Read more about isConfirmed, isDenied below */
                    if (result.isConfirmed) {
                        http({
                            url: "/api/user/address",
                            type: 'POST',
                            data: formData,
                        }).then(response => {
                            if (response.status) {
                                Swal.fire({
                                    title: "Chúc mừng!",
                                    text: "Địa chỉ mới đã được thêm",
                                    icon: "success"
                                });
                            } else {
                                Swal.fire({
                                    title: "Lỗi!",
                                    text: "Địa chỉ mới thêm không thành công",
                                    icon: "error"
                                });
                            }
                            addDataToList({
                                province: provinceInput.find("option:selected").text(),
                                district: districtInput.find("option:selected").text(),
                                ward: wardInput.find("option:selected").text(),
                                detail: formDataArray.find(item => item.name === "detail").value,
                                id: response.id
                            })
                            modalAddress.modal("hide")
                        });
                    }
                }).catch(e => {
                    modalAddress.modal("hide")
                    Swal.fire({
                        title: "Lỗi!",
                        text: "Địa chỉ mới thêm không thành công",
                        icon: "error"
                    });
                });
            }
        }
        return $("#form-address").validate(addressValidator)
    }

    function addDataToList({province, district, ward, detail, id}) {
        const html = ` <div class="col-sm-12 mb-3">
                                    <input type="hidden" name="id-address" value="${id}"/>
                                    <div class="card">
                                        <div class="card-body focus__address "
                                             onclick="selectCard(this)" style="cursor: pointer">
                                            <h5 class="card-title">Địa chỉ giao hàng</h5>
                                            <p class="card-text">${detail}, ${ward}, ${district}, ${province}</p>
                                        </div>
                                    </div>
                                </div>`;
        $("#address-list").append(html);
    }
}

handleAddress();