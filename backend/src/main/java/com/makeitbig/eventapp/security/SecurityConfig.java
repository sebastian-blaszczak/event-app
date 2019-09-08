package com.makeitbig.eventapp.security;

import com.makeitbig.eventapp.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring security
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()// allow every URI, that begins with '/api/user/'
                //.anyRequest().authenticated() // protect all other requests
                .and()
                .csrf().disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!
    }


}