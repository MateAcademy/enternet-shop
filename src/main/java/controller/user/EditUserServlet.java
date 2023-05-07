package controller.user;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import utils.AppConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/edit")
public class EditUserServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(EditUserServlet.class);
    private UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdToDelete = req.getParameter("id_user");

        Optional<User> editeOptionalUserFromDb = null;
        User userFromDb = null;

        if (userIdToDelete != null) {
            editeOptionalUserFromDb = userService.getUserById(Long.parseLong(userIdToDelete));

            User userFromAppConstant = null;
            for (User user1 : AppConstants.getInstance().getUserList()) {
                if (userIdToDelete.equals(String.valueOf(user1.getId_user()))) {
                    userFromAppConstant = user1;
                    break;
                }
            }

            if (editeOptionalUserFromDb.isPresent()) {
                userFromDb = editeOptionalUserFromDb.get();
            }

            if (userFromDb != null && userFromAppConstant != null && userFromDb.equals(userFromAppConstant)) {
                req.getSession().setAttribute("id", userFromDb.getId_user());
                req.setAttribute("name", userFromDb.getName());
                req.setAttribute("email", userFromDb.getEmail());
                req.setAttribute("password", userFromDb.getPassword());
                req.setAttribute("role", userFromDb.getRole());
                req.setAttribute("available", userFromDb.isAvailable());

                req.getRequestDispatcher("/admin/edite_user.jsp").forward(req, resp);
            }
        }
    }

}
