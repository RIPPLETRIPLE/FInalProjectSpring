package com.example.finalprojectspring.controller.controllers.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class CartPageController {
    @GetMapping(CART_PAGE)
    public String getCartPage() {
        return CART_FILE;
    }
}
