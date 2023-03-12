package com.jamdb.japi.entities.reviews;

import com.jamdb.japi.entities.BaseEntity;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String review;

    @OneToOne
    private Content content;
    @OneToOne
    private User user;

}
