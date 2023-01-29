package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
@WebServlet("/mainMenuServlet")
public class MainMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
        if (role != null) {
            role = role.toLowerCase();
        }

       if (Objects.equals(role, "admin")) {
           req.getRequestDispatcher("/main_menu_admin.jsp").forward(req, resp);
       } else if (Objects.equals(role, "uer")){
           req.getRequestDispatcher("/main_menu.jsp").forward(req, resp);
       }
    }
}
