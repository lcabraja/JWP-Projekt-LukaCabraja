package hr.lcabraja.webshop.servlet;

import hr.lcabraja.webshop.model.Product;
import hr.lcabraja.webshop.repository.CategoryRepository;
import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class NewProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = "";
        int category = -1;
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            if (param.getKey().equals("id")) {
                id = param.getValue()[0];
            }
        }
        String finalId = id;
        ProductRepository.getAllProducts().removeIf(p -> p.getId() == Long.parseLong(finalId));
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.valueOf(req.getParameter("price"));
        Long category = Long.valueOf(req.getParameter("category"));
        String imageHref = req.getParameter("imageHref");
        Long id = 0L;
        try {
            id = Long.valueOf(req.getParameter("id"));

        } catch (Exception e) {
            id = (Long) ProductRepository.getAllProducts().stream().count();

        }
        if (ProductRepository.getProductWithId(id).isPresent()) {
            ProductRepository.getAllProducts().remove(ProductRepository.getProductWithId(id).get());
        }
        Product product = new Product(id, " " + title, " " + description, price, CategoryRepository.getAllCategories().get(6), imageHref);
        ProductRepository.getAllProducts().add(product);
        req.getSession().setAttribute("products", ProductRepository.getAllProducts());
        response.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
