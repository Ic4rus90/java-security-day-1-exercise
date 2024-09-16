package com.booleanuk.api.loan;

import com.booleanuk.api.users.User;
import com.booleanuk.api.videoGames.VideoGame;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "loan")
public class Loan {

    public Loan(VideoGame videoGame, User user){
        this.videoGame = videoGame;
        this.user = user;
        this.borrowDate = LocalDateTime.now();
        this.returnDate = borrowDate.plusDays(7);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_game_id", nullable = false)
    @JsonBackReference
    private VideoGame videoGame;
}
