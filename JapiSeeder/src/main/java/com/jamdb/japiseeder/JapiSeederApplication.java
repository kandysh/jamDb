package com.jamdb.japiseeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamdb.japiseeder.dto.ApiResponse;
import com.jamdb.japiseeder.entities.Content;
import com.jamdb.japiseeder.entities.ContentRepository;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAsync
public class JapiSeederApplication {
    private final ContentRepository contentRepository;

    public static void main(String[] args) {
        SpringApplication.run(JapiSeederApplication.class, args);
    }

    @Async
    public CompletableFuture<Content> addDescriptionAndScore(Content content) {
        Jikan jikan = new Jikan();
        try {
            var temp = jikan.query()
                    .anime()
                    .search()
                    .query(content.getTitle())
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

    @Bean
    CommandLineRunner runner() {
        return args -> {
            getJson();
        };
    }

    public void saveUpdatedInfo(Content content) {
        var temp = contentRepository.findContentBySourceId(content.getSources().get(0)).orElse(content);
        addDescriptionAndScore(temp).thenAcceptAsync(t ->
                contentRepository.save(Content.builder()
                        .title(t.getTitle())
                        .likes(t.getLikes())
                        .score(t.getScore())
                        .description(t.getDescription())
                        .sources(t.getSources())
                        .picture(t.getTitle())
                        .animeSeason(t.getAnimeSeason())
                        .episodes(t.getEpisodes())
                        .tags(t.getTags())
                        .relations(t.getRelations())
                        .synonyms(t.getSynonyms())
                        .thumbnail(t.getThumbnail())
                        .type(t.getType()).status(t.getStatus())
                        .sourceId(t.getSources().get(0)).build()));
        contentRepository.save(content);

    }

    public void getJson() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ApiResponse> typeReference = new TypeReference<ApiResponse>() {
        };
        var response = mapper.readValue(
                new URL("https://raw.githubusercontent.com/manami-project/anime-offline-database/master/anime-offline-database.json"),
                typeReference);

    }

}


