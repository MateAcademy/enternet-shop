package dao.impl;

import dao.UserDao;
import db.Database;
import model.User;
import org.apache.log4j.Logger;
;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class  UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        if (user!=null) {
            Database.USER_LIST.add(user);
            logger.warn("User add to db");
        }
    }

    @Override
    public List<User> getAll() {
        return Database.USER_LIST;
    }

    @Override
    public boolean findUser(User user) {
        List<User> userList = Database.USER_LIST;

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
