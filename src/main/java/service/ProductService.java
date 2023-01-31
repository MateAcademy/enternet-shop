package service;

import model.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface ProductService {

    void addProduct(Product product);
    List<Product> getAll();
    int deleteProductById(long parseLong);
    Optional<Product> getProductById(long idProduct);

    void updateProduct(Product product);
}
