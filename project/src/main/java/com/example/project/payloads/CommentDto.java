package com.example.project.payloads;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String body;

}
