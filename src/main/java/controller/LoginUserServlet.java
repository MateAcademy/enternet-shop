package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.UserDAOFactory;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.HashUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

//    private static final UserDao userDao = UserDAOFactory.getUserDao();
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//todo: как мне сделать при возврате на главную страницу что бы убралось поле
//мы сразу можем перейти на main_menu.jsp - а так не должно быть
        req.setAttribute("error", null);

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            password = HashUtils.getSHA256SecurePassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Optional<User> optUserByLoginPassword = userService.findUserByLoginPassword(email, password);

        if (optUserByLoginPassword.isPresent() && optUserByLoginPassword.get().isAvailable()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", optUserByLoginPassword.get());
//            resp.addCookie(new Cookie("adminTE", "TE"));
            req.getRequestDispatcher("/main_menu.jsp").forward(req, resp);
        } else {
            String error = "Ваш логин и пароль неверный!";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }

}
