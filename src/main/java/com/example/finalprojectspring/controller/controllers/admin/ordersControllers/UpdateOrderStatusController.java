package com.example.finalprojectspring.controller.controllers.admin.ordersControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.service.AdminService;
import com.example.finalprojectspring.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class UpdateOrderStatusController {

    @Autowired
    UserService userService;

    private final Logger logger = LogManager.getLogger(UpdateOrderStatusController.class);

    @PostMapping(UPDATE_ORDER_STATUS_PATH)
    public String updateOrderStatus(HttpServletRequest request) {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = userService.getOrderByID(orderId);
            order.setStatus(OrderStatus.valueOf(request.getParameter("status")));
            userService.updateOrder(order);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return REDIRECT + ORDERS_MANAGE_PAGE;
        }

        return REDIRECT + ORDERS_MANAGE_PAGE;
    }
}
