package com.mealtracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Size(max = 50)
    @NotBlank(message = "Please enter your username")
    private String username;

    @Size(min = 5, max =80)
    @Email(message = "Please enter your email address")
    private String email;

    @Size(min = 4, max = 20, message = "Please enter a password of sufficient length")
    private String password;


}
