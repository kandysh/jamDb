package com.jamdb.japi.utils;

import com.jamdb.japi.services.ContentService.ContentService;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Optional;

//@Configuration
@RequiredArgsConstructor
public class DescriptionMaker {
    private final ContentService contentService;

//    @Bean
    public void describe() {
        Jikan jikan = new Jikan();
        var contents = contentService.listAllContent();
        contents.forEach(content -> {
            try {
                var temp= jikan.query().anime().search().query(content.getTitle()).limit(1).execute().collectList().block();
            if(temp!=null){
                var anime = temp.get(0);
                if(anime!=null){
                    var desc = anime.getSynopsis();
                    if(desc!=null){
                        content.setDescription(desc.getBytes());
                    }
                }
            }
            } catch (JikanQueryException |RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });
            System.out.println("sysnposis updated");
            contentService.saveAllContent(contents);
            System.out.println("Done");
    }
}
