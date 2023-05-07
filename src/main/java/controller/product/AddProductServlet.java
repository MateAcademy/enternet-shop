package controller.product;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/addProducts")
public class AddProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProductServlet.class);
    private ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String description = req.getParameter("description");

            Double priceDouble = Double.parseDouble(price);
            productService.addProduct(new Product(name, priceDouble, description));

            logger.info("we add product to database");
            resp.sendRedirect("/getAllProducts");
        } catch (Exception e) {
            logger.error("we can't add product to database, exception=" + e);
            resp.sendRedirect("/mainMenuServlet");
        }
    }

}
