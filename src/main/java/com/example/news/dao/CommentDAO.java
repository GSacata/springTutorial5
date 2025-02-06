package com.example.news.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.news.domain.Comment;
import com.example.news.domain.News;
import com.example.news.interfaces.CommentInterface;
import com.example.news.utils.Utils;

public class CommentDAO {

    @Autowired
    private CommentInterface commentInterface;

    Comment commentRef = new Comment();
    
    public List<Comment> getAllComments() {
        return this.commentInterface.findAll();
    }

    public Optional<Comment> getOneComment(UUID id) {
        return this.commentInterface.findById(id);
    }

    public Comment saveNewComment(Comment body) {
        Comment writtenComment = commentRef;
        writtenComment.setAuthor(body.getAuthor());
        writtenComment.setContent(body.getContent());
        writtenComment.setPublicationMoment(Utils.returnFormattedNow());
        this.commentInterface.save(writtenComment);

        return writtenComment;
    }
}
