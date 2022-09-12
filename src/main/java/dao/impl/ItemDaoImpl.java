package dao.impl;

import dao.ItemDao;
import model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ItemDaoImpl implements ItemDao {

    private final static List<Item> listItems = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        if (item!=null) {
            listItems.add(item);
        }
    }

    @Override
    public List<Item> getAll() {
        return listItems;
    }
}
