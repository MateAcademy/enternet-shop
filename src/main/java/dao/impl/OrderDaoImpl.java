package dao.impl;

import dao.OrderDao;
import model.Order;
import model.Product;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public List<Order> getAll() {

        List<Order> orderList = new ArrayList<>();

        String getAllOrder = "select id_order, id_product, name, count(name) from \"order\" inner join order_basket_product opb on \"order\".id_order = opb.order_id\n" +
                "inner join products on opb.product_id = products.id_product group by name, id_product, id_order order by id_product";

        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllOrder);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Long id_order = resultSet.getLong("id_order");
                Long id_product = resultSet.getLong("id_product");
                String name = resultSet.getString("name");
                Long count = resultSet.getLong("count");

                Order order = new Order();
                order.setId_order(id_order);
                Product product = new Product(id_product, name);
                order.getProducts().add(product);
                orderList.add(order);

                boolean addProduct = false;
                for (Order o : orderList) {
                    if(id_order.equals(order.getId_order())) {
                        o.getProducts().add(product);
                        addProduct = true;
                    }
                }

                if (!addProduct) {
                    orderList.add(order);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }
}
