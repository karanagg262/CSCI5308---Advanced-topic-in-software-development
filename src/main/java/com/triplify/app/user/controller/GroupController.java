package com.triplify.app.user.controller;

import com.triplify.app.user.model.Group;
import com.triplify.app.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/groups") //TODO: change url to a specific group based on user selection
public class GroupController {
    @GetMapping
    public List<Group> getGroups() {
        return List.of(new Group());
    }

}
