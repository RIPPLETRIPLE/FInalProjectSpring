package com.example.finalprojectspring.controller.controllers.admin.ordersControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

import static com.example.finalprojectspring.controller.constants.Paths.*;
@Controller
public class OrdersManagePageController {
    @Autowired
    AdminService adminService;

    @GetMapping(ORDERS_MANAGE_PAGE)
    public String ordersManagePage(Model model) {
        HashMap<User, List<Order>> userToOrders = new HashMap<>();
        List<User> users = adminService.getAllUsers();

        for (User user : users) {
            userToOrders.put(user, adminService.getAllOrdersForUser(user));
        }

        model.addAttribute("usersToOrders", userToOrders);
        return MANAGE_ORDERS_FILE;
    }
}
