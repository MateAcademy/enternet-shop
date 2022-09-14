package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author Sergey Klunniy
 */
public class UserServiceFactory {
    private static volatile UserService userService;

    static {
        getInstance();
    }
    public static synchronized UserService getUserService() {
        return userService;
    }

    private static synchronized UserService getInstance() {
        UserService localInstance = userService;
        if (localInstance == null) {
            synchronized (UserServiceFactory.class) {
                localInstance = userService;
                if (localInstance == null) {
                    userService = localInstance = new UserServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
