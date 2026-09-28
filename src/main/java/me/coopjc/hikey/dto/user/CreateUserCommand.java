package me.coopjc.hikey.dto.user;

public record CreateUserCommand(
        String email,
        String password,
        String displayName,
        Integer age
) {}