package dao;

import model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface UserDao {

    void addUser(User user);
    List<User> getAll();
    boolean findUser(User user);
    Optional<User> findUserByLoginPassword(String email, String password);
    boolean deleteUserById(long id);

}
