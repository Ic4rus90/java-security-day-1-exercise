package com.booleanuk.api.loan;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LoanDTO {

    @NotNull(message = "UserId is required.")
    private Integer userId;

    @NotNull(message = "VideoGameId is required")
    private Integer videoGameId;
}
