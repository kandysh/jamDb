package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.services.ContentService.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/list")
    public ResponseEntity<List<ContentDetailsDto>> getContentList() {
        return ResponseEntity.ok(contentService.listContent());
    }
    @GetMapping("/{contentId}")
    public ResponseEntity<ContentDetailsDto> getContent(@PathVariable String contentId){
        return ResponseEntity.ok(contentService.getContent(contentId));
    }

}
