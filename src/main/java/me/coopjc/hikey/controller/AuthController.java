package me.coopjc.hikey.controller;

import jakarta.validation.Valid;
import me.coopjc.hikey.dto.auth.AuthResponse;
import me.coopjc.hikey.dto.Response;
import me.coopjc.hikey.dto.auth.LoginRequest;
import me.coopjc.hikey.dto.auth.RegisterRequest;
import me.coopjc.hikey.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

   public AuthController(AuthService authService) {
       this.authService = authService;
   }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterRequest request) {
        String token = authService.registerUser(request);

        Response response = new Response();

        response.setMessage("Successfully registered.");
        response.setData(new AuthResponse(token));
        response.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginRequest request) {
        String token = authService.loginUser(request);

        Response response = new Response();

        response.setMessage("Successfully logged in.");
        response.setData(new AuthResponse(token));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}