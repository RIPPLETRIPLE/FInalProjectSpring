package com.example.finalprojectspring.controller.controllers.admin.productsControllers;

import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class ProductsManagePageController {
    @Autowired
    UserService userService;


    @GetMapping(PRODUCTS_MANAGE_PATH)
    public String getProductManageFile(Model model) {
        model.addAttribute("categories", userService.getAllCategories());
        model.addAttribute("colors", userService.getAllColors());
        model.addAttribute("sizes", userService.getAllSizes());
        model.addAttribute("products", userService.getAllProducts());
        model.addAttribute("products", userService.getAllProducts());
        return PRODUCTS_MANAGE_FILE;
    }
}
