package com.jamdb.japi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.services.ContentService.ContentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//@Configuration
public class JsonSeeder {
//    @Bean
    CommandLineRunner runner(ContentService contentService){
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Content>> typeReference = new TypeReference<List<Content>>() {};
            try(InputStream inputStream= TypeReference.class.getResourceAsStream("/json/anime-offline-database.json")){
                List<Content> contents =mapper.readValue(inputStream,typeReference);
                contentService.saveAllContent(contents);
                System.out.println("Contents Saved!");
            }catch (IOException e){
                System.out.println("Unable to save content" + e.getMessage());
            }
        };
    }
}
