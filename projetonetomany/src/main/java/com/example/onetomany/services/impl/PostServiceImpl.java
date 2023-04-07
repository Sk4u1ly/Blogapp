package com.example.onetomany.services.impl;


import com.example.onetomany.entites.Post;
import com.example.onetomany.payload.PostDto;
import com.example.onetomany.repositories.PostRepository;
import com.example.onetomany.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;


    @Override
    public PostDto save(PostDto postDto) {
        Post post =new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        Post posts = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setPostId(posts.getPostId());
        dto.setContent(posts.getContent());
        dto.setTitle(posts.getTitle());
        dto.setDescription(posts.getDescription());


        return dto;
    }
}
