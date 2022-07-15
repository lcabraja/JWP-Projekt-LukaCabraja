package hr.lcabraja.webshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String user = (String) httpServletRequest.getSession().getAttribute("userUsername");
        if (user == null) {
            httpServletRequest.setAttribute("errorMessage", "You need to be logged in.");
            httpServletResponse.sendRedirect("/webshop-1.0-SNAPSHOT/login.jsp");
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
