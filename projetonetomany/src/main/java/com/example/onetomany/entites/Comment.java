package com.example.onetomany.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long commentId;
    private String body;
    private String email;
    private String name;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
