package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

/**
 * @author Sergey Klunniy
 */
public class UserDAOFactory {

    private static volatile UserDao userDao;

    static {
        getInstance();
    }
    public static UserDao getUserDao() {
        return userDao;
    }

    private static UserDao getInstance() {
        UserDao localInstance = userDao;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = userDao;
                if (localInstance == null) {
                    userDao = localInstance = new UserDaoImpl();
                }
            }
        }
        return localInstance;
    }
}
