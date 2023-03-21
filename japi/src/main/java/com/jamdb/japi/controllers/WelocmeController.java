package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.WelcomeApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({"/", "/*/**"})
public class WelocmeController {
    @GetMapping
    ResponseEntity<WelcomeApiDto> landingPage() {
        return ResponseEntity.ok(WelcomeApiDto.builder()
                .message("Welcome to JamApi. Currenly we are at v3.Do look at our api documentation. Happy Jamming")
                .url("https://kandysh.gitbook.io/jamdbapi/")
                .content("/content")
                .user("/user")
                .auth("/auth")
                .build());
    }
}
