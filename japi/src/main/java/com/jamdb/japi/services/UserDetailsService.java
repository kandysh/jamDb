package com.jamdb.japi.services;

import com.jamdb.japi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;
    private final static String USER_NOT_FOUND_MESSAGE = "username %s not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,username)));
    }
}
