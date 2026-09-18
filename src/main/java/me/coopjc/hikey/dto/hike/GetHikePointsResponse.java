package me.coopjc.hikey.dto.hike;

import java.util.List;

public record GetHikePointsResponse(
        List<HikePointResponse> hikePoints
) {
    public static GetHikePointsResponse from(List<HikePointResponse> hikePoints) {
        return new GetHikePointsResponse(hikePoints);
    }
}