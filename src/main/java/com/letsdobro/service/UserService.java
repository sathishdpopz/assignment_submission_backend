package com.letsdobro.service;

import com.letsdobro.model.User;
import com.letsdobro.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service//singleton class managing by spring
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Optional<User> findUserByUsername(String username){
        return userRepo.findByUsername(username);
    }


}
