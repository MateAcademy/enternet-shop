package dao;

import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ProductDao {

    void addItem(Product product);
    List<Product> getAll();
    boolean deleteProductById(long parseLong);

}
