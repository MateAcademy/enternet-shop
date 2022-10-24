package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);

    @Override
    public void addUser(User user) {
        //Создание и управление соединениями
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("insert into shop.public.users(email, password, role) VALUES ('%s', '%s', '%s')",
                    user.getEmail(), user.getPassword(), user.getRole());

            // выполнение и управление запросами к базе данных
            Statement statement = connection.createStatement();
            boolean execute = statement.execute(sql);

            logger.info("add user to db: " + execute);
        } catch (SQLException e) {
            logger.error("can't add user to db, " + e);
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
//       userDao.addUser(new User("test5@test", "123", "admin"));
        System.out.println(userDao.getAll());
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            String sql = "SELECT * FROM shop.public.users";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id_user"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );

                userList.add(userFromDb);
            }

            System.out.println("get all users to db: " + userList);
            logger.info("get all users to db");
            return userList;
        } catch (SQLException e) {
            logger.error("can't add user to db, " + e);
        }
        return null;
    }

    @Override
    public boolean findUser(User user) {
        return false;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String email, String password) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
