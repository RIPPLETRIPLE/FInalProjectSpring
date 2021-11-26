package com.example.finalprojectspring.controller.filters;

import com.example.finalprojectspring.model.entity.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.example.finalprojectspring.controller.constants.Paths.*;

@Component
@Order(4)
public class BlockFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        HttpServletResponse httpResponse = ((HttpServletResponse) servletResponse);
        User user = httpRequest.getSession().getAttribute("user") == null ? null
                : ((User) httpRequest.getSession().getAttribute("user"));

        if (user != null) {
            if (user.getStatus().equals(User.UserStatus.Blocked) && !httpRequest.getRequestURI().contains("logout")) {
                httpRequest.getRequestDispatcher(BLOCK_ERROR).forward(httpRequest, httpResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
