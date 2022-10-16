package db;

import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunni\y
 */
public class Database {
    private static final Logger logger = Logger.getLogger(Database.class);

    public final static List<User> USER_LIST = new ArrayList<>();
    public final static List<Product> LIST_PRODUCTS = new ArrayList<>();


    static {
        User user = new User("s.klunniy@gmail.com", "123", "admin");
        USER_LIST.add(user);
        logger.debug("User" + user + " add to db");
    }

    static {
        Product product = new Product( "Bread", 20d, "...");
        LIST_PRODUCTS.add(product);
        logger.debug("Product" + product + " add to db");
    }

}
