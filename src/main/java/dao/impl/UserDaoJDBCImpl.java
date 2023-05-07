//package dao.impl;
//
//import dao.UserDao;
//import model.User;
//import org.apache.log4j.Logger;
//import utils.DbConnector;
//import exception.TEAppException;
//import utils.Role;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//    private final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);
//
//    @Override
//    public void addUser(User user) {
//        String sql = "insert into users(name, email, password, salt, hash_password, role, available) VALUES (?, ?, ?, ?, ?, ?::role_enum, ?)";
//
//        try (Connection connection = DbConnector.connect()) {
//            assert connection != null;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getEmail());
//            ps.setString(3, user.getPassword());
//            ps.setString(4, user.getSalt());
//            ps.setString(5, user.getHashPassword());
//            ps.setString(6, user.getRole().toString().toLowerCase(Locale.ROOT));
//            ps.setBoolean(7, true);
//            ps.executeUpdate();
//
//            logger.debug("add user to database, email= " + user.getEmail() + ", password= " + user.getPassword());
//        } catch (SQLException e) {
//            logger.error("can't add user to database, user: " + user + ", exception:" + e);
//        }
//    }
//
//    @Override
//    public List<User> getAll() {
//        List<User> userList = new ArrayList<>();
//        try (Connection connection = DbConnector.connect()) {
//            String sql = "SELECT * FROM users";
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                User userFromDb = new User(
//                        resultSet.getLong("id_user"),
//                        resultSet.getString("email"),
//                        resultSet.getString("password"),
//                        Role.valueOf(resultSet.getString("role").toUpperCase()),
//                        resultSet.getBoolean("available"),
//                        resultSet.getString("name"),
//                        resultSet.getString("salt"),
//                        resultSet.getString("hash_password")
//                );
//                userList.add(userFromDb);
//            }
//            logger.info("get all users to db");
//            return userList;
//        } catch (SQLException e) {
//            logger.error("can't add user to db, " + e);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean findUser(User user) {
//        return false;
//    }
//
//    @Override
//    public Optional<User> findUserByLoginPassword(String email, String password) {
//
////        String sql = "select * from users where email = ? and password = ? ";
////        try (Connection connection = DbConnector.connect()) {
////            PreparedStatement preparedStatement = connection.prepareStatement(sql);
////
////            preparedStatement.setString(1, email);
////            preparedStatement.setString(2, password);
////            ResultSet resultSet = preparedStatement.executeQuery();
////
////            User user = null;
////            if (resultSet.next()) {
////                long id_userFromDB = resultSet.getLong("id_user");
////                String nameFromDB = resultSet.getString("name");
////                String emailFromDB = resultSet.getString("email");
////                String passwordFromDB = resultSet.getString("password");
////                String saltFromDB = resultSet.getString("salt");
////                Role roleFromBB = Role.valueOf(resultSet.getString("role"));
////                boolean availableFromDB = resultSet.getBoolean("available");
////                user = new User(id_userFromDB, nameFromDB, emailFromDB, passwordFromDB, saltFromDB,
////                        roleFromBB, availableFromDB);
////            }
////            Optional<User> userOptional;
////            if (user != null)
////                userOptional = Optional.of(user);
////            else {
////                userOptional = Optional.empty();
////            }
////            return userOptional;
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//
//        return null;
//    }
//
//    @Override
//    public int deleteUserById(long id) {
//        String sql = "delete from users where id_user = ?";
//        try (Connection connection = DbConnector.connect()) {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setLong(1, id);
//
//            int result = ps.executeUpdate();
//            return result;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Optional<User> findUserByEmail(String email) {
//        String sql = "Select * from users where email = ?";
//        try (Connection connection = DbConnector.connect()) {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, email);
//
//            ResultSet resultSet = ps.executeQuery();
//
////                logger.info("ошибка при поиске юзера в БД по email, в методе findUserByEmail email: " + email );
//            User user = null;
//            if (resultSet.next()) {
//                long id_userFromDB = resultSet.getLong("id_user");
//                String nameFromDB = resultSet.getString("name");
//                String emailFromDB = resultSet.getString("email");
//                String passwordFromDB = resultSet.getString("password");
//                String saltFromDB = resultSet.getString("salt");
//                String hashPasswordFromDB = resultSet.getString("hash_password");
//                Role roleFromBB = Role.valueOf(resultSet.getString("role").toUpperCase());
//                boolean availableFromDB = resultSet.getBoolean("available");
//                user = new User(id_userFromDB, nameFromDB, emailFromDB, passwordFromDB, saltFromDB, hashPasswordFromDB,
//                        roleFromBB, availableFromDB);
//            }
//            Optional<User> userOptional;
//            if (user != null)
//                userOptional = Optional.of(user);
//            else {
//                userOptional = Optional.empty();
//            }
//            return userOptional;
//        } catch (Exception e) {
//            logger.error("we in findUserByEmail() method, exception = " + e);
//            throw new TEAppException("cannot fined user to email in method findUserByEmail(), exception= " + e);
//        }
//    }
//
//    @Override
//    public void updateUser(User user) {
//        String sql = "update users set name = ?, email = ?, password = ?, salt = ?, hash_password = ?, role = ?::role_enum, available = ? where id_user = ?";
//
//        try (Connection connection = DbConnector.connect()) {
//            /**
//             * PreparedStatement: выполнение и управление запросами к базе данных
//             */
//            assert connection != null;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getEmail());
//            ps.setString(3, user.getPassword());
//            ps.setString(4, user.getSalt());
//            ps.setString(5, user.getHashPassword());
//            ps.setString(6, user.getRole().toString().toLowerCase(Locale.ROOT));
//            ps.setBoolean(7, user.isAvailable());
//            ps.setLong(8, user.getId_user());
//
//            ps.executeUpdate();
//            logger.info("update user to db: " + user);
//        } catch (SQLException e) {
//            logger.error("can't update user to db, user: " + user + ", exception:" + e);
//        }
//    }
//
//    @Override
//    public Optional<User> getUserById(long id) {
//        String sql = "SELECT * FROM users WHERE id_user = ?";
//        try (Connection connection = DbConnector.connect()) {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setLong(1, id);
//
//            ResultSet resultSet = ps.executeQuery();
//
//            User user = null;
//            if (resultSet.next()) {
//                long id_userFromDB = resultSet.getLong("id_user");
//                String nameFromDB = resultSet.getString("name");
//                String emailFromDB = resultSet.getString("email");
//                String passwordFromDB = resultSet.getString("password");
//                String saltFromDB = resultSet.getString("salt");
//                String hashPasswordFromDB = resultSet.getString("hash_password");
//                Role roleFromBB = Role.valueOf(resultSet.getString("role").toUpperCase());
//                boolean availableFromDB = resultSet.getBoolean("available");
//                user = new User(id_userFromDB, nameFromDB, emailFromDB, passwordFromDB, saltFromDB, hashPasswordFromDB,
//                        roleFromBB, availableFromDB);
//            }
//            Optional<User> userOptional;
//            if (user != null)
//                userOptional = Optional.of(user);
//            else {
//                userOptional = Optional.empty();
//            }
//            return userOptional;
//        } catch (Exception e) {
//            logger.error("we in getUserById() method, exception = " + e);
//            throw new TEAppException("cannot fined user to email in method getUserById(), exception= " + e);
//        }
//    }
//}
