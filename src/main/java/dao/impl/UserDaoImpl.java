package dao.impl;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class  UserDaoImpl implements UserDao {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("s.klunniy@gmail.com", "123"));
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
