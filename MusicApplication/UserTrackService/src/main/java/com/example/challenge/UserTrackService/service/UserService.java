package com.example.challenge.UserTrackService.service;

import com.example.challenge.UserTrackService.exception.TrackNotFoundException;
import com.example.challenge.UserTrackService.exception.UserAlreadyExistsException;
import com.example.challenge.UserTrackService.exception.UserNotFoundException;
import com.example.challenge.UserTrackService.model.Track;
import com.example.challenge.UserTrackService.model.User;

import java.util.List;

public interface UserService {
    public abstract User addUser(User user) throws UserAlreadyExistsException;

    public abstract User addTrackToPlaylist(int userId, Track track) throws UserNotFoundException;

    public abstract User deleteTrackFromPlaylist(int userId,int trackId) throws UserNotFoundException, TrackNotFoundException;

    public abstract List<Track> getAllTrackFromPlaylist(int userId) throws UserNotFoundException;

    public abstract User updateTrackInPlaylist(int userId, Track track) throws TrackNotFoundException, UserNotFoundException;
}
