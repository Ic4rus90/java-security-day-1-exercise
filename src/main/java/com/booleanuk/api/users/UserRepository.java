package com.booleanuk.api.users;

import com.booleanuk.api.loan.Loan;
import com.booleanuk.api.videoGames.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {}
