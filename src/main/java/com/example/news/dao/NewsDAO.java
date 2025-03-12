package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.News;
import com.example.news.dto.NewsDTONoComments;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

public class NewsDAO {
    
    @Autowired public NewsInterface newsInterface;

    public List<News> getAllNews() {
        return this.newsInterface.findAll();
    }

    public List<NewsDTONoComments> getAllNewsClean(String author, String headline, String content) {
        List<News> newsList = new ArrayList<News>();
        NewsDTONoComments newsClean = new NewsDTONoComments();
        List<NewsDTONoComments> newsListClean = new ArrayList<NewsDTONoComments>();

        if (Objects.nonNull(author)) {
            newsList.addAll(this.newsInterface.findByAuthorContaining(author));
        }

        if (Objects.nonNull(headline)) {
            newsList.addAll(this.newsInterface.findByHeadlineContaining(headline));
        }

        if (Objects.nonNull(content)) {
            newsList.addAll(this.newsInterface.findByContentContaining(content));
        }

        if (newsList.isEmpty()) {
            newsList = this.newsInterface.findAll();
        }

        for (News news : newsList) {
            newsClean = new NewsDTONoComments();

            newsClean.setAuthor(news.getAuthor());
            newsClean.setId(news.getId());
            newsClean.setContent(news.getContent());
            newsClean.setHeadline(news.getHeadline());
            newsClean.setPublicationMoment(news.getPublicationMoment());

            newsListClean.add(newsClean);
        }

        return newsListClean;
    }
    
    public News getOneNew(Integer id) {
        return this.newsInterface.findById(id).get();    
    }

    public NewsDTONoComments getOneNewClean(Integer id) {
        News newsCircularReference = this.newsInterface.findById(id).get();
        NewsDTONoComments newsRefined = new NewsDTONoComments();
        
        newsRefined.setAuthor(newsCircularReference.getAuthor());
        newsRefined.setId(newsCircularReference.getId());
        newsRefined.setContent(newsCircularReference.getContent());
        newsRefined.setHeadline(newsCircularReference.getHeadline());
        newsRefined.setPublicationMoment(newsCircularReference.getPublicationMoment());

        return newsRefined;
    }

    public NewsDTONoComments saveNews(News body) {
        News writtenNews = new News();
        writtenNews.setAuthor(body.getAuthor());
        writtenNews.setContent(body.getContent());
        writtenNews.setHeadline(body.getHeadline());
        writtenNews.setPublicationMoment(Utils.returnFormattedNow());

        this.newsInterface.save(writtenNews);

        NewsDTONoComments writtenNewsDTO = new NewsDTONoComments(writtenNews.getId(), writtenNews.getHeadline(), writtenNews.getAuthor(), writtenNews.getContent(), writtenNews.getPublicationMoment());

        return writtenNewsDTO;
    }

    public NewsDTONoComments editNews(Integer id, News body) {
        News writtenNews = this.getOneNew(id);

        if (Objects.nonNull(body.getAuthor())) {
            writtenNews.setAuthor(body.getAuthor());
        }

        if (Objects.nonNull(body.getContent())) {
            writtenNews.setContent(body.getContent());
        }

        if (Objects.nonNull(body.getHeadline())) {
            writtenNews.setHeadline(body.getHeadline());
        }

        writtenNews.setPublicationMoment(Utils.returnFormattedNow());

        this.newsInterface.save(writtenNews);

        NewsDTONoComments writtenNewsDTO = new NewsDTONoComments(writtenNews.getId(), writtenNews.getHeadline(), writtenNews.getAuthor(), writtenNews.getContent(), writtenNews.getPublicationMoment());

        return writtenNewsDTO;
    }

    public void deleteNews(Integer id) {
        News newsRef = this.newsInterface.findById(id).get();
        if (Objects.nonNull(newsRef)) {
            this.newsInterface.delete(newsRef);
        }
    }
}
