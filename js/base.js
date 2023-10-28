// Sign in
var accountElement = document.querySelector(".account__wrapper");
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
// Log out
var logOutElement = document.querySelector(".setting__logOut");
logOutElement.onclick = function () {
    localStorage.removeItem("username");
    location.reload();
}