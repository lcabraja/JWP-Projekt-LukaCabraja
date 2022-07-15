package hr.lcabraja.webshop.repository;

import hr.lcabraja.webshop.model.Receipt;
import hr.lcabraja.webshop.model.Order;
import hr.lcabraja.webshop.model.LocalTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {

    private static OrderRepository singleton = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private List<Order> orders;
    private List<Receipt> receipts;
    private List<LocalTransaction> transactions;


    private OrderRepository() {
        orders = new ArrayList<>();
        receipts = new ArrayList<>();
        transactions = new ArrayList<>();

        receipts.add(new Receipt(123F, 454F, 112F, 2323F, "Cash", UserRepository.getAllUsers().get(0), LocalDateTime.now()));
        orders.add(new Order(2L, ProductRepository.getAllProducts().get(0), receipts.get(0)));
        orders.add(new Order(3L, ProductRepository.getAllProducts().get(2), receipts.get(0)));
        orders.add(new Order(6L, ProductRepository.getAllProducts().get(6), receipts.get(0)));
        transactions.add(new LocalTransaction(receipts.get(0), orders.stream().collect(Collectors.toList()), "now"));

        receipts.add(new Receipt(2123F, 234F, 12F, 452F, "PayPal", UserRepository.getAllUsers().get(1), LocalDateTime.now()));
        orders.add(new Order(7L, ProductRepository.getAllProducts().get(0), receipts.get(1)));
        orders.add(new Order(3L, ProductRepository.getAllProducts().get(2), receipts.get(1)));
        orders.add(new Order(7L, ProductRepository.getAllProducts().get(6), receipts.get(1)));
        transactions.add(new LocalTransaction(receipts.get(1), orders.stream().collect(Collectors.toList()), "now"));
    }

    public static List<Receipt> findByUserId(Long userId) {
        return getSingleton()
                .receipts
                .stream()
                .filter(order -> order.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public static List<Order> findItemsByOrderDetailId(Long receiptId) {
        return getSingleton()
                .orders
                .stream()
                .filter(order -> order.getReceipt().getId().equals(receiptId))
                .collect(Collectors.toList());
    }

    public static List<Order> getAllOrders() {
        return getSingleton().orders;
    }
    public static List<Receipt> getAllReceipts() {
        return getSingleton().receipts;
    }
    public static List<LocalTransaction> getAllTransactions() {
        return getSingleton().transactions;
    }

    public static OrderRepository getSingleton() {
        if (singleton == null) {
            singleton = new OrderRepository();
        }
        return singleton;
    }
}
