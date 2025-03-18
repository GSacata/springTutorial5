package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.example.news.dao.NewsDAO;
import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.NewsDTONoComments;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    List<Comment> tempCommentList = new ArrayList<Comment>();

    @Autowired
    private NewsDAO newsDao;
    
    @GetMapping("")
    public List<NewsDTONoComments> getAllNews(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String headline,
        @RequestParam(required = false) String content,
        @RequestParam(required = false) String start,
        @RequestParam(required = false) String end
    ) {
        return this.newsDao.getAllNewsClean(author, headline, content, start, end);
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
}
