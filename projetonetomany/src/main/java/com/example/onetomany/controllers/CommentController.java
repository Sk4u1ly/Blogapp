package com.example.onetomany.controllers;

import com.example.onetomany.entites.Comment;
import com.example.onetomany.payload.CommentDto;
import com.example.onetomany.repositories.CommentRepository;
import com.example.onetomany.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.save(postId, commentDto);

        return new ResponseEntity(dto, HttpStatus.OK);
    }
}



