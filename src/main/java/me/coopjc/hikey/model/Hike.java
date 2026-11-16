package me.coopjc.hikey.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "hikes")
public class Hike extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HikeStatus status;

    @Column(nullable = false)
    private double distanceMiles = 0.0;

    @Column(nullable = false)
    private double durationMin = 0.0;

    private LocalDateTime lastResumedAt;

    private LocalDateTime completedAt;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public HikeStatus getStatus() {
        return status;
    }

    public double getDistanceMiles() {
        return distanceMiles;
    }

    public double getDurationMin() {
        return durationMin;
    }

    public LocalDateTime getLastResumedAt() {
        return lastResumedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(HikeStatus status) {
        this.status = status;
    }

    public void setDistanceMiles(double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setDurationMin(double durationMin) {
        this.durationMin = durationMin;
    }

    public void setLastResumedAt(LocalDateTime lastResumedAt) {
        this.lastResumedAt = lastResumedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}