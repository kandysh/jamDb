package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserServiceInterface;
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

    @PostMapping("/create")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto) {

        try {
            return ResponseEntity.ok(userService.registerNewUser(userDto));
//            return ResponseEntity.ok(new UserResponse(user.getUsername(),user.getEmail()));
        } catch (UserAuthException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("user already exists" + userDto.getUserName()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAuthDto userAuthDto) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.authenticateUser(userAuthDto));
        } catch (UserAuthException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(e.getMessage()));
        }
    }

}
