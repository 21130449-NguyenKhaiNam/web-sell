//loadAvatar
const imgDiv = document.querySelector('.user__img');
const img = document.querySelector('#photo');
const file = document.querySelector('#file');
const  uploadbtn = document.querySelector('#uploadbtn');

file.addEventListener('change', function (){
    const chooseFile = this.files[0];
    if (chooseFile){
        const reader = new FileReader();
        reader.addEventListener('load', function (){
            img.setAttribute('src', reader.result);
        })
        reader.readAsDataURL(chooseFile);
    }
})

//close_open--eye
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