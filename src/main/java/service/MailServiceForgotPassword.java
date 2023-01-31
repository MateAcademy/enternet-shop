package service;

import model.Code;

/**
 * @author Sergey Klunniy
 */
public interface MailServiceForgotPassword {
    void sendEmailTo(String email);
}
