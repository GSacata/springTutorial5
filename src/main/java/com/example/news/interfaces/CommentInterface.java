package com.example.news.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.domain.Comment;
import com.example.news.domain.News;

public interface CommentInterface extends JpaRepository<Comment, UUID> {
    List<Comment> findByOwnedByNewID(News news);
    List<Comment> findByAuthorContaining(String author);

}
