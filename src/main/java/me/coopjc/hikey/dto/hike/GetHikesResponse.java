package me.coopjc.hikey.dto.hike;

import org.springframework.data.domain.Page;

public record GetHikesResponse(
        Page<HikeResponse> hikes
) {
    public static GetHikesResponse from(Page<HikeResponse> hikes) {
        return new GetHikesResponse(hikes);
    }
}