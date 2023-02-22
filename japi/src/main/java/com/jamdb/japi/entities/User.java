package com.jamdb.japi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class User extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @NotNull
    @Email
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @PrePersist
    @PreUpdate
    private void prepareEmail(){
        this.email= Objects.isNull(email)?null:email.toLowerCase();
    }


}
