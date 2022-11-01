package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;
import utils.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);

    @Override
    public void addUser(User user) {
        String sql = "insert into \"users\"(email, password, role, available) VALUES (?, ?, ?::role_enum, ?)";
        //Создание и управление соединениями
        try (Connection connection = DbConnector.connect()) {
            // выполнение и управление запросами к базе данных
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.setBoolean(4, user.isAvailable());

            int execute = ps.executeUpdate();
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
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getBoolean("available")
                );
                userList.add(userFromDb);
            }
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

        String sql = "select * from users where email = ? and password = ? ";
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                long id_user = resultSet.getLong("id_user");
                String email1 = resultSet.getString("email");
                String password1 = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                boolean available = resultSet.getBoolean("available");
                user = new User(id_user, email1, password1, role, available);
            }

            Optional<User> userOptional = Optional.of(user);
            return userOptional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
