package factory;

import dao.ItemDao;
import dao.UserDao;
import dao.impl.ItemDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * @author Sergey Klunniy
 */
public class ItemDaoFactory {
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
                    itemDao = localInstance = new ItemDaoImpl();
                }
            }
        }
        return localInstance;
    }
}
