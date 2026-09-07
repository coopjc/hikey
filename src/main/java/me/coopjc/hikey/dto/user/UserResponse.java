package me.coopjc.hikey.dto.user;

import me.coopjc.hikey.model.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String displayName,
        int age,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getAge(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}