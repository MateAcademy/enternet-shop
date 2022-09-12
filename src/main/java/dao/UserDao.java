package dao;

import model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface UserDao {

    void addUser(User user);
    List<User> getAll();

    boolean findUser(User user);

}
