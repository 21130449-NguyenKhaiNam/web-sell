function addContactRecord(){
    $(document).ready(function (){
        $('#contact__form').on('submit', function (event){
            event.preventDefault();
            let form = $('#contact__form');
            let fullName = $('input[name=fullName].form__input').val();
            let phone = $('input[name=phone].form__input').val();
            let email = $('input[name=email].form__input').val();
            let message = $('textarea[name=message].form__textarea').val();
            let subject = $('select[name=subject].select__box').val();

            $.ajax({
                url: form.attr('action'),
                type: form.attr('method'),
                data: {
                    fullName: fullName,
                    phone: phone,
                    email: email,
                    subject: subject,
                    message: message
                },
                dataType: 'json',
                success: function (response) {
                    if(response.isContactValid){
                        const successNotification = $(".success__notification");
                        successNotification.show()
                        successNotification.html(response.succeedContact)
                        function autoRemoveSuccessNotification (){
                            setTimeout(function() {
                                successNotification.addClass('slideOut');
                                setTimeout(function() {
                                    successNotification.css('display', 'none')
                                }, 1000);
                            }, 1000);
                        }
                        autoRemoveSuccessNotification();
                    }else {
                        $.each(response.errorFields, function(errorField, errorMessage) {
                            errorField = `#${errorField}`;
                            $(errorField).text(errorMessage).show();
                            $(errorField).parent().find('.form__input').addClass('input-invalid');
                        });
                    }
                }
            })
        })
    })
}
addContactRecord()

function handleRemoveErrorInputting(){
    let fieldContents = document.querySelectorAll('.form__input');
    fieldContents.forEach(fieldContent=>{
        fieldContent.addEventListener('input',()=>{
            fieldContent.classList.remove('input-invalid');
            fieldContent.parentElement.querySelector('.error-notice').innerText = '';
        })
    })
}
handleRemoveErrorInputting();