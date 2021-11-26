package com.example.finalprojectspring.controller.controllers.admin.productsControllers;

import com.example.finalprojectspring.controller.util.CommandUtility;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalprojectspring.controller.constants.Paths.*;
@Controller
public class AddProductController {
    @Autowired
    UserService userService;


    @PostMapping(ADD_PRODUCT_PATH)
    public String addProduct(HttpServletRequest request) {
        Product product;
        try {
            product = CommandUtility.extractProductFromForm(request, userService).orElseThrow(FieldDontPresent::new);
        } catch (FieldDontPresent e) {
            return REDIRECT + PRODUCTS_MANAGE_PATH;
        }
        userService.createProduct(product);
        return REDIRECT + APP + "admin" +  MAIN;
    }
}
