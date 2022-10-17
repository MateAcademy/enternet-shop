package service;

import model.Code;

/**
 * @author Sergey Klunniy
 */
public interface MailService {
    void sendConfirmCode(Code code);
}
