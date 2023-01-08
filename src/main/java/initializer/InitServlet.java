//package initializer;
//
//import dao.impl.UserDaoHibernateImpl;
//import model.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//
///**
// * @author Sergey Klunniy
// */
//@WebServlet(name = "IntServlet", value = "/init", loadOnStartup = 1)
//public class InitServlet extends HttpServlet {
//
//    @Override
//    public void init() throws ServletException {
//        System.out.println("тест");
//        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
//        User user = new User("Herman", "test@test.ru", "123", "admin");
//        User user2 = new User("Herman2", "test@test.ru", "123", "admin");
//        userDao.addUser(user);
//        userDao.addUser(user2);
//    }
//}
