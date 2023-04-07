package com.example.project.controllers;

import com.example.project.payloads.CommentDto;
import com.example.project.service.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentControllers {

   private final CommentServiceImpl commentServiceImpl;

    public CommentControllers(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment(@Valid @PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto, BindingResult result){
        if (result.hasErrors()){
            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto dto = commentServiceImpl.createComment(postId,commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

     //http://localhost:8080/api/posts/1/comments
  @GetMapping("/posts/{postId}/comments")
    public List<CommentDto>getCommentByPostId(@PathVariable("postId")long postId){

      return commentServiceImpl.getCommentsByPostId(postId);
  }

  // http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId){
        CommentDto Dto = commentServiceImpl.getCommentById(postId, commentId);
        return new ResponseEntity<>(Dto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentServiceImpl.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentServiceImpl.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }


}



