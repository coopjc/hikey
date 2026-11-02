package me.coopjc.hikey.dto.hike;

public record UpdateHikeRequest(
   String name,
   String notes,
   Float distanceMiles,
   Float durationMin
) {}