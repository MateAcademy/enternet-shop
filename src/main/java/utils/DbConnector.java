package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private final Logger logger = Logger.getLogger(DbConnector.class);

    private static final String DbURL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "vfvfgfgf23";

    public static Connection connect() {
        try {

            Connection connection = DriverManager.getConnection(DbURL, LOGIN, PASSWORD);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

    }
}
