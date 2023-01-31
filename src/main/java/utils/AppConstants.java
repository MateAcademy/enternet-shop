package utils;

import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class AppConstants {
    private List<User> userList = new ArrayList<>();

    private static final AppConstants instance = new AppConstants();

    private AppConstants(){}

    public static AppConstants getInstance() {
        return instance;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
