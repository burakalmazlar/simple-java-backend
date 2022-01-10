package com.simple.backend.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private final long expiresIn;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
        this.expiresIn = 10 * 60_000;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }
}