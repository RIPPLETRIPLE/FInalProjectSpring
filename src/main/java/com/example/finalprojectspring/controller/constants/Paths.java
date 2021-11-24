package com.example.finalprojectspring.controller.constants;

public interface Paths {
    String REDIRECT = "redirect:";
    String APP = "/app/";
    String MAIN = "/mainPage";

    String INDEX_FILE =  "index";
    String[] INDEX_PAGE =  {"/", "/app/*/mainPage"};

    String LOGIN_FILE = "guest/login";
    String LOGIN_PAGE = "/app/guest/login";

    String LOG_OUT_PATH = "/app/*/logout";

    String REGISTRATION_FILE = "guest/registration";
    String REGISTRATION_PAGE = "/app/guest/registration";

    String PAGE_NOT_FOUND_FILE = "errors/pageNotFound";

    String SERVER_ERROR = "errors/serverError";

    String CART_FILE = "user/cart";
    String CART_PAGE = "/app/*/cartPage";

    String PRODUCTS_MANAGE_FILE = "admin/productsManage";
}
