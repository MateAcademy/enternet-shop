package factory;

import dao.ItemDao;
import dao.impl.ProductDaoImpl;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoFactory {
    private static volatile ItemDao itemDao;

    static {
        getInstance();
    }
    public static ItemDao getItemDao() {
        return itemDao;
    }

    private static ItemDao getInstance() {
        ItemDao localInstance = itemDao;
        if (localInstance == null) {
            synchronized (ItemDao.class) {
                localInstance = itemDao;
                if (localInstance == null) {
                    itemDao = localInstance = new ProductDaoImpl();
                }
            }
        }
        return localInstance;
    }
}
