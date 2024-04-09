
import services.mail.IMailServices;
import services.mail.MailResetPasswordServices;

import javax.mail.MessagingException;

public class TestMail {
    public static void main(String[] args) {
        IMailServices mailServices = new MailResetPasswordServices("ducvui2003@gmail.com", "ducvui2003", "123123");
        try {
            mailServices.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
