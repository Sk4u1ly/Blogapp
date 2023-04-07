package com.example.onetomany.services;

import com.example.onetomany.entites.Comment;
import com.example.onetomany.payload.CommentDto;

public interface CommentService {
    CommentDto save(Long postId, CommentDto commentDto);
}
