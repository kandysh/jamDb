package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.AuthService.AuthServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceInterface authService;

    @PostMapping("/register")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto) throws UserAuthException {

        return ResponseEntity.ok(authService.registerNewUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAuthDto userAuthDto) throws UserAuthException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.authenticateUser(userAuthDto));
    }

    @PostMapping("/checkuser/{:username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok(new ApiResponse(authService.userExists(username) ? "true" : "false"));
    }

}
