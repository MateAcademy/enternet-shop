package controller;

import model.Product;
import service.ItemService;
import service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */

public class ItemAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String description = req.getParameter("description");

            Double priceDouble = Double.parseDouble(price);
//Todo:
            ItemService itemService = new ItemServiceImpl();
            itemService.addItem(new Product(name, priceDouble, description));

            resp.sendRedirect("/getItems");
        } catch (Exception ex) {
            //Вывести на страничку если такой товар уже есть то ввести заново другой:
        }

    }
}
