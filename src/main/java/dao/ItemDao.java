package dao;

import model.Item;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface ItemDao {

    void addItem(Item item);
    List<Item> getAll();

}
