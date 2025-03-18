package com.example.news.interfaces;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.domain.News;

public interface NewsInterface extends JpaRepository<News, Integer> {
    // Tem métodos implementados de CrudRepository, como findAll(), dentre outros e outras interfaces.

    List<News> findByAuthorContaining(String author);
    List<News> findByHeadlineContaining(String headline);
    List<News> findByContentContaining(String content);
    List<News> findByPublicationMomentBetween(Instant start, Instant end);
}
