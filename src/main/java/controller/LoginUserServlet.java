package controller;

import exception.TEAppException;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.HashUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;


/**
 * @author Sergey Klunniy
 */
@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(LoginUserServlet.class);

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

        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            try {
                password = HashUtils.getSHA256SecurePassword(password);
            } catch (NoSuchAlgorithmException e) {
                throw new TEAppException(e);
            }

            Optional<User> optUserByLoginPassword = userService.findUserByLoginPassword(email, password);

            if (optUserByLoginPassword.isPresent() && optUserByLoginPassword.get().isAvailable()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", optUserByLoginPassword.get());
//            resp.addCookie(new Cookie("adminTE", "TE"));
                req.getRequestDispatcher("main_menu.jsp").forward(req, resp);
            } else {
                String error = "Ваш логин и пароль неверный!";
                req.setAttribute("error", error);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            logger.error("login error, exception=" + e);
            req.setAttribute("error", " login error, enter correct data");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
