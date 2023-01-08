package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import dao.impl.ProductDaoJDBCImpl;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoFactory {
    private static volatile ProductDao itemDao;

    static {
        getInstance();
    }
    public static ProductDao getItemDao() {
        return itemDao;
    }

    private static ProductDao getInstance() {
        ProductDao localInstance = itemDao;
        if (localInstance == null) {
            synchronized (ProductDao.class) {
                localInstance = itemDao;
                if (localInstance == null) {
                    itemDao = localInstance = new ProductDaoJDBCImpl();
                }
            }
        }
        return localInstance;
    }
}
