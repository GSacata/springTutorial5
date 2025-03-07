package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.CommentDAO;
import com.example.news.domain.Comment;
import com.example.news.dto.CommentDTONewsId;
import com.example.news.dto.CommentDTONoNews;
// import com.example.news.interfaces.NewsInterface;

@RestController
@RequestMapping("comments")
public class CommentController {

    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired private CommentDAO commentDao;
    // @Autowired private NewsInterface newsInterface;

    @GetMapping("")
    public List<CommentDTONewsId> getAllCommentsClean() {
        return this.commentDao.getAllCommentsClean();
    }

    @GetMapping("/single-comment/{commentUUID}")
    public CommentDTONewsId getOneCommentClean(@PathVariable UUID commentUUID) {
        return this.commentDao.getOneCommentClean(commentUUID);
    }

    @GetMapping("/{id}")
    public List<CommentDTONewsId> getAllCommentsByNews(@PathVariable Integer id) {
        return this.commentDao.getAllCommentsByNewsClean(id);
    }

    @GetMapping("/{id}/single-comment/{commentUUID}")
    public CommentDTONewsId getOneCommentByNews(@PathVariable Integer id, @PathVariable UUID commentUUID) {
        return this.commentDao.getOneCommentByNewsClean(id, commentUUID);
    }

    @PostMapping("/post-comment") // TODO fix, old version
    public CommentDTONoNews commentPost(@PathVariable Integer id, @RequestBody Comment body) {
        return this.commentDao.saveNewComment(id, body);
    }

    @PatchMapping("/{id}/comments/{commentUUID}/edit") // TODO fix, old version
    public CommentDTONoNews editComment(@PathVariable Integer id, @PathVariable UUID commentUUID, @RequestBody Comment body) {
        return this.commentDao.editComment(id, commentUUID, body);
    }

    @DeleteMapping("/{id}/comments/{uuid}/delete") // TODO fix, old version
    public void deleteComment(@PathVariable Integer id, @PathVariable UUID uuid) {
        this.commentDao.deleteComment(id, uuid);
    }
}
