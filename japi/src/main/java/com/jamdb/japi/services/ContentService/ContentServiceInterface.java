package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentServiceInterface {
    void saveAllContent(List<Content> contents);

    List<Content> listAllContent();

    List<ContentDetailsDto> listContent();

    Optional<Content> findContent(UUID uuid);

    Content saveContent(Content content);

    ContentDetailsDto getContent(String contentId);

    List<ContentDetailsDto> getContentForSearchQuery(String query);

    List<ContentDetailsDto> getRelated(String contentId);



    List<ContentDetailsDto> getCurrentByStatus(String status);


    List<ContentDetailsDto> getCurrentByType(String type);

    List<ContentDetailsDto> getCurrentByStatusAndType(String status, String type);

    List<ContentDetailsDto> getCurrentTopRated();

    List<ContentDetailsDto> getTopContent(String type);

    List<ContentDetailsDto> getTrending();

    void addLikeToContent(String contentId);

    void dislikeContent(String contentId);
}
