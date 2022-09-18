package controller;

import factory.ItemServiceFactory;
import factory.UserServiceFactory;
import model.Item;
import model.User;
import service.ItemService;
import service.UserService;

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
public class GetAllItemsServlet extends HttpServlet {

    private static final ItemService itemService = ItemServiceFactory.getItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> allItems = itemService.getAll();
        req.setAttribute("allItems", allItems);
        req.getRequestDispatcher("/items.jsp").forward(req, resp);
    }





}
