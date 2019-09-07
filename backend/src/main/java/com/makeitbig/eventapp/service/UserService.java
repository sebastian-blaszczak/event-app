package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.repository.UserRepository;
import com.makeitbig.eventapp.wrapper.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new UserDetailsWrapper(user);
    }
}
