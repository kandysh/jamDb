package com.jamdb.japi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class User extends BaseEntity {

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Email
    @Size(max = 128)
    @Column(nullable = false)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

}

