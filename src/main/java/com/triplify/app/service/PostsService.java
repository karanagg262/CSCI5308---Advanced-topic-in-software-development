package com.triplify.app.service;

import com.triplify.app.model.Post;

import java.util.List;

public interface PostsService {
    Post createPost(Post employee);

    List<Post> getAllPosts();

    boolean deletePost(Long id);

    Post getPostById(Long id);

    Post updatePost(Long id, Post employee);
}
