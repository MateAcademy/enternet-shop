package service.impl;

import model.Code;
import org.apache.log4j.Logger;
import service.MailServiceForgotPassword;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Sergey Klunniy
 */
public class MailServiceForgotPasswordImpl implements MailServiceForgotPassword {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);
    private final String username = "ava.trackensure@gmail.com";
    private final String password = "abmtssclpskmpvmp";


    @Override
    public void sendEmailTo(String email) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("password recovery");
//todo достать пароль из бд и отправить его
            String password ="text";
            message.setText(password);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }





}
