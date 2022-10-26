package dao.impl;

import dto.CodeDTO;
import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodeDaoImpl implements CodeDao {

    private final Logger logger = Logger.getLogger(CodeDaoImpl.class);

    @Override
    public void add(Code code) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("insert to code(code, user_id) values('%s', '%s')",
                    code.getValue(), code.getUser().getId());

            Statement statement = connection.createStatement();
            boolean isAddToDB = statement.execute(sql);

            logger.info("add code to db, user_id: " + code.getUser().getId() + " code: " + code.getValue() +
                    " result: " + isAddToDB);
        } catch (SQLException ex) {
            logger.error("don't add code to db " + ex);
        }
    }

    @Override
    public Code getCode(User user) {
//todo: продумать как отмечать что код в базе не свежий уже           where user_id= and avaliable = true

        Code code = null;
        //Создание и управление соединениями
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("select * from code INNER JOIN users u on u.id_user = code.user_id where user_id = '%d' order by id_code desc limit 1", user.getId());

            // выполнение и управление запросами к базе данных
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                 code = new Code(
                        resultSet.getLong("id_code"),
                        resultSet.getString("valus"),
                        new User( resultSet.getLong("id_user"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role"))
                );
            }
            logger.info("add user to db: " + user.getEmail());
        } catch (SQLException e) {
            logger.error("can't add user to db, " + e);
        }
        return code;
    }
}
