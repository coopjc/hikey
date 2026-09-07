package me.coopjc.hikey.service;

import me.coopjc.hikey.dto.auth.LoginRequest;
import me.coopjc.hikey.dto.auth.RegisterRequest;
import me.coopjc.hikey.exception.auth.InvalidCredentialsException;
import me.coopjc.hikey.exception.user.UserAlreadyExistsException;
import me.coopjc.hikey.exception.user.UserNotFoundException;
import me.coopjc.hikey.model.User;
import me.coopjc.hikey.repository.UserRepository;
import me.coopjc.hikey.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();

        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setDisplayName(request.displayName());
        user.setAge(request.age());

        user = userRepository.save(user);

        return jwtService.generateToken(user.getId(), user.getEmail());
    }

    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(UserNotFoundException::new);

        // Password hashes don't match
        if(!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getId(), user.getEmail());
    }
}