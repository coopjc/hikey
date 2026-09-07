package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.Hike;

import java.time.LocalDateTime;

public record CreateHikeResponse(
        Long id,
        String name,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CreateHikeResponse from(Hike hike) {
        return new CreateHikeResponse(
                hike.getId(),
                hike.getName(),
                hike.getNotes(),
                hike.getCreatedAt(),
                hike.getUpdatedAt()
        );
    }
}