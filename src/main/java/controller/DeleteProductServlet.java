package controller;

import exception.TAException;
import factory.ProductServiceFactory;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdToDelete = req.getParameter("id_product");
        if (productIdToDelete != null) {
            int deleteProductById = productService.deleteProductById(Long.parseLong(productIdToDelete));
            if (deleteProductById == 1)
                req.getRequestDispatcher("/getAllProducts").forward(req, resp);
        } else {
            throw new TAException();
        }

    }


}
