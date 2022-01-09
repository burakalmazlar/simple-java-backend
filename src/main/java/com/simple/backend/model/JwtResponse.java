package com.simple.backend.model;
import com.simple.backend.config.JwtTokenUtil;

import java.io.Serializable;

import static com.simple.backend.config.JwtTokenUtil.JWT_TOKEN_VALIDITY;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private final long expiresIn;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
        this.expiresIn = JWT_TOKEN_VALIDITY;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }
}