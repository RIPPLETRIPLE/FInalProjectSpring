package com.example.finalprojectspring.controller.controllers.admin.productsControllers;

import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.service.AdminService;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class UpdateProductController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping(UPDATE_PRODUCT_PATH)
    public String updateProduct(HttpServletRequest request) {
        try {
            Product product = userService.getProductByID(Integer.parseInt(request.getParameter("productId")));
            product.setName(request.getParameter("name"));
            product.setCategory(userService.getCategoryByID(Integer.parseInt(request.getParameter("category"))));
            product.setPrice(Integer.parseInt(request.getParameter("price")));
            product.setSize(userService.getSizeByID(Integer.parseInt(request.getParameter("size"))));
            product.setColor(userService.getColorByID(Integer.parseInt(request.getParameter("color"))));
            product.setSex(Product.Sex.valueOf(request.getParameter("sex")));
            adminService.updateProduct(product);
        } catch (Exception ex) {
            //logger
        }
        return REDIRECT + PRODUCTS_MANAGE_PATH;
    }
}
