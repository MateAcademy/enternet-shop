package controller;

import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.CheckIsUserExist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(LoginServlet.class);

    //    private static final UserDao userDao = UserDAOFactory.getUserDao();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//todo: как мне сделать при возврате на главную страницу, что бы убралось поле
//мы сразу можем перейти на main_menu.jsp - а так не должно быть

        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");

            HttpSession session = req.getSession();
            String email = req.getParameter("email");
            String password = req.getParameter("password");

//todo: сделать проверку на валидацию емейла
            if (email != null & password != null) {
                boolean isSuchUserExists = CheckIsUserExist.isUserExist(email, password, session);
                if (isSuchUserExists) {
                    if (Objects.equals(session.getAttribute("role"), "admin")) {
                        logger.info(" login get to main_menu_admin.jsp");
                        req.getRequestDispatcher("main_menu_admin.jsp").forward(req, resp);
                    } else {
                        logger.info(" login get to main_menu.jsp");
                        req.getRequestDispatcher("main_menu.jsp").forward(req, resp);
                    }
                } else {
                    String massage = "такого юзера нет в базе данных";
                    loginError(req, resp, massage);
                }
            }
//todo  добавить куки resp.addCookie(new Cookie("adminTE", "TE"));
            else {
                String massage = "email или пароль не верные";
                loginError(req, resp, massage);
            }
        } catch (Exception e) {
            String massage = "login error, exception=" + e;
            loginError(req, resp, massage);
        }
    }

    private void loginError(HttpServletRequest req, HttpServletResponse resp, String massage) throws ServletException, IOException {
        String error = "Ваш логин и пароль неверный!";
        req.setAttribute("error", error);
        logger.error(massage);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
