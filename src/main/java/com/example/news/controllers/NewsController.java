package com.example.news.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.News;
import com.example.news.utils.Sample;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    @GetMapping("")
    public List<News> GetNews() {
        return Sample.sampleNews();
    }
}
