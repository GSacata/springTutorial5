package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.CommentDAO;
import com.example.news.dao.NewsDAO;
import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTONoNews;
import com.example.news.dto.NewsDTONoComments;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired
    private NewsDAO newsDao;

    @Autowired private CommentDAO commentDao;
    
    @GetMapping("")
    public List<NewsDTONoComments> getAllNews(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String headline,
        @RequestParam(required = false) String content
    ) {
        List<NewsDTONoComments> filteredNewsList = new ArrayList<NewsDTONoComments>();
        List<NewsDTONoComments> tempNewsList = new ArrayList<NewsDTONoComments>();
        
        if (Objects.nonNull(author)) {
            tempNewsList = this.newsDao.getAllNewsCleanByAuthor(author);
            if (!tempNewsList.isEmpty()) {
                for (NewsDTONoComments news : tempNewsList) {
                    filteredNewsList.add(news);
                }
                tempNewsList.clear();
            }
        }

        if (Objects.nonNull(headline)) {
            tempNewsList = this.newsDao.getAllNewsCleanByHeadline(headline);
            if (!tempNewsList.isEmpty()) {
                for (NewsDTONoComments news : tempNewsList) {
                    filteredNewsList.add(news);
                }
                tempNewsList.clear();
            }
        }

        if (Objects.nonNull(content)) {
            tempNewsList = this.newsDao.getAllNewsCleanByContent(content);
            if (!tempNewsList.isEmpty()) {
                for (NewsDTONoComments news : tempNewsList) {
                    filteredNewsList.add(news);
                }
                tempNewsList.clear();
            }
        }
        
        if (!filteredNewsList.isEmpty()) {
            return filteredNewsList;
        } else {
            return this.newsDao.getAllNewsClean();
        }
    }

    @GetMapping("/{id}")
    public NewsDTONoComments GetOneNew(@PathVariable Integer id) {
        return this.newsDao.getOneNewClean(id);
    }

    @PostMapping("write")
    public NewsDTONoComments newsPost(@RequestBody News body) {
        return this.newsDao.saveNews(body);
    }

    @PatchMapping("/{id}/edit")
    public NewsDTONoComments EditNews(@PathVariable Integer id, @RequestBody News body) throws Exception {
        return this.newsDao.editNews(id, body);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteNews(@PathVariable Integer id) {
        this.newsDao.deleteNews(id);
    }

    // Postagem de comentários

    @GetMapping("/{id}/comments")
    public List<CommentDTONoNews> getAllComments(@PathVariable Integer id) {
        return this.commentDao.getAllCommentsClean(id);
    }

    @GetMapping("/{id}/comments/{commentUUID}")
    public CommentDTONoNews getOneComment(@PathVariable Integer id, @PathVariable UUID commentUUID) {
        return this.commentDao.getOneCommentClean(id, commentUUID);
    }
    
    @PostMapping("/{id}/comments/post-comment")
    public CommentDTONoNews commentPost(@PathVariable Integer id, @RequestBody Comment body) {
        return this.commentDao.saveNewComment(id, body);
    }

    @PatchMapping("/{id}/comments/{commentUUID}/edit")
    public CommentDTONoNews editComment(@PathVariable Integer id, @PathVariable UUID commentUUID, @RequestBody Comment body) {
        return this.commentDao.editComment(id, commentUUID, body);
    }

    @DeleteMapping("/{id}/comments/{uuid}/delete")
    public void deleteComment(@PathVariable Integer id, @PathVariable UUID uuid) {
        this.commentDao.deleteComment(id, uuid);
    }

}
