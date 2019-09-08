package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login-user")
    public UserDetails logIn(User user) {
       return userService.loadUserByUsername(user.getUserName());
    }

    @PostMapping("/signup-user")
    public User saveUser(User user) {
        userService.save(user);
        return user;
    }
}
