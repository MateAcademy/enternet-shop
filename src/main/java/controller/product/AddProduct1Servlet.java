package controller.product;

import exception.TEAppException;
import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/addProducts1")
public class AddProduct1Servlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProduct1Servlet.class);
    private final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add_product1.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String description = req.getParameter("description");

//сделать проверку вдруг такое название уже есть в базе данных
            if (!doesSuchAProductExist(name)) {
                Double priceDouble = Double.parseDouble(price);
                productService.addProduct(new Product(name, priceDouble, description));

                logger.debug("we add product to database");
                resp.sendRedirect("/mainMenuServlet");
            } else {
                logger.debug("we have such product by name in database");
                req.setAttribute("error", "we have such product by name in database");
                doGet(req, resp);
            }
        } catch (Exception e) {
            logger.error("we can't add product to database, exception=" + e);
            resp.sendRedirect("/mainMenuServlet");
        }
    }
    private boolean doesSuchAProductExist(String name) {
        Optional<Product> productByName = productService.getProductByName(name);

        if (productByName.isPresent()) {
            logger.info("productByName isPresent, return true");
            return true;
        }

        logger.info("productByName isEmpty, return false");
        return false;
    }

}
