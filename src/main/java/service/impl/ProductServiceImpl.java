package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
    private static final ProductDao productDao = ProductDaoFactory.getItemDao();

    @Override
    public void addProduct(Product product) {
        if (product != null) {
            logger.info("try add product in service");
            productDao.addProduct(product);
        }
    }

    @Override
    public List<Product> getAll() {
        logger.info("try get all list products in service");
        return productDao.getAll();
    }

    @Override
    public int deleteProductById(long idProduct) {
        logger.info("try delete product by 'id' in service");
        return productDao.deleteProductById(idProduct);
    }

    @Override
    public Optional<Product> getProductById(long idProduct) {
        logger.info("try get optional product by 'id' in service");
        return productDao.getProductById(idProduct);
    }

    @Override
    public void updateProduct(Product product) {
        logger.info("try update product in service");
        productDao.updateProduct(product);
    }

    @Override
    public Optional<Product> getProductByName(String nameProduct) {
        logger.info("try get product by 'name' in service");
        return productDao.getProductByName(nameProduct);
    }
}
