package controller;

import factory.UserServiceFactory;
import lombok.SneakyThrows;
import model.User;
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

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
        //forward мы отправили пользователя с его запросом на /add_user.jsp
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("repeatPassword");

            // проверку сделать есть ли такой юзер
            boolean checkIfUserExist = userService.findUserByEmail(email);

            HttpSession session = req.getSession();

            if (session.getAttribute("isRegistered") != null && session.getAttribute("isRegistered").equals("true") || checkIfUserExist == true) {
                PrintWriter out = resp.getWriter();
                out.print("You already have account, just sin in!");
            } else {
                if (Objects.equals(password, repeatPassword)) {
                    req.setAttribute("error", null);

                    String salt = HashUtils.getRandomSalt();
                    String hashPassword = HashUtils.getSHA256SecurePassword(password, salt);

                    User user = new User(name, email,  password, salt, hashPassword, Role.USER);

                    userService.addUser(user);
                    session.setAttribute("isRegistered", "true");
                    resp.sendRedirect("/index.jsp");
                } else {
                    req.setAttribute("error", "Your password no equals");
                    //resp.sendRedirect("/add_user.jsp");
                    req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            //сделать что бы логгер обрабатывал ошибку и на фронт отправлять что-то, открывать

        }





    }
}
