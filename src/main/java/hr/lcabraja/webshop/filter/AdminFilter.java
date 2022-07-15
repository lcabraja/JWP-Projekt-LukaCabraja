package hr.lcabraja.webshop.filter;

import hr.lcabraja.webshop.model.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Role role = (Role) httpServletRequest.getSession().getAttribute("userRole");
        if (role != Role.ADMINISTRATOR) {
            httpServletRequest.setAttribute("errorMessage", "Forbidden");
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
