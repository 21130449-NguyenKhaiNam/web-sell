window.addEventListener('message', function (event) {
    const receivedData = event.data;
    (function () {
        const buttonAddParameter = document.querySelector(".parameter__add");
        buttonAddParameter.onclick = function () {
            addParameter(parameterList);
        }
    })();
    const htmlCloseBtn = ` <span class="parameter__item-close" >
                                        <i class="parameter__item-close-icon fa-solid fa-xmark"></i>
                                    </span>`

    function deleteParameter(parameter) {
        const closeBtn = parameter.querySelector(".parameter__item-close");
        const idCount = parameter.getAttribute("id-count");
        closeBtn.onclick = function () {
            parameter.remove();
            removeRule(`nameParameter${idCount}`);
            removeRule(`minValue${idCount}`);
            removeRule(`maxValue${idCount}`);
            removeRule(`guideImg${idCount}`);
            removeRule(`unit${idCount}`);
            if (parameterList.childElementCount > 1) {
                let lastElement = parameterList.lastElementChild
                lastElement.insertAdjacentHTML("beforeend", htmlCloseBtn);
                deleteParameter(lastElement);
            }
            loadRules();
        }
        console.log(ruleObj);
    }

    function updateCloseBtn(parameterList) {
        const closeBtns = parameterList.querySelectorAll(".parameter__item-close");
        Array.from(closeBtns).forEach(function (closeBtn) {
            closeBtn.remove();
        });
        let lastElement = parameterList.lastElementChild
        lastElement.insertAdjacentHTML("beforeend", htmlCloseBtn);
        deleteParameter(lastElement);
    }

    function loadImg(label, idCount) {
        const inputFile = label.querySelector(`input[type="file"]`);
        inputFile.addEventListener('change', function () {
            if (!ruleObj[`sizeTableImage`] && inputFile.id == "sizeTableImage") {
                ruleObj[`sizeTableImage`] = [Validation.isRequired(`#sizeTableImage`)];
            }
            if (!ruleObj[`guideImg${idCount}`] && inputFile.id == `guideImg${idCount}`) {
                ruleObj[`guideImg${idCount}`] = [Validation.isRequired(`#guideImg${idCount}`)];
            }
            const file = inputFile.files[0];
            if (file) {
                let imgElement = label.querySelector(".category__img");
                if (!imgElement) {
                    const html = `<img class="category__img" src="" alt="">`;
                    label.insertAdjacentHTML("beforeend", html);
                }
                imgElement = label.querySelector(".category__img");
                imgElement.src = URL.createObjectURL(file);
            }
        })
    }

    var countIdRule = 0;
    const parameterList = document.querySelector(".parameter__list");

    function addParameter(parameterList) {
        countIdRule++;
        const html = `<div class="parameter__item parameter__item--added" id-count="${countIdRule}">
                            <div class="parameter__grid">
                                <label class="category__label">
                                    <div class="category__title">Tên tham số
                                        <i class="category__icon-guide fa-solid fa-circle-info">
                                        </i>
                                        <div class="category__guide">Tên tham số, ví dụ: dài áo, ngang vai,...
                                        </div>
                                    </div>
                                    <div class="category__block">
                                        <input name="nameParameter" id="nameParameter${countIdRule}" type="text" class="category__name category__input">
                                        <p class="category__error"></p>
                                    </div>
                                </label>
                                <label class="category__label">
                                    <div class="category__title">Đơn vị tính toán
                                        <i class="category__icon-guide fa-solid fa-circle-info">
                                        </i>
                                        <div class="category__guide">Đơn vị của giá trị tối thiểu và giá trị tối đa
                                        </div>
                                    </div>
                                    <div class="category__block">
                                        <input type="text" name="unit" id="unit${countIdRule}" class="category__name category__input">
                                        <p class="category__error"></p>
                                    </div>
                                </label>
                                <label class="category__label">
                                    <div class="category__title">Giá trị tổi thiểu
                                        <i class="category__icon-guide fa-solid fa-circle-info">
                                        </i>
                                        <div class="category__guide">Giá trị tối thiểu mà người dùng có thể đặt may đồ
                                        </div>
                                    </div>
                                    <div class="category__block">
                                        <input type="text" name="minValue" id="minValue${countIdRule}" class="category__name category__input">
                                        <p class="category__error"></p>
                                    </div>
                                </label>
                                <label class="category__label">
                                    <div class="category__title">Giá trị tối đa
                                        <i class="category__icon-guide fa-solid fa-circle-info">
                                        </i>
                                        <div class="category__guide">Giá trị tối đa mà người dùng có thể đặt may đồ
                                        </div>
                                    </div>
                                    <div class="category__block">
                                        <input type="text" name="maxValue" id="maxValue${countIdRule}" class="category__name category__input">
                                        <p class="category__error"></p>
                                    </div>
                                </label>
                            </div>
                            <label class="category__label">
                                <div class="category__title">Ảnh hướng dẫn may đo
                                    <i class="category__icon-guide fa-solid fa-circle-info">
                                    </i>
                                    <div class="category__guide">Ảnh hướng dẫn may đo cho thông số, chỉ được chọn 1 ảnh
                                    </div>
                                </div>
                                <div class="category__block">
                                    <label class="category__file">
                                        <input name="guideImg" type="file" id="guideImg${countIdRule}" accept="image/*" hidden="hidden">
                                    </label>
                                    <p class="category__error"></p>
                                </div>
                            </label>
                        </div>`;
        parameterList.insertAdjacentHTML("beforeend", html);
        const lastElement = parameterList.lastElementChild;
        loadImg(lastElement.querySelector(`.category__file`), countIdRule);
        updateCloseBtn(parameterList);
        pushRule(`nameParameter${countIdRule}`, Validation.isRequired(`#nameParameter${countIdRule}`));
        pushRule(`unit${countIdRule}`, Validation.isRequired(`#unit${countIdRule}`));
        pushRule(`minValue${countIdRule}`, Validation.isRequired(`#minValue${countIdRule}`));
        pushRule(`minValue${countIdRule}`, Validation.isNumber(`#minValue${countIdRule}`));
        pushRule(`minValue${countIdRule}`, Validation.smallerThan(`#minValue${countIdRule}`, `maxValue${countIdRule}`))
        pushRule(`maxValue${countIdRule}`, Validation.isRequired(`#maxValue${countIdRule}`));
        pushRule(`maxValue${countIdRule}`, Validation.isNumber(`#maxValue${countIdRule}`));
        pushRule(`maxValue${countIdRule}`, Validation.greaterThan(`#maxValue${countIdRule}`, `minValue${countIdRule}`))
        pushRule(`guideImg${countIdRule}`, Validation.isRequired(`#guideImg${countIdRule}`));
        loadRules();
        console.log(ruleObj);
    }

    let ruleArray = [
        // Validation.isRequired("#nameCategory"),
        // Validation.isRequired("#sizeTableImage"),
        // Validation.isRequired("#nameParameter"),
        // Validation.isRequired("#unit"),
        // Validation.isRequired("#minValue"),
        // Validation.isNumber("#minValue"),
        // Validation.isRequired("#maxValue"),
        // Validation.isNumber("#maxValue"),
        // Validation.isRequired("#guideImg"),
    ];
// --------------------------------------------------------
//Validate
    let validate = new Validation({
        formSelector: ".category__form",
        formBlockClass: "category__block",
        errorSelector: ".category__error",
        rules: ruleArray,
        submitSelector: "#form__submit",
        onSubmit: create,
    })
    const form = document.querySelector('.category__form');

    let ruleObj = {
        nameCategory: [Validation.isRequired("#nameCategory")],
        sizeTableImage: [Validation.isRequired("#sizeTableImage")],
        nameParameter: [Validation.isRequired("#nameParameter")],
        unit: [Validation.isRequired("#unit")],
        minValue: [Validation.isRequired("#minValue"), Validation.isNumber("#minValue"), Validation.smallerThan("#minValue", "maxValue")],
        maxValue: [Validation.isRequired("#maxValue"), Validation.isNumber("#maxValue"), Validation.greaterThan("#maxValue", "minValue")],
        guideImg: [Validation.isRequired("#guideImg")],
    }
    loadRules();

    function loadRules() {
        ruleArray = [];
        for (const ruleObjKey in ruleObj) {
            ruleObj[ruleObjKey].forEach(function (rule) {
                ruleArray.push(rule);
            })
        }
        if (receivedData.state == 0) {
            validate = new Validation({
                formSelector: ".category__form",
                formBlockClass: "category__block",
                errorSelector: ".category__error",
                rules: ruleArray,
                submitSelector: "#form__submit",
                onSubmit: create,
            });
        }
        if (receivedData.state == 1) {
            validate = new Validation({
                formSelector: ".category__form",
                formBlockClass: "category__block",
                errorSelector: ".category__error",
                rules: ruleArray,
                submitSelector: "#form__submit",
                onSubmit: update,
            });
        }
    }

    function pushRule(key, rule) {
        if (!Array.isArray(ruleObj[key])) {
            ruleObj[key] = [rule];
        } else {
            ruleObj[key].push(rule);
        }
    }

    function removeRule(key) {
        if (ruleObj[key]) {
            delete ruleObj[key];
        }
    }

    if (receivedData.state == 0) {
        const labelFiles = document.querySelectorAll(".category__file");
        labelFiles.forEach(function (labelFile) {
            loadImg(labelFile);
        });
    }
    if (receivedData.state == 1) {
        const labelFiles = document.querySelectorAll(".category__file");
        labelFiles.forEach(function (labelFile) {
            loadImg(labelFile);
        });
        document.getElementById("form__submit").innerText = "Cập nhật phân loại";
        const categoryId = receivedData.categoryId;
        $.ajax({
            url: "/admin-read-category",
            type: "GET",
            data: {
                categoryId: categoryId,
            },
            cache: false,
            dataType: "json",
            success: function (data) {
                applyData(data)
            },
            error: function (error) {
            },
        });

        function applyData(data) {
            const category = data['category'];
            applyDataCategory(category);

            function applyDataCategory(category) {
                const nameCategory = document.querySelector("#nameCategory");
                const sizeTableImage = document.querySelector("#sizeTableImage").parentElement;
                nameCategory.value = category.nameType;
                if (sizeTableImage.querySelector('.category__img')) {
                    sizeTableImage.querySelector('.category__img').src = `/assets/img/size_table/${category.sizeTableImage}`;
                } else {
                    sizeTableImage.insertAdjacentHTML("beforeend", `<img class="category__img" src="/assets/img/size_table/${category.sizeTableImage}" alt="">`);
                }
            }

            const parameters = data['parameters'];
            applyDataParameter(parameters);

            function applyDataParameter(parameters) {
                // reset
                const parameterItems = document.querySelectorAll(".parameter__item--added");
                Array.from(parameterItems).forEach(function (parameterItem) {
                    const id = parameterItem.getAttribute("id-count");
                    removeRule(`nameParameter${id}`);
                    removeRule(`minValue${id}`);
                    removeRule(`maxValue${id}`);
                    removeRule(`guideImg${id}`);
                    removeRule(`unit${id}`)
                    parameterItem.remove();
                });

                for (let i = 0; i < parameters.length; i++) {
                    if (i != 0) {
                        addParameter(parameterList);
                    }
                    const parameterForm = parameterList.lastElementChild;
                    const nameParameter = parameterForm.querySelector(`input[name='nameParameter']`);
                    const unit = parameterForm.querySelector(`input[name='unit']`);
                    const minValue = parameterForm.querySelector(`input[name='minValue']`);
                    const maxValue = parameterForm.querySelector(`input[name='maxValue']`);
                    const guideImg = parameterForm.querySelector(`input[name="guideImg"]`).parentElement;
                    nameParameter.value = parameters[i].name;
                    unit.value = parameters[i].unit;
                    minValue.value = parameters[i].minValue;
                    maxValue.value = parameters[i].maxValue;
                    if (guideImg.querySelector('.category__img')) {
                        guideImg.querySelector('.category__img').src = `assets/img/parameter_guide/${parameters[i].guideImg}`;
                    } else {
                        guideImg.insertAdjacentHTML("beforeend", `<img class="category__img" src="/assets/img/parameter_guide/${parameters[i].guideImg}" alt="">`);
                    }
                }
                removeRule('sizeTableImage');
                removeRule('guideImg');
                for (let j = 1; j <= countIdRule; j++) {
                    removeRule(`guideImg${j}`);
                }
                loadRules();
            }
        }
    }

    function create() {
        let category = new FormData(form);
        $.ajax({
            url: "/admin-create-category",
            type: "POST",
            contentType: false,
            processData: false,
            dataType: "json",
            data: category,
            cache: false,
            success: function (data) {
                if (data.status == true) {
                    notifySuccess({
                        title: "Thêm thành công",
                        body: "Thêm phân loại thành công, vui lòng tải lại trang",
                    });
                } else {
                    notifyFailed({
                        title: "Thêm không thành công",
                        body: "Thêm phân loại không thành công, phân loại này đã tồn tại",
                    });
                }
            },
            error: function (error) {
            },
        });
    }

    function update() {
        let category = new FormData(form);
        category.append("categoryId", receivedData.categoryId)
        $.ajax({
            url: "/admin-update-category",
            type: "POST",
            dataType: "json",
            data: category,
            cache: false,
            success: function (data) {
                if (data.status == true) {
                    notifySuccess({
                        title: "Cập nhật thành công",
                        body: "Cập nhật phân loại thành công, vui lòng tải lại trang",
                    });
                } else {
                    notifyFailed({
                        title: "Cập nhật không thành công",
                        body: "Cập nhật phân loại không thành công, vui lòng thử lại",
                    });
                }
            },
            error: function (error) {
            },
        });
    }
});

