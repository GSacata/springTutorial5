package com.example.news.interfaces;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.domain.Comment;

public interface CommentInterface extends JpaRepository<Comment, UUID> {
    List<Comments> findByAuthorContaining(String author);
}
