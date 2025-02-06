package com.example.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.news.dao.CommentDAO;
import com.example.news.dao.NewsDAO;

@Configuration
public class NewsAppConfig {
    
    @Bean
    public CommentDAO commentDaoInstance() {
        return new CommentDAO();
    }

    @Bean
    public NewsDAO newsDaoInstance() {
        return new NewsDAO();
    }
}
