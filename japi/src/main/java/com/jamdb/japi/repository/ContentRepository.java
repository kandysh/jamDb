package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    @Query(value = "select * from anime  where to_tsvector(array_to_string(synonyms,' ') || ' ' || title) @@ plainto_tsquery(?1)", nativeQuery = true)
    List<Content> findContentBySynonymsAndTitle(String query);

    @Query(value = "select * from anime where to_tsvector(array_to_string(sources,' ')) @@ plainto_tsquery(?1)", nativeQuery = true)
    Content findContentBySourcesMatches(String sources);

}
