package controller.next;

import model.User;
import org.apache.log4j.Logger;
import utils.HashUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Sergey Klunniy
 */
@WebServlet(value = "/registration", loadOnStartup = 1)
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("user with id (?) start registration");
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        /**
         * add user to DB:
         */
        if (password.equals(repeatPassword)) {

            try {
                String hashPassword = HashUtils.getSHA256SecurePassword(password);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            User user = new User(email, password);

        } {
            req.setAttribute("error", "ошибка в пароле");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }


    }

}
