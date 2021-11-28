package com.example.finalprojectspring.controller.controllers.user.cartControllers;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class BuyFromCartController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @PostMapping(BUY_FROM_CART_PATH)
    public String buyFromCart() {
        HttpSession session = httpSessionFactory.getObject();
        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null;
        User.Role role = user != null ? user.getRole() : User.Role.Guest;
        String cartPage = REDIRECT + APP + role.toString().toLowerCase(Locale.ROOT) + CART_PAGE;


        if (role == User.Role.Guest) {
            return REDIRECT + LOGIN_PATH;
        }

        List<Order> orders = userService.getOrdersByStatus(user, OrderStatus.Unregistered);
        if (orders.size() > 0) {
            userService.updateStatusForOrders(orders, OrderStatus.Registered);
        }

        return cartPage;
    }

}
