package com.example.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/users/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {
        if (((HttpServletRequest) request).getSession() == null ||
                ((HttpServletRequest) request).getSession().getAttribute("user") == null) {
            ((HttpServletResponse) response).sendRedirect("/login.jsp");
        }
    }
}
