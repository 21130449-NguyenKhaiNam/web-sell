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

function handleDisplayDescriptionMethodOptionChecked(typeMethodRadioButtons){
    let previousSelectedButton = null;
    typeMethodRadioButtons.forEach((typeMethodRadioButton) =>{
        if(typeMethodRadioButton.checked){
            let methodContent = typeMethodRadioButton.closest(".method__content");
            let methodItem = methodContent.querySelector('.method__item');
            let noteTypeMethodOption = methodContent.querySelector(".description__method")
            methodItem.classList.add("method__checked");
            noteTypeMethodOption.style.display = "grid";
            previousSelectedButton = typeMethodRadioButton;
        }
    })

    typeMethodRadioButtons.forEach((typeMethodRadioButton) =>{
        let methodContent = typeMethodRadioButton.closest(".method__content");
        let methodItem = methodContent.querySelector('.method__item');
        let noteTypeMethodOption = methodContent.querySelector(".description__method")

        typeMethodRadioButton.addEventListener("click", ()=>{

            if(previousSelectedButton){
                let methodContent = previousSelectedButton.closest(".method__content");
                let methodItem = methodContent.querySelector('.method__item');
                let noteTypeMethodOption = methodContent.querySelector(".description__method")
                noteTypeMethodOption.style.display = "none";
                methodItem.classList.remove("method__checked")
                previousSelectedButton = null
            }

            if(typeMethodRadioButton.checked){
                noteTypeMethodOption.style.display = "grid";
                methodItem.classList.add("method__checked");
                previousSelectedButton = typeMethodRadioButton;
            }
        })
    })
}
handleDisplayDescriptionMethodOptionChecked(deliveryMethodRadioButtons);
handleDisplayDescriptionMethodOptionChecked(paymentMethodRadioButtons);

function handleCustomizeDeliveryInfo(){
    const form = $('#customize__info--form');
    let fullNameField = form.find('input[name="fullName"]');
    let emailField = form.find('input[name="email"]');
    let phoneField = form.find('input[name="phone"]');
    let addressField = form.find('textarea[name="address"]');
    let buttonCustom = form.find('.button__custom');

    $(document).ready(function (){
        form.on('submit', function (event){
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
            if(action === 'editDeliveryInfo'){
                let deliveryInfoKeyTarget = form.find('input[name="deliveryInfoTarget"]');
                deliveryInfoKey = deliveryInfoKeyTarget.val();
                objectData.deliveryInfoKey = deliveryInfoKey;
            }

            $.ajax({
                type: "POST",
                url: "/api/checkout",
                data: objectData,
                dataType: 'json',
                success: function (response){
                    if(response.isRegisterValid || response.isUpdateValid){
                        let duplicateError = response.duplicateError;
                        if(duplicateError !== undefined && duplicateError !== null){
                            alert(duplicateError);
                        }else {
                            $('.popup__bg').css('display', 'none');
                            if(action === 'addDeliveryInfo'){
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
                            }
                            else if(action === 'editDeliveryInfo'){
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
                    }else {
                        $.each(response.errorFields, function(errorField, errorMessage) {
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

function showCustomizeDeliveryInfoForm(elementOpenForm, title){
    document.querySelectorAll('.field__content').forEach(fieldContent =>{
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

    if(elementOpenForm.classList.contains("edit__delivery")){
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


    document.querySelector('.button__close').addEventListener('click', function() {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.button__cancel').addEventListener('click', function() {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.button__close').addEventListener('click', function() {
        document.querySelector('.popup__bg').style.display = 'none';
    });

    document.querySelector('.popup__bg').addEventListener('click', function(event) {
        if (event.target === this) {
            document.querySelector('.popup__bg').style.display = 'none';
        }
    });
}

function handleRemoveErrorInputting(){
    let fieldContents = document.querySelectorAll('.field__content');
    fieldContents.forEach(fieldContent=>{
        fieldContent.addEventListener('input',()=>{
            fieldContent.classList.remove('input-invalid');
            fieldContent.parentElement.querySelector('.error__notice').innerText = '';
        })
    })
}
handleRemoveErrorInputting();