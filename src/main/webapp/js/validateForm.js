function Validation(formObj) {
    var formElement = document.querySelector(formObj.formSelector);
    var rules = formObj.rules;
    var submitElement = document.querySelector(formObj.submitSelector);
    var onSubmit = formObj.onSubmit;
    var ruleFuncs = {};
    var errorMessageObj = {};

    // Chạy qua rules để lấy các yêu cầu để thực thi validate
    if (formElement) {
        rules.forEach(function (rule) {
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
            if (!Array.isArray(ruleFuncs[rule.element])) {
                ruleFuncs[rule.element] = [rule.check];
            } else {
                ruleFuncs[rule.element].push(rule.check);
            }
            const inputElements = formElement.querySelectorAll(rule.element);
            inputElements.forEach(function (inputElement) {
                inputElement.oninput = function (e) {
                    handleValidate(rule);
                };
                inputElement.onchange = function (e) {
                    handleValidate(rule);
                };
                inputElement.onblur = function (e) {
                    handleValidate(rule);
                };
            });

        });
    }
    if (submitElement) {
        submitElement.onclick = function () {
            formElement.onsubmit = function (e) {
                e.preventDefault()
            }
            rules.forEach(function (rule) {
                handleValidate(rule);
            });
            //Chỉ được thực thi form ko có Error Message
            if (Object.keys(errorMessageObj).length == 0) {
                if (typeof onSubmit == "function") {
                    onSubmit();
                } else {
                    formElement.submit();
                }
            }
        }
    }

    function handleValidate(rule) {
        const inputElement = formElement.querySelector(rule.element);
        const value = inputElement.value;
        const formBlock = getParent(inputElement, formObj.formBlockClass);
        const showError = formBlock.querySelector(formObj.errorSelector);

        let errorMessage;
        const ruleFunc = ruleFuncs[rule.element];
        /*Loop qua từng func của id tương ứng
        Ex:
        username : [isRequired] -> Loop qua 1 lần
        email: [isEmail, isRequired] -> Loop qua 2 lần
        */
        for (let i = 0; i < ruleFunc.length; i++) {
            switch (inputElement.type) {
                case"radio":
                case"checkbox":
                    errorMessage = ruleFunc[i](formElement.querySelector(rule.element + ":checked"));//Chạy các hàm test
                    break;
                default:
                    errorMessage = ruleFunc[i](value);//Chạy các hàm test
            }

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
            return value ? undefined : "Trường này không được để trống";
        },
    }
}

Validation.isPhone = function (selectPhone) {
    return {
        element: selectPhone,
        check: function (value) {
            const regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
            return regex.test(value) ? undefined : "Số điện thoại không hợp lệ";
        }
    }
}
Validation.isExistsEmail = function (selectEmail) {
    let mess;
    let debounceTimer;

    return {
        element: selectEmail,
        check: function (value) {
            // Hủy bỏ bất kỳ debounceTimer nào đang chờ xử lý
            clearTimeout(debounceTimer);

            // Thiết lập một debounceTimer mới để gọi ajax sau một khoảng thời gian nhất định (ví dụ: 800ms)
            debounceTimer = setTimeout(() => {
                $.ajax({
                    url: '/emailExists',
                    method: 'POST',
                    data: {email: value},
                    success: function (response) {
                        mess = response;
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                })
            }, 800);

            return mess; // Trả về kết quả của cuộc gọi ajax (nếu có), hoặc "undefined" nếu vẫn chưa có kết quả
        }
    }
}
Validation.isExistsUsername = function (selectUsername) {
    let mess;
    let debounceTimer;

    return {
        element: selectUsername,
        check: function (value) {
            // Hủy bỏ bất kỳ debounceTimer nào đang chờ xử lý
            clearTimeout(debounceTimer);

            // Thiết lập một debounceTimer mới để gọi ajax sau một khoảng thời gian nhất định (ví dụ: 800ms)
            debounceTimer = setTimeout(() => {
                $.ajax({
                    url: '/usernameExists',
                    method: 'POST',
                    data: {username: value},
                    success: function (response) {
                        mess = response;
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                })
            }, 800);

            return mess; // Trả về kết quả của cuộc gọi ajax (nếu có), hoặc "undefined" nếu vẫn chưa có kết quả
        }
    }
}

Validation.isRequiredRadio = function (selectorInput) {
    return {
        element: selectorInput,
        check: function (value) {
            return value ? undefined : "Trường này không được để trống";
        },
    }
}
Validation.isEmail = function (selectionInput) {
    return {
        element: selectionInput,
        check: function (value) {
            const regex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            return regex.test(value) ? undefined : "Email không hợp lệ";
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
Validation.isUnique = function (selectionInput, messageError) {
    return {
        element: selectionInput,
        check: function (value) {
            const passwordChecked = checkPass(value);
            if (passwordChecked === undefined) {
                renderPasswordValidate();
                return undefined;
            } else {
                renderPasswordValidate();
                return "Không thỏa yêu cầu";
            }

        }
    }
}
Validation.isConfirm = function (selectionInput, funcGetConfirmText) {
    return {
        element: selectionInput,
        check: function (value) {
            const confirmTest = funcGetConfirmText();
            return (value == confirmTest) ? undefined : "Mật khẩu nhập lại không trùng khớp.";
        }
    }
}
Validation.isNumber = function (selectionInput) {
    return {
        element: selectionInput,
        check: function (value) {
            return (!isNaN(value)) ? undefined : "Trường này phải nhập số";
        }
    }
}
Validation.range = function (selectionInput, min, max) {
    return {
        element: selectionInput,
        check: function (value) {
            for (let i = 0; i < value.length; i++) {
                if (isNaN(Number(value))) {
                    return "Trường này cần nhập số.";
                }
            }
            return (min <= Number(value) && Number(value) <= max) ? undefined : "Giá trị nhập quá giới hạn."
        }
    }
}
Validation.greaterThan = function (selectionInput, selectionMinValue) {
    return {
        element: selectionInput,
        check: function (value) {
            for (let i = 0; i < value.length; i++) {
                if (isNaN(Number(value))) {
                    return "Trường này cần nhập số.";
                }
            }
            const min = document.getElementById(selectionMinValue).value;
            if (min.trim() == "") {
                return undefined;
            }
            if (min >= Number(value)) {
                return `Giá trị nhập phải lớn hơn ${min}`;
            }
            return undefined;
        }
    }
}
Validation.smallerThan = function (selectionInput, selectionMaxValue) {
    return {
        element: selectionInput,
        check: function (value) {
            for (let i = 0; i < value.length; i++) {
                if (isNaN(Number(value))) {
                    return "Trường này cần nhập số.";
                }
            }
            const max = document.getElementById(selectionMaxValue).value;
            if (max.trim() == "") {
                return undefined;
            }
            if (max <= Number(value)) {
                return `Giá trị nhập phải bé hơn ${max}`;
            }
            return undefined;
        }
    }
}

function getParent(child, parent) {
    while (!child.classList.contains(parent)) {
        child = child.parentElement;
    }
    return child;
}

var passwordCheckedObj = {};

function checkPass(password) {
    let countError = 0;

    function minLength(min) {
        let check = true;
        if (password.length >= min)
            return undefined;
        else {
            countError++;
            return `Mật khẩu phải có ít nhất ${min} kí tự.`;
        }
    }

    function atLeast1Digit() {
        const regex = /[\d]/;
        if (regex.test(password))
            return undefined;
        else {
            countError++;
            return "Có ít nhất 1 chữ số như 1, 2, 3,...";
        }
    }

    function atLeast1WordUpper() {
        const regex = /[A-Z]/;
        if (regex.test(password))
            return undefined;
        else {
            countError++;
            return "Có ít nhất 1 chữ hoa như: A, B, C,...";
        }
    }

    function atLeast1WordLower() {
        const regex = /[a-z]/;
        if (regex.test(password))
            return undefined;
        else {
            countError++;
            return "Có ít nhất 1 chữ thường như a, b, c,...";
        }
    }

    function noSpace() {
        const regex = /\s/;
        if (!regex.test(password)) {
            return undefined;
        } else {
            countError++;
            return "Mật khẩu không được có khoảng trắng."
        }
    }

    function specialCharacter() {
        const regex = /[^\s\w]/;
        if (regex.test(password)) {
            return undefined;
        } else {
            countError++;
            return "Có ít nhất 1 kí tự đặc biệt.";
        }
    }

    passwordCheckedObj = {
        minLength: minLength(8),
        atLeast1Digit: atLeast1Digit(),
        atLeast1WordUpper: atLeast1WordUpper(),
        atLeast1WordLower: atLeast1WordLower(),
        noSpace: noSpace(),
        atLeast1SpecialCharacter: specialCharacter(),
    }
    if (countError === 0)
        return undefined;
    else
        return "Không thỏa yêu cầu"

}

function renderPasswordValidate() {
    const errorList = document.querySelector(".form__password-error .error__list");
    for (const key in passwordCheckedObj) {
        let html = errorList.querySelector(`#${key}`);
        if (passwordCheckedObj[key] === undefined) {
            html.classList.add("error__item--correct");
        } else {
            html.classList.remove("error__item--correct");
        }
    }
}