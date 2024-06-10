$(document).ready(()=>{
    callAjaxToPage(1)
    //Load page btn
    document.querySelector(".reload__btn").onclick = function () {
        window.location.reload();
    }

    function getClose(modal) {
        return modal.querySelector(".modal__product-close");
    }

//Read product
    const iframeRead = document.querySelector("#dialog-product-read .modal__product-iframe");
    const dataViewElement = document.querySelectorAll(".table__data-view");
    const modalRead = document.querySelector("#dialog-product-read");
    const elementCloseRead = getClose(modalRead);

    elementCloseRead.onclick = function () {
        modalRead.style.display = "none";
    };

    dataViewElement.forEach(function (element) {
        const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
        element.onclick = function () {
            // Open dialog
            modalRead.style.display = "block";

            // Send via iframe
            const tableRow = this.parentNode;
            const productId = tableRow.querySelector(".table__data-id").textContent.trim();
            console.log(pageTarget)
            iframeRead.contentWindow.postMessage({
                productId: productId,
                state: 1,
            }, pageTarget);
        }
    });

//Create product
    const iframeCreate = document.querySelector("#dialog-product-create .modal__product-iframe");
    const modalCreateBtn = document.querySelector("#button-create-product");
    const modalCreate = document.querySelector("#dialog-product-create");
    const elementCloseCreate = getClose(modalCreate);

    elementCloseCreate.onclick = function () {
        modalCreate.style.display = "none";
    }

    modalCreateBtn.onclick = function () {
        const pageTarget = `${window.location.origin}/public/admin/adminProductForm.jsp`;
        modalCreate.style.display = "block";
        // Send via iframe
        iframeCreate.contentWindow.postMessage({
            state: 0,
        }, pageTarget);

    }

//Update product
    const iframeUpdate = document.querySelector("#dialog-product-update .modal__product-iframe");
    const dataUpdateElement = document.querySelectorAll(".table__data-edit");
    const modalUpdate = document.querySelector("#dialog-product-update");
    const elementCloseUpdate = getClose(modalUpdate);

    elementCloseUpdate.onclick = function () {
        modalUpdate.style.display = "none";
    };

    dataUpdateElement.forEach(function (element) {
        element.onclick = function () {
            const pageTarget = `${window.location.origin}/public/admin/adminProductUpdateForm.jsp`;
            // Open dialog
            modalUpdate.style.display = "block";

            // Send via iframe
            const tableRow = this.parentNode;
            const productId = tableRow.querySelector(".table__data-id").textContent.trim();
            iframeUpdate.contentWindow.postMessage({
                productId: productId,
                state: 2,
            }, pageTarget);
        }
    });
//Hide product
    const dataHideElement = document.querySelectorAll(".table__data-visibility .button");
    dataHideElement.forEach(function (element) {
        element.onclick = function () {
            // Get name product
            const tableRow = this.parentNode.parentNode;
            console.log(tableRow.querySelector(".table__data-name .table__cell"))

            const nameProduct = tableRow.querySelector(".table__data-name .table__cell").textContent.trim();
            const idProduct = tableRow.querySelector(".table__data-id").textContent.trim();
            // Show alert
            if (element.classList.contains("button__hide")) {
                hideProductAlert(nameProduct, idProduct);
            }
            if (element.classList.contains("button__un-hide")) {
                unHideProductAlert(nameProduct, idProduct);
            }

        }
    });

    function hideProductAlert(nameProduct, idProduct) {
        const message = `Bạn có muốn ẩn sản phẩm "${nameProduct}" không?`;
        const result = window.confirm(message);
        if (result) {
            //     Handle
            $.ajax({
                url: "/api/admin/product/hide",
                type: "POST",
                data: {
                    id: idProduct
                },
                dataType: "json",
                cache: true,
                success: function (data) {
                    const status = data.status;
                    if (status) {
                        alert(`Ẩn sản phẩm ${nameProduct} thành công`);
                        window.location.reload();
                    } else {
                        alert(`Ẩn sản phẩm ${nameProduct} không thành công`);
                    }
                },
                error: function (error) {
                },
            });
        }
    }

    function unHideProductAlert(nameProduct, idProduct) {
        const message = `Bạn có muốn bỏ ẩn sản phẩm "${nameProduct}" không?`;
        const result = window.confirm(message);
        if (result) {
            //     Handle
            $.ajax({
                url: "/api/admin/product/un-hide",
                type: "POST",
                data: {
                    id: idProduct
                },
                dataType: "json",
                success: function (data) {
                    const status = data.status;
                    if (status) {
                        alert(`Bỏ ẩn sản phẩm ${nameProduct} thành công`);
                        window.location.reload();
                    } else {
                        alert(`Bỏ ẩn sản phẩm ${nameProduct} không thành công`);
                    }
                },
                error: function (error) {
                },
            });
        }
    }

    function changePage(aTag) {
        if (aTag) {
            productList.innerHTML = ``
            callAjaxToPage(aTag.innerText)
        }
    }

    function callAjaxToPage(pageNumber) {
        $.ajax({
            url: '/filterProductAdmin?page=' + pageNumber, type: 'POST',
            success: function (data) {
                generationPage(parseInt(pageNumber), data["quantity"])
                showProduct(data)
            }, error: function (error) {
                console.log("loi khong hien thi san pham o trang admin")
            },
        });
    }

    function showProduct(data) {
        const products = data["products"]

        products.forEach(function (value) {
            const product = value["product"]
            const id = product["id"]
            const name = product["name"]
            const originalPrice = product["originalPrice"]
            const salePrice = product["salePrice"]


            const images = value["images"]

            const nameImage = images[0]["nameImage"]
            const visibility = product["visibility"]

            const vndFormat = Intl.NumberFormat("vi-VI", {
                style: "currency", currency: "VND",
            });

            productList.innerHTML += `<tr class="table__row">
            <td class="table__data-view">
                <label>
                    <i class="fa-solid fa-eye"></i>
                </label>
            </td>
            <td class="table__data-edit">
                <label>
                    <i class="fa-solid fa-pen-to-square"></i>
                </label>
            </td>
            <td class="table__data table__data-id">
                <p class="table__cell">${id}</p>
            </td>
            <td class="table__data table__data-name">
                <p class="table__cell line-clamp line-1">${name}</p>
            </td>
            <td class="table__data">
                <p class="table__cell">${value.nameCategory}</p>
            </td>
          
            <td class="table__data">
                <p class="table__cell">${vndFormat.format(originalPrice)}</p>
            </td>
            <td class="table__data">
                <p class="table__cell">${vndFormat.format(salePrice)}</p>
            </td>
            
            ${visibility ?
                `<td class="table__data table__data-visibility table__data-hide">
                <div class="button button--hover button__hide">Ẩn</div>
            </td>` :
                `<td class="table__data table__data-visibility table__data-un-hide">
                <div class="button button--hover button__un-hide">Bỏ ẩn</div>
            </td>`
            }
        </tr>`
        })
    }

    function showBeforePaging(currentPage, minPage) {
        if (currentPage > 3) {
            productPages.innerHTML += `<a class="page access_page_quickly">...</a>`
            const div = document.createElement('div')
            for (let i = 1; i < minPage; i++) {
                const a = document.createElement('a')
                a.innerText = i
                a.classList.add("page")
                a.onclick = function () {
                    changePage(a)
                }
                div.appendChild(a)
            }

            tippy('.access_page_quickly', {
                content: div, placement: 'top-start', interactive: true, duration: [500, 250], arrow: true,
            });
        }
    }

    function generationPage(currentPage, totalPage) {
        var minPage = currentPage - 2
        var maxPage = currentPage + 2;

        if (maxPage > totalPage) {
            maxPage = totalPage
        }
        if (currentPage < 3) {
            minPage = 1;
            maxPage = 5
        }
        productPages.innerHTML = ''
        showBeforePaging(currentPage, minPage)
        for (let i = minPage; i <= maxPage; i++) {
            const a = document.createElement('a');
            if (i === currentPage) {
                a.classList.add("page--current");
            }
            a.classList.add('page')
            a.innerText = i;
            a.onclick = function () {
                changePage(a)
            }
            productPages.appendChild(a);
        }
    }

    const productList = document.querySelector('.product__list-admin')
    const productPages = document.querySelector('.paging')


})