package com.example.news.dto;

import java.time.Instant;
import java.util.UUID;

import com.example.news.domain.News;

public class CommentDTO {
    private UUID id;
    private String author;
    private String content;
    private News ownedByNewID;
    private Instant publicationMoment;
    
    public CommentDTO(UUID id, String author, String content, News ownedByNewID, Instant publicationMoment) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.ownedByNewID = ownedByNewID;
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

    public News getOwnedByNewID() {
        return ownedByNewID;
    }

    public void setOwnedByNewID(News ownedByNewID) {
        this.ownedByNewID = ownedByNewID;
    }

    public Instant getPublicationMoment() {
        return publicationMoment;
    }

    public void setPublicationMoment(Instant publicationMoment) {
        this.publicationMoment = publicationMoment;
    }
}
