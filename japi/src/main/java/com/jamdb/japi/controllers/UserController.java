package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUser(username));
        } catch (UserAuthException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user not found " + username));
        }
    }
}
