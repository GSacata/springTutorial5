package com.example.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.dto.CommentDTONewsId;
import com.example.news.dto.CommentDTONoNews;
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

    public List<CommentDTONewsId> getAllCommentsClean() {
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        List<CommentDTONewsId> DTOCommentList = new ArrayList<CommentDTONewsId>();
        
        List<Comment> commentList = new ArrayList<Comment>();
        // commentList = this.commentInterface.findByOwnedByNewID(referredNews);
        commentList = this.commentInterface.findAll();

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
        
        // News referredNews = this.newsDao.getOneNew(id);
        // CommentDTONoNews DTOComment = new CommentDTONoNews();
        // List<CommentDTONoNews> DTOCommentList = new ArrayList<CommentDTONoNews>();
        
        // if (Objects.nonNull(referredNews)) {
        //     List<Comment> commentList = new ArrayList<Comment>();
        //     commentList = referredNews.getPostedComments();

        //     for (Comment comment : commentList) {
        //         DTOComment.setAuthor(comment.getAuthor());
        //         DTOComment.setContent(comment.getContent());
        //         DTOComment.setId(comment.getId());
        //         DTOComment.setPublicationMoment(comment.getPublicationMoment());
        //         DTOCommentList.add(DTOComment);

        //         DTOComment = new CommentDTONoNews(); // Trocar para list.clean().
        //     }

        //     return DTOCommentList;
        // } else {
        //     return DTOCommentList;
        // }
    }

    // public List<CommentDTONoNews> getAllCommentsCleanByAuthor(Integer id) {
    //     News referredNews = this.newsDao.getOneNew(id);
    //     CommentDTONoNews DTOComment = new CommentDTONoNews();
    //     List<CommentDTONoNews> DTOCommentList = new ArrayList<CommentDTONoNews>();
        
    //     if (Objects.nonNull(referredNews)) {
    //         List<Comment> commentList = new ArrayList<Comment>();
    //         commentList = referredNews.getPostedComments();

    //         for (Comment comment : commentList) {
    //             DTOComment.setAuthor(comment.getAuthor());
    //             DTOComment.setContent(comment.getContent());
    //             DTOComment.setId(comment.getId());
    //             DTOComment.setPublicationMoment(comment.getPublicationMoment());
    //             DTOCommentList.add(DTOComment);

    //             DTOComment = new CommentDTONoNews();
    //         }

    //         return DTOCommentList;
    //     } else {
    //         return DTOCommentList;
    //     }
    // }

    public List<CommentDTONewsId> getAllCommentsByNewsClean(Integer id) {
        News referredNews = this.newsDao.getOneNew(id);
        CommentDTONewsId DTOComment = new CommentDTONewsId();
        List<CommentDTONewsId> DTOCommentList = new ArrayList<CommentDTONewsId>();

        List<Comment> commentList = this.commentInterface.findByOwnedByNewID(referredNews);

        if (Objects.nonNull(commentList)) {
            for (Comment comment : commentList) {
                DTOComment.setAuthor(comment.getAuthor());
                DTOComment.setContent(comment.getContent());
                DTOComment.setId(comment.getId());
                DTOComment.setNewsID(comment.getOwnedByNewID().getId());
                DTOComment.setPublicationMoment(comment.getPublicationMoment());

                DTOCommentList.add(DTOComment);
                DTOComment = new CommentDTONewsId();
            }
        }

        return DTOCommentList;
    }

    // public Comment getOneComment(Integer id, UUID commentUUID) {
    //     News referredNews = this.newsDao.getOneNew(id);
    //     Comment referredComment = new Comment();
        
    //     if (Objects.nonNull(referredNews)) {
    //         return this.commentInterface.findById(commentUUID).get();
    //     } else {
    //         return referredComment;
    //     }
    // }

    public CommentDTONewsId getOneCommentClean(UUID commentUUID) {
        // News referredNews = this.newsDao.getOneNew(id);
        // Comment referredComment = new Comment();
        
        // if (Objects.nonNull(referredNews)) {
        //     return this.commentInterface.findById(commentUUID).get();
        // } else {
        //     return referredComment;
        // }

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

    // public CommentDTONewsId getOneCommentClean(Integer id, UUID commentUUID) {
    //     CommentDTONewsId DTOComment = new CommentDTONewsId();
    //     List<CommentDTONewsId> DTOCommentList = new ArrayList<CommentDTONewsId>();
        
    //     List<Comment> commentList = new ArrayList<Comment>();
    //     // commentList = this.commentInterface.findByOwnedByNewID(referredNews);
    //     commentList = this.commentInterface.findAll();

    //     News referredNews = this.newsDao.getOneNew(id);
    //     Comment referredComment = new Comment();
    //     CommentDTONoNews commentDTOOnlyNewId = new CommentDTONoNews();
        
    //     if (Objects.nonNull(referredNews)) {
    //         referredComment = this.commentInterface.findById(commentUUID).get();
    //         commentDTOOnlyNewId.setAuthor(referredComment.getAuthor());
    //         commentDTOOnlyNewId.setContent(referredComment.getContent());
    //         commentDTOOnlyNewId.setId(referredComment.getId());
    //         commentDTOOnlyNewId.setPublicationMoment(referredComment.getPublicationMoment());

    //         return commentDTOOnlyNewId;
    //     } else {
    //         return commentDTOOnlyNewId;
    //     }
    // }

    public CommentDTONoNews saveNewComment(Integer id, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = new Comment();
        CommentDTONoNews writtenCommentDTO = new CommentDTONoNews();

        if (Objects.nonNull(referredNews)) {
            
            writtenComment.setAuthor(body.getAuthor());
            writtenComment.setContent(body.getContent());
            writtenComment.setPublicationMoment(Utils.returnFormattedNow());
            writtenComment.setOwnedByNewID(referredNews);

            this.commentInterface.save(writtenComment);

            writtenCommentDTO = new CommentDTONoNews(writtenComment.getId(), writtenComment.getAuthor(), writtenComment.getContent(), writtenComment.getPublicationMoment());
            return writtenCommentDTO;
        } else {
            return writtenCommentDTO;
        }
    }

    public CommentDTONoNews editComment(Integer id, UUID commentUUID, Comment body) {
        News referredNews = this.newsDao.getOneNew(id);
        Comment writtenComment = this.commentInterface.findById(commentUUID).get();
        CommentDTONoNews writtenCommentDTO = new CommentDTONoNews();

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

            writtenCommentDTO = new CommentDTONoNews(writtenComment.getId(), writtenComment.getAuthor(), writtenComment.getContent(), writtenComment.getPublicationMoment());
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
