package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.model.HikeStatus;

public record HikeResponse(
        long id,
        String name,
        String notes,
        HikeStatus status
) {
    public static HikeResponse from(Hike hike) {
        return new HikeResponse(
                hike.getId(),
                hike.getName(),
                hike.getNotes(),
                hike.getStatus()
        );
    }
}