package db;

import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.Role;

/**
 * @author Sergey Klunni\y
 */
public class Database {
    private static final Logger logger = Logger.getLogger(Database.class);
    public final static List<User> USER_LIST = new ArrayList<>();
    public final static List<Product> LIST_PRODUCTS = new ArrayList<>();

    static {
        User user1 = new User("s.klunniy@gmail.com", "123", Role.admin);
        User user2 = new User("ava@gmail.com", "123", Role.user);
        User user3 = new User("5", "test", Role.user);

        USER_LIST.add(user1);
        USER_LIST.add(user2);
        USER_LIST.add(user3);

        logger.debug("Users add to db in static block class Database" );
    }

    static {
        Product product1 = new Product( "Bread", 20d, "Kievskiy bread");
        Product product2 = new Product( "Milk", 100d, "Brovary milk");
        Product product3= new Product( "Meat", 83d, "Irpen meat");

        LIST_PRODUCTS.add(product1);
        LIST_PRODUCTS.add(product2);
        LIST_PRODUCTS.add(product3);

        logger.debug("Products add to db");
    }

}
