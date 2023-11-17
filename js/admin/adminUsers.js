
var bodyTable = document.querySelector(".table tbody");
var searchInput = document.getElementById("search-input");
var removeButton = document.getElementById("button-remove-product");

function loadDataToTable(user) {
    return `<tr class="table__row">
                <td class="table__date-checkbox">
                    <label class="check">
                        <input type="checkbox" name="passing" class="filter__input" data-userid="${user.id}" hidden="true">
                    </label>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.id}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.username}</p>
                </td>
           
                <td class="table__data">
                    <p class="table__cell">${user.email}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell table__data--fullname">${user.fullName}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.gender}</p>
                </td>
                <td class="table__data table__data--birthday">
                    <p class="table__cell">${user.birthDate}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.phone}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.address}</p>
                </td>
            </tr>`;
}

// xóa người dùng
function deleteSelectedUsers() {
    var checkboxes = document.querySelectorAll('.table__date-checkbox input[type="checkbox"]:checked');
    var userIdsToDelete = Array.from(checkboxes).map(function (checkbox) {
        return checkbox.dataset.userid;
    });

    // Lọc danh sách người dùng
    listUser = listUser.filter(function (user) {
        return !userIdsToDelete.includes(user.id.toString());
    });

    renderTable(listUser.slice(2));
}

//  tìm kiếm người dùng
function searchUsers(keyword) {
    var filteredUsers = listUser.filter(function (user) {
        return (
            (user.username.toLowerCase().includes(keyword.toLowerCase()) ||
             user.email.toLowerCase().includes(keyword.toLowerCase()) ||
             user.fullName.toLowerCase().includes(keyword.toLowerCase())
            ) && listUser.indexOf(user) > 1
        );
    });

    // Hiển thị danh sách người dùng đã lọc
    renderTable(filteredUsers);
}

searchInput.addEventListener("input", function (event) {
    var keyword = event.target.value.trim();
    searchUsers(keyword);
});

removeButton.addEventListener("click", deleteSelectedUsers);

//hiển thị danh sách người dùng lên bảng
function renderTable(users) {
    var htmls = users.map(function (element) {
        return loadDataToTable(element);
    });
    bodyTable.innerHTML = htmls.join("");
}

// Load dữ liệu
renderTable(listUser.slice(2));



