package com.triplify.app.controller;

import com.triplify.app.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/allposts")
public class PostsController {

    @GetMapping("/posts")
    public List<Post> getPosts(){
        List<Post> posts= new ArrayList<>();
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(){
        return new Post();
    }

   @PostMapping()
    public void savePost(){

   }
}
