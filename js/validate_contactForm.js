function ValidatorContactForm(options){

}

ValidatorContactForm.isRequired = function (selector){
    return {
        selector: selector,
        test: function (value){
            return value ? undefined : 'Vui lòng bạn nhập trường này'
        }
    }
}

ValidatorContactForm.isEmail = function (selector){

}