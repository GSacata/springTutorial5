package com.example.news.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.Comment;
import com.example.news.utils.Sample;
import com.example.news.utils.Utils;

@RestController
@RequestMapping("comments")
public class CommentController {

    List<Comment> tempCommentList = new ArrayList<Comment>();
    
    @GetMapping("")
    public List<Comment> GetComments() {
        if (!tempCommentList.isEmpty()) {
            return tempCommentList;
        }
        return Sample.sampleComments();
    }

    @PostMapping("/post-comment")
    public Comment commentPost(@RequestBody Comment body) {
        String author = body.getAuthor();
        String content = body.getContent();
        Instant publicationMoment = Utils.returnFormattedNow();

        Comment comment = new Comment(author, content, publicationMoment);
        this.tempCommentList.add(comment);
        return comment;
    }
}
