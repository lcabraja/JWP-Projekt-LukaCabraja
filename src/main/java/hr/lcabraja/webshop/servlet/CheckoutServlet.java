package hr.lcabraja.webshop.servlet;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import hr.lcabraja.webshop.model.LocalTransaction;
import hr.lcabraja.webshop.model.PaymentType;
import hr.lcabraja.webshop.model.Receipt;
import hr.lcabraja.webshop.model.User;
import hr.lcabraja.webshop.repository.OrderRepository;
import hr.lcabraja.webshop.repository.UserRepository;
import hr.lcabraja.webshop.services.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static hr.lcabraja.webshop.util.CartUtil.getCart;
import static hr.lcabraja.webshop.util.CartUtil.getOrders;

public class CheckoutServlet extends HttpServlet {
    public static final DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/review":
                processPaymentReview(request, response);
                break;
        }
    }

    private void processPaymentReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            Payment payment = PaymentService.getSingleton().getPaymentDetails(paymentId);
            DoPay(request, response, paymentId, payerId, payment);

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    static void DoPay(HttpServletRequest request, HttpServletResponse response, String paymentId, String payerId, Payment payment) throws ServletException, IOException {
        PayerInfo payerInfo = payment.getPayer().getPayerInfo();
        Transaction transaction = payment.getTransactions().get(0);
        ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

        request.setAttribute("payer", payerInfo);
        request.setAttribute("transaction", transaction);
        request.setAttribute("shippingAddress", shippingAddress);

        String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String payment = request.getParameter("payment");
        switch (payment) {
            case "CASH":
                processCashPayment(request, response);
                break;
            case "PAYPAL":
                processPaypalPayment(request, response);
                break;
        }

        request.getSession().removeAttribute("cart");
    }

    private void processPaypalPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        Float total = Float.valueOf(request.getParameter("total"));
        System.out.println("MONEY " + total);
        User user = UserRepository.getUserWithId(userId).get();
        Receipt receipt = createReceipt(PaymentType.PAYPAL, total, user);
        List<hr.lcabraja.webshop.model.Order> orders = getOrders(getCart(request));
        request.setAttribute("message", "true");

        try {
            String approvalLink = PaymentService.getSingleton().authorizePayment(receipt);
            response.sendRedirect(approvalLink);
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void processCashPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        System.out.println("TOTAL " + request.getParameter("total").getClass().getName() + "  TO " + request.getParameter("total"));
        Float total = Float.valueOf(request.getParameter("total"));
        User user = UserRepository.getUserWithId(userId).get();
        Receipt receipt = createReceipt(PaymentType.CASH, total, user);
        List<hr.lcabraja.webshop.model.Order> orders = getOrders(getCart(request));
        LocalTransaction transaction = new LocalTransaction(receipt, orders, LocalDateTime.now().toString());

        orders = orders.stream().map(o -> {
            o.setReceipt(receipt);
            return o;
        }).collect(Collectors.toList());

        OrderRepository.getAllReceipts().add(receipt);
        OrderRepository.getAllOrders().addAll(orders);
        OrderRepository.getAllTransactions().add(transaction);

        request.setAttribute("message", "true");
        response.sendRedirect(request.getContextPath() + "/success.jsp");
    }

    private Receipt createReceipt(PaymentType paymentType, float total, User user) {
        return new Receipt(total, 0f, 0f, total, paymentType.toString(), user, LocalDateTime.now());
    }
}
