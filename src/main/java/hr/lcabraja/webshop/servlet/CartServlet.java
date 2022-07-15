package hr.lcabraja.webshop.servlet;

import hr.lcabraja.webshop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static hr.lcabraja.webshop.util.CartUtil.getCart;
import static hr.lcabraja.webshop.util.CartUtil.getOrders;

public class CartServlet extends HttpServlet {

    private final String ALL = "/all";
    private final String ADD_ITEM = "/add-item";
    private final String REMOVE_ITEM = "/remove-item";
    private final String CHANGE_QUANTITY = "/change-quantity";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null) {
            processAll(request, response);
        } else {
            switch (path) {
                case ADD_ITEM:
                    processAddItem(request, response);
                    break;
                case REMOVE_ITEM:
                    processRemoveItem(request, response);
                    break;
                case CHANGE_QUANTITY:
                    processChangeQuantity(request, response);
                    break;
            }
        }
    }

    private void processAll(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setAttribute("orders", getOrders(getCart(req)));
    }

    private void processAddItem(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        ProductRepository.getProductWithId(id).ifPresent(product -> {
            HashMap<Long, Long> cart = getCart(req);
            cart.put(id, 1L);
            req.getSession().setAttribute("cart", cart);
            req.setAttribute("cart", cart);
        });
        res.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    private void processRemoveItem(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        HashMap<Long, Long> cart = (HashMap<Long, Long>) req.getSession().getAttribute("cart");
        if (cart.containsKey(id)) {
            cart.remove(id, cart.get(id));
        }
        req.getSession().setAttribute("cart", cart);
        res.sendRedirect(req.getContextPath() + "/cart.jsp");
    }

    private void processChangeQuantity(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        HashMap<Long, Long> cart = getCart(req);
        if (cart.containsKey(id)) {
            String action = req.getParameter("action");
            Long qt = cart.get(id);
            if (action.equals("inc")) {
                qt = ++qt;
            } else {
                if (qt != 1L) {
                 qt = --qt;
                }
            }
            cart.replace(id, qt);
            req.getSession().setAttribute("cart", cart);
        }
        res.sendRedirect(req.getContextPath() + "/cart.jsp");
    }
}
