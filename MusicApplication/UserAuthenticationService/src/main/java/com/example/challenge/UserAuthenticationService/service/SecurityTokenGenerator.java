package com.example.challenge.UserAuthenticationService.service;

import com.example.challenge.UserAuthenticationService.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<Integer,String> generateToken(User user);
}
