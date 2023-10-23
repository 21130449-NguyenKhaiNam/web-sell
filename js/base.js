// Sign in
var accountElement = document.querySelector(".account");
var signInSignUp = document.querySelector(".nav__cta");
window.onload = function () {
    const username = localStorage.getItem("username");
    if (username) {
        console.log(username)
        signInSignUp.style.display = "none";
        accountElement.style.display = "flex";
        const accountNameElement = document.querySelector(".account__name");
        accountNameElement.innerText = username;
    }
}
var settingShowElement = document.querySelector(".account .setting__show");
var settingListElement = document.querySelector(".account .setting__list");
console.log(settingShowElement);
settingShowElement.onclick = function () {
    settingListElement.classList.toggle("setting__list--show");
}
// Log out
var logOutElement = document.querySelector(".setting__logOut");
logOutElement.onclick = function () {
    localStorage.removeItem("username");
    location.reload();
}