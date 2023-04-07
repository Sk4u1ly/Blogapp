package com.example.project.service;

import com.example.project.entites.Post;
import com.example.project.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);


    Post getPostById(long id);
}
