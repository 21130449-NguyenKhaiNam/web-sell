function openDeleteDialog(userId) {
    document.getElementById('delete-dialog').style.display = 'block';
    var confirmDeleteLink = document.getElementById('confirm-delete');
    confirmDeleteLink.href = 'delete?userId=' + userId;
}
function closeDeleteDialog() {
    document.getElementById('delete-dialog').style.display = 'none';
}
document.getElementById('cancel-delete').addEventListener('click', closeDeleteDialog);
document.getElementById('close-dialog').addEventListener('click', closeDeleteDialog);