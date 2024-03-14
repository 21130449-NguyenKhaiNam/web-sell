package services.mail;

import javax.mail.MessagingException;

public interface IMailServices {
    void send() throws MessagingException;
}
