package com.jamdb.japi.services;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean emailExists(String email) {
        return Objects.nonNull(userRepository.findByEmail(email));
    }

    @Override
    public User registerNewUser(NewUserDto newUserDto) throws UserAuthException {
        if (emailExists(newUserDto.getEmail())) {
            throw new UserAuthException("user already exists");
        }
        User user = new User();
        user.setUserName(newUserDto.getUserName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        return userRepository.save(user);
    }
}
