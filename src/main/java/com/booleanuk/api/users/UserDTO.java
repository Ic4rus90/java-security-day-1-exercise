package com.booleanuk.api.users;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserDTO {
    @NotBlank(message = "fullName is required.")
    private String fullName;
}
