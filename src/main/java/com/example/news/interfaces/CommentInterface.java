package com.example.news.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.domain.Comment;

public interface CommentInterface extends JpaRepository<Comment, UUID> {
    
}
