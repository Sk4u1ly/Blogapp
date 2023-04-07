package com.example.project.service;

import com.example.project.payloads.CommentDto;

import java.util.List;

public interface CommentServiceImpl {


    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto>getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
