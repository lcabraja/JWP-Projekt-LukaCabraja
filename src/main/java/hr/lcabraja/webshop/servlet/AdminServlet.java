package hr.lcabraja.webshop.servlet;

import hr.lcabraja.webshop.model.Audit;
import hr.lcabraja.webshop.model.Category;
import hr.lcabraja.webshop.model.Product;
import hr.lcabraja.webshop.model.LocalTransaction;
import hr.lcabraja.webshop.repository.AuditRepository;
import hr.lcabraja.webshop.repository.CategoryRepository;
import hr.lcabraja.webshop.repository.OrderRepository;
import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LocalTransaction> transactions = OrderRepository.getAllTransactions();
        List<Product> products = ProductRepository.getAllProducts();
        List<Category> categories = CategoryRepository.getAllCategories();
        List<Audit> audits = AuditRepository.getAllAudits();

        request.setAttribute("transactions", transactions);
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("logs", audits);
    }
}
