package com.booleanuk.api.users;

import com.booleanuk.api.videoGames.VideoGame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    private List<VideoGame> rentedVideoGames;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
