package hr.lcabraja.webshop.model;

public enum PaymentType {
    PAYPAL("PayPal"),
    CASH("CASH");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
