package com.example.finalprojectspring.controller.controllers;

import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.INDEX_FILE;

@Controller
public class WebController {
    @Autowired
    UserService userService;

    @GetMapping({"/", "/app/*/mainPage"})
    public String getIndex() {
       userService.createNewUser(User.createUser("Admin", "Admin1", User.Role.Admin, User.UserStatus.Unblocked));
       return INDEX_FILE;
    }
}

