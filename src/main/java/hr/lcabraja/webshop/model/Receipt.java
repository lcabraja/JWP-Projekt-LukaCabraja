package hr.lcabraja.webshop.model;

import java.time.LocalDateTime;

public class Receipt {
    private static Long lastId = 0L;
    private Long id;
    private Float subtotal;
    private Float shipping;
    private Float tax;
    private Float total;
    private String payment;
    private User user;
    private LocalDateTime createdAt;

    public Receipt() {
    }

    public Receipt(Float subtotal, Float shipping, Float tax, Float total, String payment, User user, LocalDateTime createdAt) {
        this.id = lastId++;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.tax = tax;
        this.total = total;
        this.payment = payment;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
