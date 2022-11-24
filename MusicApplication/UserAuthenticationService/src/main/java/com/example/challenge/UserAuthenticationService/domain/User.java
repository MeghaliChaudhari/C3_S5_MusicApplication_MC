package com.example.challenge.UserAuthenticationService.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private int userId;
    private String password;

    public User() {
    }

    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
