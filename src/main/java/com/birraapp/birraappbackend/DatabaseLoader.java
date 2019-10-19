package com.birraapp.birraappbackend;

import com.birraapp.birraappbackend.user.UserService;
import com.birraapp.birraappbackend.user.model.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        userService.saveUser(new CreateUserDTO(
                "adminadmin",
                "admin",
                "istrator",
                "admin@mail.com",
                "admin123"
        ));
    }
}
