package service.impl;

import dao.ItemDao;
import dao.UserDao;
import dao.impl.ItemDaoImpl;
import dao.impl.UserDaoImpl;
import model.Item;
import service.ItemService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ItemServiceImpl implements ItemService {

    private static final ItemDao itemDao = new ItemDaoImpl();

    @Override
    public void addItem(Item item) {
        if (item!=null) {
            itemDao.addItem(item);
        }
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}
