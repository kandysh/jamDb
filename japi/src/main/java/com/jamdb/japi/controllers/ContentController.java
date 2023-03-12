package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.services.ContentService.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
@CrossOrigin
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/list")
    public ResponseEntity<List<ContentDetailsDto>> getContentList() {
        return ResponseEntity.ok(contentService.listContent());
    }

    @GetMapping("/show/{contentId}")
    public ResponseEntity<ContentDetailsDto> getContent(@PathVariable String contentId) {
        return ResponseEntity.ok(contentService.getContent(contentId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentDetailsDto>> getContentOnQuery(@RequestParam("q") String query) {

        return ResponseEntity.ok(contentService.getContentForSearchQuery(query));
    }

    @GetMapping("/recommendation/{contentId}")
    public ResponseEntity<List<ContentDetailsDto>> getRecommendations(@PathVariable String contentId) {
        return ResponseEntity.ok(contentService.getRecommendations(contentId));
    }

}
