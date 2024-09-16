package com.booleanuk.api.loan;

import com.booleanuk.api.users.User;
import com.booleanuk.api.users.UserRepository;
import com.booleanuk.api.videoGames.VideoGame;
import com.booleanuk.api.videoGames.VideoGamesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    VideoGamesRepository videoGamesRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public Loan borrowGame(@Valid @RequestBody LoanDTO loanDTO, BindingResult result) {

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please make sure all fields are properly entered.");
        }

        int userId = loanDTO.getUserId();
        int videoGameId = loanDTO.getVideoGameId();

        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with provided id does not exist"));

        VideoGame videoGame = this.videoGamesRepository
                .findById(videoGameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with provided id does not exist"));

        Loan savedLoan = this.loanRepository.save(new Loan(videoGame, user));

        videoGame.setAvailable(false);
        videoGame.getLoanHistory().add(savedLoan);
        this.videoGamesRepository.save(videoGame);

        user.getRentedVideoGames().add(savedLoan);
        this.userRepository.save(user);

        return savedLoan;
    }

    @PutMapping("/{id}/return")
    public Loan returnLoan(@PathVariable (name = "id") int id){
        Loan loan = this.loanRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found."));

        loan.setReturnDate(LocalDateTime.now());
        this.loanRepository.save(loan);

        VideoGame videoGame = loan.getVideoGame();
        videoGame.setAvailable(true);
        this.videoGamesRepository.save(videoGame);

        return this.loanRepository.save(loan);
    }
}
