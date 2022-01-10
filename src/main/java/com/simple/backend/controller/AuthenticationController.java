package com.simple.backend.controller;

import com.simple.backend.model.User;
import com.simple.backend.repository.UserRepository;
import com.simple.backend.util.JwtUtil;
import com.simple.backend.util.JwtUtil.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("auth")
@Transactional
public class AuthenticationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public JwtToken save(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(passwordEncoder.encode(request.getParameter("password")));
        userRepository.save(user);
        JwtToken token = jwtUtil.generate(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), new ArrayList<>()));
        return token;
    }
}