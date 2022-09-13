package dao.impl;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class  UserDaoImpl implements UserDao {

    private static volatile List<User> userList;

    static {
        getInstance();
        userList.add(new User("s.klunniy@gmail.com", "123"));
    }

    public static List<User> getInstance() {
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
