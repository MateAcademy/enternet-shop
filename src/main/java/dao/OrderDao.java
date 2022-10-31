package dao;

import model.Order;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface OrderDao {
    List<Order> getAll();
}
