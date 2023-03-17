package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.content.Season;
import com.jamdb.japi.entities.content.Status;
import com.jamdb.japi.entities.content.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    @Query(value = "select * from anime  where to_tsvector(array_to_string(synonyms,' ') || ' ' || title ) @@ plainto_tsquery(?1)", nativeQuery = true)
    List<Content> findContentBySynonymsAndTitle(String query);

    @Query(nativeQuery = true, value = "select * from anime  where to_tsvector(array_to_string(sources,' ')) @@ plainto_tsquery(?1)")
    Optional<Content> findContentBySource(String source);

    @Query(value = "select * from anime  order by random() limit 25", nativeQuery = true)
    List<Content> findContentRandomly();

    List<Content> findContentByAnimeSeasonAndStatus(Season season, Status status);

    List<Content> findContentByAnimeSeasonAndStatusAndType(Season season, Status status, Type type);

    List<Content> findContentByAnimeSeasonAndType(Season season, Type type);

    List<Content> findContentByAnimeSeasonOrderByScoreDesc(Season season);
    @Query("select a from anime a order by a.score desc limit 100")
    List<Content> findTopContent();

    List<Content> findContentByTypeOrderByScoreDesc(Type type);

    List<Content> findContentByAnimeSeasonAndLikesNotNullOrderByLikesDesc(Season season);

    List<Content> findContentByAnimeSeasonAndLikesNullOrderByScoreDesc(Season season);
    List<Content> findContentByLikesNotNullOrderByLikesDesc();
}
