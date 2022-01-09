package com.simple.backend.controller;

import com.simple.backend.config.JwtTokenUtil;
import com.simple.backend.model.JwtResponse;
import com.simple.backend.model.Password;
import com.simple.backend.model.User;
import com.simple.backend.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class UserController {

    UserRepository userRepository;

    @Autowired
    public void setPersonRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public JwtResponse save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        final String token = jwtTokenUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), new ArrayList<>()));

        return new JwtResponse(token);
    }

    @PostMapping("/change-password")
    @Transactional
    public JwtResponse changePassword(@RequestBody Password password) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(authentication.getName());
        user.setPassword(passwordEncoder.encode(password.getPassword()));

        final String token = jwtTokenUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), new ArrayList<>()));

        return new JwtResponse(token);
    }
}
