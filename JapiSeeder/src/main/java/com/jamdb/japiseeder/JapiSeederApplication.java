package com.jamdb.japiseeder;

import com.jamdb.japiseeder.entities.ContentRepository;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class JapiSeederApplication {
    private final ContentRepository contentRepository;
    public static void main(String[] args) {
        SpringApplication.run(JapiSeederApplication.class, args);
    }
    @Bean
    public void addDescriptionAndScore() {
        var contents = contentRepository.findAll();
        Jikan jikan = new Jikan();
        contents.forEach(content -> {
            try {
                var temp = jikan.query().anime().search().query(content.getTitle()).limit(1).execute().collectList().block();
                if (temp != null) {
                    var anime = temp.get(0);
                    content.setDescription(anime.getSynopsis());
                    content.setScore(anime.getScore());
                }
            } catch (JikanQueryException | RuntimeException e) {
                System.out.println(e.getMessage());
            }
            contentRepository.save(content);
        });
    }

}
