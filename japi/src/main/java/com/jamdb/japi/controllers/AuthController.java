package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserService.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserServiceInterface userService;

    @PostMapping("/register")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto) throws UserAuthException {

        return ResponseEntity.ok(userService.registerNewUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAuthDto userAuthDto) throws UserAuthException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.authenticateUser(userAuthDto));
    }

    @PostMapping("/checkuser/{:username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok(new ApiResponse(userService.userExists(username) ? "true" : "false"));
    }

}
