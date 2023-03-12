package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.*;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/details/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) throws UserAuthException {
        return ResponseEntity.ok(userService.getUser(username));
    }
    @GetMapping("/view/{username}")
    public ResponseEntity<List<AnimeDetailsDto>> getAllContent(@PathVariable String username){
        return ResponseEntity.ok(userService.showAnimesOfUser(username));
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<ApiResponse> addAnime(@PathVariable String username, @RequestBody AnimeDto animeDto) {
        userService.addContentToUser(username, animeDto);
        return ResponseEntity.ok(new ApiResponse("addedd content to user " + username));
    }

    @PatchMapping("/update/{username}")
    public ResponseEntity<ApiResponse> editAnime(@PathVariable String username, @RequestBody AnimeDto animeDto) {
        userService.editContentofUser(username, animeDto);
        return ResponseEntity.ok(new ApiResponse("edit content of user " + username));
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponse> deleteAnime(@PathVariable String username, @RequestBody ContentIdDto contentIdDto){
        userService.deleteContentofUser(username, contentIdDto.getContentId());
        return ResponseEntity.ok(new ApiResponse("deleted content of user " + username));
}
}
