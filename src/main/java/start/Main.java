package start;

import model.Code;
import model.User;
import service.MailService;
import service.impl.MailServiceImpl;

public class Main {
    public static void main(String[] args) {
        MailService mailService = new MailServiceImpl();
        Code code = new Code("1234", new User());
        mailService.sendEmailWithCode(code);
    }
}
