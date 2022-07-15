package hr.lcabraja.webshop.services;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import hr.lcabraja.webshop.model.Receipt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private static PaymentService singleton = null;
    private final String CLIENT_ID = "AQSYLNHfx-3Ko-8oreVhJNqiUApqsEvW3glf2m7ZwhEjDC73TsW_rglSWuVLfF7xNcbnEevGqWm3T7Oz";
    private final String CLIENT_SECRET = "EHGWtEFHsscSfTMc_YrqF-h-rmcqIcrL4AF45wHHW-U-VgjHKRAIzjHOkTaG-D-E6M7p05IqLNKsGd1o";
    private final String MODE = "sandbox";
    private PaymentService() {
    }

    public static PaymentService getSingleton() {
        if (singleton == null) {
            singleton = new PaymentService();
        }
        return singleton;
    }

    private DecimalFormat df = new DecimalFormat("0.00");
    public String authorizePayment(Receipt receipt) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(receipt);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Iama")
                .setLastName("Human")
                .setEmail("sb-0nt43n15885525@personal.example.com"); // Password: a%V7Ek>5
        payer.setPayerInfo(payerInfo);
        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/webshop-1.0-SNAPSHOT/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/webshop-1.0-SNAPSHOT/review_payment");
        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(Receipt receipt) {
//        Details details = new Details();
//        details.setShipping("0");
//        details.setSubtotal("0");
//        details.setTax("0");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(receipt.getTotal().toString());
//        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("description");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName("Product name");
        item.setPrice(receipt.getTotal().toString());
        item.setTax("0");
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return payment.execute(apiContext, paymentExecution);
    }
}
