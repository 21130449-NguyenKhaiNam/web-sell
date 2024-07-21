// function handleCloseNotificationCart() {
//     let content = document.querySelector('.cart__wrapper .notification__cart');
//     content.classList.add('closed');
//
//     setTimeout(function () {
//         document.querySelector('.cart__wrapper').removeChild(content);
//     }, 300);
// }

export const addParam = (form, {key, value}) => {
    let formDataArray = $(form).serializeArray();
    formDataArray.push({name: key, value: value}); // Add your custom parameter
    return $.param(formDataArray);
}
export const removeParamFromQueryString = (queryString, paramToRemove) => {
    // Parse the query string into an object
    const params = $.deparam(queryString);

    // Remove the parameter from the parsed query string object
    delete params[paramToRemove];

    // Return the updated query string
    return $.param(params);
};

export const addKeyFormData = (formData, key, value) => {
    formData.append(key, value);
}

export const deleteKeyFormData = (formData, keyRemove) => {
    const keysToRemove = [];
    for (let [key, value] of formData.entries()) {
        if (key === keyRemove) {
            keysToRemove.push(key);
        }
    }
    // Remove the keys from the FormData object
    keysToRemove.forEach(key => formData.delete(key));
}

export const getValuesOfKeyFormData = (formData, keyToRetrieve) => {
    const values = [];

    // Iterate over the entries of the FormData object
    for (let [key, value] of formData.entries()) {
        if (key === keyToRetrieve) {
            values.push(value);
        }
    }
    return values;
}

export const objectToQueryString = (obj) => {
    const keyValuePairs = [];

    // Helper function to handle value encoding
    function encodeValue(key, value) {
        return `${encodeURIComponent(key)}=${encodeURIComponent(value)}`;
    }

    // Iterate over each key in the object
    for (let key in obj) {
        if (obj.hasOwnProperty(key)) {
            let value = obj[key];

            // Check if the value is an array
            if (Array.isArray(value)) {
                // If it's an array, encode each element without index
                value.forEach(element => {
                    keyValuePairs.push(encodeValue(key, element));
                });
            } else {
                // If it's not an array, encode the key and value normally
                keyValuePairs.push(encodeValue(key, value));
            }
        }
    }

    // Join all key-value pairs with '&' to form the final query string
    return keyValuePairs.join('&');
}

export const convertFormDataToObject = (form) => {
    const formDataArray = $(form).serializeArray();

    const formDataJson = {};
    $.each(formDataArray, function (_, field) {
        if (formDataJson[field.name])
            formDataJson[field.name].push(field.value);
        else
            formDataJson[field.name] = [field.value];
    });
    const result = {};
    for (const key in formDataJson) {
        if (Array.isArray(formDataJson[key]) && formDataJson[key].length === 1) {
            result[key] = formDataJson[key][0];
        } else {
            result[key] = formDataJson[key];
        }
    }
    return result;
}

export const startLoading = () => {
    $.LoadingOverlay("show", {
        image: "",
        fontawesome: "fa fa-spinner fa-spin",
        background: "rgba(0, 0, 0, 0.5)"
    });
}

export const endLoading = () => {
    $.LoadingOverlay("hide");
}

export const http = ({beforeSend, complete, ...rest}, automaticLoading = true) => {
    return new Promise((resolve, reject) => {
        $.ajax({
            ...rest,
            beforeSend: function (xhr, settings) {
                if (automaticLoading)
                    startLoading();
                if (typeof beforeSend === 'function') {
                    beforeSend.call(this, xhr, settings);
                }
            },
            success: function (data) {
                resolve(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                reject(new Error(`Error: ${textStatus}, ${errorThrown}`));
            },
            complete: function (xhr, status) {
                if (automaticLoading)
                    endLoading();
                if (typeof complete === 'function') {
                    complete.call(xhr, status);
                }
            }
        })
    });
}

export const formatDate = (dateString) => {
    const d = new Date(dateString);
    const day = String(d.getDate()).padStart(2, '0');
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const year = d.getFullYear();
    return `${day} / ${month} / ${year}`;
}

export const formatCurrency = (value) => {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value);
}

export const configSweetAlert2 = {
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
}