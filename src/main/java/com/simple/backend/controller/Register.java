package com.simple.backend.controller;

import com.simple.backend.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.function.Function;

@Validated
public class Register {

    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = 4, max = 8)
    private String password;

    public User toUser(Function<CharSequence, String> passwordEncoder) {
        return User.builder().username(this.username).password(passwordEncoder.apply(this.password)).build();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}