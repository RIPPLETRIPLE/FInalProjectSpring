package com.example.finalprojectspring.controller.controllers.admin.ordersControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class DeleteOrderController {
    @Autowired
    UserService userService;
    private final Logger logger = LogManager.getLogger(DeleteOrderController.class);
    @PostMapping(DELETE_ORDER_PATH)
    public String DeleteOrder(@RequestParam(name = "orderId") int orderId) {
        Order order;

        try {
            order = userService.getOrderByID(orderId);
        } catch (FieldDontPresent e) {
            logger.error(e.getMessage(), e);
            return REDIRECT + ORDERS_MANAGE_PAGE;
        }
        userService.deleteOrder(order);
        return REDIRECT + ORDERS_MANAGE_PAGE;
    }
}
