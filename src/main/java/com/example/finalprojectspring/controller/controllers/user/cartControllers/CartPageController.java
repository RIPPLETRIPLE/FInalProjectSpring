package com.example.finalprojectspring.controller.controllers.user.cartControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class CartPageController {
    @GetMapping(CART_PATH)
    public String getCartPage() {
        return CART_FILE;
    }
}
