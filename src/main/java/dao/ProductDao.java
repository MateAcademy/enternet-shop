package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface ProductDao {

    void addProduct(Product product);
    List<Product> getAll();
    int deleteProductById(long parseLong);

    Optional<Product> getProductById(long idProduct);

}
