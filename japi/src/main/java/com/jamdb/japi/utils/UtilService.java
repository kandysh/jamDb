package com.jamdb.japi.utils;

import com.jamdb.japi.entities.content.Content;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilService {
    public Content addDescriptionAndScore(Content content) {
        Jikan jikan = new Jikan();
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
        return content;
    }

}
