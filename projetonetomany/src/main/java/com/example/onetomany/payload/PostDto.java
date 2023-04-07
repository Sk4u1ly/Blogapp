package com.example.onetomany.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private Long postId;
    private String content;
    private String description;
    private String title;
}
