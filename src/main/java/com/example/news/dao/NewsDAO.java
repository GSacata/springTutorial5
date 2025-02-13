package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.News;
import com.example.news.dto.NewsDTORelyOnCommentDTOMissingNewId;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

public class NewsDAO {
    
    @Autowired public NewsInterface newsInterface;

    News newsRef = new News();

    public List<News> getAllNews() {
        return this.newsInterface.findAll();
    }

    public List<NewsDTORelyOnCommentDTOMissingNewId> getAllNewsClean() {
        List<News> newsListCircularReference = this.newsInterface.findAll();
        NewsDTORelyOnCommentDTOMissingNewId newsRefined = new NewsDTORelyOnCommentDTOMissingNewId();
        List<NewsDTORelyOnCommentDTOMissingNewId> newsListRefined = new ArrayList<NewsDTORelyOnCommentDTOMissingNewId>();

        for (News news : newsListCircularReference) {
            newsRefined = new NewsDTORelyOnCommentDTOMissingNewId();

            newsRefined.setAuthor(news.getAuthor());
            newsRefined.setId(news.getId());
            newsRefined.setContent(news.getContent());
            newsRefined.setHeadline(news.getHeadline());
            newsRefined.setPublicationMoment(news.getPublicationMoment());

            newsListRefined.add(newsRefined);
        }

        return newsListRefined;
    }

    public News getOneNew(Integer id) {
        return this.newsInterface.findById(id).get();    
    }

    public NewsDTORelyOnCommentDTOMissingNewId getOneNewClean(Integer id) {
        News newsCircularReference = this.newsInterface.findById(id).get();
        NewsDTORelyOnCommentDTOMissingNewId newsRefined = new NewsDTORelyOnCommentDTOMissingNewId();
        
        newsRefined.setAuthor(newsCircularReference.getAuthor());
        newsRefined.setId(newsCircularReference.getId());
        newsRefined.setContent(newsCircularReference.getContent());
        newsRefined.setHeadline(newsCircularReference.getHeadline());
        newsRefined.setPublicationMoment(newsCircularReference.getPublicationMoment());

        return newsRefined;
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

    public News editNews(Integer id, News body) throws Exception {
        News writtenNews = this.getOneNew(id);
        writtenNews.setAuthor(body.getAuthor());
        writtenNews.setContent(body.getContent());
        writtenNews.setHeadline(body.getHeadline());
        writtenNews.setPublicationMoment(Utils.returnFormattedNow());

        this.newsInterface.save(writtenNews);
        return writtenNews;
    }

    public void deleteNews(Integer id) {
        Optional<News> newsRef = this.newsInterface.findById(id);
        if (!newsRef.isEmpty()) {
            this.newsInterface.delete(newsRef.get());
        }
    }
}
