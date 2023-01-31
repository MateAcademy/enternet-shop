package controller.product;

import factory.ProductServiceFactory;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/addProducts")
public class AddProductServlet extends HttpServlet {

    private ProductService itemService = ProductServiceFactory.getProductService();
    private static final long serialVersionUID = 4892911750891992L;

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
            itemService.addProduct(new Product(name, priceDouble, description));
            resp.sendRedirect("/getAllProducts");
        } catch (Exception ex) {
//Todo: если ввожу логин и один пароль
            //Вывести на страничку если такой товар уже есть то ввести заново другой:
        }

    }
}
