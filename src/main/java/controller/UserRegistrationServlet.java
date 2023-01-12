package controller;

import factory.UserServiceFactory;
import lombok.SneakyThrows;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.HashUtils;
import utils.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
//@WebServlet(value = "/admin/register", loadOnStartup = 1)
public class UserRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserRegistrationServlet.class);

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("we in class: UserRegistrationServlet, method: doGet()  ");
        req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("repeatPassword");

            if (!password.equals(repeatPassword)) {
                req.setAttribute("error", "Not valid password");
                logger.debug("no password equals");
                req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
                return;
            }

            if (session.getAttribute("isRegistered") != null && session.getAttribute("isRegistered").equals("true")) {
                logger.debug("session isRegistered = true");
                accessError(resp);
                return;
            }

            boolean findUserByEmail = userService.findUserByEmail(email);

            if (findUserByEmail) {
                logger.debug("findUserByEmail = true, access exit");
                accessError(resp);
                return;
            } else {
                User user = new User(name, email, password);
                userService.addUser(user);
                session.setAttribute("isRegistered", "true");

//TODO почему сессия=true второй раз когда логинюсь resp.sendRedirect("/index.jsp"?
//                  req.getRequestDispatcher("/index.jsp").forward(req, resp);
                logger.debug("we add user to db, email=" + email + ", password=" + password);
                resp.sendRedirect("/index.jsp");
            }
        } catch (Exception e) {
            logger.error("don't add new user to db, exception=" + e);
            //сделать что бы логгер обрабатывал ошибку и на фронт отправлять что-то, открывать
        }
    }

    private void accessError(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("You already have account, just sin in!");
    }
}
