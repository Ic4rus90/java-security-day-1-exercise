package com.booleanuk.api.videoGames;

import com.booleanuk.api.loan.Loan;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

@Entity
@Table(name = "video_games")
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "game_studio")
    private String gameStudio;

    @Column(name = "age_rating")
    private Integer ageRating;

    @Column(name = "number_of_players")
    private Integer numberOfPlayers;

    @Column(name = "genre")
    private String genre;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(mappedBy = "videoGame", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Loan> loanHistory;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
