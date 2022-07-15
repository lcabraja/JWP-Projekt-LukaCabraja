package hr.lcabraja.webshop.model;

import java.util.List;

public class LocalTransaction {
    private Receipt receipt;
    private List<Order> orders;
    private String dateTime;

    public LocalTransaction(Receipt receipt, List<Order> orderDetailItems, String dateTime) {
        this.receipt = receipt;
        this.orders = orderDetailItems;
        this.dateTime = dateTime;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
