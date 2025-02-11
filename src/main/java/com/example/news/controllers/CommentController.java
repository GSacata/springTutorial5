package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.CommentDAO;
import com.example.news.domain.Comment;
import com.example.news.interfaces.NewsInterface;

@RestController
@RequestMapping("comments")
public class CommentController {

    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired
    private CommentDAO commentDao;

    @Autowired
    private NewsInterface newsInterface;

    // @GetMapping("")
    // public List<Comment> GetComments() {
    //     return this.commentDao.getAllComments();
    // }

    // @GetMapping("/{id}")
    // public Optional<Comment> getOneComment(@PathVariable UUID id) {
    //     return this.commentDao.getOneComment(id);
    // }

    // @PostMapping("/post-comment")
    // public Comment commentPost(@RequestBody Comment body) {
    //     return this.commentDao.saveNewComment(body);
    // }
}
