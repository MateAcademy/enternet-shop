package service;

import model.Item;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ItemService {

    void addItem(Item item);
    List<Item> getAll();

}
