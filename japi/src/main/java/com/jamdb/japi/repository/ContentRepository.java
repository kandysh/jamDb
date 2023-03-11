package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
