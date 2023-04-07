package com.example.project.service.impl;

import com.example.project.entites.Comment;
import com.example.project.entites.Post;
import com.example.project.exception.BlogApiException;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.payloads.CommentDto;
import com.example.project.repositries.CommentRepository;
import com.example.project.repositries.PostRepository;
import com.example.project.service.CommentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements CommentServiceImpl {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newcomment = commentRepository.save(comment);

        CommentDto dto = mapToDto(newcomment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return  comments.stream().map(comment ->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
            // retrieve post entity by id
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new ResourceNotFoundException("Post", "id", postId));
            // retrieve comment by id
            Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                    new ResourceNotFoundException("Comment", "id", commentId));


            if(comment.getPost().getId()!=post.getId()){
                throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong post");
            }
           
            return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));


        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

//    update post by post id  comment id
        if (comment.getPost().getId()!=post.getId()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Post not belong with this comment");
        }
         comment.setId(comment.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(comment.getBody());

        Comment newcomment = commentRepository.save(comment);

        return mapToDto(newcomment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId()!=post.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");

        }
        commentRepository.delete(comment);
    }


    private CommentDto mapToDto(Comment newcomment) {
        CommentDto commentDto = modelMapper.map(newcomment, CommentDto.class);

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);

        return comment;
    }
}
