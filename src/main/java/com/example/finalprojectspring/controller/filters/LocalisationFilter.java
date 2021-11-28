package com.example.finalprojectspring.controller.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(2)
public class LocalisationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String lang = servletRequest.getParameter("lang");

        if (lang != null) {
            req.getSession().setAttribute("lang", lang);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
