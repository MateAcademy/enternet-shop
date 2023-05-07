//package controller.product;
//
//import factory.ProductServiceFactory;
//import model.Product;
//import org.apache.log4j.Logger;
//import service.ProductService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet("/admin/editProduct")
//public class EditProductServlet extends HttpServlet {
//
//    private Logger logger = Logger.getLogger(EditProductServlet.class);
//
//    private ProductService productService = ProductServiceFactory.getProductService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String productIdToDelete = req.getParameter("id_product");
//
//        Optional<Product> editeOptionalProductFromDb = null;
//        Product productFromDb = null;
//
//        if (productIdToDelete != null) {
//            editeOptionalProductFromDb = productService.getProductById(Long.parseLong(productIdToDelete));
//
//            if (editeOptionalProductFromDb.isPresent()) {
//                productFromDb = editeOptionalProductFromDb.get();
//            }
//
//            if (productFromDb != null) {
//                req.getSession().setAttribute("id_product", productFromDb.getId_product());
//                req.setAttribute("name_product", productFromDb.getName());
//                req.setAttribute("price_product", productFromDb.getPrice());
//                req.setAttribute("description_product", productFromDb.getDescription());
//
//                req.getRequestDispatcher("/admin/edite_product.jsp").forward(req, resp);
//            }
//
//        }
//
//    }
//}
