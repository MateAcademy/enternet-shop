import dao.OrderDao;
//import dao.impl.OrderDaoImpl;
import utils.HashUtils;

import java.security.NoSuchAlgorithmException;

/**
 * @author Sergey Klunniy
 */
public class TestOrderList {
    public static void main(String[] args) throws NoSuchAlgorithmException {
//        OrderDao orderDao = new OrderDaoImpl();
//        orderDao.getAll().forEach(System.out::println);


        System.out.println(HashUtils.getSHA256SecurePassword("test"));
    }
}
