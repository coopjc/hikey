package me.coopjc.hikey.dto.auth;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank(message = "Email is required!")
        String email,

        @NotBlank(message = "Password is required!")
        String password,

        @NotBlank(message = "Name is required!")
        String displayName,

        @NotNull(message = "Age is required!")
        @Min(value = 13, message = "Must be at least 13!")
        Integer age
) {}