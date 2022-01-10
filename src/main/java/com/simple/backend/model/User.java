package com.simple.backend.model;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)

    private String username;

    @Column(nullable = false)

    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
