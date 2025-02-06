package com.example.news.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.News;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

public class NewsDAO {
    
    @Autowired
    public NewsInterface newsInterface;

    News newsRef = new News();

    public List<News> getAllNews() {
        return this.newsInterface.findAll();
    }

    public Optional<News> getOneNew(Integer id) {
        return this.newsInterface.findById(id);    
    }

    public News saveNews(News body) {
        News writtenNews = newsRef;
        writtenNews.setAuthor(body.getAuthor());
        writtenNews.setContent(body.getContent());
        writtenNews.setHeadline(body.getHeadline());
        writtenNews.setPublicationMoment(Utils.returnFormattedNow());

        this.newsInterface.save(writtenNews);
        return writtenNews;
    }
}
