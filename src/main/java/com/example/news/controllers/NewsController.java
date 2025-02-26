package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.CommentDAO;
import com.example.news.dao.NewsDAO;
import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTOMissingNewId;
import com.example.news.dto.NewsDTORelyOnCommentDTOMissingNewId;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired
    private NewsDAO newsDao;

    @Autowired private CommentDAO commentDao;
    
    @GetMapping("")
    public List<NewsDTORelyOnCommentDTOMissingNewId> getAllNews() {
        return this.newsDao.getAllNewsClean();
    }

    @GetMapping("/{id}")
    public NewsDTORelyOnCommentDTOMissingNewId GetOneNew(@PathVariable Integer id) {
        return this.newsDao.getOneNewClean(id);
    }

    @PostMapping("write")
    public NewsDTORelyOnCommentDTOMissingNewId newsPost(@RequestBody News body) {
        return this.newsDao.saveNews(body);
    }

    @PatchMapping("/{id}/edit")
    public NewsDTORelyOnCommentDTOMissingNewId EditNews(@PathVariable Integer id, @RequestBody News body) throws Exception {
        return this.newsDao.editNews(id, body);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteNews(@PathVariable Integer id) {
        this.newsDao.deleteNews(id);
    }

    // Postagem de comentários

    @GetMapping("/{id}/comments")
    public List<CommentDTOMissingNewId> getAllComments(@PathVariable Integer id) {
        return this.commentDao.getAllCommentsRefined(id);
    }

    @GetMapping("/{id}/comments/{commentUUID}")
    public CommentDTOMissingNewId getOneComment(@PathVariable Integer id, @PathVariable UUID commentUUID) {
        return this.commentDao.getOneCommentRefined(id, commentUUID);
    }
    
    @PostMapping("/{id}/comments/post-comment")
    public CommentDTOMissingNewId commentPost(@PathVariable Integer id, @RequestBody Comment body) {
        return this.commentDao.saveNewComment(id, body);
    }

    @PatchMapping("/{id}/comments/{commentUUID}/edit")
    public CommentDTOMissingNewId editComment(@PathVariable Integer id, @PathVariable UUID commentUUID, @RequestBody Comment body) {
        return this.commentDao.editComment(id, commentUUID, body);
    }

    @DeleteMapping("/{id}/comments/{uuid}/delete")
    public void deleteComment(@PathVariable Integer id, @PathVariable UUID uuid) {
        this.commentDao.deleteComment(id, uuid);
    }

}
