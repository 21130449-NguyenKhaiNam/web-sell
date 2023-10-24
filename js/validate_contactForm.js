function ValidatorContactForm(options){
    var contactFormElement = document.querySelector(options.form);
    if(contactFormElement){
        options.rules.forEach(function (rule){
            var inputElement = contactFormElement.querySelector(rule.selector);
            var errorMessageElement = inputElement.parentElement.querySelector(options.errorSelector);
            if (inputElement){
                inputElement.onblur = function (){
                    var errorMessage = rule.test(inputElement.value);
                    if(errorMessage){
                        errorMessageElement.innerText = errorMessage;
                        errorMessageElement.style.color = '#E40F0A';
                    }else {
                        errorMessageElement.innerText = '';
                    }

                }

                inputElement.oninput = function (){
                    errorMessageElement.innerText = '';
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
            return regex.test(value) ? undefined : 'Trường này phải là email'
        }
    }
}