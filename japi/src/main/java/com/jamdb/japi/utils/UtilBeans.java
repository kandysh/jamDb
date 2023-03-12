package com.jamdb.japi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.services.ContentService.ContentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class UtilBeans {

    private final ContentServiceInterface contentService;

    //    @Bean
    CommandLineRunner runner() {
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

}
