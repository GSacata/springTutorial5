package com.example.news.services;

import org.springframework.stereotype.Service;

import com.example.news.controllers.CommentController;
import com.example.news.controllers.NewsController;

@Service
public class MainService {

    public CommentController commentController;
    public NewsController newsController;

    
}
