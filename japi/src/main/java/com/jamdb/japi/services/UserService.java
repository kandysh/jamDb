package com.jamdb.japi.services;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.entities.UserRole;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.repository.UserRepository;
import com.jamdb.japi.security.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public boolean userExists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    public UserResponse registerNewUser(NewUserDto newUserDto) throws UserAuthException {
        var user = User.builder()
                .username(newUserDto.getUserName())
                .email(newUserDto.getEmail())
                .password(encoder.encode(newUserDto.getPassword()))
                .userRole(UserRole.USER)
                .build();
        var savedUser =  userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        tokenService.saveUserToken(savedUser,jwtToken);
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token(jwtToken).build();
    }

    @Override
    public UserResponse getUser(String userName) throws UserAuthException {
        var user = userRepository.findByUserName(userName).orElseThrow(() -> new UserAuthException(String.format("username %s not Found", userName)));
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token("").build();
    }

    @Override
    public UserResponse authenticateUser(UserAuthDto userAuthDto) throws UserAuthException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthDto.getUserName(), userAuthDto.getPassword()));
        } catch (RuntimeException e) {
            throw new UserAuthException(e.getMessage());
        }
        var user = userRepository.findByUserName(userAuthDto.getUserName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, jwtToken);
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token(jwtToken).build();
    }



}
