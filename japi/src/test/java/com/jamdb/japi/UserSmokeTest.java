package com.jamdb.japi;

import com.jamdb.japi.controllers.AuthController;
import com.jamdb.japi.dto.NewUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserSmokeTest {

    @Autowired
    private AuthController userController;

    @Test
    public void newUserRegistration() throws Exception {
        NewUserDto dto = new NewUserDto("chinfus", "chingieMingie", "Hellothere");

    }
}
