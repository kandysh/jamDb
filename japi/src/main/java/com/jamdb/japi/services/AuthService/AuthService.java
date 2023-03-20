package com.jamdb.japi.services.AuthService;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.user.User;
import com.jamdb.japi.entities.user.UserRole;
import com.jamdb.japi.repository.UserRepository;
import com.jamdb.japi.security.config.JwtService;
import com.jamdb.japi.services.TokenService.TokenServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenServiceInterface tokenService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    @Override
    public boolean userExists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    public UserResponse registerNewUser(NewUserDto newUserDto) {
        var user = User.builder()
                .username(newUserDto.getUserName())
                .email(newUserDto.getEmail())
                .password(encoder.encode(newUserDto.getPassword()))
                .userRole(UserRole.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        tokenService.saveUserToken(savedUser, jwtToken);
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token(jwtToken).build();
    }

    @Override
    public UserResponse authenticateUser(UserAuthDto userAuthDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDto.getUserName(), userAuthDto.getPassword()));
        var user = userRepository.findByUserName(userAuthDto.getUserName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, jwtToken);
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token(jwtToken).build();
    }
}
