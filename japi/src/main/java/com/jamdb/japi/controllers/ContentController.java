package com.jamdb.japi.controllers;

import com.jamdb.japi.dto.ApiResponse;
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

    @PutMapping("/like/{contentId}")
    public ResponseEntity<ApiResponse> likeContent(@PathVariable String contentId) {
        contentService.addLikeToContent(contentId);
        return ResponseEntity.ok(new ApiResponse("liked content"));
    }

    @PutMapping("/dislike/{contentId}")
    public ResponseEntity<ApiResponse> dislikeContent(@PathVariable String contentId) {
        contentService.dislikeContent(contentId);
        return ResponseEntity.ok(new ApiResponse("disliked Content"));
    }

    @GetMapping("/show/{contentId}")
    public ResponseEntity<ContentDetailsDto> getContent(@PathVariable String contentId) {
        return ResponseEntity.ok(contentService.getContent(contentId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentDetailsDto>> getContentOnQuery(@RequestParam("q") String query) {

        return ResponseEntity.ok(contentService.getContentForSearchQuery(query));
    }

    @GetMapping("/related/{contentId}")
    public ResponseEntity<List<ContentDetailsDto>> getRecommendations(@PathVariable String contentId) {
        return ResponseEntity.ok(contentService.getRelated(contentId));
    }

    @GetMapping("/current")
    public ResponseEntity<List<ContentDetailsDto>> getCurrentContent() {
        return ResponseEntity.ok(contentService.getCurrentByStatus("ongoing"));
    }

    @GetMapping("/current/{status}/{type}")
    public ResponseEntity<List<ContentDetailsDto>> getCurrentContentByStatusAndType(@PathVariable String type, @PathVariable String status) {
        return ResponseEntity.ok(contentService.getCurrentByStatusAndType(status, type));
    }

    @GetMapping("/current/{type}")
    public ResponseEntity<List<ContentDetailsDto>> getCurrentContentByType(@PathVariable String type) {
        return ResponseEntity.ok(contentService.getCurrentByType(type));
    }

    @GetMapping("/current/top")
    public ResponseEntity<List<ContentDetailsDto>> getCurrentContentTopRated() {
        return ResponseEntity.ok(contentService.getCurrentTopRated());
    }

    @GetMapping("/top/{type}")
    public ResponseEntity<List<ContentDetailsDto>> getTopContent(@PathVariable String type) {
        return ResponseEntity.ok(contentService.getTopContent(type));
    }

    @GetMapping("/trending")
    public ResponseEntity<List<ContentDetailsDto>> getTrending() {
        return ResponseEntity.ok(contentService.getTrending());
    }


}
