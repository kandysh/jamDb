package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.entities.content.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentServiceInterface {
    void saveAllContent(List<Content> contents);

    List<Content> listAllContent();

    Optional<Content> findContent(UUID uuid);
}
