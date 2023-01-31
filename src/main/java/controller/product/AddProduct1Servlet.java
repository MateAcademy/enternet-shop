package controller.product;

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

@WebServlet("/addProducts1")
public class AddProduct1Servlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProduct1Servlet.class);
    private final ProductService productService = ProductServiceFactory.getProductService();
    private static final long serialVersionUID = 4892911750891991L;

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

            Double priceDouble = Double.parseDouble(price);
            productService.addProduct(new Product(name, priceDouble, description));

            logger.info("AddProduct1Servlet, we add product to db");
            resp.sendRedirect("/mainMenuServlet");
        } catch (Exception e) {
            logger.error("AddProduct1Servlet, we cannot add product to db, exception=" + e);
            resp.sendRedirect("/mainMenuServlet");
        }

    }
}
