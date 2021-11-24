package com.example.finalprojectspring.controller.controllers.guest;

import com.example.finalprojectspring.controller.util.CommandUtility;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.example.finalprojectspring.controller.constants.Paths.*;

@Controller
public class LogOutController {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping(LOG_OUT_PATH)
    public String logOut() {
        HttpSession session = httpSessionFactory.getObject();
        if (session.getAttribute("user") != null) {
            CommandUtility.deleteUserFromLogged(session);
        }
        return REDIRECT + APP + "guest" + MAIN;
    }
}
