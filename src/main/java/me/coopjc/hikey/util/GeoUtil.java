package me.coopjc.hikey.util;

public class GeoUtil {

    private static final double EARTH_RADIUS_METERS = 6_371_000.0;
    private static final double METERS_PER_MILE = 1_609.34;

    public static double calcHaversineMeters(double lat1, double lng1, double lat2, double lng2) {
        double rLat = Math.toRadians(lat2 - lat1);
        double rLng = Math.toRadians(lng2 - lng1);

        double sinLat = Math.sin(rLat / 2);
        double sinLng = Math.sin(rLng / 2);

        double a = sinLat * sinLat
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * sinLng * sinLng;

        a = Math.min(1.0, a);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_METERS * c;
    }

    public static double calcHaversineMiles(double lat1, double lng1, double lat2, double lng2) {
        return calcHaversineMeters(lat1, lng1, lat2, lng2) / METERS_PER_MILE;
    }
}