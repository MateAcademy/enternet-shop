package factory;

import service.ProductService;
import service.impl.ProductServiceImpl;

/**
 * @author Sergey Klunniy
 */
public class ProductServiceFactory {
    private static volatile ProductService itemService;

    static {
        getInstance();
    }
    public static synchronized ProductService getItemService() {
        return itemService;
    }

    private static synchronized ProductService getInstance() {
        ProductService localInstance = itemService;
        if (localInstance == null) {
            synchronized (UserServiceFactory.class) {
                localInstance = itemService;
                if (localInstance == null) {
                    itemService = localInstance = new ProductServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
