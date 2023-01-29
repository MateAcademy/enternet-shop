package service;

import model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    void addUser(User user);
    List<User> getAll();
    Optional<User> findUserByLoginPassword(String email, String password);
//    Optional<User> findUserByEmail(String email);
    int deleteUserById(long id);

    Optional<User> findUserByEmail(String email);

    void updateUser(User user);

    Optional<User> getUserById(long id);
}
