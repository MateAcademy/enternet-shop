package controller.user;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import utils.Role;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey Klunniy
 */
@WebServlet("/admin/editUserData")
public class EditUserDataServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditUserDataServlet.class);
    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long id = (long) req.getSession().getAttribute("id");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            Role roleEnum = Role.valueOf(role);
            String available = req.getParameter("available");
            boolean availableBoolean = Boolean.parseBoolean(available);

            User user = new User(id, name, email, password, roleEnum, availableBoolean);

            userService.updateUser(user);
            logger.info("update user " + user);

            req.getRequestDispatcher("/admin/users").forward(req, resp);
        } catch (Exception e) {
            logger.error("in EditUserDataServlet, e=" + e);
        }
    }
}
