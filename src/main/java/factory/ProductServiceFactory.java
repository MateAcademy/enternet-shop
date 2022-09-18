package factory;

import service.ItemService;
import service.impl.ItemServiceImpl;

/**
 * @author Sergey Klunniy
 */
public class ProductServiceFactory {
    private static volatile ItemService itemService;

    static {
        getInstance();
    }
    public static synchronized ItemService getItemService() {
        return itemService;
    }

    private static synchronized ItemService getInstance() {
        ItemService localInstance = itemService;
        if (localInstance == null) {
            synchronized (UserServiceFactory.class) {
                localInstance = itemService;
                if (localInstance == null) {
                    itemService = localInstance = new ItemServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
