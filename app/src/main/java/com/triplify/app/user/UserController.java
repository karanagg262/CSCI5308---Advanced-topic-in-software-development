package com.triplify.app.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @GetMapping
    public List<User> getUsers(){

        return List.of(
                new User(
                        1,
                        "testuser",
                        "John",
                        "Doe",
                        LocalDate.of(1998,01,01),
                        "M",
                        "Halifax,NS"
                )
        );
    }
}
