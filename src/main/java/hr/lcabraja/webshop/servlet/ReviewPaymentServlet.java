package hr.lcabraja.webshop.servlet;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.lcabraja.webshop.services.PaymentService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReviewPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            Payment payment = PaymentService.getSingleton().getPaymentDetails(paymentId);
            CheckoutServlet.DoPay(request, response, paymentId, payerId, payment);
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
