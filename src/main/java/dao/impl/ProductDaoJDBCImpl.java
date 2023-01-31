package dao.impl;

import dao.ProductDao;
import exception.TEAppException;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;
import utils.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public class ProductDaoJDBCImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJDBCImpl.class);

    @Override
    public void addProduct(Product product) {
        final String sql = "INSERT INTO products(name, price, description) values(?::varchar, ?::bigint, ?::varchar)";
        try (Connection connection = DbConnector.connect()) {
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
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, parseLong);

            int isDeleteProduct = ps.executeUpdate();
            return isDeleteProduct;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> getProductById(long idProduct) {
        String sql = "SELECT * FROM products WHERE id_product = ?";
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, idProduct);

            ResultSet resultSet = ps.executeQuery();

            Product product = null;
            if (resultSet.next()) {
                long id_productFromDB = resultSet.getLong("id_product");
                String nameFromDB = resultSet.getString("name");
                String priceFromDB = resultSet.getString("price");
                String descriptionFromDB = resultSet.getString("description");

                product = new Product(id_productFromDB, nameFromDB, Double.parseDouble(priceFromDB), descriptionFromDB);
            }
            Optional<Product> productOptional;
            if (product != null)
                productOptional = Optional.of(product);
            else {
                productOptional = Optional.empty();
            }
            return productOptional;
        } catch (Exception e) {
            logger.error("we in getProductById() method, exception=" + e);
            throw new TEAppException("cannot fined user to email in method getUserById(), exception= " + e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "update products set name = ?, price = ?, description = ? where id_product = ?";

        try (Connection connection = DbConnector.connect()) {
            /**
             * PreparedStatement: выполнение и управление запросами к базе данных
             */
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setLong(4, product.getId_product());

            ps.executeUpdate();
            logger.info("update product to db: " + product);
        } catch (SQLException e) {
            logger.error("can't update product to db, product: " + product + ", exception:" + e);
        }
    }
}
