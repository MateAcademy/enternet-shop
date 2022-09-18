package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class  UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static volatile List<User> userList;

    static {
        getInstance();
        User user = new User("s.klunniy@gmail.com", "123");
        userList.add(user);
        logger.debug("User" + user + " add to db");
    }

    public static List<User> getInstance( ) {
        List<User> localInstance = userList;
        if (localInstance == null) {
            synchronized (ArrayList.class) {
                localInstance = userList;
                if (localInstance == null) {
                    userList = localInstance = new ArrayList<>();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
        logger.debug("User" + user + " add to db");
    }

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public boolean findUser(User user) {
        if (user!=null) {
            for (User u : userList) {
                if (user.equals(u)) {
                    return true;
                }
            }
        }
        return false;
    }
}
