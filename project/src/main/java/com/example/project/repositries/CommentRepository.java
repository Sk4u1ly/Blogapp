package com.example.project.repositries;

import com.example.project.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
     List<Comment> findByPostId(long postId);

}
