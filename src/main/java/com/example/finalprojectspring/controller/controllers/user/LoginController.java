package com.example.finalprojectspring.controller.controllers.user;

import com.example.finalprojectspring.controller.util.CommandUtility;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Locale;


import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class LoginController {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    UserService userService;

    @GetMapping(LOGIN_PAGE)
    public String getLogin() {
        return LOGIN_FILE;
    }

    @PostMapping(LOGIN_PAGE)
    public String logUser(@RequestParam(name = "login") String login
            , @RequestParam(name = "password") String password) {
        HttpSession session = httpSessionFactory.getObject();

        User user = User.createUser(login, password, User.Role.User, User.UserStatus.Unblocked);

        if (userService.DBContainsUser(user)) {
            if (CommandUtility.checkUserIsLogged(session, user)) {
                session.setAttribute("error", true);
                session.setAttribute("errorType", "user_already_logged");
                return REDIRECT + LOGIN_PAGE;
            }

            session.setAttribute("user", user);

            if (session.getAttribute("cart") != null && user.getRole() != User.Role.Admin) {
//                userService.retainCartForLoggedUser((List<Order>) session.getAttribute("cart"), user);
                session.removeAttribute("cart");
            }

            return REDIRECT + APP + user.getRole().toString().toLowerCase(Locale.ROOT) + MAIN;
        }
        session.setAttribute("error", true);
        session.setAttribute("errorType", "wrong_data");

        return REDIRECT + LOGIN_PAGE ;
    }
}
