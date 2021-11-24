package com.example.finalprojectspring.controller.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class Registration {
    @GetMapping(REGISTRATION_PAGE)
    public String getRegistration() {
        return REGISTRATION_FILE;
    }
}
