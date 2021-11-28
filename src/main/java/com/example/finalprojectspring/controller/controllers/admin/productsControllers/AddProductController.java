package com.example.finalprojectspring.controller.controllers.admin.productsControllers;

import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.AdminService;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class AddProductController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @PostMapping(ADD_PRODUCT_PATH)
    public String addProduct(@RequestParam(name = "image") MultipartFile image,
                             @RequestParam(name = "category") int categoryId,
                             @RequestParam(name = "color") int colorId,
                             @RequestParam(name = "size") int sizeId,
                             @RequestParam(name = "sex") String sex,
                             @RequestParam(name = "price") int price,
                             @RequestParam(name = "name") String name) {

        Product product;
        String mainPage = REDIRECT + APP + "admin" + MAIN;
        try {
            adminService.saveImage(image);
        } catch (IOException e) {
            //logger
            return mainPage;
        }
        try {
            product = Product.createProduct(0, name, price, Product.Sex.valueOf(sex), image.getOriginalFilename()
                    , userService.getCategoryByID(categoryId), userService.getColorByID(colorId), userService.getSizeByID(sizeId));
            userService.createProduct(product);
        } catch (FieldDontPresent e) {
            //logger
            return mainPage;
        }
        return mainPage;
    }
}
