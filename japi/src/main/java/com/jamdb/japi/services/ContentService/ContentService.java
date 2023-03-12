package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.repository.ContentRepository;
import com.jamdb.japi.utils.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ContentService implements ContentServiceInterface {
    private final ContentRepository contentRepository;
    private final UtilService utilService;

    @Override
    public void saveAllContent(List<Content> contents) {
        contentRepository.saveAll(contents);
    }

    @Override
    public List<Content> listAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public List<ContentDetailsDto> listContent() {
        Random random = new Random();
        var rand = random.nextInt(30);
        Pageable pageable = PageRequest.of(rand, 10);
        Page<Content> randomSample = contentRepository.findAll(pageable);
        return randomSample.getContent().stream().map(
                content -> ContentDetailsDto
                        .builder()
                        .contentId(content.getId().toString())
                        .title(content.getTitle())
                        .picture(content.getPicture())
                        .description(content.getDescription())
                        .thumbnail(content.getThumbnail())
                        .totalEpisodes(content.getEpisodes())
                        .type(content.getType())
                        .contentStatus(content.getStatus())
                        .score(content.getScore())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Content> findContent(UUID uuid) {
        return contentRepository.findById(uuid);
    }

    @Override
    public Content saveContent(Content content) {
        if (Objects.isNull(content.getDescription())) {
            content = utilService.addDescriptionAndScore(content);
        }
        contentRepository.save(content);
        return content;
    }
    @Override
    public ContentDetailsDto getContent(String contentId){
        var content = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        return ContentDetailsDto
                .builder()
                .contentId(content.getId().toString())
                .title(content.getTitle())
                .picture(content.getPicture())
                .description(content.getDescription())
                .thumbnail(content.getThumbnail())
                .totalEpisodes(content.getEpisodes())
                .type(content.getType())
                .contentStatus(content.getStatus())
                .score(content.getScore())
                .build();
    }
}
