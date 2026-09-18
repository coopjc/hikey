package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.HikePoint;

import java.time.LocalDateTime;

public record HikePointResponse(
        Long id,
        Double latitude,
        Double longitude,
        Double elevation,
        LocalDateTime savedAt

) {
    public static HikePointResponse from(HikePoint hikePoint) {
        return new HikePointResponse(
                hikePoint.getId(),
                hikePoint.getLatitude(),
                hikePoint.getLongitude(),
                hikePoint.getElevation(),
                hikePoint.getSavedAt()
        );
    }
}