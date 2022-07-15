package hr.lcabraja.webshop.util;

import hr.lcabraja.webshop.model.Order;
import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUtil {
    private CartUtil() {
    }

    public static HashMap<Long, Long> getCart(HttpServletRequest req) {
        HashMap<Long, Long> cart = (HashMap<Long, Long>) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        return cart;
    }

    public static List<Order> getOrders(HashMap<Long, Long> cart) {
        List<Order> orders = new ArrayList<>();
        for (Map.Entry<Long, Long> entry : cart.entrySet()) {
            ProductRepository.getProductWithId(entry.getKey()).ifPresent(product -> {
                orders.add(new Order(entry.getValue(), product));
            });
        }
        return orders;
    }
}
