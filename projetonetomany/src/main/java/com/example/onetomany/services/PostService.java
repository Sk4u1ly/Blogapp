package com.example.onetomany.services;

import com.example.onetomany.payload.PostDto;

public interface PostService {
    PostDto save(PostDto postDto);
}
