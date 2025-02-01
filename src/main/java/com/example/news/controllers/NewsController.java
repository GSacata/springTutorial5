package com.example.news.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.domain.News;
import com.example.news.utils.Sample;
import com.example.news.utils.Utils;

@RestController
@RequestMapping("/news")
public class NewsController {

    List<News> tempNewsList = new ArrayList<News>();
    
    @GetMapping("")
    public List<News> GetNews() {
        if (!this.tempNewsList.isEmpty()) {
            return this.tempNewsList;
        }
        return Sample.sampleNews();
    }

    @PostMapping("write")
    public News newsPost(@RequestBody News body) {
        String headline = body.getHeadline();
        String author = body.getAuthor();
        String content = body.getContent();
        Instant publicationMoment = Utils.returnFormattedNow();

        News postedNew = new News(headline, author, content, publicationMoment);
        this.tempNewsList.add(postedNew);
        return postedNew;
    }
}
