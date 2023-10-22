function Validation(formObj) {
    var formElement = document.querySelector(formObj.formSelector);
    var rules = formObj.rules;
    var submitElement = document.querySelector(formObj.submitSelector);
    var ruleFuncs = {};
    var errorMessageObj = {};

    // Chạy qua rules để lấy các yêu cầu để thực thi validate
    if (formElement) {
        rules.forEach(function (rule) {
            const inputElement = formElement.querySelector(rule.element);
            /*
            -Do mỗi input sẽ có nhiều yêu cầu validate nên ruleFuncs sẽ là 1 một obj gồm có:
            -Mỗi key sẽ là id của input
            -Mỗi value tương ứng với key sẽ là một array để chứa các
            yêu cầu validate hay chính là các function thực thi validate
            (các hàm test)
            Ex:
            username : [isRequired]
            email: [isEmail, isRequired]
            */
            if (!Array.isArray(ruleFuncs[inputElement.id])) {
                ruleFuncs[inputElement.id] = [rule.check];
            } else {
                ruleFuncs[inputElement.id].push(rule.check);
            }

            inputElement.oninput = function (e) {
                handleValidate(inputElement);
            }
            inputElement.onblur = function (e) {
                handleValidate(inputElement);
            }
        });
    }
    if (submitElement) {
        // Chặn việc gửi thông tin của form khi nhấn submit
        submitElement.submit = function (e) {
            e.preventDefault();
        };
        submitElement.onclick = function () {
            rules.forEach(function (rule) {
                const inputElement = formElement.querySelector(rule.element);
                handleValidate(inputElement);
            });

            //Chỉ được thực thi không form ko có Error Message
            if (Object.keys(errorMessageObj).length == 0) {
               formObj.funcAfterSubmit();
               return false;
            }
        }
    }

    function handleValidate(inputElement) {
        const value = inputElement.value;
        const formBlock = getParent(inputElement, formObj.formBlockClass);
        const showError = formBlock.querySelector(formObj.errorSelector);

        let errorMessage;
        const ruleFunc = ruleFuncs[inputElement.id];
        /*Loop qua từng func của id tương ứng
        Ex:
        username : [isRequired] -> Loop qua 1 lần
        email: [isEmail, isRequired] -> Loop qua 2 lần
        */
        for (let i = 0; i < ruleFunc.length; i++) {
            errorMessage = ruleFunc[i](value);//Chạy các hàm test
            if (errorMessage) {
                errorMessageObj[inputElement.id] = errorMessage;
                break;
            } else {
                (errorMessageObj[inputElement.id]) ? delete errorMessageObj[inputElement.id] : undefined;
            }
        }
        if (errorMessage) {
            formBlock.classList.add("invalid");
            showError.innerText = errorMessage;
        } else {
            formBlock.classList.remove("invalid");
            showError.innerText = "";
        }
    }
}

/*
* Quy tắc hiện thực các hàm validate
* Input: bắt buộc phải có selectorInput (selector của thẻ input)
* Output: trả về obj gồm element của selectorInput và function check (thực thi việc validate cho selectorInput)
* Function check sẽ trả về undefined nếu validate, nếu không validate thì trả về lỗi
* */
Validation.isRequired = function (selectorInput) {
    return {
        element: selectorInput,
        check: function (value) {
            return value.trim() ? undefined : "Trường này không được để trống";
        },
    }
}
Validation.isEmail = function (selectionInput) {
    return {
        element: selectionInput,
        check: function (value) {
            return value.includes("@") ? undefined : "Email không hợp lệ";
        }
    }
}

Validation.minLength = function (selectionInput, minLength) {
    return {
        element: selectionInput,
        check: function (value) {
            return (value.length >= minLength) ? undefined : `Mật khẩu cần có tối thiểu ${minLength} kí tự`;
        }
    }
}
Validation.isUnique = function (selectionInput, unique, messageError) {
    return {
        element: selectionInput,
        check: function (value) {
            return (unique(value)) ? undefined : messageError;
        }
    }
}
Validation.isConfirm = function (selectionInput, funcGetConfirmText) {
    return {
        element: selectionInput,
        check: function (value) {
            const confirmTest = funcGetConfirmText();
            return (value == confirmTest) ? undefined : "Mật khẩu nhập lại không trùng khớp";
        }
    }
}


function getParent(child, parent) {
    while (!child.classList.contains(parent)) {
        child = child.parentElement;
    }
    return child;
}

