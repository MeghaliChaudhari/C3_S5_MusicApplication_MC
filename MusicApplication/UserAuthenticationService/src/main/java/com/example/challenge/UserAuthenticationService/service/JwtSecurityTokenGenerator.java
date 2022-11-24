package com.example.challenge.UserAuthenticationService.service;

import com.example.challenge.UserAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator{
    @Override
    public Map<Integer, String> generateToken(User user) {
        String jwtToken = null;
        jwtToken = Jwts.builder()
                .setSubject(String.valueOf(user.getUserId()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mykey").compact();
        Map<Integer,String> map = new HashMap<>();
        map.put(0,jwtToken);
        map.put(1,"User Successfully Log in");

        return map;
    }
}
