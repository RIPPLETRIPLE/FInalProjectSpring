package com.example.finalprojectspring.controller.controllers.user.cartControllers;

import com.example.finalprojectspring.controller.util.CommandUtility;
import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class ChangeProductQuantityController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @PostMapping(CHANGE_PRODUCT_QUANTITY_PATH)
    public String changeProductQuantity(@RequestParam(name = "action") String action, @RequestParam(name = "id") int id) {
        HttpSession session = httpSessionFactory.getObject();

        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null;
        User.Role role = user != null ? user.getRole() : User.Role.Guest;
        String cartPage = REDIRECT + APP + role.toString().toLowerCase(Locale.ROOT) + CART_PAGE;


        if (user == null) {
            Product product;
            try {
                product = userService.getProductByID(id);
            } catch (FieldDontPresent e) {
                return cartPage;
            }
            if (action.equals("increment")) {
                CommandUtility.addProductToCartForUnloggedUser(session, product, 1);
            }
            if (action.equals("decrement")) {
                CommandUtility.addProductToCartForUnloggedUser(session, product, -1);
            }
            if (action.equals("remove")) {
                CommandUtility.removeProductFromCardForUnloggedUser(session, product);
            }
        } else {
            Order order;

            try {
                order = userService.getOrderByID(id);
            } catch (FieldDontPresent e) {
                return cartPage;
            }
            if (action.equals("increment")) {
                order.setQuantity(order.getQuantity() + 1);
                userService.updateOrder(order);
            }
            if (action.equals("decrement")) {
                if (order.getQuantity() - 1 > 0) {
                    order.setQuantity(order.getQuantity() - 1);
                    userService.updateOrder(order);
                }
            }
            if (action.equals("remove")) {
                userService.deleteOrder(order);
            }
        }

        return cartPage;
    }
}
