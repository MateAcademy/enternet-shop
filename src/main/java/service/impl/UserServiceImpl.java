package service.impl;

import dao.UserDao;
import model.User;
import factory.UserDAOFactory;
import service.UserService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> findUserByLoginPassword(String email, String password) {
        return userDao.findUserByLoginPassword(email, password);
    }

    @Override
    public boolean deleteUserById(long id) {
        return userDao.deleteUserById(id);
    }
}
