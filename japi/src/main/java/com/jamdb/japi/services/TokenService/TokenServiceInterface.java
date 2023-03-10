package com.jamdb.japi.services.TokenService;

import com.jamdb.japi.entities.token.Token;
import com.jamdb.japi.entities.user.User;

public interface TokenServiceInterface {
    Token saveUserToken(User user, String token);

    void revokeAllUserTokens(User user);
    Token findBytoken(String jwt);
    Token saveToken(Token token);
}
