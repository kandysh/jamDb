package com.jamdb.japiseeder.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    Optional<List<Content>> findContentByTitle(String title);

    Optional<Content> findContentBySourceId(String soruceId);
}
