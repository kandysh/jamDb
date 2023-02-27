package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
import com.jamdb.japi.dto.NewUserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @PostMapping("/create")
    public ResponseEntity<?> newUserRegistration(@RequestBody @Valid NewUserDto userDto){
        return ResponseEntity.ok(new ApiResponse("User Created Successfully"));
    }
}
