package com.example.finalprojectspring.controller.controllers.user.cartControllers;

import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class CartPageController {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    UserService userService;

    @GetMapping(CART_PATH)
    public String getCartPage(Model model) {
        HttpSession session = httpSessionFactory.getObject();
        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null;

        if (user != null) {
            model.addAttribute("cart", userService.getOrdersByStatus(user, OrderStatus.Unregistered));
        }
        return CART_FILE;
    }
}
