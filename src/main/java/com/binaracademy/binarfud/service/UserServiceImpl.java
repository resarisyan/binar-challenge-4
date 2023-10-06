package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.User;
import com.binaracademy.binarfud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean addNewUser(User user) {
        return Optional.ofNullable(user)
                .map(newUser -> userRepository.save(user))
                .map(Objects::nonNull)
                .orElse(false);
    }

    public Boolean updateUser(String username, User user) {
        return Optional.ofNullable(username)
                .map(userRepository::findByUsername)
                .map(user1 -> {
                    user1.setUsername(user.getUsername());
                    user1.setPassword(user.getPassword());
                    return userRepository.save(user1);
                })
                .map(Objects::nonNull)
                .orElse(false);
    }

    public Boolean deleteUser(String username) {
        return Optional.ofNullable(username)
                .map(userRepository::deleteByUsername)
                .map(Objects::nonNull)
                .orElse(false);
    }
}
