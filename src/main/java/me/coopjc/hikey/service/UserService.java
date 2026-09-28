package me.coopjc.hikey.service;

import me.coopjc.hikey.dto.user.CreateUserCommand;
import me.coopjc.hikey.exception.user.UserAlreadyExistsException;
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

    public User createUser(CreateUserCommand createUserCommand) {
        if(userRepository.existsByEmail(createUserCommand.email())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();

        user.setEmail(createUserCommand.email());
        user.setDisplayName(createUserCommand.displayName());
        user.setAge(createUserCommand.age());

        return userRepository.save(user);
    }
}