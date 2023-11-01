var inputForm = document.querySelectorAll(".input__form");

inputForm.forEach(function (input) {
    const eyeClose = input.querySelector(".icon__eye--close");
    const eyeOpen = input.querySelector(".icon__eye--open");
    const inputElement = input.querySelector("input")
    eyeClose.onclick = function (e) {
        inputElement.type = "text";
        eyeOpen.style.display = "block";
        eyeClose.style.display = "none";
    }
    eyeOpen.onclick = function (e) {
        inputElement.type = "password";
        eyeOpen.style.display = "none";
        eyeClose.style.display = "block";
    }

})


