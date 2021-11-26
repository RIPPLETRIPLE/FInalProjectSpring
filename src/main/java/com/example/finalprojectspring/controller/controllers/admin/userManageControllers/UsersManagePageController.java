package com.example.finalprojectspring.controller.controllers.admin.userManageControllers;

import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.*;
@Controller
public class UsersManagePageController {
    @Autowired
    UserService userService;

    @GetMapping(MANAGE_USER_PATH)
    public String getUsersManagePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return MANAGE_USER_FILE;
    }
}
