package controller.user;

import factory.UserServiceFactory;
import org.apache.log4j.Logger;
import service.UserService;
import utils.AppConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        AppConstants appConstants = AppConstants.getInstance();
        appConstants.setUserList(userService.getAll());

        logger.info("initialize userList in GetAllUsersServlet");

        req.setAttribute("allUsers", appConstants.getUserList());
        req.getRequestDispatcher("/show_all_users.jsp").forward(req, resp);
    }

}
