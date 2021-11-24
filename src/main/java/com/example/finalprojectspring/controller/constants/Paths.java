package com.example.finalprojectspring.controller.constants;

public interface Paths {
    String INDEX_FILE =  "index";
    String[] INDEX_PAGE =  {"/", "/app/*/mainPage"};

    String LOGIN_FILE = "guest/login";
    String LOGIN_PAGE = "/app/guest/login";

    String REGISTRATION_FILE = "guest/registration";
    String REGISTRATION_PAGE = "/app/guest/registration";

    String PAGE_NOT_FOUND_FILE = "errors/pageNotFound";

    String SERVER_ERROR = "errors/serverError";

    String CART_FILE = "user/cart";
    String CART_PAGE = "/app/*/cartPage";
}
