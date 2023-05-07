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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@WebServlet(value = "/getAllProducts")
public class GetAllProductsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(GetAllProductsServlet.class);
    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.getAll();
        req.setAttribute("productList", productList);
        HttpSession session = req.getSession();

        Object roleFromSession = session.getAttribute("role");
        String role = null;
        if (roleFromSession != null) {
            role = String.valueOf(roleFromSession).toLowerCase();
        }

        if (role != null) {
            role = role.toLowerCase();
        } else {
            logger.error("not role in GetAllProductsServlet");
            resp.sendRedirect("index.jsp");
        }

        if (role.equals("admin")) {
            logger.info("role admin in GetAllProductsServlet");
            req.getRequestDispatcher("/show_all_products_admin.jsp").forward(req, resp);
        } else {
            logger.info("role user in GetAllProductsServlet");
            req.getRequestDispatcher("/show_all_products_user.jsp").forward(req, resp);
        }
    }

}
