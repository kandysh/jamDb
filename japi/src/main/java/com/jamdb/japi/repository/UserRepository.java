package com.jamdb.japi.repository;

import com.jamdb.japi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "Select u from users u where u.username=?1")
    public Optional<User> findByUserName(String userName);
}
