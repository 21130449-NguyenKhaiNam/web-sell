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

const folderAvatarUser = "/assets/img/user/"

$(".admin__detail").each((index, item) => {
    item.addEventListener("click", function () {
        const userId = item.getAttribute("data-id");
        console.log(userId)
        showDialog(userId);
    });

});

function showDialog(userId) {
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
            console.log(data)
        },
        error: function (error) {
        },
    });
}


function applyDataDialog(user) {
    const model = $("#model");
    model.find("#staticAvatar").attr("src", user.avatar? folderAvatarUser + user.avatar : folderAvatarUser + "userDefaultAvatar.jpg");
    model.find("#staticUsername").val(user.username);
    model.find("#staticEmail").val(user.email);
    model.find("#staticFullName").val(user.fullName);
    model.find("#staticPhone").val(user.phone);
    model.find("#staticDate").val(user.birthDay);
    model.find("#staticAddress").text(user.address);
    model.find("#staticRole").val(user.role);
}