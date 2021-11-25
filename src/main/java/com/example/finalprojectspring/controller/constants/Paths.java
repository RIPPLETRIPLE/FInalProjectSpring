package com.example.finalprojectspring.controller.constants;

public interface Paths {
    String REDIRECT = "redirect:";
    String APP = "/app/";
    String MAIN = "/mainPage";

    String INDEX_FILE =  "index";

    String LOGIN_FILE = "guest/login";
    String LOGIN_PATH = "/app/guest/login";

    String LOG_OUT_PATH = "/app/*/logout";

    String REGISTRATION_FILE = "guest/registration";
    String REGISTRATION_PATH = "/app/guest/registration";

    String PAGE_NOT_FOUND_FILE = "errors/pageNotFound";

    String SERVER_ERROR = "errors/serverError";

    String CART_FILE = "user/cart";
    String CART_PATH = "/app/*/cartPage";

    String ADD_TO_CART_PATH = "/app/*/addToCart";

    String PRODUCTS_MANAGE_FILE = "admin/productsManage";
}
