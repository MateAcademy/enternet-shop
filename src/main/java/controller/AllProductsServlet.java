package controller;

import factory.ProductServiceFactory;
import model.Product;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@WebServlet(value = "/getItems")
public class AllProductsServlet extends HttpServlet {

    private static final ItemService itemService = ProductServiceFactory.getItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = itemService.getAll();
        req.setAttribute("allItems", allProducts);
        req.getRequestDispatcher("/items.jsp").forward(req, resp);
    }





}
