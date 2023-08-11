package controller;

import org.apache.log4j.Logger;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Sergey Klunniy
 */
@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(StartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("role", "user");

        logger.debug("we went back to index.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
