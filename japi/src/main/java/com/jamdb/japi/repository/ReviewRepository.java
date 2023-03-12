package com.jamdb.japi.repository;

import com.jamdb.japi.entities.reviews.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
