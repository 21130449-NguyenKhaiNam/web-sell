//setup ckfinder
var editor = CKEDITOR.replace('ck-editor');
CKFinder.setupCKEditor(editor, 'ckfinder/');


function addSize() {
    const addSizeBtn = document.querySelector(".form__add-size");
    const formSizes = document.querySelector(".form__sizes");
    addSizeBtn.onclick = function () {
        const formSizeHTML = `<div class="form__size" onclick="removeSize(this)">
                                        <input type="text" name="size" class="form__size-input">
                                            <i class="form__size-delete fa-solid fa-xmark" ></i>
                                    </div>`;
        formSizes.insertAdjacentHTML("beforeend", formSizeHTML);
    }
}

addSize();

function removeSize(formSize) {
    const formSizeDelete = formSize.querySelector(".form__size-delete");
    formSizeDelete.onclick = function (e) {
        formSize.remove();
    }
}

function addColor() {
    const addColorBtn = document.querySelector(".form__add-color");
    const formColors = document.querySelector(".form__colors");
    addColorBtn.onclick = function () {
        const formSizeHTML = `<div class="form__color" onclick="removeColor(this)">
                                        <input type="color" name="color" class="form__size-input">
                                            <i class="form__color-delete fa-solid fa-xmark" ></i>
                                    </div>`;
        formColors.insertAdjacentHTML("beforeend", formSizeHTML);
    }
}

addColor();

function removeColor(formColor) {
    const formSizeDelete = formColor.querySelector(".form__color-delete");
    formSizeDelete.onclick = function (e) {
        formColor.remove();
    }
}
