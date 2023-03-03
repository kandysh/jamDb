package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserServiceInterface userService;

    @PostMapping("/create")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto) throws UserAuthException {

        return ResponseEntity.ok(userService.registerNewUser(userDto));
    }
    @GetMapping
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(new ApiResponse("success"));
    }
}
