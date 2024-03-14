package services.mail;

import properties.MailProperties;
import services.mail.IMailServices;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MailResetPasswordServices implements IMailServices {
    private String emailTo;
    private String email;
    private String tokenResetPassword;

    public MailResetPasswordServices(String emailTo, String email, String tokenResetPassword) {
        this.emailTo = emailTo;
        this.email = email;
        this.tokenResetPassword = tokenResetPassword;
    }

    @Override
    public void send() throws MessagingException {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailProperties.getEmail(), MailProperties.getPassword());
            }
        };

//        Create Session
        Session session = Session.getInstance(MailProperties.getProperties(), auth);
//        Create message
        Message message = new MimeMessage(session);
//        From
        message.setFrom(new InternetAddress(MailProperties.getEmail()));
//        To
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
//        Subject
        String subjectMess = "Quên mật khấu";
        message.setSubject(subjectMess);
//        Set no reply
        message.setReplyTo(null);
//        Content
        InputStream is = MailProperties.class.getClassLoader().getResourceAsStream("templates/templateEmailResetPassword.html");
        String htmlTemplate = htmlTemplate(is);
        message.setContent(htmlTemplate, "text/html; charset = UTF-8");

//        Send mail
        Transport.send(message);
    }

    private String htmlTemplate(InputStream is) {
        String template = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            template += line + "\n";
        }
        template = template.replace("%%EMAIL%%", email);
        template = template.replace("%%TOKENRESETPASSWORD%%", tokenResetPassword);
        return template;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenResetPassword() {
        return tokenResetPassword;
    }

    public void setTokenResetPassword(String tokenResetPassword) {
        this.tokenResetPassword = tokenResetPassword;
    }
}
