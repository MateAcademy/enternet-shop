package controller;

import exception.TAException;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdToDelete = req.getParameter("id");
        if (productIdToDelete != null) {
            boolean deleteUser = productService.deleteProductById(Long.parseLong(productIdToDelete));
            if (deleteUser==true)
                req.getRequestDispatcher( "/getAllProducts").forward(req, resp);
        }
        throw new TAException();
    }


}
