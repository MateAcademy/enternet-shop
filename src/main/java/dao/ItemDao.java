package dao;

import model.Product;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ItemDao {

    void addItem(Product product);
    List<Product> getAll();

}
