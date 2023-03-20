package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;

import java.util.List;

public interface CustomContentRepository {
    List<Content> findContentBasedOnQuery(String search, String tag, String year, String season, String status, String type);
}
