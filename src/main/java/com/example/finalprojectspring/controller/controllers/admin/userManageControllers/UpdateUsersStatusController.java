package com.example.finalprojectspring.controller.controllers.admin.userManageControllers;

import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.service.AdminService;
import com.example.finalprojectspring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.finalprojectspring.controller.constants.Paths.*;
@Controller
public class UpdateUsersStatusController {
    @Autowired
    AdminService adminService;


    @PostMapping(UPDATE_USER_STATUS_PATH)
    public String updateUserStatus(@RequestParam(name = "userId") int userId, @RequestParam(name = "status")String status) {
        try {
            User user = adminService.getUserByID(userId);
            user.setStatus(User.UserStatus.valueOf(status));
            adminService.updateUser(user);
        } catch (FieldDontPresent e) {
            //logger
        }
        return REDIRECT + MANAGE_USER_PATH;
    }
}
