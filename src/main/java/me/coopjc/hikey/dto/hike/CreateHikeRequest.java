package me.coopjc.hikey.dto.hike;

import jakarta.validation.constraints.NotBlank;

public record CreateHikeRequest(
    @NotBlank(message = "Name is required!")
    String name,

    String notes
) {}