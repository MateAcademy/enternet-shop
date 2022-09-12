package service;

import model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    void addUser(User user);
    List<User> getAll();
}
