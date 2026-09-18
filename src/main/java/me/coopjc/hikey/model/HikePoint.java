package me.coopjc.hikey.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "hike_points")
public class HikePoint extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hike_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hike hike;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double elevation;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    public Long getId() {
        return id;
    }

    public Hike getHike() {
        return hike;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return savedAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return savedAt;
    }

    public void setHike(Hike hike) {
        this.hike = hike;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}