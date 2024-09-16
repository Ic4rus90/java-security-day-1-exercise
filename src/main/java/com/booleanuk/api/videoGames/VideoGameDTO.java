package com.booleanuk.api.videoGames;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter

public class VideoGameDTO {

    private Integer id;

    @NotBlank(message = "title is required.")
    private String title;

    @NotBlank(message = "gameStudio is required.")
    private String gameStudio;

    @NotNull(message = "ageRating is required.")
    private Integer ageRating;

    @NotNull(message = "numberOfPlayers is required.")
    private Integer numberOfPlayers;

    @NotBlank(message = "genre is required")
    private String genre;
}
