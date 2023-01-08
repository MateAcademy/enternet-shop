package dao;

import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ProductDao {

    void addProduct(Product product);
    List<Product> getAll();
    int deleteProductById(long parseLong);

}
