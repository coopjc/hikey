package me.coopjc.hikey.controller;

import me.coopjc.hikey.dto.Response;
import me.coopjc.hikey.dto.user.UserResponse;
import me.coopjc.hikey.model.User;
import me.coopjc.hikey.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Response> getCurrentUser(@AuthenticationPrincipal Long id) {
        User user = userService.getUserById(id);

        Response response = new Response();

        response.setMessage("This is you!");
        response.setData(UserResponse.from(user));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO: Authenticated (Admin)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getAge(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO: Authenticated (Admin)
    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getAge(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}