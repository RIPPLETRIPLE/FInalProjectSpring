package com.example.finalprojectspring.controller.filters;

import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(4)
public class UpdateUserFilter implements Filter {
    @Autowired
    private AdminService adminService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);

        User user = httpRequest.getSession().getAttribute("user") == null ? null
                : ((User) httpRequest.getSession().getAttribute("user"));

        if (user != null) {
            httpRequest.getSession().removeAttribute("user");
            try {
                httpRequest.getSession().setAttribute("user", adminService.getUserByID((int) user.getId()));
            } catch (FieldDontPresent e) {
                //logger
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
