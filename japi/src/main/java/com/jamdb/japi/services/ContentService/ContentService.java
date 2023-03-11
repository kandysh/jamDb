package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.repository.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ContentService implements ContentServiceInterface {
    private final ContentRepository contentRepository;

    @Override
    public void saveAllContent(List<Content> contents) {
        contentRepository.saveAll(contents);
    }

    @Override
    public List<Content> listAllContent() {
        Random random = new Random();
        var rand = random.nextInt(30);
        Pageable pageable = PageRequest.of(rand, 25);
        Page<Content> randomSample = contentRepository.findAll(pageable);
        return randomSample.getContent();
    }

    @Override
    public Optional<Content> findContent(UUID uuid) {
        return contentRepository.findById(uuid);
    }
}
