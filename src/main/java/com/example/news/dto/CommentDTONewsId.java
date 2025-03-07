package com.example.news.dto;

import java.time.Instant;
import java.util.UUID;

public class CommentDTONewsId {
    private UUID id;
    private String author;
    private String content;
    private Integer NewsID;
    private Instant publicationMoment;
    
    public CommentDTONewsId() {}

    public CommentDTONewsId(UUID id, String author, String content, Integer newsID, Instant publicationMoment) {
        this.id = id;
        this.author = author;
        this.content = content;
        NewsID = newsID;
        this.publicationMoment = publicationMoment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getNewsID() {
        return NewsID;
    }

    public void setNewsID(Integer newsID) {
        NewsID = newsID;
    }

    public Instant getPublicationMoment() {
        return publicationMoment;
    }

    public void setPublicationMoment(Instant publicationMoment) {
        this.publicationMoment = publicationMoment;
    }

    
}
