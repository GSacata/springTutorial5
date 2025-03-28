package com.example.news.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTONewsId;
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

    public List<CommentDTONewsId> getAllCommentsClean(String author, String content, String start, String end) {
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        List<CommentDTONewsId> DTOCommentList = new ArrayList<CommentDTONewsId>();
        List<Comment> commentList = new ArrayList<Comment>();

        if (Objects.nonNull(start) && Objects.nonNull(end)) {
            Instant startDate = Instant.parse(start + "T00:00:00.00Z");
            Instant endDate = Instant.parse(end + "T00:00:00.00Z");
            commentList.addAll(this.commentInterface.findByPublicationMomentBetween(startDate, endDate));
        }

        if (Objects.nonNull(author)) {
            commentList.addAll(this.commentInterface.findByAuthorContaining(author));
        }
        
        if (Objects.nonNull(content)) {
            commentList.addAll(this.commentInterface.findByContentContaining(content));
        }

        if (commentList.isEmpty()) {
            commentList = this.commentInterface.findAll();
        }

        for (Comment comment : commentList) {
            DTOComment.setAuthor(comment.getAuthor());
            DTOComment.setContent(comment.getContent());
            DTOComment.setId(comment.getId());
            DTOComment.setNewsID(comment.getOwnedByNewID().getId());
            DTOComment.setPublicationMoment(comment.getPublicationMoment());
            DTOCommentList.add(DTOComment);

            DTOComment = new CommentDTONewsId(); // Trocar para list.clean().
        }

        return DTOCommentList;
    }

    public List<CommentDTONewsId> getAllCommentsByNewsClean(Integer id, String author, String content, String start, String end) {
        News referredNews = this.newsDao.getOneNew(id);
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        List<CommentDTONewsId> DTOCommentList = new ArrayList<CommentDTONewsId>();
        List<Comment> commentList = new ArrayList<Comment>();

        if (Objects.nonNull(start) && Objects.nonNull(end)) {
            Instant startDate = Instant.parse(start + "T00:00:00.00Z");
            Instant endDate = Instant.parse(end + "T00:00:00.00Z");
            commentList.addAll(this.commentInterface.findByPublicationMomentBetween(startDate, endDate));
        }

        if (Objects.nonNull(author)) {
            commentList.addAll(this.commentInterface.findByAuthorContaining(author));
        }
        
        if (Objects.nonNull(content)) {
            commentList.addAll(this.commentInterface.findByContentContaining(content));
        }

        if (commentList.isEmpty()) {
            commentList = this.commentInterface.findByOwnedByNewID(referredNews);
        }

        for (Comment comment : commentList) {
            DTOComment.setAuthor(comment.getAuthor());
            DTOComment.setContent(comment.getContent());
            DTOComment.setId(comment.getId());
            DTOComment.setNewsID(comment.getOwnedByNewID().getId());
            DTOComment.setPublicationMoment(comment.getPublicationMoment());
            DTOCommentList.add(DTOComment);

            DTOComment = new CommentDTONewsId(); // Trocar para list.clean().
        }

        return DTOCommentList;
    }

    public CommentDTONewsId getOneCommentClean(UUID commentUUID) {
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        Comment comment = this.commentInterface.findById(commentUUID).get();
        
        if (Objects.nonNull(comment)) {
            DTOComment.setId(commentUUID);
            DTOComment.setAuthor(comment.getAuthor());
            DTOComment.setContent(comment.getContent());
            DTOComment.setNewsID(comment.getOwnedByNewID().getId());
            DTOComment.setPublicationMoment(comment.getPublicationMoment());
            
            return DTOComment;
        }
        return DTOComment;
    }
    
    public CommentDTONewsId getOneCommentByNewsClean(Integer id, UUID commentUUID) {
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        Comment comment = this.commentInterface.findById(commentUUID).get();

        if (Objects.nonNull(comment) && comment.getOwnedByNewID().getId() == id) {
            DTOComment.setId(commentUUID);
            DTOComment.setAuthor(comment.getAuthor());
            DTOComment.setContent(comment.getContent());
            DTOComment.setNewsID(comment.getOwnedByNewID().getId());
            DTOComment.setPublicationMoment(comment.getPublicationMoment());
            
            return DTOComment;
        } else {
            System.out.println("Um dos dois parâmetros não é compatível. Retorna vazio.");
            return DTOComment;
        }
    }

    public CommentDTONewsId saveNewComment(Integer id, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = new Comment();
        CommentDTONewsId writtenCommentDTO = new CommentDTONewsId();

        if (Objects.nonNull(referredNews)) {
            
            writtenComment.setAuthor(body.getAuthor());
            writtenComment.setContent(body.getContent());
            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            writtenCommentDTO = new CommentDTONewsId(
                writtenComment.getId(),
                writtenComment.getAuthor(),
                writtenComment.getContent(),
                writtenComment.getOwnedByNewID().getId(),
                writtenComment.getPublicationMoment()
            );
            return writtenCommentDTO;
        } else {
            return writtenCommentDTO;
        }
    }

    public CommentDTONewsId editComment(Integer id, UUID commentUUID, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();
        CommentDTONewsId writtenCommentDTO = new CommentDTONewsId();

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

            writtenCommentDTO = new CommentDTONewsId(
                writtenComment.getId(),
                writtenComment.getAuthor(),
                writtenComment.getContent(),
                writtenComment.getOwnedByNewID().getId(),
                writtenComment.getPublicationMoment()
            );
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
