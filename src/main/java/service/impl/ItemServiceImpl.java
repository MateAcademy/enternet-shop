package service.impl;

import dao.ItemDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ItemService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ItemServiceImpl implements ItemService {

    private static final ItemDao itemDao = ProductDaoFactory.getItemDao();

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
}
