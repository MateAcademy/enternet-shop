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
    private static final UserDao userDao = new UserDaoImpl();

    @Override
    public void addUser(User user) {
        if (user!=null) {
            userDao.addUser(user);
        }
        //какую то валидацию сделать
        //что то залогировать
        
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
