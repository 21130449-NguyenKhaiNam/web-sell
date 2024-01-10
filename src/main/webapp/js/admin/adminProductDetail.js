<<<<<<< HEAD
//Validate form
var ruleObj = {
    name: [Validation.isRequired("#name")],
    originalPrice: [Validation.isRequired("#originalPrice"), Validation.isNumber("#originalPrice")],
    salePrice: [Validation.isRequired("#salePrice"), Validation.isNumber("#salePrice")],
    description: [Validation.isRequired("#description")],
    nameSize: [Validation.isRequired("#nameSize")],
    sizePrice: [Validation.isRequired("#sizePrice"), Validation.isNumber("#sizePrice")],
    image: [Validation.isRequired("#image")],
};
var ruleArray = [];
loadRules();
var validate = new Validation({
    formSelector: ".product__form",
    formBlockClass: "form__block",
    errorSelector: ".form__error",
    rules: ruleArray,
    submitSelector: "#form__submit",
    onSubmit: addNewProduct,
});
=======
window.addEventListener('message', function (event) {
    // state:{
    // 0: create
    // 1: read
    // 2: update
    // 3. delete}
>>>>>>> 2f2e062eb617b76e5cf7f6eee7de2166a2940dfa

    const receivedData = event.data;
    //Commons
    const form = document.querySelector(".product__form");
    const imgPreviews = document.querySelector(".img__previews");

<<<<<<< HEAD
function loadRules() {
    ruleArray = [];
    for (const key in ruleObj) {
        for (const keyElement of ruleObj[key]) {
            ruleArray.push(keyElement);
        }
    }
    validate = new Validation({
        formSelector: ".product__form",
        formBlockClass: "form__block",
        errorSelector: ".form__error",
        rules: ruleArray,
        submitSelector: "#form__submit",
        onSubmit: addNewProduct,
    });
}

function pushRule(selectorId, rule) {
    if (!Array.isArray(ruleObj[selectorId])) {
        ruleObj[selectorId] = [rule];
    } else {
        ruleObj[selectorId].push(rule);
    }
    loadRules();
}

function removeRule(selectorId) {
    delete ruleObj[selectorId];
    loadRules();
}


var countSize = 0;
function addSize() {
    const addSizeBtn = document.querySelector(".form__add-size");
    const formSizes = document.querySelector(".form__sizes");
    addSizeBtn.onclick = function () {
        countSize++;
        const formSizeHTML = `<div class="form__size" onclick="removeSize(this)">        
                                          <div class="form__block">
                                             <label>
                                                Tên kích thước
                                                <input type="text" name="nameSize"
                                                       class="form__size-input" id="nameSize_${countSize}">
                                            </label>
                                              <span class="form__error"></span>
                                          </div>
                                       <div class="form__block">
                                            <label class="form__size-price">
                                                Giá:
                                                <input type="text" id="sizePrice_${countSize}" name="sizePrice">
                                                <span>VNĐ</span>
                                            </label>
                                              <span class="form__error"></span>
                                        </div>
                                        <i class="form__size-delete fa-solid fa-xmark" ></i>    
                                      </div>`;
        formSizes.insertAdjacentHTML("beforeend", formSizeHTML);
        pushRule(`nameSize_${countSize}`, Validation.isRequired(`#nameSize_${countSize}`));
        pushRule(`sizePrice_${countSize}`, Validation.isRequired(`#sizePrice_${countSize}`));
        pushRule(`sizePrice_${countSize}`, Validation.isNumber(`#sizePrice_${countSize}`));
    }
}
=======
    // Create
    if (receivedData.state === 0) {
        let ruleObj = {
            name: [Validation.isRequired("#name")],
            originalPrice: [Validation.isRequired("#originalPrice"), Validation.isNumber("#originalPrice")],
            salePrice: [Validation.isRequired("#salePrice"), Validation.isNumber("#salePrice")],
            description: [Validation.isRequired("#description")],
            nameSize: [Validation.isRequired("#nameSize")],
            sizePrice: [Validation.isRequired("#sizePrice"), Validation.isNumber("#sizePrice")],
            image: [Validation.isRequired("#image")],
        };
        let ruleArray = [];
>>>>>>> 2f2e062eb617b76e5cf7f6eee7de2166a2940dfa

        let validate = new Validation({
            formSelector: ".product__form",
            formBlockClass: "form__block",
            errorSelector: ".form__error",
            rules: ruleArray,
            submitSelector: "#form__submit",
            onSubmit: create,
        });

<<<<<<< HEAD
function removeSize(formSize) {
    const formSizeDelete = formSize.querySelector(".form__size-delete");
    const inputs = formSize.querySelectorAll(`input[name]`);
    for (const input of inputs) {
        removeRule(input.id);
    }
    formSizeDelete.onclick = function (e) {
        formSize.remove();
    }
    loadRules();
}
=======
        function loadRules() {
            ruleArray = [];
            for (const key in ruleObj) {
                for (const keyElement of ruleObj[key]) {
                    ruleArray.push(keyElement);
                }
            }
            validate = new Validation({
                formSelector: ".product__form",
                formBlockClass: "form__block",
                errorSelector: ".form__error",
                rules: ruleArray,
                submitSelector: "#form__submit",
                onSubmit: create,
            });
        }
>>>>>>> 2f2e062eb617b76e5cf7f6eee7de2166a2940dfa

        loadRules();

        function pushRule(selectorId, rule) {
            if (!Array.isArray(ruleObj[selectorId])) {
                ruleObj[selectorId] = [rule];
            } else {
                ruleObj[selectorId].push(rule);
            }
            loadRules();
        }

        function removeRule(selectorId) {
            delete ruleObj[selectorId];
            loadRules();
        }

        let countSize = 0;

        function addSize() {
            const addSizeBtn = document.querySelector(".form__add-size");
            const formSizes = document.querySelector(".form__sizes");

            addSizeBtn.onclick = function () {
                countSize++;
                const formSizeHTML = `<div class="form__size">        
                                          <div class="form__block">
                                             <label>
                                                Tên kích thước
                                                <input type="text" name="nameSize"
                                                       class="form__size-input" id="nameSize_${countSize}">
                                            </label>
                                              <span class="form__error"></span>
                                          </div>
                                       <div class="form__block">
                                            <label class="form__size-price">
                                                Giá:
                                                <input type="text" id="sizePrice_${countSize}" name="sizePrice">
                                                <span>VNĐ</span>
                                            </label>
                                              <span class="form__error"></span>
                                        </div>
                                        <i class="form__size-delete fa-solid fa-xmark" ></i>    
                                      </div>`;
                formSizes.insertAdjacentHTML("beforeend", formSizeHTML);

                const addedFormSize = formSizes.lastElementChild;

                addedFormSize.querySelector(".form__size-delete").addEventListener("click", function () {
                    removeSize(addedFormSize);
                });

                pushRule(`nameSize_${countSize}`, Validation.isRequired(`#nameSize_${countSize}`));
                pushRule(`sizePrice_${countSize}`, Validation.isRequired(`#sizePrice_${countSize}`));
                pushRule(`sizePrice_${countSize}`, Validation.isNumber(`#sizePrice_${countSize}`));
            }
        }

        addSize();

        function removeSize(formSize) {
            const formSizeDelete = formSize.querySelector(".form__size-delete");
            const inputs = formSize.querySelectorAll(`input[name]`);
            for (const input of inputs) {
                removeRule(input.id);
            }
            formSizeDelete.onclick = function (e) {
                formSize.remove();
            }
            loadRules();
        }

        function addColor() {
            const addColorBtn = document.querySelector(".form__add-color");
            const formColors = document.querySelector(".form__colors");
            addColorBtn.onclick = function () {
                const formColorHTML = `<div class="form__color">
                                        <input type="color" name="color" class="form__size-input">
                                            <i class="form__color-delete fa-solid fa-xmark" ></i>
                                    </div>`;
                formColors.insertAdjacentHTML("beforeend", formColorHTML);

                const addedFormColor = formColors.lastElementChild;
                addedFormColor.querySelector(".form__color-delete").addEventListener("click", function () {
                    removeColor(addedFormColor);
                });
            }
        }

        addColor();

        function removeColor(formColor) {
            const formSizeDelete = formColor.querySelector(".form__color-delete");
            formSizeDelete.onclick = function (e) {
                formColor.remove();
            }
        }

        Image
        const inputImg = document.querySelector(".img__input");

        inputImg.onchange = function () {
            addFiles();
        }

        function addFiles() {
            imgPreviews.innerHTML = "";
            const files = inputImg.files;
            for (let i = 0; i < files.length; i++) {
                if (!files[i].name.endsWith(".jpg") && !files[i].name.endsWith(".png") && !files[i].name.endsWith(".jpeg")) {
                    continue;
                } else {
                    const formImgHTML = `<div class="img__preview">
                                                  <img src="${URL.createObjectURL(files[i])}" alt="" >
                                                </div>`;

                    imgPreviews.insertAdjacentHTML("beforeend", formImgHTML);
                }
            }
        }

        const inputDesc = document.querySelector(`input[name = "description"]`)
        editorCK.on('change', function () {
            inputDesc.value = editorCK.getData();
        });

        function getDataForm(form) {
            const formData = new FormData(form);
            return formData;
        }

// Ajax
        function create() {
            const product = getDataForm(form);
            $.ajax({
                url: "admin-create-product",
                type: "POST",
                contentType: false,
                processData: false,
                dataType: "json",
                cache: false,
                data: product,
                success: function (data) {
                    if (data.status === true) {
                        notifySuccess();
                    } else {
                        notifyFailed();
                    }
                },
                error: function (error) {
                    console.log(error)
                },
            });
        }

        const toastList = document.querySelector(".toast__list");

        function notifySuccess() {
            toastList.innerHTML = `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--success">
                    <i class="toast__icon fa-solid fa-check"></i>
                </span>
                <strong class="toast__title">Thêm sản phẩm thành công</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
                Sản phẩm đã được thêm vào gian hàng.
            </div>
        </div>`;
            // Get the snackbar DIV
            const x = document.getElementById("snackbar");

            // Add the "show" class to DIV
            x.className = "show";

            // After 3 seconds, remove the show class from DIV
            setTimeout(function () {
                x.className = x.className.replace("show", "");
                x.remove();
            }, 3000);

            // Send a message to the parent window
            window.parent.postMessage('Reload', `${window.location.origin}+"/adminProducts.jsp`);

        }

        function notifyFailed() {
            toastList.innerHTML = `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--failed">
                    <i class="toast__icon fa-solid fa-xmark"></i>
                </span>
                <strong class="toast__title">Thêm sản phẩm không thành công thành công</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
                Sản phẩm đã có tên trên đã tồn tại vào gian hàng.
            </div>
        </div>`;
            // Get the snackbar DIV
            const x = document.getElementById("snackbar");

            // Add the "show" class to DIV
            x.className = "show";

            // After 3 seconds, remove the show class from DIV
            setTimeout(function () {
                x.className = x.className.replace("show", "");
                x.remove();
            }, 3000);
        }
    }
<<<<<<< HEAD
}


const imgPreviews = document.querySelector(".img__previews");
const inputImg = document.querySelector(".img__input");

inputImg.onchange = function (e) {
    const files = e.target.files;
    const htmls = [];
    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        if (!file.name.endsWith(".jpg") && !file.name.endsWith(".png") && !file.name.endsWith(".jpeg")) continue;
        htmls.push(`<div class="img__preview">
                      <img src="${URL.createObjectURL(file)}" alt="">
                   </div>`);
    }
    imgPreviews.innerHTML = htmls.join("");
}

const inputDesc = document.querySelector(`input[name = "description"]`)
editorCK.on('change', function () {
    inputDesc.value = editorCK.getData();
});


function getDataForm(form) {
    const formData = new FormData(form);
    return formData;
}

// Ajax
function addNewProduct() {
    const formElement = document.querySelector(".product__form");
    const product = getDataForm(formElement);
    const buttonForm = formElement.querySelector(`button[type="submit"]`);
    $.ajax({
        url: "admin-add-product",
        type: "POST",
        contentType: false,
        processData: false,
        dataType: "json",
        cache: false,
        data: product,
        success: function (data) {
            if (data.status === true) {
                notifySuccess();
            } else {
                notifyFailed();
            }
        },
        error: function (error) {
            console.log(error)
        },
    });
}

const toastList = document.querySelector(".toast__list");

function notifySuccess() {
    toastList.innerHTML =
        `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--success">
                    <i class="toast__icon fa-solid fa-check"></i>
                </span>
                <strong class="toast__title">Thêm sản phẩm thành công</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
                Sản phẩm đã được thêm vào gian hàng.
            </div>
        </div>`;
    // Get the snackbar DIV
    const x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
        x.remove();
    }, 3000);
}

function notifyFailed() {
    toastList.innerHTML =
        `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--failed">
                    <i class="toast__icon fa-solid fa-xmark"></i>
                </span>
                <strong class="toast__title">Thêm sản phẩm không thành công thành công</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
                Sản phẩm đã có tên trên đã tồn tại vào gian hàng.
            </div>
        </div>`;
    // Get the snackbar DIV
    const x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
        x.remove();
    }, 3000);
}
=======

    // Read product: No validate
    if (receivedData.state === 1) {
        const name = form.querySelector(`input[name ="name"]`);
        const categoryId = form.querySelector(`select[name ="idCategory"]`);
        const originalPrice = form.querySelector(`input[name ="originalPrice"]`);
        const salePrice = form.querySelector(`input[name ="salePrice"]`);

        // Use ajax call -> Show productDetail
        callAjax();

        function setUpForm(form) {
            // Remove button
            if (form.querySelector(`button[type="submit"]`))
                form.querySelector(`button[type="submit"]`).remove();

            // Remove các add btn size và color
            if (form.querySelector(".form__add-size"))
                form.querySelector(".form__add-size").remove();
            if (form.querySelector(".form__add-color"))
                form.querySelector(".form__add-color").remove();

            // disable tất cả các thẻ input
            const allInputs = document.querySelectorAll(`input[name]`)

            allInputs.forEach(function (input) {
                input.readOnly = true;
                input.disable = true;
            })
            categoryId.addEventListener('mousedown', function (event) {
                event.preventDefault();
            });

            let editor = form.querySelector("#ck-editor + div");
            editor.style.userSelect = "none";
            editor.style.pointerEvents = "none";
            editor.addEventListener('mousedown', function (event) {
                event.preventDefault();
            });

            editor = CKEDITOR.instances['ck-editor'];
            const toolbar = editor.container.$.querySelector('.cke_top');
            if (toolbar) {
                toolbar.style.display = 'none';
            }

            if (document.querySelector(".img__label")) {
                document.querySelector(".img__label").remove();
            }
        }

        function applyDateToForm(data) {
            const product = data["product"];
            const sizes = data["sizes"];
            const colors = data["colors"];
            const images = data["images"];
            imgPreviews.innerHTML = ""
            // Name
            name.value = product.name;
            // Category
            categoryId.querySelector(`option[value="${product.categoryId}"]`).selected = true;
            // Original Price
            originalPrice.value = product.originalPrice;
            // Sale Price
            salePrice.value = product.salePrice;
            // Description
            editorCK.setData(product.description);
            // Sizes
            addSize(sizes);
            // Colors
            addColor(colors);
            // Images
            asyncImageLoading(images)
        }

        function callAjax() {
            $.ajax({
                url: "admin-read-product?id=" + receivedData.productId,
                type: "POST",
                contentType: false,
                processData: false,
                dataType: "json",
                cache: false,
                success: function (data) {
                    applyDateToForm(data);
                    setUpForm(form);
                },
                error: function (error) {
                },
            });
        }

// Get the current URL
        const currentURL = window.location.href;

// Create a URL object using the current URL
        const url = new URL(currentURL);

// Extract the base URL
        const basePath = `${url.protocol}//${url.hostname}:${url.port}/${url.pathname.split('/')[1]}`;

        async function asyncImageLoading(images) {
            for (let i = 0; i < images.length; i++) {
                const url = `${basePath}/read-image?name=${images[i]["nameImage"]}`
                const response = await fetch(url)
                const blob = await response.blob();
                const blobUrl = URL.createObjectURL(blob);
                addImages(blobUrl);
                console.log(images[i]["nameImage"])
            }
        }
        function addSize(sizes) {
            const formSizes = document.querySelector(".form__sizes");
            formSizes.innerHTML = "";
            const formSizesHTML = sizes.map(function (size) {
                return `<div class="form__size">        
                              <div>
                                 <label>
                                    Tên kích thước
                                    <input type="text" name="nameSize"
                                           class="form__size-input"  value="${size.nameSize}">
                                </label>
                              </div>
                               <div>
                                    <label class="form__size-price">
                                        Giá:
                                        <input type="text"  name="sizePrice" value="${size.sizePrice}">
                                        <span>VNĐ</span>
                                    </label>
                                </div>
                         </div>`;
            });
            formSizes.innerHTML = formSizesHTML.join("");
        }

        function addColor(colors) {
            const formColors = document.querySelector(".form__colors");
            formColors.innerHTML = "";
            formColors.style.display = "flex";
            formColors.style.flexWrap = "wrap";
            formColors.style.gap = "10px";
            const formColorsHTML = colors.map(function (color) {
                return `<div style="border: 1px solid #ccc" class="form__color" onclick="removeColor(this)">
                            <span style="display:inline-block; background-color: ${color["codeColor"]}"  class="form__size-input"></span>
                        </div>`;
            });
            formColors.innerHTML = formColorsHTML.join("");
        }

        function addImages(blobUrl) {
            const imageHTML = `<div class="img__preview">
                                          <img src="${blobUrl}" alt="">
                                       </div>`
            imgPreviews.insertAdjacentHTML("beforeend", imageHTML);
        }
    }
});
>>>>>>> 2f2e062eb617b76e5cf7f6eee7de2166a2940dfa
