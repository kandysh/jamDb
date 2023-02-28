package com.jamdb.japi;

import com.jamdb.japi.controllers.UserController;
import com.jamdb.japi.dto.NewUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSmokeTest {

    @Autowired
    private UserController userController;

    @Test
    public void newUserRegistration() throws Exception {
        NewUserDto dto = new NewUserDto("chinfus", "chingieMingie", "Hellothere");

    }
}
