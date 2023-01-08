package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getItemDao();

    @Override
    public void addItem(Product product) {
        if (product !=null) {
            productDao.addProduct(product);
        }
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public int deleteProductById(long idProduct) {
        return productDao.deleteProductById(idProduct);
    }
}
