package com.jamdb.japi.repository;

import com.jamdb.japi.entities.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    @Query("select t from Token t inner join users u on t.user.id=u.id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(UUID id);

    @Query("select t from Token t where t=?1")
    Optional<Token> findByToken(String token);
}
