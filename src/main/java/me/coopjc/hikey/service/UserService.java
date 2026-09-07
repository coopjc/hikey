package me.coopjc.hikey.service;

import me.coopjc.hikey.exception.user.UserNotFoundException;
import me.coopjc.hikey.model.User;
import me.coopjc.hikey.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}