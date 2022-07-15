package hr.lcabraja.webshop.servlet;


import hr.lcabraja.webshop.model.Audit;
import hr.lcabraja.webshop.model.Role;
import hr.lcabraja.webshop.model.User;
import hr.lcabraja.webshop.repository.AuditRepository;
import hr.lcabraja.webshop.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class AuthServlet extends HttpServlet {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String LOGOUT_PATH = "/logout";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case LOGOUT_PATH:
                processLogout(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        switch (path) {
            case LOGIN:
                doLogin(request, response);
                break;
            case REGISTER:
                doRegister(request, response);
                break;
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Testing
//        Optional<User> optionalUser =
//                UserRepository
//                        .getAllUsers()
//                        .stream()
//                        .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
//                        .findFirst();
        Optional<User> optionalUser = Optional.of(UserRepository.getAllUsers().get(0));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            req.getSession().setAttribute("userUsername", user.getUsername());
            req.getSession().setAttribute("userId", user.getId());
            req.getSession().setAttribute("userRole", user.getRole());
            AuditRepository.getAllAudits().add(new Audit(user, LocalDateTime.now(), req.getRemoteAddr()));

            res.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("errorMessage", "Bad Credentials");
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    private void doRegister(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> optionalUser =
                UserRepository
                        .getAllUsers()
                        .stream()
                        .filter(u -> u.getUsername().equals(username))
                        .findFirst();

        if (!optionalUser.isPresent()) {
            User user = new User(username, password, Role.USER);
            UserRepository.getAllUsers().add(user);
            req.getSession().setAttribute("userUsername", user.getUsername());
            req.getSession().setAttribute("userId", user.getId());
            req.getSession().setAttribute("userRole", user.getRole());
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("errorMessage", "User exists");
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    private void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.getSession().removeAttribute("userUsername");
        req.getSession().removeAttribute("userRole");
        req.getSession().removeAttribute("userId");
        res.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
