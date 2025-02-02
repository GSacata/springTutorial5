package com.example.news.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.News;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsInterface newsInterface;

    @GetMapping("")
    public List<News> GetNews() {
        List<News> foundNewsList = new ArrayList<News>();
        foundNewsList = this.newsInterface.findAll();

        return foundNewsList;
    }

    @PostMapping("write")
    public News newsPost(@RequestBody News body) {
        News writtenNew = new News();
        
        writtenNew.setAuthor(body.getAuthor());
        writtenNew.setContent(body.getContent());
        writtenNew.setHeadline(body.getHeadline());
        writtenNew.setPublicationMoment(Utils.returnFormattedNow());

        this.newsInterface.save(writtenNew);

        return writtenNew;
    }
}
