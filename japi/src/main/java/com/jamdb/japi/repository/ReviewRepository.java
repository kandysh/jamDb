package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.reviews.Review;
import com.jamdb.japi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("select r from Review r where r.user=?1 and r.content=?2")
    Review getReviewByUserAndContent(User user, Content content);
}
