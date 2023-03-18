package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.content.Status;
import com.jamdb.japi.entities.content.Type;
import com.jamdb.japi.repository.ContentRepository;
import com.jamdb.japi.utils.UtilFunctions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ContentService implements ContentServiceInterface {
    private final ContentRepository contentRepository;
    private final UtilFunctions utilFunctions;

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
        return utilFunctions.contentToContentDetails.apply(contentRepository.findContentRandomly());
    }

    @Override
    public Optional<Content> findContent(UUID uuid) {
        return contentRepository.findById(uuid);
    }

    @Override
    public Content saveContent(Content content) {
        contentRepository.save(content);
        return content;
    }

    @Override
    public ContentDetailsDto getContent(String contentId) {
        var content = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        return utilFunctions.contentToDetails.apply(content);
    }

    @Override
    public List<ContentDetailsDto> getContentForSearchQuery(String query) {
        return utilFunctions.contentToContentDetails.apply(contentRepository.findContentBySynonymsAndTitle(query));
    }

    @Override
    public List<ContentDetailsDto> getRelated(String contentId) {
        var contents = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        if (contents.getRelations().isEmpty())
            return new ArrayList<>();
        return contents.getRelations().stream().limit(5).map(relation -> {
            var content = contentRepository.findContentBySource(relation).orElseThrow();
            return utilFunctions.contentToDetails.apply(content);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContentDetailsDto> getCurrentByStatus(String status) {
        return utilFunctions.contentToContentDetails.apply(contentRepository
                .findContentByAnimeSeasonAndStatus(utilFunctions.getCurrentAnimeSeason(),
                        Status.valueOf(status.toUpperCase())));
    }

    @Override
    public List<ContentDetailsDto> getCurrentByType(String type) {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonAndType(utilFunctions.getCurrentAnimeSeason(),
                        Type.valueOf(type.toUpperCase())));
    }

    @Override
    public List<ContentDetailsDto> getCurrentByStatusAndType(String status, String type) {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonAndStatusAndType(utilFunctions.getCurrentAnimeSeason(),
                        Status.valueOf(status.toUpperCase()), Type.valueOf(type.toUpperCase())));
    }

    @Override
    public List<ContentDetailsDto> getCurrentTopRated() {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonOrderByScoreDesc(utilFunctions.getCurrentAnimeSeason())
                        .stream()
                        .limit(25)
                        .toList());
    }

    @Override
    public List<ContentDetailsDto> getTopContent(String type) {
        if (type.equals("all"))
            return utilFunctions.contentToContentDetails.apply(contentRepository.findTopContent());
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByTypeOrderByScoreDesc(Type.valueOf(type.toUpperCase()))
                        .stream()
                        .limit(100)
                        .toList());
    }

    @Override
    public List<ContentDetailsDto> getTrending() {
        var contents = contentRepository.findContentByAnimeSeasonAndLikesNotNullOrderByLikesDesc(
                utilFunctions.getCurrentAnimeSeason());
        if (contents.isEmpty()) {
            return getCurrentTopRated();
        }
        if(contents.size()<25){
            contents.addAll(contentRepository.findContentByLikesNotNullOrderByLikesDesc());
        }
        if (contents.size() < 25) {
            contents.addAll(contentRepository.findContentByAnimeSeasonAndLikesNullOrderByScoreDesc(
                    utilFunctions.getCurrentAnimeSeason()));
        }
        return utilFunctions.contentToContentDetails.apply(contents.stream().limit(25).toList());
    }

    @Override
    public void addLikeToContent(String contentId) {
        var content = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        content.setLikes(Objects.isNull(content.getLikes()) ? 0 : content.getLikes() + 1);
        contentRepository.save(content);
    }

    @Override
    public void dislikeContent(String contentId) {
        var content = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        content.setLikes(Objects.isNull(content.getLikes()) ? 0 : content.getLikes() - 1);
        contentRepository.save(content);
    }

}
