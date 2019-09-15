package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.UserService;
import com.makeitbig.eventapp.wrapper.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup-user")
    public User saveUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }
}
