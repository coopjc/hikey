package me.coopjc.hikey.dto.hike;

import me.coopjc.hikey.model.HikeStatus;

public record UpdateHikeRequest(
   String name,
   String notes,
   HikeStatus status,
   Float distanceMiles,
   Float durationMin
) {}