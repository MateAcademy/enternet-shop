//package controller.user;
//
//import factory.UserServiceFactory;
//import model.User;
//import org.apache.log4j.Logger;
//import service.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Objects;
//import java.util.Optional;
//
///**
// * @author Sergey Klunniy
// */
//@WebServlet(value = "/register2", loadOnStartup = 1)
//public class UserRegistration2Servlet extends HttpServlet {
//
//    private static final Logger logger = Logger.getLogger(UserRegistration2Servlet.class);
//    private int tryToRegister = 2;
//    private int countTryToRegister = 0;
//    private static final UserService userService = UserServiceFactory.getUserService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Object tryToGer = session.getAttribute("register");
//        String role = (String) session.getAttribute("role");
//
//        if (tryToGer != null && (int) tryToGer > tryToRegister && !Objects.equals(role, "admin")) {
//            resp.sendRedirect("403.jsp");
//        }
//        req.getSession().setAttribute("register", ++countTryToRegister);
//
//        logger.debug("we in class: UserRegistrationServlet, method: doGet()  ");
//        req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        try {
//            HttpSession session = req.getSession();
//
//            String name = req.getParameter("name");
//            String email = req.getParameter("email");
//            String password = req.getParameter("password");
//            String repeatPassword = req.getParameter("repeatPassword");
//
//            String role = (String) session.getAttribute("role");
//            if (role != null) {
//                role = role.toLowerCase();
//            }
//
//            if (!password.equals(repeatPassword)) {
//                req.setAttribute("error", "Not valid password");
//                logger.debug("no password equals");
////проверить туда ли мы преходим
//                req.getRequestDispatcher("/add_user.jsp").forward(req, resp);
//                return;
//            }
//
//            String isRegistered = (String) session.getAttribute("isRegistered");
//
//            if (isRegistered != null && isRegistered.equals("true") && !Objects.equals(role, "admin")) {
//                logger.debug("session isRegistered = true");
//                accessError(resp);
//                return;
//            }
//
//            Optional<User> findUserByEmail = userService.findUserByEmail(email);
////todo проверить вот это:
//            if (findUserByEmail.isPresent()) {
//                logger.debug("findUserByEmail = true, access exit");
//                accessError(resp);
//                return;
//            } else {
//                User user = new User(name, email, password);
//                userService.addUser(user);
//                session.setAttribute("isRegistered", "true");
//
////TODO почему сессия=true второй раз когда логинюсь resp.sendRedirect("/index.jsp"?
////                  req.getRequestDispatcher("/index.jsp").forward(req, resp);
//                logger.debug("we add user to db, email=" + email + ", password=" + password);
//
//                if (Objects.equals(role, "admin")) {
////                    resp.sendRedirect("/main_menu_admin.jsp");
//                    resp.sendRedirect("/admin/users");
//                } else if(Objects.equals(role, "user")){
//                    resp.sendRedirect("/main_menu.jsp");
//                }
//                else {
//                    resp.sendRedirect("/index.jsp");
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
