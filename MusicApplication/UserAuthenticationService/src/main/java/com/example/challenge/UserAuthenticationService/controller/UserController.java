package com.example.challenge.UserAuthenticationService.controller;

import com.example.challenge.UserAuthenticationService.domain.User;
import com.example.challenge.UserAuthenticationService.exception.UserNotFoundException;
import com.example.challenge.UserAuthenticationService.service.SecurityTokenGenerator;
import com.example.challenge.UserAuthenticationService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userauth")
public class UserController {
    private UserServiceImpl userServiceImpl;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, SecurityTokenGenerator securityTokenGenerator) {
        this.userServiceImpl = userServiceImpl;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userServiceImpl.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        Map<Integer,String> map = null;
        try {
            User user1 = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
            if (user1.getUserId() == user.getUserId()){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity<>(map,HttpStatus.OK);
        }catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Try After Some Time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchUser(){
        return new ResponseEntity<>(userServiceImpl.getAllUser(),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) throws UserNotFoundException {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userServiceImpl.deleteUser(userId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
}
