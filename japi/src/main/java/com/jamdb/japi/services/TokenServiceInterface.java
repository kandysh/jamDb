package com.jamdb.japi.services;

import com.jamdb.japi.entities.Token;
import com.jamdb.japi.entities.User;

public interface TokenServiceInterface {
    Token saveUserToken(User user, String token);

    void revokeAllUserTokens(User user);
    Token findBytoken(String jwt);
    Token saveToken(Token token);
}
