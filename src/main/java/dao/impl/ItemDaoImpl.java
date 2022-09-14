package dao.impl;

import dao.ItemDao;
import db.Database;
import model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public void addItem(Item item) {
        if (item!=null) {
            Database.listItems.add(item);
        }
    }

    @Override
    public List<Item> getAll() {
        return Database.listItems;
    }
}
