package services.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public interface IMailServices {
    void send() throws MessagingException;
}
