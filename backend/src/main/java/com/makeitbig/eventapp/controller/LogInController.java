package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    @Autowired
    private UserService userService;

    @PostMapping("/login-user")
    public boolean logIn(User user) {
        userService.loadUserByUsername(user.getUserName());
        return true;
    }
}
