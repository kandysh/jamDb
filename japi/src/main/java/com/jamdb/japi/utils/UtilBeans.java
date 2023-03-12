package com.jamdb.japi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.services.ContentService.ContentService;
import com.jamdb.japi.services.ContentService.ContentServiceInterface;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UtilBeans {

    private final ContentServiceInterface contentService;

    @Bean
    CommandLineRunner runner(ContentService contentService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Content>> typeReference = new TypeReference<List<Content>>() {
            };
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/anime-offline-database.json")) {
                List<Content> contents = mapper.readValue(inputStream, typeReference);
                contentService.saveAllContent(contents);
                System.out.println("Contents Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save content" + e.getMessage());
            }
        };
    }

    @Bean
    public void describe() {
        Jikan jikan = new Jikan();
        var contents = contentService.listAllContent();
        contents.forEach(content -> {
            try {
                var temp = jikan.query().anime().search().query(content.getTitle()).limit(1).execute().collectList().block();
                if (temp != null) {
                    var anime = temp.get(0);
                    if (anime != null) {
                        var desc = anime.getSynopsis();
                        if (desc != null) {
                            content.setDescription(desc.getBytes());
                        }
                    }
                }
            } catch (JikanQueryException | RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("description updated");
        contentService.saveAllContent(contents);
        System.out.println("Done");
    }
}
