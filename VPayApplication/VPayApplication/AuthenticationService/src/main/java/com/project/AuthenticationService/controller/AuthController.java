package com.project.AuthenticationService.controller;

import com.project.AuthenticationService.dto.LoginRequest;
import com.project.AuthenticationService.dto.RegisterRequest;
import com.project.AuthenticationService.exception.EmailAlreadyExistsException;
import com.project.AuthenticationService.exception.UserNotFoundException;
import com.project.AuthenticationService.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) throws EmailAlreadyExistsException {
        if (registerRequest.getRole().equals("ADMIN"))
        {
            return authService.registerAdmin(registerRequest);
        }
        else
        {
            return authService.registerCustomer(registerRequest);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) throws UserNotFoundException {
        return authService.login(loginRequest);
    }
}
