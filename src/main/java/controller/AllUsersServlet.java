package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.AppConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.AppConstants.*;

/**
 * @author Sergey Klunniy
 */
@WebServlet(value = "/admin/users")
public class AllUsersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllUsersServlet.class);

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userList = userService.getAll();
        logger.info("initialize userList in AllUsersServlet");

        req.setAttribute("allUsers", userList);
        req.getRequestDispatcher("/show_all_users.jsp").forward(req, resp);
    }

}
