package com.example.finalprojectspring.controller.controllers.user.cartControllers;

import static com.example.finalprojectspring.controller.constants.Paths.*;

import com.example.finalprojectspring.controller.util.CommandUtility;
import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class AddToCart {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    UserService userService;

    @PostMapping(ADD_TO_CART_PATH)
    public String addToCart(@Validated @RequestParam(name = "productId") int productId) {
        HttpSession session = httpSessionFactory.getObject();

        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null;
        User.Role role = user != null ? user.getRole() : User.Role.Guest;

        Product product;

        String mainPage = REDIRECT + APP + role.toString().toLowerCase(Locale.ROOT) + MAIN;

        try {
            product = userService.getProductByID(productId);
        } catch (FieldDontPresent e) {
            //logger
            return mainPage;
        }

        if (user == null) {
            CommandUtility.addProductToCartForUnloggedUser(session, product, 1);
        } else {
            Order order = Order.createOrder(user, product, 1, OrderStatus.valueOf("Unregistered"));
            if (!userService.createOrder(order)) {
                Order orderToIncrement;
                try {
                    orderToIncrement = userService.findOrderByUserAndStatusAndProduct(order);
                    orderToIncrement.setQuantity(orderToIncrement.getQuantity() + 1);
                    userService.updateOrder(orderToIncrement);
                } catch (FieldDontPresent e) {
                    e.printStackTrace();
                }
            }
        }
        return mainPage;
    }
}
