package controller;

import org.apache.log4j.Logger;
import service.MailServiceForgotPassword;
import service.impl.MailServiceForgotPasswordImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ForgotPassword.class);
    private int countSendMessage = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("error", "мы отправили пароль на Вашу почту!");
        req.setAttribute("error2", "мы отправили пароль на Вашу почту!");

        if (countSendMessage == 2) {
           logger.info("countSendMessage = 2, forward to index.jsp");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        session.setAttribute("countSendMessage", ++countSendMessage);
        String email = req.getParameter("email");

//todo сделать проверку на валидность емейла
        MailServiceForgotPassword mailServiceForgotPassword = new MailServiceForgotPasswordImpl();
        if (email!=null) {
            logger.info("try to resent password to email:" + email);
            mailServiceForgotPassword.sendEmailTo(email);
            req.getRequestDispatcher("index2.jsp").forward(req, resp);
        } else {
            logger.info("try to resent password, but email=null");
            req.getRequestDispatcher("index2.jsp").forward(req, resp);
        }

    }
}
