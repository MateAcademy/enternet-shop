package factory;

import service.ProductService;
import service.impl.ProductServiceImpl;

/**
 * @author Sergey Klunniy
 */
public class ProductServiceFactory {
    private static volatile ProductService productService;

    static {
        getInstance();
    }
    public static synchronized ProductService getProductService() {
        return productService;
    }

    private static synchronized ProductService getInstance() {
        ProductService localInstance = productService;
        if (localInstance == null) {
            synchronized (UserServiceFactory.class) {
                localInstance = productService;
                if (localInstance == null) {
                    productService = localInstance = new ProductServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
