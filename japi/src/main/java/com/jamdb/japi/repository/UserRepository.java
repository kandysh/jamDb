package com.jamdb.japi.repository;

import com.jamdb.japi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "Select u from users u where u.email=?1")
    public User findByEmail(String email);

}
