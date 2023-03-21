package com.jamdb.japi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamdb.japi.dto.ApiSeederResponse;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import org.apache.commons.collections4.ListUtils;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilService {
    private final ContentRepository contentRepository;

    private final CacheManager cacheManager;
    private final ObjectMapper mapper = new ObjectMapper();

    @Async
    public CompletableFuture<Content> addDescriptionAndScore(Content content) {
        Jikan jikan = new Jikan();
        try {
            var temp = jikan.query()
                    .anime()
                    .search()
                    .query(content.getTitle() + content.getType())
                    .limit(1)
                    .execute()
                    .collectList()
                    .block();
            if (temp != null) {
                var anime = temp.get(0);
                content.setDescription(anime.getSynopsis());
                content.setScore(anime.getScore());
            }
        } catch (JikanQueryException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return CompletableFuture.completedFuture(content);
    }

    @SneakyThrows
    @Async
    public void saveUpdatedInfo(Content content) {
        var temp = contentRepository.findContentBySourceId(content.getSources().get(0));
        if (temp.isEmpty()) {
            addDescriptionAndScore(content).thenApplyAsync(t ->
                    contentRepository.save(Content.builder()
                            .title(t.getTitle())
                            .likes(t.getLikes())
                            .score(t.getScore())
                            .description(t.getDescription())
                            .sources(t.getSources())
                            .picture(t.getPicture())
                            .animeSeason(t.getAnimeSeason())
                            .episodes(t.getEpisodes())
                            .tags(t.getTags())
                            .relations(t.getRelations())
                            .synonyms(t.getSynonyms())
                            .thumbnail(t.getThumbnail())
                            .type(t.getType()).status(t.getStatus())
                            .sourceId(t.getSources().get(0)).build()));
        } else {
            temp.get().setStatus(content.getStatus().toString());
            temp.get().setEpisodes(content.getEpisodes());
            temp.get().setPicture(content.getPicture());
            contentRepository.save(temp.get());
        }

    }

    @Scheduled(cron = "0 1 * * 6 ?")
    public void getJson() {

        TypeReference<ApiSeederResponse> typeReference = new TypeReference<>() {
        };
        CompletableFuture<ApiSeederResponse> response = CompletableFuture.supplyAsync(() -> {
            try {
                return mapper.readValue(
                        new URL("https://raw.githubusercontent.com/manami-project/anime-offline-database/master/anime-offline-database.json"),
                        typeReference);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ExecutorService service = Executors.newCachedThreadPool();

        response.thenAcceptAsync(apiResponse ->
                ListUtils.partition(apiResponse.getData(), 1000).forEach((contents -> service.execute(
                        () -> contents.forEach(this::saveUpdatedInfo)))));
    }

    @Scheduled(cron = "0 3 * * 6 ?")
    public void clearCacheSchedule() {
        cacheManager.getCacheNames().forEach(cache -> Objects.requireNonNull(cacheManager.getCache(cache)).clear());
    }
}
