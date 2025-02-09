package com.example.news.controllers;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.CommentDAO;
import com.example.news.dao.NewsDAO;
import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.interfaces.CommentInterface;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    List<Comment> tempCommentList = new ArrayList<Comment>();
    
    @Autowired
    private NewsInterface newsInterface;
    
    @Autowired
    private CommentInterface commentInterface;

    @Autowired
    private NewsDAO newsDao;

    @Autowired private CommentDAO commentDao;
    
    @GetMapping("")
    public List<News> getAllNews() {
        return this.newsDao.getAllNews();
    }

    @GetMapping("/{id}")
    public Optional<News> GetOneNew(@PathVariable Integer id) {
        return this.newsDao.getOneNew(id);
    }

    @PostMapping("write")
    public News newsPost(@RequestBody News body) {
        return this.newsDao.saveNews(body);
    }

    @PutMapping("/{id}/edit")
    public News EditNews(@PathVariable Integer id, @RequestBody News body) throws Exception {
        return this.newsDao.editNews(id, body);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteNews(Integer id) {
        this.newsDao.deleteNews(id);
    }

    // Postagem de comentários, preciso aprender a estender endpoints em classes diferentes... Se é que tem como.


    @GetMapping("/{id}/comments")
    public List<Comment> GetComments(@PathVariable Integer id) {
        News referredNews = this.newsDao.getOneNew(id).get();
        
        if (Objects.nonNull(referredNews)) {
            return referredNews.getPostedComments();
        } else {
            return new ArrayList<>();
        }
    }
    
        
    // @PostMapping("/{id}/comments/post-comment")
    // public Comment commentPost(@RequestBody Comment body) {
    //     Comment writtenComment = new Comment();
        
    //     writtenComment.setAuthor(body.getAuthor());
    //     writtenComment.setContent(body.getContent());
    //     // writtenComment.setId(UUID.randomUUID());
    //     writtenComment.setPublicationMoment(Utils.returnFormattedNow());

    //     this.commentInterface.save(writtenComment);

    //     return writtenComment;
    // }
}
