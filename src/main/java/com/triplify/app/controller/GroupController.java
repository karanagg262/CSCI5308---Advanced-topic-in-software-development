package com.triplify.app.controller;

import com.triplify.app.model.Group;
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
