package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.repository.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ContentService implements ContentServiceInterface{
    private final ContentRepository contentRepository;
    @Override
    public Iterable<Content> saveAllContent(List<Content> contents) {
        return contentRepository.saveAll(contents);
    }

    @Override
    public Iterable<Content> listAllContent() {
        return contentRepository.findAll();
    }
}
