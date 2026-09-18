package me.coopjc.hikey.model;

import jakarta.persistence.*;

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
    private float distanceMiles;

    @Column(nullable = false)
    private float durationMin;

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

    public float getDistanceMiles() {
        return distanceMiles;
    }

    public float getDurationMin() {
        return durationMin;
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

    public void setDistanceMiles(float distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public void setDurationMin(float durationMin) {
        this.durationMin = durationMin;
    }
}