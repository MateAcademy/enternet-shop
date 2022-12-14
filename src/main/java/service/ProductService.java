package service;

import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ProductService {

    void addItem(Product product);
    List<Product> getAll();
    int deleteProductById(long parseLong);
}
