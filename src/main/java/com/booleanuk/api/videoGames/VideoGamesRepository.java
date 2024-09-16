package com.booleanuk.api.videoGames;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGamesRepository extends JpaRepository<VideoGame, Integer> {
}
