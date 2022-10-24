package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static final Logger logger = Logger.getLogger(DbConnector.class);

    private static final String DbURL = "jdbc:postgresql://localhost:5432/shop";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "vfvfgfgf23";

    public static Connection connect() {
        try {
//          System.setProperty("jdbc.driver", "org.postgresql.Driver");

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DbURL, LOGIN, PASSWORD);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("no connect to db: " + e);
        }
        return null;
    }

}
