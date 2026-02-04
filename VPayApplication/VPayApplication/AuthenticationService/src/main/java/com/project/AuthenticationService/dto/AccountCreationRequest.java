package com.project.AuthenticationService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AccountCreationRequest {
    @NotNull(message = "Customer Id cannot be empty")
    private Long customerId;
    @NotBlank(message = "Account type ")
    private String accountType;
    @Pattern(regexp = "^\\d{4}$",message = "Pin should be of length 4 and should consist only digits")
    private int pin;
}
