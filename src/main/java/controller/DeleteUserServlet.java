package controller;

import exception.TAException;
import factory.UserServiceFactory;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserServiceFactory.getUserService();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, TAException {
        String userIdToDelete = req.getParameter("id_user");
        if (userIdToDelete != null) {
            int deleteUser = userService.deleteUserById(Long.parseLong(userIdToDelete));
            if (deleteUser == 1)
                req.getRequestDispatcher("/admin/users").forward(req, resp);
        }
        throw new TAException();
    }

}
