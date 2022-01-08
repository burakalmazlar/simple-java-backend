package com.simple.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SimpleJavaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJavaBackendApplication.class, args);
    }

}
