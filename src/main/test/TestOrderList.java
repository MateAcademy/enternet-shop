import dao.OrderDao;
import dao.impl.OrderDaoImpl;

/**
 * @author Sergey Klunniy
 */
public class TestOrderList {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.getAll().forEach(System.out::println);
    }
}
