package hr.lcabraja.webshop.servlet;


import hr.lcabraja.webshop.model.LocalTransaction;
import hr.lcabraja.webshop.repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailServlet extends HttpServlet {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        List<LocalTransaction> transactions =
                OrderRepository
                        .getAllTransactions()
                        .stream()
                        .filter(t -> t.getReceipt().getUser().getId() == userId)
                .collect(Collectors.toList());
        request.setAttribute("transactions", transactions);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
