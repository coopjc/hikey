package me.coopjc.hikey.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "credentials")
public class Credentials extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    public Credentials(User user, String passwordHash) {
        this.user = user;
        this.passwordHash = passwordHash;
    }

    public Credentials() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}