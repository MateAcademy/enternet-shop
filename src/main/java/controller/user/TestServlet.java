package controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Serhii Klunniy
 */
//@WebServlet("/hello")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<h1> Hello + " + name + "surname " + surname + "</h1>");
        pw.println("</html>");
//        resp.sendRedirect("/test.jsp");
        //req.getRequestDispatcher("/test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/test.jsp");
    }
}
