package com.example.news.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.Comment;
import com.example.news.utils.Sample;

@RestController
@RequestMapping("comments")
public class CommentController {
    
    @GetMapping("")
    public List<Comment> GetComments() {
        return Sample.sampleComments();
    }
}
