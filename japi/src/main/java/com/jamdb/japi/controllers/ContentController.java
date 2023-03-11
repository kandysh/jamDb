package com.jamdb.japi.controllers;

import com.jamdb.japi.services.ContentService.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(contentService.listAllContent());
    }

}
