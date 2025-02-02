package com.example.news.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.domain.News;

public interface NewsInterface extends JpaRepository<News, Integer> {
    // Tem métodos implementados de CrudRepository, como findAll(), dentre outros e outras interfaces.
}
