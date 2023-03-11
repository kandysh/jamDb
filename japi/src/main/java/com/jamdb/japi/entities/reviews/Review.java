package com.jamdb.japi.entities.reviews;

import com.jamdb.japi.entities.BaseEntity;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {
    @Lob
    private byte[] review;

    @OneToOne
    private Content content;
    @OneToOne
    private User user;

}
