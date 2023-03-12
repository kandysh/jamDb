package com.jamdb.japi.controllers;

import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.services.UserService.UserService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<?> getUser(@PathVariable String username) throws UserAuthException {
        return ResponseEntity.ok(userService.getUser(username));
    }

//    @PostMapping("add/{username}")
//    public ResponseEntity<?> addContent(@PathVariable String username, @RequestBody AddContentDto addContentDto) {
//        userService.addContent(username, UUID.fromString(addContentDto.getContentId()));
//        return ResponseEntity.ok(new ApiResponse("content added successfully"));
//    }

//    @GetMapping("view/{username}")
//    public ResponseEntity<?> showContent(@PathVariable String username) throws UserAuthException {
//        return ResponseEntity.ok(userService.showContent(username).stream());
//    }
//
//    @DeleteMapping("/delete/{username}")
//    public ResponseEntity<?> updateContent(@PathVariable String username, @RequestBody AddContentDto addContentDto) {
//        userService.deleteContent(username, UUID.fromString(addContentDto.getContentId()));
//        return ResponseEntity.ok(new ApiResponse("deleted successfully"));
//    }

}
