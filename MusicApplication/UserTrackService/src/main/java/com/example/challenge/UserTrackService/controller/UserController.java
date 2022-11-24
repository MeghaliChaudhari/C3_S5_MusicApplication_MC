package com.example.challenge.UserTrackService.controller;

import com.example.challenge.UserTrackService.exception.TrackNotFoundException;
import com.example.challenge.UserTrackService.exception.UserAlreadyExistsException;
import com.example.challenge.UserTrackService.exception.UserNotFoundException;
import com.example.challenge.UserTrackService.model.Track;
import com.example.challenge.UserTrackService.model.User;
import com.example.challenge.UserTrackService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/usertrack/app")
public class UserController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            user.setTrackList(new ArrayList<>());
            return new ResponseEntity<>(userServiceImpl.addUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
    }

    @PutMapping("/addtrack/{userId}")
    public ResponseEntity<?> addTrack(@PathVariable int userId, @RequestBody Track track) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userServiceImpl.addTrackToPlaylist(userId,track),HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("/deletetrack/{userId}/{trackId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int userId,@PathVariable int trackId) throws TrackNotFoundException, UserNotFoundException {
        try {
            return new ResponseEntity<>(userServiceImpl.deleteTrackFromPlaylist(userId, trackId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (TrackNotFoundException e){
            throw new TrackNotFoundException();
        }
    }

    @GetMapping("/gettrack/{userId}")
    public ResponseEntity<?> getTrack(@PathVariable int userId) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userServiceImpl.getAllTrackFromPlaylist(userId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @PutMapping("/updatetrack/{userId}")
    public ResponseEntity<?> updateTrack(@PathVariable int userId, @RequestBody Track track) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userServiceImpl.updateTrackInPlaylist(userId,track),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }
}
