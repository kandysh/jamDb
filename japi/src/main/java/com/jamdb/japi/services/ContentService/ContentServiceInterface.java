package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.entities.content.Content;

import java.util.List;

public interface ContentServiceInterface {
    Iterable<Content> saveAllContent(List<Content> contents);

    Iterable<Content> listAllContent();
}
