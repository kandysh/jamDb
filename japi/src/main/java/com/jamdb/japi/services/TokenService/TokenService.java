package com.jamdb.japi.services.TokenService;

import com.jamdb.japi.entities.token.Token;
import com.jamdb.japi.entities.token.TokenType;
import com.jamdb.japi.entities.user.User;
import com.jamdb.japi.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService implements TokenServiceInterface {
    private final TokenRepository tokenRepository;

    @Override
    public Token saveUserToken(User user, String token) {
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        return tokenRepository.save(jwtToken);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        List<Token> allValidTokenByUser = tokenRepository.findAllValidTokenByUser(user.getId());
        if (allValidTokenByUser.isEmpty())
            return;
        allValidTokenByUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(allValidTokenByUser);
    }

    @Override
    public Token findBytoken(String jwt) {
        return tokenRepository.findByToken(jwt).orElse(null);
    }

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
