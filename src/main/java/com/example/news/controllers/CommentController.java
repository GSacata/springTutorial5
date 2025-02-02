package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.Comment;
import com.example.news.interfaces.CommentInterface;
import com.example.news.utils.Utils;

@RestController
@RequestMapping("comments")
public class CommentController {

    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired
    private CommentInterface commentInterface;

    @GetMapping("")
    public List<Comment> GetComments() {
        List<Comment> foundCommentList = new ArrayList<Comment>();
        foundCommentList = commentInterface.findAll();

        return foundCommentList;
    }

    @PostMapping("/post-comment")
    public Comment commentPost(@RequestBody Comment body) {
        Comment writtenComment = new Comment();
        
        writtenComment.setAuthor(body.getAuthor());
        writtenComment.setContent(body.getContent());
        // writtenComment.setId(UUID.randomUUID());
        writtenComment.setPublicationMoment(Utils.returnFormattedNow());

        this.commentInterface.save(writtenComment);

        return writtenComment;
    }
}
