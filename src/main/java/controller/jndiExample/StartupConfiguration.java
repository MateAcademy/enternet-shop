package controller.jndiExample;

import org.apache.log4j.Logger;
import utils.DbConnector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Sergey Klunniy
 */
public class StartupConfiguration implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(StartupConfiguration.class);
    private static final String URL = "java:comp/env/CONFIG_PROPERTIES_DATABASE_URL";
    private static final String LOGIN = "java:comp/env/CONFIG_PROPERTIES_DATABASE_LOGIN";
    private static final String PASSWORD = "java:comp/env/CONFIG_PROPERTIES_DATABASE_PASSWORD";

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            Context initialContext = new InitialContext();
            String url = (String) initialContext.lookup(URL);
            String login = (String) initialContext.lookup(LOGIN);
            String password = (String) initialContext.lookup(PASSWORD);

//          System.out.println(url);
//          System.out.println(login);
//          System.out.println(password);

            logger.info("url " + url);
            logger.info("login " + login);
            logger.info("password " + url);

            DbConnector.getInstance(url, login, password);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

}
