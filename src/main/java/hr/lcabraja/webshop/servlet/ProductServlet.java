package hr.lcabraja.webshop.servlet;

import hr.lcabraja.webshop.model.Order;
import hr.lcabraja.webshop.repository.CategoryRepository;
import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hr.lcabraja.webshop.util.CartUtil.getCart;

public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = "";
        int category = -1;
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            if (param.getKey().equals("query")) {
                name = param.getValue()[0];
            } else if (param.getKey().equals("categoryFilter")) {
                category = Integer.parseInt(param.getValue()[0]);
                System.out.println("CATEGORIES" + category);
            }
        }
        HashMap<Long, Long> cart = getCart(request);
        List<Order> orders = getOrders(name, category, cart);

        request.setAttribute("orders", orders);
        request.setAttribute("categories", CategoryRepository.getAllCategories());
    }

    private List<Order> getOrders(String name, int category, HashMap<Long, Long> cart) {
        return ProductRepository
                .getAllProducts()
                .stream()
                .filter(p -> p.getTitle().startsWith(name))
                .filter(p -> category > 0 ? p.getCategory().getId() == category : true)
                .map(product -> {
                    Order order = new Order();
                    order.setQuantity(0L);
                    if (cart != null && cart.containsKey(product.getId())) {
                        order.setQuantity(cart.get(product.getId()));
                    }
                    order.setProduct(product);
                    return order;
                })
                .collect(Collectors.toList());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = "";
//        String[] categoryIds = new String[]{};
//
//        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
//            if (param.getKey().equals("query")) {
//                name = param.getValue()[0];
//            } else if (param.getKey().equals("categoryFilter")) {
//                categoryIds = param.getValue();
//            }
//        }
//
//        HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute("cart");
////        List<ProductDto> products = productRepository.findByNameAndCategories(name, categoryIds).stream().map(product -> {
////            ProductDto productDto = new ProductDto();
////            productDto.setQuantity(0L);
////            if (cart != null) {
////                if (cart.containsKey(product.getId())) {
////                    productDto.setQuantity(cart.get(product .getId()));
////                }
////            }
////            productDto.setProduct(product);
////            return productDto;
////        }).collect(Collectors.toList());
//        List<ProductDto> products = new ArrayList<>();
//
//        List<Category> categories = new ArrayList<>();
////        List<Category> categories = categoryRepository.findAll();
//        request.setAttribute("products", products);
//        request.setAttribute("categories", categories);
    }
}
