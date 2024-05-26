// // deleteDialog
// function openDeleteDialog(userId) {
//     document.getElementById('delete-dialog').style.display = 'block';
//     var confirmDeleteLink = document.getElementById('confirm-delete');
//     confirmDeleteLink.href = 'Delete?userId=' + userId;
// }
// function closeDeleteDialog() {
//     document.getElementById('delete-dialog').style.display = 'none';
// }
// document.getElementById('cancel-delete').addEventListener('click', closeDeleteDialog);
// document.getElementById('close-dialog').addEventListener('click', closeDeleteDialog);
//
// // updateDialog
// function openUpdateDialog(userId, username, fullName, gender, email, phone, address, birthDay, role) {
//     document.getElementById('update-user-dialog').style.display = 'block';
//
//     document.getElementById('id--Update').value = userId;
//     document.getElementById('username--Update').value = username;
//     document.getElementById('fullName--Update').value = fullName;
//     document.getElementById('gender--Update').value = gender;
//     document.getElementById('email--Update').value = email;
//     document.getElementById('phone--Update').value = phone;
//     document.getElementById('address--Update').value = address;
//     document.getElementById('birthDay--Update').value = birthDay;
//     document.getElementById('role--Update').value = role;
//     console.log(role)
// }
// function closeUpdateDialog() {
//     document.getElementById('update-user-dialog').style.display = 'none';
// }
// document.getElementById('close-update-user-dialog').addEventListener('click', closeUpdateDialog);
// document.getElementById('cancel-update-user').addEventListener('click', closeUpdateDialog);

// const folderAvatarUser = "/assets/img/user/"

// $(".admin__detail").each((index, item) => {
//     item.addEventListener("click", function () {
//         const userId = item.getAttribute("data-id");
//         showDialog(userId);
//     });
//
// });


$(document).ready(() => {
    const modalUpdate = $("#modal-update");
    const pagingElement = $(".pagination");
    const formSearch = $("#form-search");
    const searchInput = $("#search-input")
    callAjaxToPage(1);
    formSearch.on("submit", function (e) {
        e.preventDefault();
    })
    searchInput.on("input", () => {
        callAjaxToPage(1);
    })

    function callAjaxToPage(pageNumber) {
        $.ajax({
            url: "/api/admin/user/search",
            type: "GET",
            data: {
                search: searchInput.val() ?? "",
                page: pageNumber,
            },
            success: function (resp) {
                setupData(resp.lists);
                paging(resp.page, resp.totalPage);
            },
            error: function (error) {
                console.log("loi khong hien thi san pham")
            },
        });
    }

    function paging(pageCurrent, totalPage) {
        const pageShowing = 3
        pagingElement.html("");
        let i = 1;
        if (pageCurrent > 1) {
            i = pageCurrent - 1;
            const prev = `  <a page="${i}" href="" class="previous__page"><i class="fa-solid fa-chevron-left"></i></a>`
            pagingElement.append(prev)
        }
        for (; i <= pageCurrent + Math.min(pageShowing, totalPage - pageCurrent); i++) {
            if (i === pageCurrent) {
                pagingElement.append(`<a class="active" page="${i}" href="#">${i}</a>`);
            } else {
                pagingElement.append(`<a  page="${i}" class="page__forward" href="#">${i}</a>`);
            }
        }
        if (totalPage != pageCurrent) {
            const next = `<a href="" page="${pageCurrent + 1}"  class="next__page"><i class="fa-solid fa-chevron-right"></i></a>`
            pagingElement.append(next);
        }

        //     Set up event for paging
        eventPaging(pagingElement.find("a"));

        function eventPaging(pageButton) {
            pageButton.on("click", function (e) {
                e.preventDefault();
                let page = $(this).attr("page")
                callAjaxToPage(page);
            })
        }
    }

    const table = $(".table tbody")

    function setupData(data) {
        table.html(data.map(loadUserRow).join(""));
        handleUpdateUser();
    }

    const role = {
        0: "Khách",
        1: "Mod",
        2: "Admin"
    }

// onclick="openUpdateDialog(${user.id}, '${user.username}', '${user.fullName}', '${user.gender}', '${user.email}', '${user.phone}', '${user.address}', '${user.birthDay}','${user.role}')">
    function loadUserRow(user) {
        return ` <tr class="table__row">
                <td class="table__data">
                    <a id="updateUser" class="update-user" data-bs-toggle="modal" data-bs-target="#modal-update" data-id="${user.id}">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>
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
                    <p class="table__cell">${formatDate(user.birthDay)}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">${user.phone}</p>
                </td>
                <td class="table__data">
                    <p class="table__cell">
                    ${role[user.role]}
                    </p>
                </td>
            </tr>`
    }

// onclick="openDeleteDialog(${user.id})"

    function handleUpdateUser() {
        const updateUserBtn = $(".update-user");
        updateUserBtn.on("click", function () {
            resetModalUpdate();
            showModalUpdate($(this).attr("data-id"));
        });
    }

    function resetModalUpdate() {
        modalUpdate.find("input").val("");
    }

    function showModalUpdate(userId) {
        $.ajax({
            url: "/api/admin/user",
            type: "GET",
            data: {
                id: userId
            },
            dataType: "json",
            cache: true,
            success: function (data) {
                applyDataDialog(data);
            },
            error: function (error) {
            },
        });
    }


    function applyDataDialog(user) {
        // model.find("#staticAvatar").attr("src", user.avatar ? folderAvatarUser + user.avatar : folderAvatarUser + "userDefaultAvatar.jpg");
        modalUpdate.find("#staticUsername").val(user.username);
        modalUpdate.find("#staticEmail").val(user.email);
        modalUpdate.find("#staticFullName").val(user.fullName);
        modalUpdate.find("#staticPhone").val(user.phone);
        modalUpdate.find("#staticDate").val(user.birthDay);
        modalUpdate.find("#staticAddress").text(user.address);
        modalUpdate.find("#staticRole").val(user.role);
    }

    function formatDate(dateStr) {
        let dateObj = new Date(dateStr);

        let day = String(dateObj.getDate()).padStart(2, '0'); // Adds leading zero if needed
        let month = String(dateObj.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed, add 1
        let year = dateObj.getFullYear();

        return `${day}/${month}/${year}`;
    }

    // Debounce function
    function debounce(callback, delay) {
        let timeoutId;
        return function () {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => {
                callback();
            }, delay);
        };
    }
});