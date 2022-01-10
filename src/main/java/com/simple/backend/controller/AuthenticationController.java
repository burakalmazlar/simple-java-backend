package com.simple.backend.controller;

import com.simple.backend.model.User;
import com.simple.backend.repository.UserRepository;
import com.simple.backend.util.JwtUtil;
import com.simple.backend.util.JwtUtil.JwtToken;
import com.simple.backend.util.PojoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.function.Function;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("auth")
@Transactional
public class AuthenticationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PojoValidator validator;

    @PostMapping("/register")
    public JwtToken save(HttpServletRequest request) {
        Register register = new Register(
                request.getParameter("username"),
                request.getParameter("password"));

        validator.validate(register);

        User user = register.toUser(passwordEncoder::encode);
        userRepository.save(user);

        JwtToken token = jwtUtil.generate(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), new ArrayList<>()));

        return token;
    }

    private record Register(@NotBlank
                            @Email String username, @NotBlank
                            @Size(min = 4, max = 8) String password) {

        public User toUser(Function<CharSequence, String> passwordEncoder) {
            return User.builder().username(this.username).password(passwordEncoder.apply(this.password)).build();
        }
    }
}