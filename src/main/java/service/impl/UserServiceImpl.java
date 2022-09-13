package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class UserServiceImpl implements UserService {

    //сделать синглтон
    //сделать что бы не нужно было повторно вводить емейл
    private static volatile UserDao userDao;

    /**
     * @return singleton userDao;
     */
    public static UserDao getInstance() {
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

    static {
        getInstance();
    }


    @Override
    public void addUser(User user) {
        if (user!=null) {
            userDao.addUser(user);
        }
        //что то залогировать
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
