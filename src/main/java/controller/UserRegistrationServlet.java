package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (Objects.equals(password, repeatPassword)) {
            req.setAttribute("error", null);
            User user = new User(email, password, "user");
            System.out.println("Кладем юзера в бд");
            userService.addUser(user);
            resp.sendRedirect("/admin/users");
        } else {
            req.setAttribute("error", "Your password no equals");
            //resp.sendRedirect("/add_user.jsp");
            req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
        }

    }
}
