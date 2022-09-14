package service.impl;

import dao.UserDao;
import model.User;
import factory.UserDAOFactory;
import service.UserService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class UserServiceImpl implements UserService {

    //сделать что бы не нужно было повторно вводить емейл
//    private static final volatile UserDao userDao = UserDAOFactory.getUserDao();
    private static final UserDao userDao = UserDAOFactory.getUserDao();

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
