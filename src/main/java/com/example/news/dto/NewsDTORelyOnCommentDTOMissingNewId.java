package com.example.news.dto;

import java.time.Instant;
import java.util.List;

public class NewsDTORelyOnCommentDTOMissingNewId {
    private Integer id;
    private String headline;
    private String author;
    private String content;
    private List<CommentDTOMissingNewId> postedComments;
    private Instant publicationMoment;
    
    public NewsDTORelyOnCommentDTOMissingNewId(Integer id, String headline, String author, String content, List<CommentDTOMissingNewId> postedComments,
            Instant publicationMoment) {
        this.id = id;
        this.headline = headline;
        this.author = author;
        this.content = content;
        this.postedComments = postedComments;
        this.publicationMoment = publicationMoment;
    }

    public NewsDTORelyOnCommentDTOMissingNewId() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentDTOMissingNewId> getPostedComments() {
        return postedComments;
    }

    public void setPostedComments(List<CommentDTOMissingNewId> postedComments) {
        this.postedComments = postedComments;
    }

    public Instant getPublicationMoment() {
        return publicationMoment;
    }

    public void setPublicationMoment(Instant publicationMoment) {
        this.publicationMoment = publicationMoment;
    }
}
