package com.jamdb.japi.repository;

import com.jamdb.japi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("Select u from User u where email=?1")
    public boolean emailExists(String email);
}
