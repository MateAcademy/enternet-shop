package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

//    private static final Logger logger = Logger.getLogger(DbConnector.class);
//    private static final String DbURL = "jdbc:postgresql://localhost:5432/shop";
//    private static final String LOGIN = "postgres";
//    private static final String PASSWORD = "test";

    private static final Logger logger = Logger.getLogger(DbConnector.class);

    private static DbConnector instance;

    private final String DbURL;
    private final String LOGIN;
    private final String PASSWORD;

    public static void getInstance(String DbURL, String LOGIN, String PASSWORD) {
        if (instance == null) {
            instance = new DbConnector(DbURL, LOGIN, PASSWORD);
        }
//        return instance;
    }
    private DbConnector(String DbURL, String LOGIN, String PASSWORD) {
        this.DbURL = DbURL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    };


    public static DbConnector getInstance() throws Exception {
        if (instance == null) {
            throw new Exception("DbConnector instance == null");
        }
        return instance;
    }


    public static Connection connect() {
        try {
//          System.setProperty("jdbc.driver", "org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");

            DbConnector connector = DbConnector.getInstance();
            Connection connection = DriverManager.getConnection(connector.DbURL, connector.LOGIN, connector.PASSWORD);
            logger.debug("connection" + connection);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("no connect to db: " + e);
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
