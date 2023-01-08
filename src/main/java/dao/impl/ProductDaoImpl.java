package dao.impl;

import dao.ProductDao;
import db.Database;
import model.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        if (product !=null) {
            Database.LIST_PRODUCTS.add(product);
        }
    }

    @Override
    public List<Product> getAll() {
        return Database.LIST_PRODUCTS;
    }

    @Override
    public int deleteProductById(long id) {
        Optional<Product > first = Database.LIST_PRODUCTS.stream().filter(t -> t.getId_product().equals(id)).findFirst();
        if (first.isPresent()) {
            Product product = first.get();
            Database.LIST_PRODUCTS.remove(product);
            return 1;
        }
        return 0;
    }
}
