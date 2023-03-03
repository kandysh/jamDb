package com.jamdb.japi.services;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.entities.UserRole;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public boolean userExists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    public User registerNewUser(NewUserDto newUserDto) throws UserAuthException {
        if (userExists(newUserDto.getEmail())) {
            throw new UserAuthException("user already exists");
        }
        User user = new User();
        user.setUsername(newUserDto.getUserName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        user.setUserRole(String.valueOf(UserRole.USER));
        return userRepository.save(user);
    }
}
