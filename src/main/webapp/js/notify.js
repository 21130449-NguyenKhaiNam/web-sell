const toastList = document.querySelector(".toast__list");
const bodyElement = document.querySelector("body");
if (bodyElement.style.position === undefined) {
    bodyElement.style.position = "relative";
}

function notifySuccess(obj) {
    toastList.innerHTML = `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--success">
                    <i class="toast__icon fa-solid fa-check"></i>
                </span>
                <strong class="toast__title">${obj.title}</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
             ${obj.body}
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

function notifyFailed(obj) {
    toastList.innerHTML = `<div class="toast" id="snackbar">
            <div class="toast__header">
                <span class="toast__icon-wrapper toast__icon--failed">
                    <i class="toast__icon fa-solid fa-xmark"></i>
                </span>
                <strong class="toast__title">${obj.title}</strong>
                <i class="toast__icon-close fa-solid fa-xmark"></i>
            </div>
            <div class="toast__body">
                 ${obj.body}
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

export function alert(onconfirm, oncancel, message) {
    Swal.fire({
        title: message ? message.notify : "Bạn có muốn lưu thay đổi không?",
        showDenyButton: true,
        confirmButtonText: "Lưu",
        denyButtonText: "Không lưu",
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            onconfirm();
        } else if (result.isDenied) {
            if (oncancel) oncancel();
            else Swal.fire({
                title: message ? " message.success" : "Thay đổi của bạn đã sẽ không được lưu",
                icon: "info"
            });
        }
    });
}

