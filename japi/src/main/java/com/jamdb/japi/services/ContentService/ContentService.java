package com.jamdb.japi.services.ContentService;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.content.Status;
import com.jamdb.japi.entities.content.Type;
import com.jamdb.japi.repository.ContentRepository;
import com.jamdb.japi.utils.UtilFunctions;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    public List<ContentDetailsDto> listContent() {
        return utilFunctions.contentToContentDetails.apply(contentRepository.findContentRandomly());
    }

    @Override
    public Optional<Content> findContent(UUID uuid) {
        return contentRepository.findById(uuid);
    }

    @Override
    @Cacheable(value = "content")
    public ContentDetailsDto getContent(String contentId) {
        var content = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        return utilFunctions.contentToDetails.apply(content);
    }


    @Cacheable(value = "related")
    @Override
    public List<ContentDetailsDto> getRelated(String contentId) {
        var contents = contentRepository.findById(UUID.fromString(contentId)).orElseThrow();
        if (contents.getRelations().isEmpty())
            return new ArrayList<>();
        return contents.getRelations().stream().limit(5).map(relation -> {
            var content = contentRepository.findContentBySourceId(relation);
            return content.map(value -> utilFunctions.contentToDetails.apply(value)).orElse(null);
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Cacheable("currentBystatus")
    @Override
    public List<ContentDetailsDto> getCurrentByStatus(String status) {
        return utilFunctions.contentToContentDetails.apply(contentRepository
                .findContentByAnimeSeasonAndStatus(utilFunctions.getCurrentAnimeSeason(),
                        Status.valueOf(status.toUpperCase())));
    }

    @Cacheable("currentByType")
    @Override
    public List<ContentDetailsDto> getCurrentByType(String type) {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonAndType(utilFunctions.getCurrentAnimeSeason(),
                        Type.valueOf(type.toUpperCase())));
    }

    @Cacheable("currentByStatusAndType")
    @Override
    public List<ContentDetailsDto> getCurrentByStatusAndType(String status, String type) {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonAndStatusAndType(utilFunctions.getCurrentAnimeSeason(),
                        Status.valueOf(status.toUpperCase()), Type.valueOf(type.toUpperCase())));
    }

    @Cacheable("top-current")
    @Override
    public List<ContentDetailsDto> getCurrentTopRated() {
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentByAnimeSeasonOrderByScoreDesc(
                                utilFunctions.getCurrentAnimeSeason())
                        .stream()
                        .limit(25)
                        .toList());
    }

    @Cacheable("top")
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
            contents.addAll(contentRepository.findAllContentAndOrderByLikes());
        }
        if (contents.size() < 25)
            contents.addAll(contentRepository.findTopContent());
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

    @Cacheable("search-params")
    @Override
    public List<ContentDetailsDto> getContentBySearchParams(String search, String tag, String year, String season, String status, String type) {
        if (Objects.nonNull(tag) && (Objects.isNull(search) || Objects.isNull(year) || Objects.isNull(
                season) || Objects.isNull(status) || Objects.isNull(type)))
            return utilFunctions.contentToContentDetails.apply(contentRepository.findContentByTag(tag));

        if (Objects.nonNull(search) && Objects.isNull(tag))
            return utilFunctions.contentToContentDetails.apply(contentRepository.findContentBySynonymsAndTitle(search));
        return utilFunctions.contentToContentDetails.apply(
                contentRepository.findContentBasedOnQuery(search, tag, year, season, status, type));
    }


}
