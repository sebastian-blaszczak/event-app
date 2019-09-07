package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    UserService userService;

    @PostMapping("/signup-user")
    public User saveUser(User user) {
        userService.save(user);
        return user;
    }

}
