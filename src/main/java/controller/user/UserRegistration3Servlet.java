//package controller.user;
//
//import factory.UserServiceFactory;
//import model.User;
//import org.apache.log4j.Logger;
//import service.UserService;
//import utils.CheckMaximumNumberAttemptsToRegister;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Objects;
//import java.util.Optional;
//
//public class UserRegistration3Servlet extends HttpServlet {
//
//    private static final Logger logger = Logger.getLogger(UserRegistration3Servlet.class);
//    private static final UserService userService = UserServiceFactory.getUserService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CheckMaximumNumberAttemptsToRegister nar = new CheckMaximumNumberAttemptsToRegister();
//        boolean canIRegister = nar.canIRegister(req);
//
//        if (canIRegister) {
//            logger.debug("we in registration form");
//            req.getRequestDispatcher("/add_user3.jsp").forward(req, resp);
//        } else {
//            logger.debug("we can't registration, go to 403.jsp ");
//            resp.sendRedirect("403.jsp");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        try {
//            logger.debug("trying to register a user");
//            HttpSession session = req.getSession();
//
//            Object isRegisteredFromSession = session.getAttribute("isRegistered");
//
//            if (isRegisteredFromSession != null) {
//                logger.debug("session isRegistered = true, accessError");
//                accessError(resp);
//            } else {
//                String name = req.getParameter("name");
//                String email = req.getParameter("email");
//                String password = req.getParameter("password");
//                String repeatPassword = req.getParameter("repeatPassword");
//
//                if (!Objects.equals(password, repeatPassword)) {
//                    req.setAttribute("error", "Not valid password");
//                    logger.debug("no password equals");
//                    req.getRequestDispatcher("/add_user3.jsp").forward(req, resp);
//                } else {
//                    Optional<User> findUserByEmail = userService.findUserByEmail(email);
//                    if (findUserByEmail.isPresent()) {
//                        logger.debug("findUserByEmail = true, access exit");
////можно сделать что бы тот кто логиниться не знал что такой юзер есть
//                        accessError(resp);
//                    } else {
//                        User user = new User(name, email, password);
//                        userService.addUser(user);
//                        session.setAttribute("isRegistered", "true");
//
//                        req.getSession().setAttribute("role", "user");
//                        logger.debug("we add user to database, email=" + email + ", password=" + password);
//                        resp.sendRedirect("/main_menu.jsp");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("don't add new user to db, exception=" + e);
//            //сделать что бы логгер обрабатывал ошибку и на фронт отправлять что-то, открывать
//            accessError(resp);
//        }
//    }
//
//    private void accessError(HttpServletResponse resp) throws IOException {
//        PrintWriter out = resp.getWriter();
//        out.print("You already have account, just sin in!");
//    }
//}
