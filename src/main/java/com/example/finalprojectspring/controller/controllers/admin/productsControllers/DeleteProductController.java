package com.example.finalprojectspring.controller.controllers.admin.productsControllers;

import com.example.finalprojectspring.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.finalprojectspring.controller.constants.Paths.*;
@Controller
public class DeleteProductController {
    @Autowired
    AdminService adminService;

    @PostMapping(DELETE_PRODUCT_PATH)
    public String deleteOrder(@RequestParam(name = "productId") int productId) {
        adminService.deleteProductByID(productId);
        return REDIRECT + APP + "admin" + MAIN;
    }
}
