package services;

import javax.mail.MessagingException;

public interface IMailServices {
    void send() throws MessagingException;
}
