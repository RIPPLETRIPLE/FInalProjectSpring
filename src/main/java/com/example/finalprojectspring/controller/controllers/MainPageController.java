package com.example.finalprojectspring.controller.controllers;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class MainPageController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping({"/", "/app/*/mainPage"})
    public String getIndexForUser(Model model,
                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "order", defaultValue = "ASC") String sortDirection,
                                  @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                  @RequestParam(name = "color", defaultValue = "%") String color,
                                  @RequestParam(name = "size", defaultValue = "%") String size,
                                  @RequestParam(name = "category", defaultValue = "%") String category,
                                  @RequestParam(name = "sex", defaultValue = "%") String sex

    ) {
        HttpSession session = httpSessionFactory.getObject();
        if (sex.equals("Male")) {
            int i = 0;
        }
        model.addAttribute("colors", userService.getAllColors());
        model.addAttribute("categories", userService.getAllCategories());
        model.addAttribute("sizes", userService.getAllSizes());
        model.addAttribute("products",
                userService.getProductsWithSortAndPagination(category,color,size,sex, page - 1, Sort.Direction.fromString(sortDirection.toUpperCase(Locale.ROOT)), sortBy));


        List<Integer> pages = new ArrayList<>();
        long amountOfProducts = userService.getCountOfProducts();

        for (int i = 0; i <= (amountOfProducts % 8 == 0 ? amountOfProducts / 8 - 1 : amountOfProducts / 8); i++) {
            pages.add(i + 1);
        }

        model.addAttribute("pages", pages);


        User user = session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
        if (user == null || user.getRole() == User.Role.User) {
            return INDEX_FILE;
        }

        return REDIRECT + PRODUCTS_MANAGE_PATH;
    }

}

