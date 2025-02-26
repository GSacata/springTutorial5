package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTONoNews;
import com.example.news.dto.NewsDTONoComments;
import com.example.news.interfaces.NewsInterface;
import com.example.news.utils.Utils;

public class NewsDAO {
    
    @Autowired public NewsInterface newsInterface;
    @Autowired public CommentDAO commentDAO;

    public List<News> getAllNews() {
        return this.newsInterface.findAll();
    }

    public List<NewsDTONoComments> getAllNewsClean() {
        List<News> newsListCircularReference = this.newsInterface.findAll();
        NewsDTONoComments newsRefined = new NewsDTONoComments();
        List<NewsDTONoComments> newsListRefined = new ArrayList<NewsDTONoComments>();

        for (News news : newsListCircularReference) {
            newsRefined = new NewsDTONoComments();
            List<CommentDTONoNews> commentList = commentDAO.getAllCommentsRefined(newsRefined.getId());

            newsRefined.setAuthor(news.getAuthor());
            newsRefined.setId(news.getId());
            newsRefined.setContent(news.getContent());
            newsRefined.setHeadline(news.getHeadline());
            // newsRefined.setPostedComments(commentDAO.getAllCommentsRefined(newsRefined.getId())); // Cria referência circular
            newsRefined.setPostedComments(commentList);
            newsRefined.setPublicationMoment(news.getPublicationMoment());

            newsListRefined.add(newsRefined);
        }

        return newsListRefined;
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

        NewsDTONoComments writtenNewsDTO = new NewsDTONoComments(writtenNews.getId(), writtenNews.getHeadline(), writtenNews.getAuthor(), writtenNews.getContent(), null, writtenNews.getPublicationMoment());

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

        NewsDTONoComments writtenNewsDTO = new NewsDTONoComments(writtenNews.getId(), writtenNews.getHeadline(), writtenNews.getAuthor(), writtenNews.getContent(), null, writtenNews.getPublicationMoment());

        return writtenNewsDTO;
    }

    public void deleteNews(Integer id) {
        News newsRef = this.newsInterface.findById(id).get();
        if (Objects.nonNull(newsRef)) {
            this.newsInterface.delete(newsRef);
        }
    }
}
