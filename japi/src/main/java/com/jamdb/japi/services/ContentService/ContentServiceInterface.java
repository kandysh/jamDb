package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentServiceInterface {


    List<ContentDetailsDto> listContent();

    Optional<Content> findContent(UUID uuid);


    ContentDetailsDto getContent(String contentId);




    List<ContentDetailsDto> getRelated(String contentId);


    List<ContentDetailsDto> getCurrentByStatus(String status);


    List<ContentDetailsDto> getCurrentByType(String type);

    List<ContentDetailsDto> getCurrentByStatusAndType(String status, String type);

    List<ContentDetailsDto> getCurrentTopRated();

    List<ContentDetailsDto> getTopContent(String type);

    List<ContentDetailsDto> getTrending();

    void addLikeToContent(String contentId);

    void dislikeContent(String contentId);


    List<ContentDetailsDto> getContentBySearchParams(String search, String tag, String year, String season, String status, String type);
}
