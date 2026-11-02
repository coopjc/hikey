package me.coopjc.hikey.dto.hike;

import jakarta.validation.constraints.Size;

public record StartHikeRequest(
    @Size(max = 100, message = "Hike name cannot exceed 100 characters!")
    String name
) {}