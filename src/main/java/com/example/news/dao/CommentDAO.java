package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.interfaces.CommentInterface;
import com.example.news.utils.Utils;

public class CommentDAO {

    @Autowired private CommentInterface commentInterface;
    @Autowired private NewsDAO newsDao;

    Comment commentRef = new Comment();
    News newsRef = new News();
    
    public List<Comment> getAllComments(Integer id) {
        News referredNews = this.newsDao.getOneNew(id).get();
        
        if (Objects.nonNull(referredNews)) {
            return referredNews.getPostedComments();
        } else {
            return new ArrayList<>();
        }
    }

    public Comment getOneComment(Integer id, UUID commentUUID) {
        News referredNews = this.newsDao.getOneNew(id).get();
        Comment referredComment = commentRef;
        
        if (Objects.nonNull(referredNews)) {
            return this.commentInterface.findById(commentUUID).get();
        } else {
            return referredComment;
        }
    }

    public Comment saveNewComment(Integer id, Comment body) {
        News referredNews = this.newsDao.getOneNew(id).get();
        Comment writtenComment = commentRef;

        if (Objects.nonNull(referredNews)) {
            
            writtenComment.setAuthor(body.getAuthor());
            writtenComment.setContent(body.getContent());
            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            return writtenComment;
        } else {
            return writtenComment;
        }
    }

    public Comment editComment(Integer id, UUID commentUUID, Comment body) {
        News referredNews = this.newsDao.getOneNew(id).get();
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();

        if (Objects.nonNull(referredNews) && Objects.nonNull(writtenComment)) {
            
            writtenComment.setAuthor(body.getAuthor());
            writtenComment.setContent(body.getContent());
            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            return writtenComment;
        } else {
            return writtenComment;
        }
    }

    public void deleteComment(Integer id, UUID commentUUID) {
        News referredNews = this.newsDao.getOneNew(id).get();
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();

        if (Objects.nonNull(referredNews) && Objects.nonNull(writtenComment)) {
            this.commentInterface.delete(writtenComment);
        } else {
            return;
        }
    }
}
