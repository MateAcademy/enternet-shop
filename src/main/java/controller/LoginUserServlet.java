package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.UserDAOFactory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */
@WebServlet(value = "/login")
public class LoginUserServlet extends HttpServlet {

    private static final UserDao userDao = UserDAOFactory.getUserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//todo: как мне сделать при возврате на главную страницу что бы убралось поле
        req.setAttribute("error", null);

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(email, password);

        boolean isUserInDB = userDao.findUser(user);
        if (isUserInDB) {
            resp.sendRedirect("/main.jsp");
        } else {
            String error = "Вы ввели неправильные данные!";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

}
