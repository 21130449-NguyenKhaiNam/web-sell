function ValidatorContactForm(options){
    var selectorRules = {};
    function validate(inputElement, rule, errorMessageElement){
        var errorMessage;

        //Lấy ra các rules của selector
        var arrayRules = selectorRules[rule.selector];

        //Lập qua từng rule và kiểm tra
        //Nếu có lỗi thì bắt lỗi ngay và dừng việc kiểm tra
        for(var i = 0; i < arrayRules.length; i++){
            errorMessage = arrayRules[i](inputElement.value);
            if(errorMessage){
                break;
            }
        }

        if(errorMessage){
            errorMessageElement.innerText = errorMessage;
            errorMessageElement.style.color = '#E40F0A';
            inputElement.classList.add('input-invalid')
        }else {
            errorMessageElement.innerText = '';
            inputElement.classList.remove('input-invalid')
        }
    }

    var contactFormElement = document.querySelector(options.form);
    if(contactFormElement){
        options.rules.forEach(function (rule){
            var inputElement = contactFormElement.querySelector(rule.selector);
            var errorMessageElement = inputElement.parentElement.querySelector(options.errorSelector);
            if (inputElement){
                inputElement.onblur = function (){
                    validate(inputElement,rule,errorMessageElement);
                }
                inputElement.oninput = function (){
                    errorMessageElement.innerText = '';
                    inputElement.classList.remove('input-invalid')
                }
            }
        });
    }
}

ValidatorContactForm.isRequired = function (selector){
    return {
        selector: selector,
        test: function (value){
            return value.trim() ? undefined : 'Vui lòng bạn nhập trường này'
        }
    }
}

ValidatorContactForm.isEmail = function (selector){
    return {
        selector: selector,
        test: function (value){
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Thông tin bạn nhập không phải là email'
        }
    }
}