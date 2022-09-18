package dao.impl;

import dao.ItemDao;
import db.Database;
import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoImpl implements ItemDao {

    @Override
    public void addItem(Product product) {
        if (product !=null) {
            Database.LIST_PRODUCTS.add(product);
        }
    }

    @Override
    public List<Product> getAll() {
        return Database.LIST_PRODUCTS;
    }
}
