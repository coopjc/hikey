package me.coopjc.hikey.dto.hike;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PushHikePointsRequest(
        @NotNull
        List<@Valid HikePointRequest> hikePoints
) { }