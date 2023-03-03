package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto) throws UserAuthException {
        return ResponseEntity.ok(userService.registerNewUser(userDto));

    }
}
