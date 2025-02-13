package com.example.news.dto;

import java.time.Instant;
import java.util.UUID;

public class CommentDTOMissingNewId {
    private UUID id;
    private String author;
    private String content;
    private Instant publicationMoment;
    
    public CommentDTOMissingNewId(UUID id, String author, String content, Instant publicationMoment) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.publicationMoment = publicationMoment;
    }

    public CommentDTOMissingNewId() {}

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

    public Instant getPublicationMoment() {
        return publicationMoment;
    }

    public void setPublicationMoment(Instant publicationMoment) {
        this.publicationMoment = publicationMoment;
    }
}
