package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.model.HikeStatus;

import java.time.LocalDateTime;

public record HikeResponse(
        Long id,
        String name,
        String notes,
        HikeStatus status,
        float distanceMiles,
        float durationMin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public static HikeResponse from(Hike hike) {
        return new HikeResponse(
                hike.getId(),
                hike.getName(),
                hike.getNotes(),
                hike.getStatus(),
                hike.getDistanceMiles(),
                hike.getDurationMin(),
                hike.getCreatedAt(),
                hike.getUpdatedAt()
        );
    }
}