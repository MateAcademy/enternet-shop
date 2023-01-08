package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoJDBCImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJDBCImpl.class);
    @Override
    public void addProduct(Product product) {
        final String sql = "INSERT INTO products(name, price, description) values(?::varchar, ?::bigint, ?::varchar)";
        try(Connection connection = DbConnector.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productsList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            String sql = "SELECT * FROM products";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id_product"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description")
                );
                productsList.add(productFromDb);
            }
            logger.info("get all products from db");
        } catch (SQLException e) {
            logger.error("can't add product to db, " + e);
        }
        return productsList;
    }

    @Override
    public int deleteProductById(long parseLong) {
        String sql = "delete from products where id_product = ?";
        try (Connection connection = DbConnector.connect()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, parseLong);

            int isDeleteProduct = ps.executeUpdate();
            return isDeleteProduct;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
