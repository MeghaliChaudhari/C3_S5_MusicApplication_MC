package com.example.challenge.UserAuthenticationService.repository;

import com.example.challenge.UserAuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserIdAndPassword(int userId, String password);
}
