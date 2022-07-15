package hr.lcabraja.webshop.servlet;

import hr.lcabraja.webshop.model.Product;
import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = ProductRepository.getAllProducts();
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
