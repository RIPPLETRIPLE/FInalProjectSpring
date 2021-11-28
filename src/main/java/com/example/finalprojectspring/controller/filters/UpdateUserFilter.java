package com.example.finalprojectspring.controller.filters;

import com.example.finalprojectspring.controller.controllers.admin.productsControllers.AddProductController;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.AdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(4)
@RequestMapping("/app/user/*")
public class UpdateUserFilter implements Filter {
    @Autowired
    private AdminService adminService;

    private final Logger logger = LogManager.getLogger(UpdateUserFilter.class);

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
                logger.warn(e.getMessage(), e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
