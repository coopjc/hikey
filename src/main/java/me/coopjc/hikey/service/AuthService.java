package me.coopjc.hikey.service;

import me.coopjc.hikey.dto.auth.LoginRequest;
import me.coopjc.hikey.dto.auth.RegisterRequest;
import me.coopjc.hikey.dto.user.CreateUserCommand;
import me.coopjc.hikey.exception.auth.InvalidCredentialsException;
import me.coopjc.hikey.exception.user.UserAlreadyExistsException;
import me.coopjc.hikey.exception.user.UserNotFoundException;
import me.coopjc.hikey.model.Credentials;
import me.coopjc.hikey.model.User;
import me.coopjc.hikey.repository.CredentialsRepository;
import me.coopjc.hikey.repository.UserRepository;
import me.coopjc.hikey.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CredentialsRepository credentialsRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(CredentialsRepository credentialsRepository, UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest request) {
        User user = userService.createUser(
                new CreateUserCommand(
                        request.email(),
                        request.password(),
                        request.displayName(),
                        request.age()
                )
        );

        String passwordHash = passwordEncoder.encode(request.password());
        credentialsRepository.save(new Credentials(user, passwordHash));

        return jwtService.generateToken(user.getId(), user.getEmail());
    }

    public String loginUser(LoginRequest request) {
        User user;

        // Catch UserNotFoundException and Throw InvalidCredentialsException
        // Avoid Showing Existing User Account
        try {
            user = userService.getUserByEmail(request.email());
        } catch (UserNotFoundException ex) {
            throw new InvalidCredentialsException();
        }

        Credentials credentials = credentialsRepository.findByUserId(user.getId())
                .orElseThrow(InvalidCredentialsException::new);

        // Password hashes don't match
        if(!passwordEncoder.matches(request.password(), credentials.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getId(), user.getEmail());
    }
}