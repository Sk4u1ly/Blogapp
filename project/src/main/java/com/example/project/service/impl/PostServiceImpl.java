package com.example.project.service.impl;

import com.example.project.entites.Post;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.payloads.PostDto;
import com.example.project.repositries.PostRepository;
import com.example.project.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final
    PostRepository postRepository;

    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }

    /**
     * @Getmapping
     */
    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

    }


    @Override
    public PostDto updatePost(PostDto postDto, long id) {
            // get post by id from the database
            Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setContent(postDto.getContent());

            Post updatedPost = postRepository.save(post);
            return mapToDTO(updatedPost);

        }


    @Override
    public void deletePostById(long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


    @Override
    public Post getPostById(long id) {
        Post dto = postRepository.getById(id);


        return dto;
    }


    // convert DTO to entity
    private PostDto mapToDTO(Post newPost) {

        PostDto postDto = modelMapper.map(newPost, PostDto.class);

        // PostDto postDto = new PostDto();
     //   postDto.setId(newPost.getId());
      //  postDto.setTitle(newPost.getTitle());
     //   postDto.setDescription(newPost.getDescription());
    //    postDto.setContent(newPost.getContent());
        return postDto;

    }
    // convert entity to DTO
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        //  Post post = new Post();
      //  post.setTitle(postDto.getTitle());
       // post.setDescription(postDto.getDescription());
      //  post.setContent(postDto.getContent());
        return post;

    }
}
