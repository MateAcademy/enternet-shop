package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
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
@WebServlet(value = "/enter")
public class EnterUserServlet extends HttpServlet {

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
        UserDao userDao = new UserDaoImpl();
        boolean isUserInDB = userDao.findUser(user);
        if (isUserInDB) {
            resp.sendRedirect("/main.jsp");
        } else {
            String error = "Вы ввели неправильные данные!";
            req.setAttribute("error", error);
//            resp.sendRedirect("/index.jsp");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

}
