package hr.lcabraja.webshop.model;

public class Order {
    private static Long lastId = 0L;
    private Long id;
    private Long quantity;
    private Product product;
    private Receipt receipt;

    public Order() {
    }

    public Order(Long quantity, Product product) {
        this.id = lastId++;
        this.quantity = quantity;
        this.product = product;
        this.receipt = null;
    }

    public Order(Long quantity, Product product, Receipt receipt) {
        this.id = lastId++;
        this.quantity = quantity;
        this.product = product;
        this.receipt = receipt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
