package com.example.onetomany.payload;

import com.example.onetomany.entites.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CommentDto {
    private Long CommentId;
    private String body;
    private String Email;
    private String name;

    private Post post;
}
