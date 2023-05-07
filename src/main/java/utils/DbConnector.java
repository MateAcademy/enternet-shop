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

    private String DbURL;
    private String LOGIN;
    private String PASSWORD;

    public static DbConnector getInstance(String DbURL, String LOGIN, String PASSWORD) {
        if (instance == null) {
            instance = new DbConnector(DbURL, LOGIN, PASSWORD);
        }
        return instance;
    }
    private DbConnector(String DbURL, String LOGIN, String PASSWORD) {
        this.DbURL = DbURL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    };


    public static DbConnector getInstance() throws Exception {
        if (instance == null) {
            throw new Exception("DbConnec");
        }
        return instance;
    }


    public Connection connect() {
        try {
//          System.setProperty("jdbc.driver", "org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DbURL, LOGIN, PASSWORD);
            logger.debug("connection" + connection);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("no connect to db: " + e);
            return null;
        }
    }

}
