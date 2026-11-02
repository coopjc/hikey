package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.HikeStats;

public record HikeStatsResponse(
        long totalHikes,
        float totalDistance
) {
    public static HikeStatsResponse from(HikeStats hikeStats) {
        return new HikeStatsResponse(
                hikeStats.getTotalHikes(),
                hikeStats.getTotalDistance()
        );
    }
}