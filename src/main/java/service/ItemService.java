package service;

import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ItemService {

    void addItem(Product product);
    List<Product> getAll();

}
