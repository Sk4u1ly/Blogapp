package com.example.onetomany.services.impl;

import com.example.onetomany.entites.Comment;
import com.example.onetomany.entites.Post;
import com.example.onetomany.payload.CommentDto;
import com.example.onetomany.repositories.CommentRepository;
import com.example.onetomany.repositories.PostRepository;
import com.example.onetomany.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
   private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto save(Long postId, CommentDto commentDto) {
  //      Post post =postRepository.findById(postId).orElseThrow(() ->new EntityNotFoundException());
        Post post =postRepository.findById(postId).get();
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setPost(post);
        Comment comments = commentRepository.save(comment);
        CommentDto dto =new CommentDto();
        dto.setName(comments.getName());
        dto.setEmail(comments.getEmail());
        dto.setBody(comments.getBody());
        dto.setCommentId(comments.getCommentId());

        return dto;
    }
}
