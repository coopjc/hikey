package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.Hike;

import java.util.List;

public record GetHikesResponse(
        List<HikeResponse> hikes
) {
    public static GetHikesResponse from(List<HikeResponse> hikes) {
        return new GetHikesResponse(hikes);
    }
}