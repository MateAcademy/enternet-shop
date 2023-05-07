//package controller.product;
//
//import factory.ProductServiceFactory;
//import model.Product;
//import org.apache.log4j.Logger;
//import service.ProductService;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Sergey Klunniy
// */
//@WebServlet("/admin/editProductData")
//public class EditProductDataServlet  extends HttpServlet {
//
//    private static final Logger logger = Logger.getLogger(EditProductDataServlet.class);
//    private static final ProductService productService = ProductServiceFactory.getProductService();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            long id_product = (long) req.getSession().getAttribute("id_product");
//            String name_product = req.getParameter("name_product");
//            Double price_product = Double.parseDouble(req.getParameter("price_product"));
//            String description_product = req.getParameter("description_product");
//
//            Product product = new Product(id_product, name_product, price_product, description_product);
//
//            productService.updateProduct(product);
//            logger.info("product update " + product);
//
//            req.getRequestDispatcher("/getAllProducts").forward(req, resp);
//        } catch (Exception e) {
//            logger.error("in EditProductDataServlet, e=" + e);
//        }
//    }
//}
//
//
