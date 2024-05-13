// Thêm class 'loader__wrapper' vào body
// CSS loader được implement trong base.css
export function addSpinner() {
    $("body .loader__wrapper").removeClass('d-none').show().fadeIn();
}

export function cancelSpinner() {
    $("body .loader__wrapper").addClass('d-none').hide().fadeOut();
}