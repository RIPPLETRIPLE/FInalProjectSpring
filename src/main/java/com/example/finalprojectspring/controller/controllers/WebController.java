package com.example.finalprojectspring.controller.controllers;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class WebController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping({"/", "/app/*/mainPage"})
    public String getIndexForUser(Model model) {
        HttpSession session = httpSessionFactory.getObject();
        Map<String, Object> attributes = new HashMap<>();

        attributes.put("products", userService.getAllProducts());
        model.addAllAttributes(attributes);

        User user = session.getAttribute("user") == null ? null : (User) session.getAttribute("user");
        if (user == null || user.getRole() == User.Role.User) {
            return INDEX_FILE;
        }

        return REDIRECT + PRODUCTS_MANAGE_PATH;
    }

}

