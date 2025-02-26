package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTOMissingNewId;
import com.example.news.interfaces.CommentInterface;
import com.example.news.utils.Utils;

public class CommentDAO {

    @Autowired private CommentInterface commentInterface;
    @Autowired private NewsDAO newsDao;
    
    public List<Comment> getAllComments(Integer id) {
        News referredNews = this.newsDao.getOneNew(id);
        
        if (Objects.nonNull(referredNews)) {
            return referredNews.getPostedComments();
        } else {
            return new ArrayList<>();
        }
    }

    public List<CommentDTOMissingNewId> getAllCommentsRefined(Integer id) {
        News referredNews = this.newsDao.getOneNew(id);
        CommentDTOMissingNewId DTOComment = new CommentDTOMissingNewId();
        List<CommentDTOMissingNewId> DTOCommentList = new ArrayList<CommentDTOMissingNewId>();
        
        if (Objects.nonNull(referredNews)) {
            List<Comment> commentList = new ArrayList<Comment>();
            commentList = referredNews.getPostedComments();

            for (Comment comment : commentList) {
                DTOComment.setAuthor(comment.getAuthor());
                DTOComment.setContent(comment.getContent());
                DTOComment.setId(comment.getId());
                DTOComment.setPublicationMoment(comment.getPublicationMoment());
                DTOCommentList.add(DTOComment);

                DTOComment = new CommentDTOMissingNewId();
            }

            return DTOCommentList;
        } else {
            return DTOCommentList;
        }
    }

    public Comment getOneComment(Integer id, UUID commentUUID) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment referredComment = new Comment();
        
        if (Objects.nonNull(referredNews)) {
            return this.commentInterface.findById(commentUUID).get();
        } else {
            return referredComment;
        }
    }

    public CommentDTOMissingNewId getOneCommentRefined(Integer id, UUID commentUUID) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment referredComment = new Comment();
        CommentDTOMissingNewId commentDTOOnlyNewId = new CommentDTOMissingNewId();
        
        if (Objects.nonNull(referredNews)) {
            referredComment = this.commentInterface.findById(commentUUID).get();
            commentDTOOnlyNewId.setAuthor(referredComment.getAuthor());
            commentDTOOnlyNewId.setContent(referredComment.getContent());
            commentDTOOnlyNewId.setId(referredComment.getId());
            commentDTOOnlyNewId.setPublicationMoment(referredComment.getPublicationMoment());

            return commentDTOOnlyNewId;
        } else {
            return commentDTOOnlyNewId;
        }
    }

    public CommentDTOMissingNewId saveNewComment(Integer id, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = new Comment();
        CommentDTOMissingNewId writtenCommentDTO = new CommentDTOMissingNewId();

        if (Objects.nonNull(referredNews)) {
            
            writtenComment.setAuthor(body.getAuthor());
            writtenComment.setContent(body.getContent());
            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            writtenCommentDTO = new CommentDTOMissingNewId(writtenComment.getId(), writtenComment.getAuthor(), writtenComment.getContent(), writtenComment.getPublicationMoment());
            return writtenCommentDTO;
        } else {
            return writtenCommentDTO;
        }
    }

    public CommentDTOMissingNewId editComment(Integer id, UUID commentUUID, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();
        CommentDTOMissingNewId writtenCommentDTO = new CommentDTOMissingNewId();

        if (Objects.nonNull(referredNews) && Objects.nonNull(writtenComment)) {
            
            if (Objects.nonNull(body.getAuthor())) {
                writtenComment.setAuthor(body.getAuthor());
            }

            if (Objects.nonNull(body.getContent())) {
                writtenComment.setContent(body.getContent());
            }

            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            writtenCommentDTO = new CommentDTOMissingNewId(writtenComment.getId(), writtenComment.getAuthor(), writtenComment.getContent(), writtenComment.getPublicationMoment());
            return writtenCommentDTO;
        } else {
            return writtenCommentDTO;
        }
    }

    public void deleteComment(Integer id, UUID commentUUID) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();

        if (Objects.nonNull(referredNews) && Objects.nonNull(writtenComment)) {
            this.commentInterface.delete(writtenComment);
        } else {
            return;
        }
    }
}
