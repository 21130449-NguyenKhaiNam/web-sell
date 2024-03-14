// var obj = {
//     itemOnList: "",
//     limit: 6
// }

function Paging(obj) {
    var currentPage = 1;
    var limit = obj.limit;
    var listItem = document.querySelectorAll(obj.itemSelector);

    function loadItem() {
        let beginGet = limit * (currentPage - 1);
        let endGet = limit * currentPage - 1;
        listItem.forEach(function (item, index) {
            if (index >= beginGet && index <= endGet)
                item.style.display = obj.displayShowType;
            else
                item.style.display = "none";
        });
        listPage();
    }

    function changePage(i) {
        currentPage = i;
        loadItem();
    }

    function listPage() {
        let count = Math.ceil(listItem.length / limit);
        let listPage = document.querySelector(obj.listPage);
        listPage.innerHTML = "";

        // Create prev button
        if (currentPage != 1) {
            let prevButton = document.createElement(obj.tagNameItemPage);
            prevButton.classList.add(obj.classNameItemPage);
            prevButton.classList.add(obj.prevBtn);
            prevButton.innerText = "Quay lại";
            prevButton.addEventListener("click", function () {
                changePage(currentPage - 1);
            });
            listPage.appendChild(prevButton);
        }

        for (let i = 1; i <= count; i++) {
            let newPage = document.createElement(obj.tagNameItemPage);
            newPage.classList.add(obj.classNameItemPage);
            newPage.innerText = i;
            if (i == currentPage) newPage.classList.add(obj.activeItemPage);
            newPage.addEventListener("click", function () {
                changePage(i)
            });
            listPage.appendChild(newPage);
        }

        // Create next button
        if (currentPage != count) {
            let nextButton = document.createElement(obj.tagNameItemPage);
            nextButton.classList.add(obj.classNameItemPage);
            nextButton.classList.add(obj.nextBtn);
            nextButton.innerText = "Tiếp theo";
            nextButton.addEventListener("click", function () {
                changePage(currentPage + 1)
            });
            listPage.appendChild(nextButton);
        }
    }

    loadItem();
}