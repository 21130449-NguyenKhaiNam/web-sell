const deliveryInfoRadioButtons = document.querySelectorAll(".delivery__info--container .radio__button");
const paymentMethodRadioButtons = document.querySelectorAll(".payment__method--container .radio__button");
const customizeDeliveryInfo = document.getElementById("customize__info--form");

function sectionSelectionHandleEvent(radioButtonElements) {
    let preRadioButtonElement = null;
    radioButtonElements.forEach((radioButtonElement, index) => {
        radioButtonElement.addEventListener("change", () => {
            if (preRadioButtonElement) {
                Object.assign(preRadioButtonElement.parentElement.style, {
                    backgroundColor: null,
                    color: null,
                    opacity: null
                })
            }
            if (radioButtonElement.checked) {
                Object.assign(radioButtonElement.parentElement.style, {
                    backgroundColor: "var(--blue-color-medium)",
                    color: "white",
                    opacity: "0.8"
                })
                preRadioButtonElement = radioButtonElement;
            }

            if (radioButtonElements === deliveryInfoRadioButtons) {
                if (index === radioButtonElements.length - 1) {
                    customizeDeliveryInfo.querySelectorAll('.error__notice').forEach(errorNotice => {
                        errorNotice.textContent = "";
                    });

                    Object.assign(customizeDeliveryInfo.style, {
                        display: "grid",
                        gap: "24px"
                    })
                } else {
                    customizeDeliveryInfo.style.display = "none"
                }
            }
        })
    })
}
sectionSelectionHandleEvent(deliveryInfoRadioButtons)
sectionSelectionHandleEvent(paymentMethodRadioButtons)