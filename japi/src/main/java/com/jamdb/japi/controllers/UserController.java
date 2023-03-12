package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.AddAnimeDto;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) throws UserAuthException {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping("add/{username}/")
    public ResponseEntity<?> addAnime(@PathVariable String username, @RequestBody AddAnimeDto addAnimeDto) {

        return null;
    }

}
