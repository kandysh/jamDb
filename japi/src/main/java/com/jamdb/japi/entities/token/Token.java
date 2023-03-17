package com.jamdb.japi.entities.token;

import com.jamdb.japi.entities.BaseEntity;
import com.jamdb.japi.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity {
    @Column(unique = true)
    public String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    public Boolean revoked;
    public Boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
