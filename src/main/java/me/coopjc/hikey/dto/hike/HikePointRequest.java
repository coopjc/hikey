package me.coopjc.hikey.dto.hike;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HikePointRequest(
        @NotNull
        Double latitude,

        @NotNull
        Double longitude,

        @NotNull
        Double elevation,

        @NotNull
        LocalDateTime savedAt
) {}