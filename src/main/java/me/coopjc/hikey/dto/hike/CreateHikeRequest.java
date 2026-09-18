package me.coopjc.hikey.dto.hike;

public record CreateHikeRequest(
    String name,
    String notes
) {}