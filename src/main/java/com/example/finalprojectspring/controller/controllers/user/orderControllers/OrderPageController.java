package com.example.finalprojectspring.controller.controllers.user.orderControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class OrderPageController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping(ORDER_PATH)
    public String getOrderPage(Model model) {
        HttpSession session = httpSessionFactory.getObject();
        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null;
        List<Order> orders = new ArrayList<>();

        if (user != null) {
            orders = userService.getOrdersForUser(user).stream().filter(e -> e.getStatus() != OrderStatus.Unregistered).collect(Collectors.toList());
        }

        model.addAttribute("orders", orders);
        return ORDER_PAGE;
    }
}
