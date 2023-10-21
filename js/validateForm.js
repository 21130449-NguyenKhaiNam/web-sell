function Validation(formObj) {
    var formElement = document.querySelector(formObj.formSelector);
    var rules = formObj.rules;

    if (formElement) {
        var ruleFuncs = {};
        rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.element);

            if (!Array.isArray(ruleFuncs[inputElement.id])) {
                ruleFuncs[inputElement.id] = [rule.check];
            } else {
                ruleFuncs[inputElement.id].push(rule.check);
            }


            inputElement.oninput = function (e) {
                handleValidate(inputElement, rule);
            }
            inputElement.onblur = function (e) {
                handleValidate(inputElement, rule);
            }
        });


        var submitElement = document.querySelector(formObj.submitSelector);
        if (submitElement) {
            submitElement.submit = function (e) {
                e.preventDefault();
            };

            submitElement.onclick = function () {
                rules.forEach(function (rule) {
                    handleValidate(document.querySelector(rule.element));
                });
            }
        }

        function handleValidate(inputElement) {
            const value = inputElement.value;
            const formBlock = getParent(inputElement, formObj.formBlockClass);
            const showError = formBlock.querySelector(formObj.errorSelector);

            let errorMessage;
            const ruleFunc = ruleFuncs[inputElement.id];
            for (let i = 0; i < ruleFunc.length; i++) {
                errorMessage = ruleFunc[i](value);
                if (errorMessage) {
                    break;
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
}

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

