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

    private static final ProductDao itemDao = ProductDaoFactory.getItemDao();

    @Override
    public void addItem(Product product) {
        if (product !=null) {
            itemDao.addItem(product);
        }
    }

    @Override
    public List<Product> getAll() {
        return itemDao.getAll();
    }

    @Override
    public boolean deleteProductById(long idProduct) {
        return itemDao.deleteProductById(idProduct);
    }
}
