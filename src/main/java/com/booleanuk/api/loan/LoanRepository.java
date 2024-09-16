package com.booleanuk.api.loan;

import com.booleanuk.api.users.User;
import com.booleanuk.api.videoGames.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    public List<Loan> findLoansByVideoGame(VideoGame videoGame);
    public List<Loan> findLoansByUser(User user);
}
