function handleEventShoppingCart() {
    const promotionCodeElement = document.getElementById("promotion_code");
    const promotionSidebar = document.querySelector(".promotion__sidebar")
    const promotionDisplayAll = document.querySelector(".promotion__all span:last-child");
    const iconBackShoppingCart = document.querySelector(".promotion__header i");
    const buttonBackShoppingCart = document.querySelector(".promotion__footer button")

    promotionDisplayAll.addEventListener("click", ()=>{
        promotionSidebar.classList.add("visible")
    })

    iconBackShoppingCart.addEventListener("click",()=>{
        promotionSidebar.classList.remove("visible")
    })

    buttonBackShoppingCart.addEventListener("click",()=>{
        promotionSidebar.classList.remove("visible")
    })

    function handleCopyDiscountCode(){
        const copyButtonElements = document.querySelectorAll(".button__copy");

        copyButtonElements.forEach(copyButtonElement =>{
            let originalContent = copyButtonElement.innerHTML;
            copyButtonElement.addEventListener('click', ()=>{
                copyButtonElement.innerHTML = `Đã sao chép <i class="fa-solid fa-copy"></i>`;
                setTimeout(() => {
                    copyButtonElement.innerHTML = originalContent;
                }, 1000);

                const codeToCopy = copyButtonElement.getAttribute('data-code');
                copyToClipboard(codeToCopy)
                    .then(() => {
                        console.log(codeToCopy);
                    })
                    .catch(error =>{
                        console.error("Không thể sao chép: ", error);
                    });
            })
        })
    }
    handleCopyDiscountCode();
}
handleEventShoppingCart();

async function copyToClipboard(text) {
    try {
        await navigator.clipboard.writeText(text);
    } catch (error) {
        throw new Error("Không thể sao chép vào clipboard: ", error);
    }
}

function preventSubmitWithEmptyCode(){
    const promoApplyForm = document.getElementById('promotion__form');
    const inputPromoCode = promoApplyForm.querySelector("#promotion_code")
    promoApplyForm.onsubmit = function (event){
        if(inputPromoCode.value.trim() === '' || inputPromoCode.value.trim() === null){
            event.preventDefault();
        }else{
            promoApplyForm.submit();
        }
    }
}
preventSubmitWithEmptyCode();