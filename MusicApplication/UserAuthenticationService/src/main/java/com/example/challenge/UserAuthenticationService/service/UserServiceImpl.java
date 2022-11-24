package com.example.challenge.UserAuthenticationService.service;

import com.example.challenge.UserAuthenticationService.domain.User;
import com.example.challenge.UserAuthenticationService.exception.UserNotFoundException;
import com.example.challenge.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException {
        boolean flag = false;
        if (userRepository.findById(userId).isEmpty()){
            throw  new UserNotFoundException();
        }else {
            userRepository.deleteById(userId);
            flag = true;
        }
        return flag;
    }

    @Override
    public User findByUserIdAndPassword(int userId, String password) throws UserNotFoundException {
        User user = userRepository.findByUserIdAndPassword(userId,password);
        if (user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
