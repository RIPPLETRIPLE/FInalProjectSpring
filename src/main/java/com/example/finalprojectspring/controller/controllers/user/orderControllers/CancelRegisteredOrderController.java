package com.example.finalprojectspring.controller.controllers.user.orderControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
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
public class CancelRegisteredOrderController {
    @Autowired
    UserService userService;

    private final Logger logger = LogManager.getLogger(CancelRegisteredOrderController.class);
    @PostMapping(CANCEL_REGISTERED_ORDER_PATH)
    public String cancelUnregisteredOrder(@RequestParam(name = "orderId") int orderId) {
        Order order;

        try {
            order = userService.getOrderByID(orderId);
        } catch (FieldDontPresent e) {
            logger.warn(e.getMessage(), e);
            return REDIRECT + ORDER_PATH;
        }

        if (order.getStatus() == OrderStatus.Registered) {
            userService.deleteOrder(order);
        }
        return REDIRECT + ORDER_PATH;
    }
}
