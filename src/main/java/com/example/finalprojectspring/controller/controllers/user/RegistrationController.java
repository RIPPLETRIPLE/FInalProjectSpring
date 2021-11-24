package com.example.finalprojectspring.controller.controllers.user;

import com.example.finalprojectspring.controller.util.ValidationUtil;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class RegistrationController {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    UserService userService;

    @GetMapping(REGISTRATION_PAGE)
    public String getRegistration() {
        return REGISTRATION_FILE;
    }

    @PostMapping(REGISTRATION_PAGE)
    public String regUser(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, Model model) {
        HttpSession session = httpSessionFactory.getObject();
        Map<String, Object> attributes = new HashMap<>();
        model.addAllAttributes(attributes);

        if (!ValidationUtil.isLoginValid(login) || !ValidationUtil.isPasswordValid(password)) {
            attributes.put("error", "invalid_data");
            return REDIRECT + REGISTRATION_PAGE;
        }
        if (userService.createNewUser(User.createUser(login, password, User.Role.User, User.UserStatus.Unblocked))) {
            return REDIRECT + LOGIN_PAGE;
        }

        session.setAttribute("loginAlreadyExist", "true");
        return REDIRECT + REGISTRATION_PAGE;
    }
}
