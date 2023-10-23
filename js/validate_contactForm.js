function ValidatorContactForm(options){
    var contactFormElement = document.querySelector(options.form);
    if(contactFormElement){
        options.rules.forEach(function (rule){
            var inputElement = contactFormElement.querySelector(rule.selector);
            var errorElement = inputElement.parentElement.querySelector('.error-notice')

            if (inputElement){
                inputElement.onblur = function (){
                    var errorMessage = rule.test(inputElement.value);
                    if(errorMessage){
                        errorElement.innerText = errorMessage;
                    }
                }
            }
        });
    }
}

ValidatorContactForm.isRequired = function (selector){
    return {
        selector: selector,
        test: function (value){
            return value ? undefined : 'Vui lòng nhập họ và tên của bạn'
        }
    }
}

ValidatorContactForm.isEmail = function (selector){
    return {
        selector: selector,
        test: function (){

        }
    }
}