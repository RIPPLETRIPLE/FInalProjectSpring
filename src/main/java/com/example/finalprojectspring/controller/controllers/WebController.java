package com.example.finalprojectspring.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.finalprojectspring.controller.constants.Paths.INDEX_FILE;

@Controller
public class WebController {
    @GetMapping({"/", "/app/*/mainPage"})
    public String getIndex() {
       return INDEX_FILE;
    }
}

